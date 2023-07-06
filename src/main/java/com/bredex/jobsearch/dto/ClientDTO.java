package com.bredex.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for registering clients
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private String name;

    private String email;

}
