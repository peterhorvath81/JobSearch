package com.bredex.jobsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Model class for Job entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;

    private String place;

}
