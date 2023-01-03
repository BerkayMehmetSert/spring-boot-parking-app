package com.bms.parkingapp.repository;

import com.bms.parkingapp.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, String> {
    Optional<Offer> findByOfferCode(String offerCode);
}