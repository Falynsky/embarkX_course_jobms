package com.falynsky.jobms.app.dto;


import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class JobWithCompanyDTO {

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
}
