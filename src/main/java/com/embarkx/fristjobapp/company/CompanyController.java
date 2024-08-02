package com.embarkx.fristjobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @RequestBody Company company){
        boolean isUpdated =companyService.updateCompany(company,id);
        if(isUpdated) {
            return new ResponseEntity<>("company updated succesfully ", HttpStatus.OK);

        }
        else{
           return new ResponseEntity<>("company  id is not avilable ", HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping
    public ResponseEntity<String > addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company added sucessfully" ,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted=companyService.deleteCompanyById(id);
        if(isDeleted){
            return new ResponseEntity<>("company sucessfully deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("company not Found",HttpStatus.NOT_FOUND);

        }
    }

@GetMapping("/{id}")
    public ResponseEntity<Company >getCompany(@PathVariable  Long id){
        Company company=companyService.getCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


}
