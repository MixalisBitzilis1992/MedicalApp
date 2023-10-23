package com.bitzilis.medicalApplication.entity;

import com.bitzilis.medicalApplication.enumeration.MedicalExaminationTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
public class MedicalExamination {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medical_examination_id")
    private Long medicalExaminationId;
    @Column(name = "medical_examination_type", nullable = false, length = 150)
    @Enumerated(value = EnumType.STRING)
    private MedicalExaminationTypeEnum medicalExaminationType;
    @Column(name = "medical_examination_date", nullable = false, length = 150)
    private String medicalExaminationDate;
    @Column(name = "medical_examination_result", length = 150)
    private String medicalExaminationResult;

    @ManyToOne
    @JsonBackReference
    //@JoinColumn(name = "amka")
    private Patient patient;

    public Long getMedicalExaminationId() {
        return medicalExaminationId;
    }

    public void setMedicalExaminationId(Long medicalExaminationId) {
        this.medicalExaminationId = medicalExaminationId;
    }

    public MedicalExaminationTypeEnum getMedicalExaminationType() {
        return medicalExaminationType;
    }

    public void setMedicalExaminationType(MedicalExaminationTypeEnum medicalExaminationCategory) {
        this.medicalExaminationType = medicalExaminationCategory;
    }

    public String getMedicalExaminationDate() {
        return medicalExaminationDate;
    }

    public void setMedicalExaminationDate(String medicalExaminationDate) {
        this.medicalExaminationDate = medicalExaminationDate;
    }

    public String getMedicalExaminationResult() {
        return medicalExaminationResult;
    }

    public void setMedicalExaminationResult(String medicalExaminationResult) {
        this.medicalExaminationResult = medicalExaminationResult;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setPatient(String patient) {

    }
}