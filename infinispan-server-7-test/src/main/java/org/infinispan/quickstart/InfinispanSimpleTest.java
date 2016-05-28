package org.infinispan.quickstart;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.junit.Test;

/**
 * Infinispan Simple Test Class. <p/>
 *
 * @author <a href="iamtedwon@gmail.com">Ted Won</a>
 * @version 0.1.0
 */
public class InfinispanSimpleTest {

    @Test
    public void test3() throws Exception {

        //API entry point, by default it connects to localhost:11222
        RemoteCacheManager remoteCacheManager = new RemoteCacheManager();

        //obtain a handle to the remote default cache
//        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache();
        RemoteCache<String, String> remoteCache = remoteCacheManager.getCache("myJBUGKoreaCache");

        //now add something to the cache and make sure it is there
        for (int i = 0; i < 1000000; i++) {
            remoteCache.put("car" + i, "ferrari" + i);
            String value = remoteCache.get("car" + i);
            System.out.println(value);
        }

        int size = remoteCache.keySet().size();
        System.out.println(size);
    }
}
