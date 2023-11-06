package com.adityar.cachedataspringapp.service;

import com.adityar.cachedataspringapp.model.CacheEntry;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private final Cache<String, CacheEntry> guavaCache;

    @Autowired
    public CacheService(Cache<String, CacheEntry> guavaCache) {
        this.guavaCache = guavaCache;
    }

    public CacheEntry addCacheEntry(CacheEntry cacheEntry) {
        guavaCache.put(cacheEntry.getKey(), cacheEntry);
        return cacheEntry;
    }

    public CacheEntry getCacheEntry(String key) {
        return guavaCache.getIfPresent(key);
    }

    public CacheEntry updateCacheEntry(String key, CacheEntry updateCacheEntry) {
        CacheEntry existingEntry = guavaCache.getIfPresent(key);
        if(existingEntry != null) {
            guavaCache.put(key, updateCacheEntry);
            return updateCacheEntry;
        } else {
            //handle not found case
            return null;
        }
    }

    public void deleteCacheEntry(String key) {
        guavaCache.invalidate(key);
    }
}
