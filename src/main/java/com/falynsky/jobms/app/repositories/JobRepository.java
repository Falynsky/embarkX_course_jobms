package com.falynsky.jobms.app.repositories;

import com.falynsky.jobms.app.enities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByCompanyId(Long id);
}
