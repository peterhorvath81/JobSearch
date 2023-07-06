package com.bredex.jobsearch.repository;

import com.bredex.jobsearch.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for the Job domain
 */
@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    /**
     * Finds jobs by keywords and location
     * @param keyWordList the list of keywords
     * @param location location
     * @return Optional of list of jobs
     */
    @Query("SELECT j FROM Job j WHERE j.name IN :keyWordList AND (j.place =:location)")
    Optional<List<Job>> findJobsByKeyword(@Param("keyWordList") List<String> keyWordList, @Param("location")String location);
}
