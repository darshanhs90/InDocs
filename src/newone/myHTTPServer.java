package newone;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;

public class myHTTPServer extends Thread {



	Socket connectedClient = null;
	BufferedReader inFromClient = null;
	DataOutputStream outToClient = null;
	ClientCache cache ;
	ServerSocket Server;
	static int counter=1;
	Region<String, byte[]> region;

	public myHTTPServer(Socket client) {
		connectedClient = client;
	}

	public void run() {

		try {

			System.out.println( "The Client "+
					connectedClient.getInetAddress() + ":" + connectedClient.getPort() + " is connected");

			inFromClient = new BufferedReader(new InputStreamReader (connectedClient.getInputStream()));
			outToClient = new DataOutputStream(connectedClient.getOutputStream());

			String requestString = inFromClient.readLine();
			String headerLine = requestString;

			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			String httpQueryString = tokenizer.nextToken();



			if (httpMethod.equals("GET")) {
				if (httpQueryString.contains("/init")) {
					initialise();
					outToClient.writeBytes("asd");
					outToClient.close();
				} 
				else if (httpQueryString.equals("/uploadFile")) {
					System.out.println("uploadfile");
					String substring=httpQueryString.substring(10);
					String str[]=substring.split("<>?");
					String filename=str[0];
					uploadFileToInmemoryDb(filename);
					outToClient.writeBytes("uploaded successfully");
					outToClient.close();
				}
				else if (httpQueryString.equals("/getFile")) {
					String substring=httpQueryString.substring(7);
					String str[]=substring.split("<>?");
					System.out.println(str[0]);
					String resp=getFileFromInmemoryDb(str[1]);
					outToClient.writeBytes(resp);
					outToClient.close();
				}
				else if (httpQueryString.equals("/getDocs")) {
					String substring=httpQueryString.substring(7);
					String str[]=substring.split("<>?");
					System.out.println(str[0]);
					String resp=getDocsFromInmemoryDb(str[1]);
					
					outToClient.writeBytes(resp);
					outToClient.close();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
	}




	public static void main (String args[]) throws Exception {

		ServerSocket Server = new ServerSocket (5000, 10, InetAddress.getByName("127.0.0.1"));
		System.out.println ("TCPServer Waiting for client on port 5000");

		while(true) {
			Socket connected = Server.accept();
			(new myHTTPServer(connected)).start();
		}
	}


	public void initialise() throws FileNotFoundException{
		cache = new ClientCacheFactory()
		.addPoolLocator("localhost", 10334)
		.create();

		region = cache
				.<String, byte[]>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
				.create("region");
	}
	public void uploadFileToInmemoryDb(String filename) throws Exception{
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum); 
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		byte[] bytes = bos.toByteArray();
		region.put(filename, bytes);
	}
	public String getFileFromInmemoryDb(String filename) throws Exception{
		File someFile=null;
		String name=null;
		for (Entry<String, byte[]>  entry : region.entrySet()) {
			if(entry.getKey().equals(filename)){
				name="a"+filename;
				someFile = new File(name);
				counter++;
				FileOutputStream fos = new FileOutputStream(someFile);
				fos.write(entry.getValue());
				fos.flush();
				fos.close();
				return name;
			}
		}
		return name;
	}

	
	
	public String getDocsFromInmemoryDb(String filetype) throws Exception{
		File someFile=null;
		String str="filename";
		StringBuilder response=new StringBuilder("[");
		for (Entry<String, byte[]>  entry : region.entrySet()) {
			if(entry.getKey().contains(filetype)){
				String name="java2"+counter+"."+filetype;
				someFile = new File(name);
				counter++;
				FileOutputStream fos = new FileOutputStream(someFile);
				fos.write(entry.getValue());
				fos.flush();
				fos.close();
				response.append("{'name':"+entry.getKey()+",'value':"+name+"},");
			}
		}
		response.deleteCharAt(response.length()-1);
		response.append("]");
		return response.toString();
	}









}