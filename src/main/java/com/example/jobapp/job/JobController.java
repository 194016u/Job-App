package com.example.jobapp.job;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>>  findAll(){
        
        return ResponseEntity.ok(jobService.findAll());
    }

    //post request
    @PostMapping
    public ResponseEntity<String>  createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully", HttpStatus.CREATED);
    }

    //get job by id
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job =jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //delete job by id
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJoById(id);
        if (deleted)
            return new ResponseEntity<>("job deleted successfully", HttpStatus.OK);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //update job
    //@RequestMapping(value = "/jobs/{id}" , method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job updated successfully",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
