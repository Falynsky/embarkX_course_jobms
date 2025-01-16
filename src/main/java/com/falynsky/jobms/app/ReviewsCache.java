package com.falynsky.jobms.app;

import com.falynsky.jobms.app.enities.external.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewsCache {

    public static final ReviewsCache INSTANCE = new ReviewsCache();
    private final Map<Long, List<Review>> CACHE = new HashMap<>();
    private ReviewsCache() {}
    public void addReviews(Long id, List<Review> reviews) {
        CACHE.put(id, reviews);
    }

    public boolean hasReviews(Long id) {
        return CACHE.containsKey(id);
    }

    public List<Review> getReviews(Long id) {
        return CACHE.get(id);
    }

    public void removeReviews(Long id) {
        CACHE.remove(id);
    }

    public void clearCache() {
        CACHE.clear();
    }
}
