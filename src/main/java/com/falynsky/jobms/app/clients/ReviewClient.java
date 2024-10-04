package com.falynsky.jobms.app.clients;

import com.falynsky.jobms.app.enities.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEWMS")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
