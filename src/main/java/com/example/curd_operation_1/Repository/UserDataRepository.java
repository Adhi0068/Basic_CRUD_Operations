package com.example.curd_operation_1.Repository;

import com.example.curd_operation_1.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByMobile(String mobile);
}