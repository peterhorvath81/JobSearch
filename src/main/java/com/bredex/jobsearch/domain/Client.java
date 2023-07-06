package com.bredex.jobsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Model class for Client entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private String email;

}
