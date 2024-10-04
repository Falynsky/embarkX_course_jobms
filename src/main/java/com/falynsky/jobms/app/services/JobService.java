package com.falynsky.jobms.app.services;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();

    JobDTO findById(Long id);

    void createJob(JobDTO jobDTO);

    void deleteJob(Long id);

    void updateJob(JobDTO existingJob, Job job);
}
