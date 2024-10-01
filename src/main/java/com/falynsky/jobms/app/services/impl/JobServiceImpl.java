package com.falynsky.jobms.app.services.impl;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.repositories.JobRepository;
import com.falynsky.jobms.app.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(JobDTO jobDTO) {
        Job newJob = new Job();
        newJob.setTitle(jobDTO.getTitle());
        newJob.setDescription(jobDTO.getDescription());
        newJob.setMinSalary(jobDTO.getMinSalary());
        newJob.setMaxSalary(jobDTO.getMaxSalary());
        newJob.setLocation(jobDTO.getLocation());
        newJob.setCompanyId(jobDTO.getCompanyId());

        jobRepository.save(newJob);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public void updateJob(Job existingJob, Job updatedJob) {
        existingJob.setTitle(updatedJob.getTitle() == null ? existingJob.getTitle() : updatedJob.getTitle());
        existingJob.setDescription(updatedJob.getDescription() == null ? existingJob.getDescription() : updatedJob.getDescription());
        existingJob.setMinSalary(updatedJob.getMinSalary() == null ? existingJob.getMinSalary() : updatedJob.getMinSalary());
        existingJob.setMaxSalary(updatedJob.getMaxSalary() == null ? existingJob.getMaxSalary() : updatedJob.getMaxSalary());
        existingJob.setLocation(updatedJob.getLocation() == null ? existingJob.getLocation() : updatedJob.getLocation());
        jobRepository.save(existingJob);
    }
}
