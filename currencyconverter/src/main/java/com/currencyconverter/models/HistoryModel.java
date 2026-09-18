package com.currencyconverter.models;



import jakarta.persistence.Id;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PrePersist;



@Entity
public class HistoryModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;
    public String currency;
    public Integer amount;
    public String targetCurrency;
    public Integer targetValue;
    public LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
}