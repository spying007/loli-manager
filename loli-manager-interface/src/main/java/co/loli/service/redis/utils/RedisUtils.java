package co.loli.service.redis.utils;

public interface RedisUtils {
    /**
     * 设置key
     * @param key 键
     * @param value 值
     * @return 返回设置key的值
     */
    String set(String key,String value);

    /**
     * 获取key
     * @param key 键
     * @return 返回键的value
     */
    String get(String key);

    /**
     * 根据键删除
     * @param key 键
     * @return 删除键的值
     */
    Long del(String key);

    /**
     * 设置生存时间
     * @param key 键
     * @param value 值
     * @param time 生存时间
     * @return 剩余时间
     */
    Long expire(String key,String  value,Integer time);

    /**
     * 更改生存时间
     * @param key 键
     * @param time 生存时间
     * @return 剩余的生存时间
     */
    Long exprie(String key,Integer time);
}
