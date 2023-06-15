package com.redis.demo.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {

    /**
     * Key值
     */
    String key() default "";

    /**
     * key过期时间
     */
    int expire() default 300;
}
