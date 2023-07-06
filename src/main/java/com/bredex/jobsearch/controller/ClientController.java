package com.bredex.jobsearch.controller;

import com.bredex.jobsearch.domain.Job;
import com.bredex.jobsearch.dto.ClientDTO;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.dto.SearchDTO;
import com.bredex.jobsearch.service.ClientService;
import com.bredex.jobsearch.service.JobService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for Jobsearch application
 */
@RestController
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    private JobService jobService;

    /**
     * Post endpoint for registering clients
     * @param clientDTO clientDTO
     * @return api Key in UUID format.
     */
    @PostMapping("/client")
    public UUID saveClient(@Valid @RequestBody ClientDTO clientDTO) {

        return clientService.saveClient(clientDTO);
    }

    /**
     * Post endpoint for registering positions
     * @param jobDTO jobDTO
     * @return the URL of the registered job
     */
    @PostMapping("/position")
    public String postJob( @Valid @RequestBody JobDTO jobDTO) {
        clientService.validateApiKey(jobDTO.getAuthor());
        return jobService.postJob(jobDTO);
    }

    /**
     * Endpoint for searching positions
     * @param searchDTO searchDTO
     * @return a list of the search result URLs
     */
    @GetMapping("/position/search")
    public List<String> getJobList( @Valid @RequestBody SearchDTO searchDTO) {
        clientService.validateApiKeyForSearch(searchDTO.getRequester());
        return jobService.searchJobByKeyWord(searchDTO);
    }

    /**
     * Endpoint for finding jobs by id
     * @param id UUID
     * @return the details of the job
     */
    @GetMapping("/position/{id}")
    public Job getJobById(@PathVariable(value = "id") UUID id) {
        return jobService.getJobById(id);
    }
}
