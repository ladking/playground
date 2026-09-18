package com.currencyconverter.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.currencyconverter.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}