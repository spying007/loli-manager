package co.loli.service.redis.utils.impl;

import co.loli.service.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisUtilsImpl implements RedisUtils {

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    private <T> T excute(Function<ShardedJedis, T> function) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            //从redis中获取数据
            return function.callback(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (shardedJedis != null) {
                shardedJedis.close();
            }
        }
        shardedJedisPool.close();
        return null;
    }

    @Override
    public String set(final String key, final String value) {
        return excute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.set(key,value);
            }
        });
    }


    @Override
    public String get(final String key) {
        return excute(new Function<ShardedJedis, String>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    @Override
    public Long del(final String key) {
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    @Override
    public Long expire(final String key, final String value, final Integer time) {
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return expire(key,value,time);
            }
        });
    }

    @Override
    public Long exprie(final String key, final Integer time) {
        return excute(new Function<ShardedJedis, Long>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key,time);
            }
        });
    }
}
