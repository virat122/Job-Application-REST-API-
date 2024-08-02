package com.embarkx.fristjobapp.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import  java.util.List;



/*
 requestMapping work at class level --->>>work as a base url
@RequestMapping("/jobs")   now in below mwthod need not to mension
 [
 @GetMapping("/jobs/{id}")    ---->>> @GetMapping("/{id}")
 @GetMapping("/jobs") ---->>> @GetMapping
 ]

 Advantage -: in future if we want to change any url no need to change in alll method
  we can only change it on class level.

 */
@RestController
public class JobController {

    private  JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }




    @GetMapping("/jobs")
    public  ResponseEntity<List<Job> >findAll(){
         // another way of doing ->
         // return new ResponseEntity<>("job added Sucessfully",HttpStatus.OK);
        return ResponseEntity.ok(jobService.findAll());


    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return  new ResponseEntity<>("job added Sucessfully",HttpStatus.CREATED);
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job!=null){
            return new ResponseEntity<>(job,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // my code now
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String > DeleteJobById(@PathVariable Long id){
       boolean deleted=jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("deleted sucessfully ",HttpStatus.OK);
        }

        return new ResponseEntity<>(  HttpStatus.NOT_FOUND);
    }

   // @RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)  ->>>> same as below
    @PutMapping("/jobs/{id}")
    public  ResponseEntity<String> updateJob(@PathVariable  Long id, @RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated){
            return  new ResponseEntity<>("job updated sucessfully",HttpStatus.OK);

        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
