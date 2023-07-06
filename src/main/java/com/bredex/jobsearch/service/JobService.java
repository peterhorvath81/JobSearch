package com.bredex.jobsearch.service;

import com.bredex.jobsearch.domain.Job;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.dto.SearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for Job domain
 */
@Service
public interface JobService {

    /**
     * Method for posting jobs
     * @param jobDTO jobDTO
     * @return the URL of the posted job
     */
    String postJob(JobDTO jobDTO);

    /**
     * Gets a job by its ID
     * @param id UUID
     * @return the job with the given id
     */
    Job getJobById(UUID id);

    /**
     * Searches jobs by the given keywords and location
     * @param searchDTO searchDTO
     * @return the list of the job URLs
     */
    List<String> searchJobByKeyWord(SearchDTO searchDTO);
}
