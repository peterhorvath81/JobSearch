package com.bredex.jobsearch.repository;

import com.bredex.jobsearch.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for client domain
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    /**
     * Finds the client by e-mail address
     * @param email email
     * @return Optional of Client
     */
    @Query("SELECT name FROM Client c WHERE c.email=:email")
    Optional<Client> findClientByEmail(@Param("email")String email);

    /**
     * Finds the client when posting a job
     * @param author the name of the searched client
     * @return Optional of client
     */
    @Query("SELECT c FROM Client c WHERE c.name=:name")
    Optional<Client> findByauthor(@Param("name")String author);

}
