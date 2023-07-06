package com.bredex.jobsearch.service;

import com.bredex.jobsearch.domain.Job;
import com.bredex.jobsearch.dto.JobDTO;
import com.bredex.jobsearch.dto.SearchDTO;
import com.bredex.jobsearch.exception.ApiRequestException;
import com.bredex.jobsearch.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl implements JobService {

    private static final String JOB_LOCATION = "localhost:8080/position/";

    private JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public String postJob(JobDTO jobDTO) {
        validateJobDTO(jobDTO);
        Job job = new Job();
        job.setName(jobDTO.getName());
        job.setPlace(jobDTO.getLocation());

        jobRepository.save(job);

        jobRepository.findById(job.getId());

        return JOB_LOCATION + job.getId();
    }

    @Override
    public Job getJobById(UUID id) {
        return jobRepository.findById(id).orElseThrow(() -> new ApiRequestException("Sorry, the job you looked for does not exists"));
    }

    @Override
    public List<String> searchJobByKeyWord(SearchDTO searchDTO) {
        validateSearchDTO(searchDTO);
        List<String> keyWordList = List.of(searchDTO.getKeyWord().split(" "));
        String location = searchDTO.getLocation();

         List<Job> jobList = jobRepository.findJobsByKeyword(keyWordList, location).orElseThrow(() -> new ApiRequestException("No results. Try with other parameters."));

         return jobList.stream().map(job -> JOB_LOCATION + job.getId()).collect(Collectors.toList());
    }

    private void validateSearchDTO(SearchDTO searchDTO) {
        if (searchDTO.getKeyWord() == null || searchDTO.getKeyWord().isBlank() || searchDTO.getKeyWord().length()>50) {
            throw new ApiRequestException("The keyword is mandatory and must be between 1 and 50 characters");
        }
        if (searchDTO.getLocation() == null || searchDTO.getLocation().isBlank() || searchDTO.getLocation().length()>50) {
            throw new ApiRequestException("The location is mandatory and must be between 1 and 50 characters");
        }
        if (searchDTO.getRequester() == null || searchDTO.getRequester().isBlank() || searchDTO.getRequester().length()>50) {
            throw new ApiRequestException("The requester is mandatory and must be between 1 and 50 characters");
        }
    }

    private void validateJobDTO(JobDTO jobDTO) {
        if (jobDTO.getName() == null || jobDTO.getName().isBlank() || jobDTO.getName().length()>50) {
            throw new ApiRequestException("The name of the position is mandatory and must be between 1 and 50 characters");
        }
        if (jobDTO.getLocation() == null || jobDTO.getLocation().isBlank() || jobDTO.getLocation().length()>50) {
            throw new ApiRequestException("The name of the location is mandatory and must be between 1 and 50 characters");
        }
        if (jobDTO.getAuthor() == null || jobDTO.getAuthor().isBlank() || jobDTO.getAuthor().length()>50) {
            throw new ApiRequestException("The author is mandatory and must be between 1 and 50 characters");
        }
    }
}
