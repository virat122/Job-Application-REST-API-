package com.embarkx.fristjobapp.job;

import com.embarkx.fristjobapp.company.Company;
import jakarta.persistence.*;

//@Table(name = "JobTable")
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String tittle;
    private  String description ;
    private String minSalary;
    private String maxSalary;
    private String location;

    @ManyToOne
    private Company company;

    public Job() {
        // without this(Empty) constructer JPA would not be able to instantiated the object
        //default constructor is needed  in JPA
    }
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job(Long id, String tittle, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
