package com.bitzilis.medicalApplication.Scheduler;


import com.bitzilis.medicalApplication.entity.Patient;
import com.bitzilis.medicalApplication.entity.User;
import com.bitzilis.medicalApplication.repository.MedicalExaminationRepository;
import com.bitzilis.medicalApplication.repository.PatientRepository;
import com.bitzilis.medicalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class SchedulerConfig  {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalExaminationRepository medicalExaminationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> userList = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        Map<Integer,Patient> genericMap = new HashMap<>();
        if(((List<Patient>)patientRepository.findAll()).isEmpty()){
            System.out.println("The database has no users");
        }else{
            List<Patient> patientList = (List<Patient>) patientRepository.findAll();
            patientList.stream().forEach(patient -> genericMap.put(Math.toIntExact(patient.getPatientId()),patient));
            for(Patient patient : patientList){
                Patient patientObj = patientRepository.findPatientByPatientId(Long.valueOf(patient.getPatientId()));
                   if(null != patientObj){
                   System.out.println("We found user with id : " + patientObj.getPatientId() + " name : " + patientObj.getPatientName() + " surname: " + patientObj.getPatientSurname() );
                    User user = new User();
                    user.setUserId(patientObj.getPatientId());
                    user.setUserName(patientObj.getPatientName());
                    user.setUserSurname(patientObj.getPatientSurname());
                    userList.add(user);
                    userRepository.save(user);
                }
            }
            if(userList.size() > 3){
                truncateTable(userList);
            }
        }
        System.out.println("Scheduler " + sdf.format(Calendar.getInstance().getTime()) + "\n");
    }


    void truncateTable(List<User> userList) {
        userList.stream().forEach(user -> userRepository.delete(user));
    }
}
