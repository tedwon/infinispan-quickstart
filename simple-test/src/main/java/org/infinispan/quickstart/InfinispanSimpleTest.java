package org.infinispan.quickstart;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.junit.Test;

/**
 * Infinispan Simple Test Class.
 * <p/>
 *
 * @author <a href="iamtedwon@gmail.com">Ted Won</a>
 * @version 0.1.0
 */
public class InfinispanSimpleTest {

    @Test
    public void test1() throws Exception {

        //API entry point, by default it connects to localhost:11222
        RemoteCacheManager remoteCacheManager = new RemoteCacheManager();

        //obtain a handle to the remote default cache
//        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache();
        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache("statsCache");

        //now add something to the cache and make sure it is there
        remoteCache.put("car", "ferrari");

        assert remoteCache.get("car").equals("ferrari");

        //remove the data
        remoteCache.remove("car");
        assert !remoteCache.containsKey("car") : "Value must have been removed!";
    }

    @Test
    public void test2() throws Exception {

        //API entry point, by default it connects to localhost:11222
        RemoteCacheManager remoteCacheManager = new RemoteCacheManager();

        //obtain a handle to the remote default cache
//        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache();
        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache("statsCache");

        //now add something to the cache and make sure it is there
        remoteCache.put("car", "ferrari");

        Thread.sleep(1000);

        String value = remoteCache.get("car");
        System.out.println(value);

    }

    @Test
    public void test3() throws Exception {

        //API entry point, by default it connects to localhost:11222
        RemoteCacheManager remoteCacheManager = new RemoteCacheManager();

        //obtain a handle to the remote default cache
//        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache();
        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache("statsCache");

        //now add something to the cache and make sure it is there
        for (int i = 0; i < 1000; i++) {
            remoteCache.put("car" + i, "ferrari");
        }

        int size = remoteCache.size();

        System.out.println(size);


    }
}
