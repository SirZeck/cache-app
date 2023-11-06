package com.adityar.cachedataspringapp.repository;

import com.adityar.cachedataspringapp.model.CacheEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CacheEntryRepository extends JpaRepository<CacheEntry, String> {
    //add custom query methods
}
