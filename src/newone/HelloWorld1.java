package newone;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gemstone.gemfire.cache.Region;

import java.util.Map;
public class HelloWorld1 {
	public static void main(Region<String, String> region) throws Exception {
		//		File file = new File("toll.mp3");
		//		FileInputStream fis = new FileInputStream(file);
		//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		//		byte[] buf = new byte[1024];
		//		try {
		//			for (int readNum; (readNum = fis.read(buf)) != -1;) {
		//				bos.write(buf, 0, readNum); 
		//				System.out.println("read " + readNum + " bytes,");
		//			}
		//		} catch (IOException ex) {
		//			ex.printStackTrace();
		//		}
		//		byte[] bytes = bos.toByteArray();
		//
		//		//below is the different part
		//		File someFile = new File("java2.mp3");
		//		FileOutputStream fos = new FileOutputStream(someFile);
		//		fos.write(bytes);
		//		fos.flush();
		//		fos.close();
		for (Map.Entry<String, String>  entry : region.entrySet()) {
//			File someFile = new File("java2.mp3");
//			FileOutputStream fos = new FileOutputStream(someFile);
//			fos.write(entry.getValue());
//			fos.flush();
//			fos.close();
			System.out.println(entry.getValue());
		}


	}
}
