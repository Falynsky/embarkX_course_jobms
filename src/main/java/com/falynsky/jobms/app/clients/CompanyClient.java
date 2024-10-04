package com.falynsky.jobms.app.clients;

import com.falynsky.jobms.app.enities.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANYMS")
public interface CompanyClient {

    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable("companyId") Long companyId);
}
