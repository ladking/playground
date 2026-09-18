package com.currencyconverter.repository;




import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.currencyconverter.models.HistoryModel;

public interface HistoryRepository extends JpaRepository<HistoryModel, Long> {

}