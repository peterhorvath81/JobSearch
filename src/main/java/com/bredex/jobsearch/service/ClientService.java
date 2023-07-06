package com.bredex.jobsearch.service;

import com.bredex.jobsearch.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for client domain
 */
@Service
public interface ClientService {

    /**
     * saves client
     * @param clientDTO clientDTO
     * @return api key in UUID format
     */
    public UUID saveClient(ClientDTO clientDTO);

    /**
     * Validates the api key
     * @param author author
     */
    void validateApiKey(String author);

    /**
     * Validates api key when searching jobs
     * @param requester the name of the search requester
     */
    void validateApiKeyForSearch(String requester);
}
