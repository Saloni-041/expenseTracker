package com.example.expenseTrackerApi.repository;

import com.example.expenseTrackerApi.entity.Role_Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role_Table,Long>
{
    Optional<Role_Table> findById(Long id);
}
