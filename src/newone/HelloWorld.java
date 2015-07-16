package newone;
import java.io.File;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.client.ClientRegionShortcut;

public class HelloWorld {
  public static void main(String[] args) throws Exception {
    ClientCache cache = new ClientCacheFactory()
      .addPoolLocator("localhost", 10334)
      .create();
    Region<String, String> region = cache
      .<String, String>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
      .create("region");
    
    System.out.println(region.isEmpty());
    region.put("3", "1.0");
    region.put("4", "2.0");
    HelloWorld1.main(region);
//    for (Map.Entry<String, String>  entry : region.entrySet()) {
//      System.out.format("key = %s, value = %s\n", entry.getKey(), entry.getValue());
//    }
    cache.close();
  }
  public void uploadFileToDb(File file){
	  
  }
  public File getFileFromDb(File file){
	 
	  return null;
  }

  
  
  
  
}
