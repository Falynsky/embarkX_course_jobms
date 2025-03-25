package com.falynsky.jobms.app.services.impl;

import com.falynsky.jobms.app.CompanyCache;
import com.falynsky.jobms.app.ReviewsCache;
import com.falynsky.jobms.app.clients.CompanyClient;
import com.falynsky.jobms.app.clients.ReviewClient;
import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.enities.external.Company;
import com.falynsky.jobms.app.enities.external.Review;
import com.falynsky.jobms.app.helpers.SalaryBonusCalculator;
import com.falynsky.jobms.app.repositories.JobRepository;
import com.falynsky.jobms.app.services.JobService;
import com.falynsky.jobms.mappers.JobCompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    private final CompanyClient companyClient;
    private final ReviewClient reviewClient;

    private final CompanyCache companyCache = CompanyCache.INSTANCE;
    private final ReviewsCache reviewsCache = ReviewsCache.INSTANCE;


    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        clearCaches();
        List<JobDTO> jobDTOS = jobs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        clearCaches();
        return jobDTOS;
    }

    @Override
    public JobDTO findById(Long id) {
        return jobRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public double findMaxSalaryWithBonusById(Long id) {
        return jobRepository.findById(id)
                .map(this::calculateMaxSalaryWithBonus)
                .orElseThrow();
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
        String title = updatedJob.getTitle();
        String description = updatedJob.getDescription();
        Long minSalary = updatedJob.getMinSalary();
        Long maxSalary = updatedJob.getMaxSalary();
        String location = updatedJob.getLocation();
        Long companyId = updatedJob.getCompanyId();
        existingJob.setTitle(title == null ? existingJob.getTitle() : title);
        existingJob.setDescription(description == null ? existingJob.getDescription() : description);
        existingJob.setMinSalary(minSalary == null ? existingJob.getMinSalary() : minSalary);
        existingJob.setMaxSalary(maxSalary == null ? existingJob.getMaxSalary() : maxSalary);
        existingJob.setLocation(location == null ? existingJob.getLocation() : location);
        existingJob.setCompanyId(companyId == null ? existingJob.getCompanyId() : companyId);
        Job job = jobCompanyMapper.to(existingJob);
        jobRepository.save(job);
    }

    private JobDTO convertToDto(Job job) {
        Long companyId = job.getCompanyId();
        Company company = getCompany(companyId);

        List<Review> reviews = getReviews(companyId);
        if (CollectionUtils.isEmpty(reviews)) {
            throw new NoSuchElementException("Reviews not found");
        }

        return jobCompanyMapper.from(job, company, reviews);
    }

    private Double calculateMaxSalaryWithBonus(Job job) {
        Long maxSalary = job.getMaxSalary();
        SalaryBonusCalculator salaryBonusCalculator = new SalaryBonusCalculator(maxSalary, 2);
        Class<? extends SalaryBonusCalculator> clazz = salaryBonusCalculator.getClass();
        clazz.getConstructors();
        clazz.getMethods();
        return salaryBonusCalculator.calculateBonus(600);
    }

    private void clearCaches() {
        companyCache.clearCache();
        reviewsCache.clearCache();
    }

    private Company getCompany(Long companyId) {
        if (companyCache.hasCompany(companyId)) {
            return companyCache.getCompany(companyId);
        }

        Company company = companyClient.getCompany(companyId);
        companyCache.addCompany(companyId, company);

        return company;

    }

    private List<Review> getReviews(Long companyId) {
        if (reviewsCache.hasReviews(companyId)) {
            return reviewsCache.getReviews(companyId);
        }

        List<Review> reviews = reviewClient.getReviews(companyId);
        reviewsCache.addReviews(companyId, reviews);

        return reviews;

    }
}
