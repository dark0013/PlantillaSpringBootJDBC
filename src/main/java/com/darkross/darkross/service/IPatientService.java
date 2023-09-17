package com.darkross.darkross.service;

import com.darkross.darkross.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> findAll();

    Patient findById(Integer id);

    Patient save(Patient patient);

    Patient update(Patient patient);

    void deleteById(Integer id);
}
