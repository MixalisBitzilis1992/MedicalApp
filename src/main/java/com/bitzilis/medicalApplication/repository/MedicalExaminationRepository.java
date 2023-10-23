package com.bitzilis.medicalApplication.repository;

import com.bitzilis.medicalApplication.entity.MedicalExamination;
import com.bitzilis.medicalApplication.enumeration.MedicalExaminationTypeEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MedicalExaminationRepository extends CrudRepository<MedicalExamination,Long> {
    MedicalExamination findMedicalExaminationByMedicalExaminationId(Long medicalExaminationId);
    MedicalExamination findMedicalExaminationByMedicalExaminationDate(@Param("examinationDate") Date examinationDate);
    MedicalExamination findMedicalExaminationByMedicalExaminationType(@Param("medicalExaminationType") MedicalExaminationTypeEnum medicalExaminationType);
    List<MedicalExamination> findMedicalExaminationByPatient(Long amka);
    void deleteMedicalExaminationByMedicalExaminationId(Long medicalExaminationId);
}
