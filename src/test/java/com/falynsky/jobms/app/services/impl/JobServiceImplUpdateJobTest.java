package com.falynsky.jobms.app.services.impl;

import com.falynsky.jobms.app.dto.JobDTO;
import com.falynsky.jobms.app.enities.Job;
import com.falynsky.jobms.app.repositories.JobRepository;
import com.falynsky.jobms.mappers.JobCompanyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobServiceImplUpdateJobTest {

    @Mock
    private JobRepository jobRepository;
    
    @Mock
    private JobCompanyMapper jobCompanyMapper;
    
    private JobServiceImpl jobService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobService = new JobServiceImpl(
            jobRepository, 
            null, // restTemplate not needed for this test
            jobCompanyMapper, 
            null, // companyClient not needed
            null  // reviewClient not needed
        );
    }
    
    @Test
    void testUpdateJob_AllFieldsUpdated() {
        // Given
        JobDTO existingJob = new JobDTO();
        existingJob.setId(1L);
        existingJob.setTitle("Old Title");
        existingJob.setDescription("Old Description");
        existingJob.setMinSalary(1000L);
        existingJob.setMaxSalary(2000L);
        existingJob.setLocation("Old Location");
        existingJob.setCompanyId(10L);
        
        Job updatedJob = new Job();
        updatedJob.setTitle("New Title");
        updatedJob.setDescription("New Description");
        updatedJob.setMinSalary(1500L);
        updatedJob.setMaxSalary(2500L);
        updatedJob.setLocation("New Location");
        updatedJob.setCompanyId(20L);
        
        Job mappedJob = new Job();
        when(jobCompanyMapper.to(existingJob)).thenReturn(mappedJob);
        
        // When
        jobService.updateJob(existingJob, updatedJob);
        
        // Then
        assertEquals("New Title", existingJob.getTitle());
        assertEquals("New Description", existingJob.getDescription());
        assertEquals(1500L, existingJob.getMinSalary());
        assertEquals(2500L, existingJob.getMaxSalary());
        assertEquals("New Location", existingJob.getLocation());
        assertEquals(20L, existingJob.getCompanyId());
        
        verify(jobCompanyMapper).to(existingJob);
        verify(jobRepository).save(mappedJob);
    }
    
    @Test
    void testUpdateJob_SomeFieldsNull() {
        // Given
        JobDTO existingJob = new JobDTO();
        existingJob.setId(1L);
        existingJob.setTitle("Old Title");
        existingJob.setDescription("Old Description");
        existingJob.setMinSalary(1000L);
        existingJob.setMaxSalary(2000L);
        existingJob.setLocation("Old Location");
        existingJob.setCompanyId(10L);
        
        Job updatedJob = new Job();
        updatedJob.setTitle("New Title");
        updatedJob.setDescription(null); // null field
        updatedJob.setMinSalary(1500L);
        updatedJob.setMaxSalary(null); // null field
        updatedJob.setLocation("New Location");
        updatedJob.setCompanyId(null); // null field
        
        Job mappedJob = new Job();
        when(jobCompanyMapper.to(existingJob)).thenReturn(mappedJob);
        
        // When
        jobService.updateJob(existingJob, updatedJob);
        
        // Then
        assertEquals("New Title", existingJob.getTitle());
        assertEquals("Old Description", existingJob.getDescription()); // unchanged
        assertEquals(1500L, existingJob.getMinSalary());
        assertEquals(2000L, existingJob.getMaxSalary()); // unchanged
        assertEquals("New Location", existingJob.getLocation());
        assertEquals(10L, existingJob.getCompanyId()); // unchanged
        
        verify(jobCompanyMapper).to(existingJob);
        verify(jobRepository).save(mappedJob);
    }
    
    @Test
    void testUpdateJob_AllFieldsNull() {
        // Given
        JobDTO existingJob = new JobDTO();
        existingJob.setId(1L);
        existingJob.setTitle("Old Title");
        existingJob.setDescription("Old Description");
        existingJob.setMinSalary(1000L);
        existingJob.setMaxSalary(2000L);
        existingJob.setLocation("Old Location");
        existingJob.setCompanyId(10L);
        
        Job updatedJob = new Job();
        // All fields are null
        
        Job mappedJob = new Job();
        when(jobCompanyMapper.to(existingJob)).thenReturn(mappedJob);
        
        // When
        jobService.updateJob(existingJob, updatedJob);
        
        // Then - all fields should remain unchanged
        assertEquals("Old Title", existingJob.getTitle());
        assertEquals("Old Description", existingJob.getDescription());
        assertEquals(1000L, existingJob.getMinSalary());
        assertEquals(2000L, existingJob.getMaxSalary());
        assertEquals("Old Location", existingJob.getLocation());
        assertEquals(10L, existingJob.getCompanyId());
        
        verify(jobCompanyMapper).to(existingJob);
        verify(jobRepository).save(mappedJob);
    }
}