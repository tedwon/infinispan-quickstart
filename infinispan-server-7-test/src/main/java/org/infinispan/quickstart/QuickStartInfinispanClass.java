package org.infinispan.quickstart;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import java.util.concurrent.TimeUnit;

/**
 * Quick Start for Infinispan Test Class.
 * <p/>
 * <p/>See http://community.jboss.org/wiki/5minutetutorialonInfinispan
 *
 * @author Ted Won
 */
public class QuickStartInfinispanClass {

    public static void main(String[] args) throws Exception {

        // Step 1: Create a cache manager
        EmbeddedCacheManager manager = new DefaultCacheManager();

        // Step 2: Create a cache
        // To get the default cache
        Cache<String, String> cache = manager.getCache();

        // Step 3: Use the cache
        cache.put("key", "value");
        assert cache.size() == 1;
        assert cache.containsKey("key");
        Object v = cache.remove("key");
        assert v.equals("value");
        assert cache.isEmpty();

        // remember that Cache extends ConcurrentMap!
        cache.put("key", "value");
        cache.putIfAbsent("key", "newValue");
        assert "value".equals(cache.get("key"));

        cache.clear();
        assert cache.isEmpty();

        // Step 4: Set expiry for entries
        cache.put("key", "value", 5, TimeUnit.SECONDS);
        assert cache.containsKey("key");
        Thread.sleep(10000);
        assert !cache.containsKey("key");

    }
}