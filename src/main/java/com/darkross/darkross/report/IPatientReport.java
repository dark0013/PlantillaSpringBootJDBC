package com.darkross.darkross.report;

import com.darkross.darkross.model.Patient;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
//@NoRepositoryBean
public interface IPatientReport {
    List<Patient> generatePatientReport();
}
