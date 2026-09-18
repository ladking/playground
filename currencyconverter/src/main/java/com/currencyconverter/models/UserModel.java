package com.currencyconverter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;


@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String email;
    public String passwordHash;
    public LocalDateTime createdAt;



    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

}