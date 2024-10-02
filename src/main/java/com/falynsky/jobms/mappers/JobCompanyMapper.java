package com.falynsky.jobms.mappers;

import com.falynsky.jobms.app.dto.JobWithCompanyDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobCompanyMapper {

    @Mapping(source = "job.id", target = "id")
    @Mapping(source = "job.title", target = "title")
    @Mapping(source = "job.description", target = "description")
    @Mapping(source = "job.minSalary", target = "minSalary")
    @Mapping(source = "job.location", target = "location")
    @Mapping(source = "job.companyId", target = "companyId")
    @Mapping(source = "company", target = "company")
    JobWithCompanyDTO from(Job job, Company company);
}