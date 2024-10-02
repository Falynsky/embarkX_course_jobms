package com.falynsky.jobms.app.controllers;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.dto.JobWithCompanyDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.services.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @GetMapping()
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody JobDTO jobDTO) {
        jobService.createJob(jobDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> find(@PathVariable Long id) {
        JobWithCompanyDTO jobWithCompanyDTO = jobService.findById(id);

        if (jobWithCompanyDTO == null) {
            throw new NoSuchElementException("Job not found");
        }

        return ResponseEntity.ok(jobWithCompanyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id) {
        JobWithCompanyDTO job = jobService.findById(id);

        if (job == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.deleteJob(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable Long id, @RequestBody Job job) {
        JobWithCompanyDTO jobWithCompanyDTO = jobService.findById(id);

        if (jobWithCompanyDTO == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.updateJob(jobWithCompanyDTO, job);

        return ResponseEntity.ok().build();
    }
}
