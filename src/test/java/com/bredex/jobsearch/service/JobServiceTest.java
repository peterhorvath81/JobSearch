package com.bredex.jobsearch.service;

import com.bredex.jobsearch.domain.Job;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.dto.SearchDTO;
import com.bredex.jobsearch.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private UUID testUuid = new UUID(1L,2L);

    private String jobLocation = "localhost:8080/position/1";

    @Test
    public void shouldPostJob() {
        JobDTO jobDTO = createJobDTO();
        Job job = createJob();
        when(jobRepository.save(job)).thenReturn(job);
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));

        String result = jobService.postJob(jobDTO);

        assertTrue(result.contains("position"));
    }

    @Test
    public void shouldGetJobById() {
        Job job = createJob();
        when(jobRepository.findById(job.getId())).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(job.getId());

        assertEquals(result, job);
    }

    @Test
    public void shouldSearchByKeyword() {
        SearchDTO searchDTO = createSearchDTO();

        Job job = createJob();
        List<Job> jobList = new ArrayList<>();
        jobList.add(job);
        when(jobRepository.findJobsByKeyword(List.of(searchDTO.getKeyWord()), searchDTO.getLocation())).thenReturn(Optional.of(jobList));

        List<String> result = jobService.searchJobByKeyWord(searchDTO);

        assertEquals(result, jobList.stream().map(j -> "localhost:8080/position/" + j.getId()).collect(Collectors.toList()));
    }

    private SearchDTO createSearchDTO() {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setKeyWord("developer");
        searchDTO.setLocation("hungary");
        searchDTO.setRequester("John");
        return searchDTO;
    }


    private Job createJob() {
        Job job = new Job();
        job.setId(new UUID(1,2));
        job.setPlace("hungary");
        job.setName("developer");
        return job;
    }

    private JobDTO createJobDTO() {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setLocation("hungary");
        jobDTO.setName("developer");
        jobDTO.setAuthor("John");
        return jobDTO;
    }
}
