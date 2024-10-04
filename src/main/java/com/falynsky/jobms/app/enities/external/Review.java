package com.falynsky.jobms.app.enities.external;

import lombok.Data;

@Data
public class Review {

    private Long id;
    private String review;
    private String description;
    private Double rating;
}
