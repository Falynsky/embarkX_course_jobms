package com.falynsky.jobms.app;

import com.falynsky.jobms.app.enities.external.Company;

import java.util.HashMap;
import java.util.Map;

public class CompanyCache {

    public static final CompanyCache INSTANCE = new CompanyCache();
    private final Map<Long, Company> CACHE = new HashMap<>();
    private CompanyCache() {}
    public void addCompany(Long id, Company company) {
        CACHE.put(id, company);
    }

    public boolean hasCompany(Long id) {
        return CACHE.containsKey(id);
    }

    public Company getCompany(Long id) {
        return CACHE.get(id);
    }

    public void removeCompany(Long id) {
        CACHE.remove(id);
    }

    public void clearCache() {
        CACHE.clear();
    }
}
