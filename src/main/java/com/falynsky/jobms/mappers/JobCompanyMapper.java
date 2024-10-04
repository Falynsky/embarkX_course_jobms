package com.falynsky.jobms.mappers;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import com.falynsky.jobms.app.enities.external.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobCompanyMapper {
    
    @Mapping(source = "job.id", target = "id")
    @Mapping(source = "job.title", target = "title")
    @Mapping(source = "job.description", target = "description")
    @Mapping(source = "job.minSalary", target = "minSalary")
    @Mapping(source = "job.maxSalary", target = "maxSalary")
    @Mapping(source = "job.location", target = "location")
    @Mapping(source = "job.companyId", target = "companyId")
    @Mapping(source = "company", target = "company")
    @Mapping(source = "reviews", target = "reviews")
    JobDTO from(Job job, Company company, List<Review> reviews);

    Job to(JobDTO job);
}