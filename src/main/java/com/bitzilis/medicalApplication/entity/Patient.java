package com.bitzilis.medicalApplication.entity;

import com.bitzilis.medicalApplication.enumeration.GenderEnum;
import com.bitzilis.medicalApplication.enumeration.MedicalExaminationTypeEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {
    @Id
    @Column(name = "patient_id", nullable = false ,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "patient_name", nullable = false, length = 150)
    private String patientName;

    @Column(name = "patient_surname", nullable = false, length = 150)
    private String patientSurname;

    @Column(name = "amka", nullable = false, length = 11,unique = true)
    private Long amka;

    @Column(name = "date_of_birth", nullable = false, length = 150)
    private String dateOfBirth;

    @Column(name = "gender", nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "home_address", nullable = false, length = 150)
    private String homeAddress;

    @Column(name = "mobile_phone", nullable = false, length = 150)
    private String mobilePhone;

    @Column(name = "next_scheduled_examination_date", length = 150)
    private String nextScheduledExaminationDate;


    @Column(name = "type_of_next_scheduled_examination_date", length = 150)
    @Enumerated(value = EnumType.STRING)
    private MedicalExaminationTypeEnum typeOfNextScheduledExamination;

    @OneToMany(mappedBy = "patient",  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List <MedicalExamination> medicalExaminations;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public Long getAmka() {
        return amka;
    }

    public void setAmka(Long amka) {
        this.amka = amka;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNextScheduledExaminationDate() {
        return nextScheduledExaminationDate;
    }

    public void setNextScheduledExaminationDate(String nextScheduledExaminationDate) {
        this.nextScheduledExaminationDate = nextScheduledExaminationDate;
    }

    public MedicalExaminationTypeEnum getTypeOfNextScheduledExamination() {
        return typeOfNextScheduledExamination;
    }

    public void setTypeOfNextScheduledExamination(MedicalExaminationTypeEnum typeOfNextScheduledExamination) {
        this.typeOfNextScheduledExamination = typeOfNextScheduledExamination;
    }

    public List<MedicalExamination> getMedicalExaminations() {
        return medicalExaminations;
    }

    public void setMedicalExaminations(List<MedicalExamination> medicalExaminations) {
        this.medicalExaminations = medicalExaminations;
    }



    @Override
    public String toString() {
        return "id : " + patientId + " name : " + patientName  + "surname : " + patientSurname;
    }
}
