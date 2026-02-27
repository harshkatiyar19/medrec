package com.example.spring_security.service;

import com.example.admin.repository.AdminUserDetailsRepository;
import com.example.shared_library.entity.admin.AdminUserDetails;
import com.example.spring_security.dto.RegisterAdminDto;
import com.example.spring_security.enums.LoginIdentifierType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminSecurityService implements UserDetailsService {
    private final AdminUserDetailsRepository adminRepo;

    public AdminSecurityService(AdminUserDetailsRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        LoginIdentifierType type = LoginIdentifierType.from(identifier);

        AdminUserDetails admin = switch (type) {

            case EMAIL ->  adminRepo.findByEmail(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Email not found"));

            case PHONE ->  adminRepo.findByPhoneNumber(identifier)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Phone number not found"));

            case ADMIN_ID ->  adminRepo.findByAdminId(Long.valueOf(identifier))
                    .orElseThrow(() ->
                            new UsernameNotFoundException("Admin ID not found"));
            default ->throw new UsernameNotFoundException("Invalid login identifier type");
        };


        return org.springframework.security.core.userdetails.User
                .withUsername(String.valueOf(admin.getAdminId()))
                .password(admin.getPassword())
//                .roles(user.getRole())
                .build();
    }


    public String register(RegisterAdminDto request) {
        AdminUserDetails adminUserDetails = AdminUserDetails.builder()
                        .build();
        adminRepo.save(adminUserDetails);
        return null;
    }
}
