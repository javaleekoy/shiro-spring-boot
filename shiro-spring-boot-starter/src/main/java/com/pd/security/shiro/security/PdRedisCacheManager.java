package com.pd.security.shiro.security;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import java.util.Collection;
import java.util.Set;

/**
 * @author peramdy on 2018/10/25.
 */
public class PdRedisCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new JedisCache<>();
    }


    public class JedisCache<K, V> implements Cache<K, V> {

        @Override
        public V get(K k) throws CacheException {
            return null;
        }

        @Override
        public V put(K k, V v) throws CacheException {
            return null;
        }

        @Override
        public V remove(K k) throws CacheException {
            return null;
        }

        @Override
        public void clear() throws CacheException {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public Set<K> keys() {
            return null;
        }

        @Override
        public Collection<V> values() {
            return null;
        }
    }
}
