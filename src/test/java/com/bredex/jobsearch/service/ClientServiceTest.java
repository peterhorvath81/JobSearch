package com.bredex.jobsearch.service;


import com.bredex.jobsearch.domain.Client;
import com.bredex.jobsearch.dto.ClientDTO;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveClient() {
        ClientDTO clientDTO = createClientDTO();
        Client client = createClient();
        client.setId(null);
        when(clientRepository.save(client)).thenReturn(client);

        clientService.saveClient(clientDTO);

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void shouldValidateApiKey() {
        Client client = createClient();
        JobDTO jobDTO = createJobDTO();
        when(clientRepository.findByauthor(jobDTO.getAuthor())).thenReturn(Optional.of(client));

        clientService.validateApiKey(jobDTO.getAuthor());

        verify(clientRepository, times(1)).findByauthor(jobDTO.getAuthor());
    }

    private JobDTO createJobDTO() {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setAuthor("John");
        jobDTO.setLocation("budapest");
        jobDTO.setName("developer");
        return jobDTO;
    }


    private Client createClient() {
        Client client = new Client();
        client.setId(new UUID(1,2));
        client.setName("John");
        client.setEmail("email@email.com");
        return client;
    }

    private ClientDTO createClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail("email@email.com");
        clientDTO.setName("John");
        return clientDTO;
    }
}
