package com.bredex.jobsearch.controller;

import com.bredex.jobsearch.domain.Job;
import com.bredex.jobsearch.dto.ClientDTO;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.dto.SearchDTO;
import com.bredex.jobsearch.service.ClientService;
import com.bredex.jobsearch.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private JobService jobService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final UUID testUuid = new UUID(1L,2L);

    @Test
    public void shouldSaveClient() {
        ClientDTO clientDTO = createClientDTO();
        when(clientService.saveClient(clientDTO)).thenReturn(testUuid);

        UUID result = clientController.saveClient(clientDTO);

        assertEquals(result, testUuid);
    }

    @Test
    public void shouldSaveJob() {
        JobDTO jobDTO = createJobDTO();
        doNothing().when(clientService).validateApiKey("James");
        when(jobService.postJob(jobDTO)).thenReturn("testString");

        String result = clientController.postJob(jobDTO);

        assertEquals(result, "testString");
    }

    @Test
    public void shouldGetJobList() {
        SearchDTO searchDTO = createSearchDTO();
        doNothing().when(clientService).validateApiKey("James");
        when(jobService.searchJobByKeyWord(searchDTO)).thenReturn(List.of("localhost:8080/position/" + new UUID(1,2)));

        List<String> result = clientController.getJobList(searchDTO);

        assertEquals(result, List.of("localhost:8080/position/" + new UUID(1,2)));
    }

    @Test
    public void shouldGetJobById() {
        Job job = createJob();
        when(jobService.getJobById(new UUID(1L,2L))).thenReturn(job);

        Job result = clientController.getJobById(new UUID(1L,2L));

        assertEquals(result, job);
    }

    private Job createJob() {
        Job job = new Job();
        job.setPlace("remote");
        job.setName("developer");
        job.setId(new UUID(1,2));
        return job;
    }

    private SearchDTO createSearchDTO() {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setLocation("remote");
        searchDTO.setKeyWord("developer");
        return searchDTO;
    }

    private JobDTO createJobDTO() {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setName("developer");
        jobDTO.setLocation("remote");
        jobDTO.setAuthor("James");
        return jobDTO;
    }

    private ClientDTO createClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        clientDTO.setEmail("email@email.com");
        return clientDTO;
    }
}
