package com.falynsky.jobms.app.controllers;

import com.falynsky.jobms.app.dto.JobDTO;
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
    public ResponseEntity<List<JobDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody JobDTO jobDTO) {
        jobService.createJob(jobDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> find(@PathVariable Long id) {
        JobDTO jobDTO = jobService.findById(id);

        if (jobDTO == null) {
            throw new NoSuchElementException("Job not found");
        }

        return ResponseEntity.ok(jobDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long id) {
        JobDTO job = jobService.findById(id);

        if (job == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.deleteJob(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJob(@PathVariable Long id, @RequestBody Job job) {
        JobDTO jobDTO = jobService.findById(id);

        if (jobDTO == null) {
            throw new NoSuchElementException("Job not found");
        }

        jobService.updateJob(jobDTO, job);

        return ResponseEntity.ok().build();
    }
}
