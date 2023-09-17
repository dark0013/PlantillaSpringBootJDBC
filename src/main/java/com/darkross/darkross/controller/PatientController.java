package com.darkross.darkross.controller;

import com.darkross.darkross.model.Patient;
import com.darkross.darkross.service.PatientServicesImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServicesImpl service;
    @GetMapping
    public List<Patient> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Patient findById(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public Patient save(@RequestBody Patient patient){
        return service.save(patient);
    }
    @PutMapping
    public Patient update(@RequestBody Patient patient){
        return service.update(patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.deleteById(id);
    }
}
