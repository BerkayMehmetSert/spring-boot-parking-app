package com.bms.parkingapp.helper;

import com.bms.parkingapp.exception.ParkingMonthlyPriceNotFoundException;
import com.bms.parkingapp.helper.message.BusinessMessage;
import com.bms.parkingapp.model.*;

import java.util.Random;

public class Generator {
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final Random random = new Random();
    private static final StringBuilder code = new StringBuilder();

    private Generator() {
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
    }

    public static String generateOfferCode() {
        for (int i = 0; i < 2; i++) {
            code.append(UPPERCASE_CHARACTERS.charAt(random.nextInt(UPPERCASE_CHARACTERS.length())));
        }

        code.append(" ");

        for (int i = 0; i < 3; i++) {
            code.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }

        code.append(" ");

        for (int i = 0; i < 3; i++) {
            code.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }

        code.append(" ");

        for (int i = 0; i < 2; i++) {
            code.append(LOWERCASE_CHARACTERS.charAt(random.nextInt(LOWERCASE_CHARACTERS.length())));
        }

        return code.toString();
    }

    public static Double calculateAllDayCost(final Double morningHoursCost,
                                             final Double midDayHoursCost,
                                             final Double eveningHoursCost) {
        return (morningHoursCost * 8) + (midDayHoursCost * 5) + (eveningHoursCost * 9);
    }

    public static Double calculateNetCost(ParkingOneTime parkingOneTime, ParkingLot parkingLot, Offer offer) {
        Double basicParkingCost = parkingOneTime.getBasicParkingCost();
        Double bookingForHours = parkingOneTime.getBookingForHours();
        double netCost = 0.0;

        double offerCodeDiscount = parkingLot.getOffers().stream()
                .filter(x -> x.getOfferCode().equals(offer.getOfferCode()))
                .map(Offer::getDiscountInPercentage)
                .findFirst()
                .orElse(0.0);


        if (Boolean.TRUE.equals(parkingLot.getOperationalInNight())) {
            netCost = (basicParkingCost + (basicParkingCost * 2)) * bookingForHours;
        }

        if (Boolean.TRUE.equals(parkingLot.getIsValetParkingAvailable())) {
            netCost = (basicParkingCost + (basicParkingCost * 0.1)) * bookingForHours;
        }

        if (offerCodeDiscount > 0) {
            netCost = netCost - (netCost * offerCodeDiscount);
        }

        if (netCost < 0) {
            netCost = 0.0;
        }

        return netCost;
    }

    public static Double generateCost(ParkingMonthly parkingMonthly, ParkingLot parkingLot, Customer customer) {
        Integer currentDay = parkingMonthly.getStartDate().getDayOfWeek().getValue();
        double cost = 0.0;

        Double exceptionalPrice = parkingLot.getPricingExceptionals().stream()
                .filter(p -> p.getDate().equals(parkingMonthly.getStartDate()))
                .findFirst().orElseThrow(() -> {
                    throw new ParkingMonthlyPriceNotFoundException(BusinessMessage
                            .ParkingMonthly.PARKING_MONTHLY_PRICE_NOT_FOUND);
                }).getAllDayCost();

        Double parkingLotPrice = parkingLot.getParkingPrices().stream()
                .filter(dayOfWeek -> dayOfWeek.getDayOfWeek().equals(currentDay))
                .findFirst().orElseThrow(() -> {
                    throw new ParkingMonthlyPriceNotFoundException(BusinessMessage
                            .ParkingMonthly.PARKING_MONTHLY_PRICE_NOT_FOUND);
                }).getAllDayCost();

        if (parkingLot.getPricingExceptionals().size() > 0) {
            cost = exceptionalPrice + cost;
        }

        if (Boolean.TRUE.equals(customer.getIsRegularCustomer())) {
            cost = cost - (cost * 0.2);
        }

        return (cost + parkingLotPrice) * parkingMonthly.getDurationInDays();
    }
}
