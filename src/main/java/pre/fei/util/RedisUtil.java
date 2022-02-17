package pre.fei.util;


import pre.fei.enums.ResultEnum;
import pre.fei.exceptions.InitException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auth puhongfei
 * @Date 2021/12/4
 * @Desc TODO
 */
public class RedisUtil {

    private static final String ip = "120.25.241.73";
    private static final Integer port = 6379;

    private static JedisPool jedisPool = null;

    static {
        jedisPool = new JedisPool(ip, port);
    }

    private static Jedis getJedis () {
        if (jedisPool == null){
            throw new RuntimeException(ResultEnum.INIT_ERROR.getMsg());
        }
        return jedisPool.getResource();
    }

    public static boolean setString(String key, String value){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close(jedis);
        }
    }

    public static String getString(String key){
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }

    private static void close(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }



}
