package com.bitzilis.medicalApplication.controller;
import com.bitzilis.medicalApplication.Scheduler.SchedulerConfig;
import com.bitzilis.medicalApplication.repository.MedicalExaminationRepository;
import com.bitzilis.medicalApplication.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchedulerRestController {

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private SchedulerConfig schedulerConfig;

    @GetMapping(value = "/start")
    public String start() {
        postProcessor.postProcessAfterInitialization(schedulerConfig, "Task");
        return "STARTED";
    }

    @GetMapping(value = "/stop")
    public String stop() {
        postProcessor.postProcessBeforeDestruction(schedulerConfig, "Task");
        return "STOPPED";
    }
}
