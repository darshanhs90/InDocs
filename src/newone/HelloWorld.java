package newone;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;
import com.gemstone.gemfire.internal.cache.tier.sockets.command.Query;
import com.gemstone.gemfire.rest.internal.web.controllers.QueryAccessController;

public class HelloWorld {
	public static void main(String[] args) throws Exception {
		ClientCache cache = new ClientCacheFactory()
		.addPoolLocator("localhost", 10334)
		.create();
		Region<String, String> region = cache
				.<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
				.create("region");
//		Region<String, byte[]> region = cache
//				.<String, byte[]>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
//				.create("region");
//				File file = new File("toll.mp3");
//				FileInputStream fis = new FileInputStream(file);
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				byte[] buf = new byte[1024];
//				try {
//					for (int readNum; (readNum = fis.read(buf)) != -1;) {
//						bos.write(buf, 0, readNum); 
//					}
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//				byte[] bytes = bos.toByteArray();
				region.put("3", "1");
				region.put("4", "2");
				region.put("3", "3");
				
				HelloWorld1.main(region);
				cache.close();

	}
	public void uploadFileToDb(File file){

	}
	public File getFileFromDb(File file){
		
		return null;
	}





}
