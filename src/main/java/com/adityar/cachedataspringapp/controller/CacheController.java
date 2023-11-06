package com.adityar.cachedataspringapp.controller;

import com.adityar.cachedataspringapp.model.CacheEntry;
import com.adityar.cachedataspringapp.repository.CacheEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private final CacheEntryRepository cacheEntryRepository;

    @Autowired
    public CacheController(CacheEntryRepository cacheEntryRepository) {
        this.cacheEntryRepository = cacheEntryRepository;
    }

    @PostMapping
    public CacheEntry addCacheEntry(@RequestBody CacheEntry cacheEntry) {
        //logic to add a cache entry
        return cacheEntryRepository.save(cacheEntry);
    }

    @GetMapping("/{key}")
    public CacheEntry getCacheEntry(@PathVariable String key) {
        //logic to retrieve a cache entry by key
        return cacheEntryRepository.findById(key).orElse(null);
    }

    @PutMapping("/{key}")
    public CacheEntry updateCacheEntry(@PathVariable String key, @RequestBody CacheEntry updateCacheEntry) {
        //logic to update an existing cache
        if(cacheEntryRepository.existsById(key)) {
            updateCacheEntry.setKey(key);
            return cacheEntryRepository.save(updateCacheEntry);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{key}")
    public void deleteCacheEntry(@PathVariable String key) {
        //logic to delete a cache entry
        cacheEntryRepository.deleteById(key);
    }
}
