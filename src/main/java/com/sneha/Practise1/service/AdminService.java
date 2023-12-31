package com.sneha.Practise1.service;

import com.sneha.Practise1.entity.Admin;
import com.sneha.Practise1.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String saveAdmin(Admin admin) {
        Optional<Admin> admin_fetched = Optional.ofNullable(adminRepository.findByUsername(admin.getUsername()));
        if (admin_fetched.isPresent()) {
            log.info("admin alrady registerd");
            return ("ADMIN ALREADY REGISTERED");
        } else {
            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            adminRepository.save(admin);
            return "ADMIN REGISTERED";
        }
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin loginAdmin(Admin admin) {
        Optional<Admin> admin_fetched = Optional.ofNullable(adminRepository.findByUsername(admin.getUsername()));
        if (admin_fetched.isPresent()) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
            if(admin.getUsername().equals(admin_fetched.get().getUsername())) {
                if (bCryptPasswordEncoder.matches(admin.getPassword(), admin_fetched.get().getPassword())) {
                    return admin;
                }
            }
            else {
                // username not found, how to return this message.
//                return "";
                return null;
            }


            System.out.println(bCryptPasswordEncoder.encode(admin.getPassword()).getClass());
            System.out.println(admin.getPassword().getClass());
//            admin.setPassword(encoded_ped);
//            log.info("enc_Pwd:", admin.getPassword());

//            log.info(post_pwd);
            log.info(admin_fetched.get().getPassword());//
//            if (encoded_ped.equals(admin_fetched.get().getPassword())) {
//                return "LOGGED IN";
//            } else {
//                return "INCORRECT PASSWORD";
//            }

        }
        return null;
    }
}
