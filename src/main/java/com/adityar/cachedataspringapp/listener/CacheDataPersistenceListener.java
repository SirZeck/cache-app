package com.adityar.cachedataspringapp.listener;

import com.adityar.cachedataspringapp.model.CacheEntry;
import com.adityar.cachedataspringapp.repository.CacheEntryRepository;
import com.google.common.cache.Cache;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CacheDataPersistenceListener {

    private final Cache<String, CacheEntry> guavaCache;
    private final CacheEntryRepository cacheEntryRepository;

    public CacheDataPersistenceListener(Cache<String, CacheEntry> guavaCache, CacheEntryRepository cacheEntryRepository) {
        this.guavaCache = guavaCache;
        this.cacheEntryRepository = cacheEntryRepository;
    }

    @EventListener(ContextClosedEvent.class)
    public void onContextClosedEvent() {
        //iterate through guava cache and save entries to the database
        guavaCache.asMap().forEach((key, cacheEntry) -> {
            //convert and save cacheEntry to the database
            cacheEntryRepository.save(cacheEntry);
        });
    }
}
