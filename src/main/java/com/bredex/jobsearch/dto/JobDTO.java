package com.bredex.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for registering jobs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {

    private String name;

    private String location;

    private String author;
}
