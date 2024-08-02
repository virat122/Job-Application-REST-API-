package com.embarkx.fristjobapp.job.impl;

import com.embarkx.fristjobapp.job.Job;
import com.embarkx.fristjobapp.job.JobRepository;
import com.embarkx.fristjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs=new ArrayList<>();
    //private  Long nextId=1l;
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }



    @Override
    public List<Job> findAll() {
       // return jobs;
       return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
//        job.setId(nextId++);
//        jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        for (Job job  :  jobs) {
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator=jobs.iterator();
//        while (iterator.hasNext()){
//            Job job=iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
//        for (Job job  :   jobs) {
//            if(job.getId().equals(id)){
//                job.setTittle(updatedJob.getTittle());
//                job.setDescription(updatedJob.getDescription());
//                job.setLocation(updatedJob.getLocation());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setMinSalary(updatedJob.getMinSalary());
//                return true;
//            }
//        }
//        return false;
        Optional<Job> jobOptional=jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job=jobOptional.get();
            job.setTittle(updatedJob.getTittle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
