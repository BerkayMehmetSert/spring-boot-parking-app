package com.bms.parkingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private Integer numberOfBlocks;

    private Boolean isSlotAvailable;

    private String address;

    private String zipCode;

    private Boolean isReentryAllowed;

    private String operatingCompany;

    private Boolean isValetParkingAvailable;

    private Boolean operationalInNight;

    private Double minimumHoursToPay;

    private Boolean isMonthlyPassAllowed;

    private Double monthlyPassCost;

    @OneToMany(mappedBy = "parkingLot")
    private List<Offer> offers;

    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingPrice> parkingPrices;

    @OneToMany(mappedBy = "parkingLot")
    private List<PricingExceptional> pricingExceptionals;

    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingMonthly> parkingMonthlies;

    @OneToMany(mappedBy = "parkingLot")
    private List<ParkingOneTime> parkingOneTimes;
}
