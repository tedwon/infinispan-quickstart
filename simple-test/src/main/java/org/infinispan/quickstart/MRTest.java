package org.infinispan.quickstart;

import org.infinispan.Cache;
import org.infinispan.distexec.mapreduce.Collector;
import org.infinispan.distexec.mapreduce.MapReduceTask;
import org.infinispan.distexec.mapreduce.Mapper;
import org.infinispan.distexec.mapreduce.Reducer;
import org.infinispan.manager.DefaultCacheManager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * A class representing a window on the screen.
 * For example:
 * <pre>
 *    Window win = new Window(parent);
 *    win.show();
 * </pre>
 *
 * @author <A HREF="mailto:[kiora1120@gmail.com]">TJune Kim</A>
 * @version 1.0
 */
public class MRTest {


    public static void main(String[] args) throws IOException, InterruptedException {

        DefaultCacheManager cm = new DefaultCacheManager("tj-dist.xml");
        DefaultCacheManager cm2 = new DefaultCacheManager("tj-dist2.xml");
//        Cache<Object, Object> cache1 = cm.getCache("demo1");

        Cache c1 = cm.getCache("distributedCache");
        Cache c2 = cm2.getCache("distributedCache");
//        System.out.println(c1.size());

        //Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            c1.put(String.valueOf(i++), "Hello world here I am");
            c2.put(String.valueOf(i++), "Infinispan rules the world");
            c1.put(String.valueOf(i++), "JUDCon is in Boston");
            c2.put(String.valueOf(i++), "JBoss World is in Boston as well");
            c1.put(String.valueOf(i++), "JBoss Application Server");
            c2.put(String.valueOf(i++), "Hello world");
            c1.put(String.valueOf(i++), "Infinispan community");
            c2.put(String.valueOf(i++), "Hello world");
            c1.put(String.valueOf(i++), "Infinispan open source");
            c2.put(String.valueOf(i++), "Boston is close to Toronto");
            c1.put(String.valueOf(i++), "Toronto is a capital of Ontario");
            c2.put(String.valueOf(i++), "JUDCon is cool");
            c1.put(String.valueOf(i++), "JBoss World is awesome");
            c2.put(String.valueOf(i++), "JBoss rules");
            c1.put(String.valueOf(i++), "JBoss division of RedHat ");
            c2.put(String.valueOf(i++), "RedHat community");
        }
        //Thread.sleep(10000);

        MapReduceTask<String, String, String, Integer> t = new MapReduceTask<String, String, String, Integer>(c1);
        t.mappedWith(new WordCountMapper()).reducedWith(new WordCountReducer());

        long st = System.currentTimeMillis();
        Map<String, Integer> wordCountMap = t.execute();
//        System.out.println(c1.containsKey("1"));
//        System.out.println(c2.containsKey("2"));
        long edt = System.currentTimeMillis();
        System.out.println(edt - st);
        System.out.println(wordCountMap.toString());
    }


    static class WordCountMapper implements Mapper<String, String, String, Integer> {
        /**
         * The serialVersionUID
         */
        private static final long serialVersionUID = -5943370243108735560L;

        @Override
        public void map(String key, String value, Collector<String, Integer> c) {
            StringTokenizer tokens = new StringTokenizer(value);
            while (tokens.hasMoreElements()) {
                String s = (String) tokens.nextElement();
                c.emit(s, 1);
            }
        }
    }

    static class WordCountReducer implements Reducer<String, Integer> {
        /**
         * The serialVersionUID
         */
        private static final long serialVersionUID = 1901016598354633256L;

        @Override
        public Integer reduce(String key, Iterator<Integer> iter) {
            int sum = 0;
            while (iter.hasNext()) {
                Integer i = (Integer) iter.next();
                sum += i;
            }
            return sum;
        }
    }


}
