package com.falynsky.jobms.app.dto;


import com.falynsky.jobms.app.enities.external.Company;
import com.falynsky.jobms.app.enities.external.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class JobDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("minSalary")
    private Long minSalary;

    @JsonProperty("maxSalary")
    private Long maxSalary;

    @JsonProperty("location")
    private String location;

    @JsonProperty("companyId")
    private Long companyId;

    @JsonProperty("company")
    private Company company;

    @JsonProperty("reviews")
    private List<Review> reviews;
}
