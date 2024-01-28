package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    /**
     * Specifies the type of memory used to store data
     */
    CacheType cacheType() default CacheType.IN_MEMORY;

    /**
     * Specifies the name of the file where the data will be stored.
     * If the name is not specified, then the name of the method will be taken
     * according to the logic of the caching proxy
     */
    String fileNamePrefix() default "";

    /**
     * Specifies that the file should be compressed into a zip archive
     */
    boolean zip() default false;

    /**
     * Specifies the list of parameters for identifying the uniqueness of the method
     */
    Class<?>[] identityBy() default {};

    /**
     * Specifies the maximum number of elements in the cache
     */
    int memoryCacheSize() default 0;
}