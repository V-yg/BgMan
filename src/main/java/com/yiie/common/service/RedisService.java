package com.yiie.common.service;

import com.yiie.enums.BaseResponseCode;
import com.yiie.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Time：2020-1-2 9:31
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 删除key
     * @param key
     * @return
     */
    public Boolean delete(String key) {
        if (null==key){
            return false;
        }
        return redisTemplate.delete(key);
    }

    /**
     * 将指定Key的Value原子性的增加increment。如果该Key不存在，其初始值为0，在incrby之后其值为increment。
     * 如果Value的值不能转换为整型值，如Hi，该操作将执行失败并抛出相应异常。操作成功则返回增加后的value值。
     *
     * @param key
     * @param increment
     * @return
     */
    public long incrby(String key,long increment){
        if(null==key){
            throw new BusinessException(BaseResponseCode.DATA_ERROR.getCode(),"key不能为空");
        }
        return redisTemplate.opsForValue().increment(key,increment);
    }

    /**
     * 批量删除key
     * @param keys
     * @return
     */
    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        if (null==key){
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    /**
     * 查找匹配的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        if (null==pattern){
            return null;
        }
        return redisTemplate.keys(pattern);
    }

    /**
     * 设置指定 key 的值
     * @param key
     * @param value
     */
    public void set(String key, Object value) {

        if(null==key||null==value){
            return;
        }
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     *设置key 的值 并设置过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void set(String key, Object value, long time, TimeUnit unit){

        if(null==key||null==value||null==unit){
            return;
        }
        if(time<0){
            redisTemplate.opsForValue().set(key,value);
        }else {
            redisTemplate.opsForValue().set(key,value,time,unit);
        }
    }

    /**
     *返回 key 的剩余的过期时间
     * @param key
     * @param unit
     * @return
     */
    public Long getExpire(String key, TimeUnit unit) {
        if(null==key||null==unit){
            throw new BusinessException(BaseResponseCode.DATA_ERROR.getCode(),"key or TomeUnit 不能为空");
        }
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 获取指定Key的Value。如果与该Key关联的Value不是string类型，Redis将抛出异常，
     * 因为GET命令只能用于获取string Value，如果该Key不存在，返回null
     * @param key
     * @return
     */
    public Object get(String key){

        if(null==key){
            return null;
        }
        return  redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key 的值 并设置过期时间
     * key存在 不做操作返回false
     * key不存在设置值返回true
     * @param key
     * @param value
     * @param time
     * @param unit
     * @return
     */
    public Boolean setifAbsen(String key,Object value,long time,TimeUnit unit){

        if(null==key||null==value||null==unit){
            throw new BusinessException(BaseResponseCode.DATA_ERROR.getCode(),"key、value、unit都不能为空");
        }
        return redisTemplate.opsForValue().setIfAbsent(key,value,time,unit);
    }
}
