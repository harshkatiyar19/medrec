package com.example.spring_security.service;

import com.example.admin.repository.AdminUserDetailsRepository;
import com.example.drWrite.repository.DoctorRepository;
import com.example.patientWrite.repository.PatientUserDetailsRepository;
import com.example.spring_security.dto.RegistrationDto;
import com.example.spring_security.entity.RegisterUserEntity;
import com.example.spring_security.enums.TypeUser;
import com.example.spring_security.repository.RegistraryUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationService {
    private final AdminUserDetailsRepository adminRepo;
    private final PatientUserDetailsRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final PasswordEncoder encoder;
    private final RegistraryUserRepository userRepo;
    private final EncryptionService encryptionService;


    public RegistrationService(AdminUserDetailsRepository adminRepo, PatientUserDetailsRepository patientRepo, DoctorRepository doctorRepo, PasswordEncoder encoder, RegistraryUserRepository userRepo, EncryptionService encryptionService) {
        this.adminRepo = adminRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.encryptionService = encryptionService;
    }


    public String register(RegistrationDto request) throws Exception {
        String email = request.email();
        TypeUser typeUser =  request.user();
        boolean existingUser = checkExistingUser(typeUser,email);

        // Check if admin not exists
        if (!existingUser) {
            String password = encoder.encode(request.password());
            LocalDateTime timestamp = LocalDateTime.now();
            String token = generateToken(email, timestamp);

            RegisterUserEntity temp = RegisterUserEntity.builder()
                    .email(email)
                    .token(token)
                    .tokenMade(timestamp)
                    .tokenValidTill(timestamp.plusMinutes(15))
                    .password(password)
                    .typeUser(TypeUser.ADMIN)
                    .build();

            RegisterUserEntity registerUserEntity=userRepo.save(temp);
            return String.format("Id: %d token: %s",
                    registerUserEntity.getRegistrationId(),
                    registerUserEntity.getToken());
        }else{
            return "User with "+ email + "email already exists .";
        }
    }

    private boolean checkExistingUser (TypeUser typeUser, String email){
        return switch (typeUser){
            case DOCTOR -> adminRepo.existsByEmail(email);
            case ADMIN -> doctorRepo.existsByEmail(email);
            case PATIENT -> patientRepo.existsByEmail(email);
            default -> throw new IllegalArgumentException("Wrong user type id .");
        };
    }



    private String generateToken(String email, LocalDateTime timestamp) throws Exception {
        String timestampStr = timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String data = "email :"+email+ ",timestamp :"+ timestampStr;
        return encryptionService.encrypt(data);
    }

    public Boolean verifyToken(String token) throws Exception {
        RegisterUserEntity user = userRepo.findByToken(token);
        if (user.getTokenValidTill().isAfter(LocalDateTime.now())) {
            userRepo.verifyUserByToken(token);
            return true;// token is valid
        } else {
            return false; // token expired
        }
    }

    private  ParsedToken parseDecryptedToken(String decrypted) {

        // Example: "email: abc@example.com, timestamp: 2026-03-03T10:15:30"
        String[] parts = decrypted.split(",\\s*"); // split by comma + spaces

        String email = parts[0].split("email:\\s*")[1].trim();
        String timestampStr = parts[1].split("timestamp:\\s*")[1].trim();
        LocalDateTime timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new ParsedToken(email, timestamp);
    }

    private  record ParsedToken(String email,LocalDateTime timestamp) {
    }


}
