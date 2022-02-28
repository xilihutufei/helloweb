package pre.fei.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pre.fei.config.EvmConfig;
import pre.fei.enums.ResultEnum;
import pre.fei.exceptions.InitException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @Auth puhongfei
 * @Date 2021/12/4
 * @Desc TODO
 */

//@AutoConfigurationPackage
//@Configuration
@Component
public class RedisUtil {

    @Autowired
    EvmConfig config;

    private JedisPool jedisPool = null;

    @PostConstruct
    private void initRedisOnline(){
        System.out.println("redis 初始化成功========");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(50);// 最大连接数，连接全部用完，进行等待
        poolConfig.setMinIdle(10); // 最小空余数
        poolConfig.setMaxIdle(30); // 最大空余数
        if (config.getPasswd() == null || config.getPasswd().length() == 0){
            jedisPool = new JedisPool(poolConfig, config.getRedisIP(), config.getRedisPort());
        }else {
            jedisPool = new JedisPool(poolConfig, config.getRedisIP(), config.getRedisPort(), 1000, config.getPasswd());
        }
        System.out.println("redis 初始化成功========");
    }

    private Jedis getJedis () {
        if (jedisPool == null){
            initRedisOnline();
        }
        Jedis jedis = jedisPool.getResource();
//        jedis.auth("HUNfbjkdaUIO7868rwi1+9");// todo 有密码的redis配置
        return jedis;
    }

    public boolean setString(String key, String value){
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

    public String getString(String key){
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

    private void close(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }


}
