package com.bitzilis.medicalApplication.repository;

import com.bitzilis.medicalApplication.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends CrudRepository<Patient,Long> {
    Patient findPatientByPatientSurname(@Param("patientSurname") String surname);
    Patient findPatientByPatientId(Long patientId);
    Patient findPatientByAmka(Long amka);
    //int findPatientByNumberOfUpdatedDates(Long patientId);
    void deletePatientByAmka(Long amka);

}
