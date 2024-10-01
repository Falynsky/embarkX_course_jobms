package com.falynsky.jobms.app.dto;


import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class JobWithCompanyDTO {

    @JsonProperty("title")
    private Job job;

    @JsonProperty("company")
    private Company company;

}
