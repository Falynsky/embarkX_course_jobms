package com.falynsky.jobms.app.controllers;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping()
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody JobDTO jobDTO) {
        jobService.createJob(jobDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> find(@PathVariable Long id) {
        Job job = jobService.findById(id);

        if (job == null) {
            throw new NoSuchElementException("Job not found");
        }

        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id) {
        Job job = jobService.findById(id);

        if (job == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.deleteJob(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable Long id, @RequestBody Job job) {
        Job existingJob = jobService.findById(id);

        if (existingJob == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.updateJob(existingJob, job);

        return ResponseEntity.ok().build();
    }
}
