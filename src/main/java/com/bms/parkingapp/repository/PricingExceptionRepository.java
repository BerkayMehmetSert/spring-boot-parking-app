package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.PricingException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingExceptionRepository extends JpaRepository<PricingException, String> {
}