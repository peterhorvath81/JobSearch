package com.bredex.jobsearch.service;

import com.bredex.jobsearch.domain.Client;
import com.bredex.jobsearch.dto.ClientDTO;
import com.bredex.jobsearch.exception.ApiRequestException;
import com.bredex.jobsearch.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public UUID saveClient(ClientDTO clientDTO) {
        validateClientDTO(clientDTO);
        if (!validateEmailFormat(clientDTO.getEmail())) {
            throw new ApiRequestException("Incorrect email format");
        } else {
            log.info("saving email to DB");
        }
        validateEmail(clientDTO.getEmail());

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());


        clientRepository.save(client);

        return client.getId();
    }

    @Override
    public void validateApiKey(String author) {
        Optional<Client> client = clientRepository.findByauthor(author);
        if (client.isPresent() && client.get().getId() != null) {
            log.info("API Key is present");
        } else {
            throw new ApiRequestException("You must register for posting jobs (API key with given username does not exists!)");
        }
    }

    @Override
    public void validateApiKeyForSearch(String requester) {
        Optional<Client> client = clientRepository.findByauthor(requester);
        if (client.isPresent() && client.get().getId() != null) {
            log.info("API Key is present");
        } else {
            throw new ApiRequestException("You must register for search. (The API key with given username does not exists)");
        }
    }

    private void validateEmail(String email) {
        Optional<Client> clientByEmail = clientRepository.findClientByEmail(email);
        if (clientByEmail.isPresent()) {
            throw new ApiRequestException("The user with this e-mail address already exists. Try with another e-mail address");
        } else {
            log.info("Email does not taken, saving client");
        }
    }

    private void validateClientDTO(ClientDTO clientDTO) {
        if (clientDTO.getEmail().isBlank() || clientDTO.getEmail() == null) {
            throw new ApiRequestException("The e-mail address is mandatory");
        }
        if (clientDTO.getName() == null) {
            throw new ApiRequestException("Client name is mandatory and must be between 1 and 100 characters");
        }
        if (clientDTO.getName().isBlank() || clientDTO.getName().length()>100) {
            throw new ApiRequestException("Client name is mandatory and must be between 1 and 100 characters");
        }
    }
    private boolean validateEmailFormat(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
