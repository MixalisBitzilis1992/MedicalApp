package com.bitzilis.medicalApplication;

import com.bitzilis.medicalApplication.entity.Patient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
//@EnableScheduling
public class IatrikiPlElectronicMedicalFolderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IatrikiPlElectronicMedicalFolderApplication.class, args);
    }


}
