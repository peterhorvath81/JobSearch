package com.bredex.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for search
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

    private String keyWord;

    private String location;

    private String requester;
}
