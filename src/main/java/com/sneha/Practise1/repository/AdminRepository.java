package com.sneha.Practise1.repository;

import com.sneha.Practise1.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUsername(String username);

}
