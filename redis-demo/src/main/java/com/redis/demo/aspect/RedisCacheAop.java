package com.redis.demo.aspect;

import com.redis.demo.annotations.RedisCache;
import com.redis.demo.utils.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author psikun
 * @Description RedisCacheAop
 * @Date 2023 /06/07/ 10:13
 */
@Aspect
@Component
public class RedisCacheAop {


    @Autowired
    private RedisUtils redisUtils;

    /**
     * 存储方法签名 MethodSignature 与 RedisCache 注解的映射关系
     */
    private Map<MethodSignature, RedisCache> redisCacheAnnotationMap = new ConcurrentHashMap<>();

    /**
     * Redis cache object.
     *
     * @param joinPoint the join point
     * @return the object
     */

    @Around("@annotation(com.redis.demo.annotations.RedisCache)")
    public Object RedisCache(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解

        // 1. 检查缓存，如果缓存中存在该方法签名对应的 RedisCheck 注解，则从缓存中获取
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RedisCache annotation = redisCacheAnnotationMap.get(methodSignature);

        // 2. map缓存中没有则通过反射获取该方法上的 RedisCheck 注解，并将其放入缓存中
        if (Objects.isNull(annotation)) {
            Method method = methodSignature.getMethod();
            annotation = method.getAnnotation(RedisCache.class);
            redisCacheAnnotationMap.put(methodSignature, annotation);
        }

        // 获取key
        String key = annotation.key();

        // 获取expire过期时间
        int expire = annotation.expire();

        // getArgs() 是 joinPoint 的一个方法，用于获取切入点方法的参数
        Object[] args = joinPoint.getArgs();

        Object data = null;

        // 判断Redis中是否有该key
        if (redisUtils.hasKey(key)) {
            data = redisUtils.get(key);
            return data;
        }

        //调用原本的Service函数，访问mysql获取数据
        Object result = joinPoint.proceed(args);

        // 将数据存入Redis缓存中,并设置过期时间
        redisUtils.set(key, result, expire);

        // 返回查询结果
        return result;
    }


}
