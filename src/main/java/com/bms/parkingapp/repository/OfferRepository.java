package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, String> {
}