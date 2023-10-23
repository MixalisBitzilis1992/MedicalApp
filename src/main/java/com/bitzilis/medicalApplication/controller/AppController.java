package com.bitzilis.medicalApplication.controller;

import com.bitzilis.medicalApplication.entity.MedicalExamination;
import com.bitzilis.medicalApplication.entity.Patient;
import com.bitzilis.medicalApplication.entity.User;
import com.bitzilis.medicalApplication.enumeration.GenderEnum;
import com.bitzilis.medicalApplication.enumeration.MedicalExaminationTypeEnum;
import com.bitzilis.medicalApplication.repository.MedicalExaminationRepository;
import com.bitzilis.medicalApplication.repository.PatientRepository;
import com.bitzilis.medicalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AppController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/patientList")
    public Iterable<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/userList")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }


    @GetMapping("/findPatientById/{patientId}")
    public Patient findPatientByPatientId(@PathVariable Long patientId) {
        return patientRepository.findPatientByPatientId(patientId);
    }

    @GetMapping("/findPatientByAmka/{amka}")
    public Patient findPatientByAmka(@PathVariable Long amka) {
        return patientRepository.findPatientByAmka(amka);
    }

    @GetMapping("/findPatientBySurname/{patientSurname}")
    public Patient findPatientsBySurname(@PathVariable String patientSurname) {
        return patientRepository.findPatientByPatientSurname(patientSurname);
    }

    @PostMapping("/newPatient")
    public String addPatient(@RequestBody Map<String, String> body) {
        Patient patient = new Patient();
        patient.setPatientName(body.get("patientName"));
        patient.setPatientSurname(body.get("patientSurname"));
        patient.setAmka(Long.valueOf(body.get("amka")));
        patient.setDateOfBirth(body.get("dateOfBirth"));
        patient.setGender(GenderEnum.valueOf(body.get("gender")));
        patient.setHomeAddress(body.get("homeAddress"));
        patient.setMobilePhone(body.get("mobilePhone"));
        patientRepository.save(patient);
        return "Patient inserted successfully";
    }

    @PutMapping("/updatePatientDetails")
    // @Scheduled(fixedRate = 1000)
    public String updatePatientDetails(@RequestBody Map<String, String> body) {
        Patient patient = patientRepository.findPatientByPatientId(Long.parseLong(body.get("id")));
        if (patient != null) {
            patient.setHomeAddress(body.get("homeAddress"));
            patient.setMobilePhone(body.get("mobilePhone"));
            patientRepository.save(patient);
        } else {
            return "We dont find patient with these credentials";
        }
        return "Patient Details updated successfully";
    }




    @PutMapping("/addOrUpdateNextScheduledExamination")
    public String addOrUpdateNextScheduledExamination(@RequestBody Map<String, String> body) {
        Patient patient = patientRepository.findPatientByPatientId(Long.parseLong(body.get("id")));
        if (patient != null) {
            patient.setTypeOfNextScheduledExamination(MedicalExaminationTypeEnum.valueOf(body.get("typeOfNextScheduledExamination")));
            patientRepository.save(patient);
        }
        return "Next Scheduled Examination saved/updated successfully";
    }

    @PutMapping("/addOrUpdateDateOfNextScheduledExamination")
    public String addOrUpdateDateOfNextScheduledExamination(@RequestBody Map<String, String> body) {
        Patient patient = patientRepository.findPatientByPatientId(Long.parseLong(body.get("id")));
        if (patient != null) {
            patient.setNextScheduledExaminationDate(body.get("nextScheduledExaminationDate"));
            patientRepository.save(patient);
        }
        return "Date Of Next Scheduled Examination saved/updated succefully";
    }

    @DeleteMapping("/deletePatientByAmka/{amka}")
    public String deletePatientByAmka(@PathVariable Long amka) {
        patientRepository.deletePatientByAmka(amka);
        return "Patient Deleted Successfully";
    }

    @DeleteMapping("/deletePatientByPatientId/{patientId}")
    public String deletePatientByPatientId(@PathVariable Long patientId) {
        patientRepository.deleteById(patientId);
        return "Patient Deleted Successfully";
    }

    @PostMapping("/newMedicalExamination")
    public String addMedicalExamination(@RequestBody Map<String, String> body) {
        MedicalExamination medicalExamination = new MedicalExamination();
        medicalExamination.setMedicalExaminationType(MedicalExaminationTypeEnum.valueOf(body.get("medicalExaminationType")));
        medicalExamination.setMedicalExaminationDate(body.get("medicalExaminationDate"));
        medicalExamination.setMedicalExaminationResult(body.get("medicalExaminationResult"));
        medicalExaminationRepository.save(medicalExamination);
        return "Medical Examinations inserted successfully";
    }

    @PostMapping("/newMedicalExaminationFull/{patientId}")
    public String addMedicalExaminationFull(@RequestBody Map<String, String> body, @PathVariable Long patientId) {
        MedicalExamination medicalExamination = new MedicalExamination();
        medicalExamination.setMedicalExaminationType(MedicalExaminationTypeEnum.valueOf(body.get("medicalExaminationType")));
        medicalExamination.setMedicalExaminationDate(body.get("medicalExaminationDate"));
        medicalExamination.setMedicalExaminationResult(body.get("medicalExaminationResult"));
        Patient p = patientRepository.findPatientByPatientId(patientId);
        medicalExamination.setPatient(p);
        medicalExamination.setMedicalExaminationId(patientId);
        medicalExaminationRepository.save(medicalExamination);
        return "Medical Examinations inserted successfully";
    }


    @PutMapping("/updateMedicalExaminationDate")
    public Object updateMedicalExaminationDate(@RequestBody Map<String, String> body) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findMedicalExaminationByMedicalExaminationId(Long.valueOf(body.get("id")));
        if (medicalExamination != null) {
            {
                medicalExamination.setMedicalExaminationDate(body.get("medicalExaminationDate"));
                medicalExaminationRepository.save(medicalExamination);
            }
        }
        return "Medical Examination Date updated successfully";
    }

    @PutMapping("/updateMedicalExaminationResults")
    public String updateMedicalExaminationResults(@RequestBody Map<String, String> body) {
        MedicalExamination medicalExamination = medicalExaminationRepository.findMedicalExaminationByMedicalExaminationId(Long.parseLong(body.get("id")));
        if (medicalExamination != null) {
            medicalExamination.setMedicalExaminationResult(body.get("medicalExaminationResult"));
            medicalExaminationRepository.save(medicalExamination);
        }
        return "Medical Examination Results updated successfully";
    }

    @GetMapping("/findMedicalExaminationById/{medicalExaminationId}")
    public MedicalExamination findMedicalExaminationById(@PathVariable Long medicalExaminationId) {
        return medicalExaminationRepository.findMedicalExaminationByMedicalExaminationId(medicalExaminationId);
    }

    @GetMapping("/findMedicalExaminationByDate/{medicalExaminationDate}")
    public MedicalExamination findMedicalExaminationByDate(@PathVariable Date medicalExaminationDate) {
        return medicalExaminationRepository.findMedicalExaminationByMedicalExaminationDate(medicalExaminationDate);
    }

    @GetMapping("/findMedicalExaminationType/{medicalExaminationType}")
    public MedicalExamination findMedicalExaminationByMedicalExaminationType(@PathVariable MedicalExaminationTypeEnum medicalExaminationType) {
        return medicalExaminationRepository.findMedicalExaminationByMedicalExaminationType(medicalExaminationType);
    }

    @GetMapping("/medicalExaminationList")
    public Iterable<MedicalExamination> getMedicalExamianations() {
        return medicalExaminationRepository.findAll();
    }

    @DeleteMapping("/deleteMedicalExaminationByMedicalExaminationId/{medicalExaminationId}")
    public String deleteMedicalExaminationByMedicalExaminationId(@PathVariable Long medicalExaminationId) {
        medicalExaminationRepository.deleteById(medicalExaminationId);
        return "Medical Examination Deleted Successfully";
    }


}
