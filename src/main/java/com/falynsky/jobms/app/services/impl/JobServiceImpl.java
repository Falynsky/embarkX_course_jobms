package com.falynsky.jobms.app.services.impl;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import com.falynsky.jobms.app.enities.external.Review;
import com.falynsky.jobms.app.repositories.JobRepository;
import com.falynsky.jobms.app.services.JobService;
import com.falynsky.jobms.configs.MSLinks;
import com.falynsky.jobms.mappers.JobCompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final RestOperations restTemplate;
    private final JobCompanyMapper jobCompanyMapper;

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobDTO findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job != null) {
            return convertToDto(job);
        }

        return null;
    }

    @Override
    public void createJob(JobDTO oldJobDTO) {
        Job newJob = new Job();
        newJob.setTitle(oldJobDTO.getTitle());
        newJob.setDescription(oldJobDTO.getDescription());
        newJob.setMinSalary(oldJobDTO.getMinSalary());
        newJob.setMaxSalary(oldJobDTO.getMaxSalary());
        newJob.setLocation(oldJobDTO.getLocation());
        newJob.setCompanyId(oldJobDTO.getCompanyId());

        jobRepository.save(newJob);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(JobDTO existingJob, Job updatedJob) {
        existingJob.setTitle(updatedJob.getTitle() == null ? existingJob.getTitle() : updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription() == null ? existingJob.getDescription() : updatedJob.getDescription());
        existingJob.setMinSalary(updatedJob.getMinSalary() == null ? existingJob.getMinSalary() : updatedJob.getMinSalary());
        existingJob.setMaxSalary(updatedJob.getMaxSalary() == null ? existingJob.getMaxSalary() : updatedJob.getMaxSalary());
        existingJob.setLocation(updatedJob.getLocation() == null ? existingJob.getLocation() : updatedJob.getLocation());
        existingJob.setCompanyId(updatedJob.getCompanyId() == null ? existingJob.getCompanyId() : updatedJob.getCompanyId());
        Job job = jobCompanyMapper.to(existingJob);
        jobRepository.save(job);
    }

    private JobDTO convertToDto(Job job) {
        Company company = restTemplate.getForObject(MSLinks.COMPANYMS + "/companies/" + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(MSLinks.REVIEWMS + "/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        if (reviewResponse.getStatusCode() != HttpStatusCode.valueOf(200)) {
            throw new NoSuchElementException("Reviews not found");
        }

        List<Review> reviews = reviewResponse.getBody();
        return jobCompanyMapper.from(job, company, reviews);
    }
}
