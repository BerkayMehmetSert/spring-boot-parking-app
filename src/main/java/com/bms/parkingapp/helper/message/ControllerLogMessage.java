package com.bms.parkingapp.helper.message;

public class ControllerLogMessage {
    private ControllerLogMessage(){
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
    }

    public static class Customer{
        private Customer(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String CUSTOMER_SAVE_SUCCESS = "Customer API: Customer saved successfully";
        public static final String CUSTOMER_UPDATE_SUCCESS = "Customer API: Customer updated successfully : {}";
        public static final String CUSTOMER_DELETE_SUCCESS = "Customer API: Customer deleted successfully : {}";
        public static final String CUSTOMER_LIST_SUCCESS = "Customer API: Customer list retrieved successfully";
        public static final String CUSTOMER_FOUND_SUCCESS = "Customer API: Customer found successfully : {}";
        public static final String CUSTOMER_LISTED_SUCCESS = "Customer API: Customer listed successfully : {}";
    }

    public static class Offer{
        private Offer(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String OFFER_SAVE_SUCCESS = "Offer API: Offer saved successfully";
        public static final String OFFER_UPDATE_SUCCESS = "Offer API: Offer updated successfully : {}";
        public static final String OFFER_DELETE_SUCCESS = "Offer API: Offer deleted successfully : {}";
        public static final String OFFER_LIST_SUCCESS = "Offer API: Offer list retrieved successfully";
        public static final String OFFER_FOUND_SUCCESS = "Offer API: Offer found successfully : {}";
        public static final String OFFER_LISTED_SUCCESS = "Offer API: Offer listed successfully : {}";
    }

    public static class ParkingLot{
        private ParkingLot(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_LOT_SAVE_SUCCESS = "Parking lot API: Parking lot saved successfully";
        public static final String PARKING_LOT_UPDATE_SUCCESS = "Parking lot API: Parking lot updated successfully : {}";
        public static final String PARKING_LOT_DELETE_SUCCESS = "Parking lot API: Parking lot deleted successfully : {}";
        public static final String PARKING_LOT_LIST_SUCCESS = "Parking lot API: Parking lot list retrieved successfully";
        public static final String PARKING_LOT_FOUND_SUCCESS = "Parking lot API: Parking lot found successfully : {}";
        public static final String PARKING_LOT_LISTED_SUCCESS = "Parking lot API: Parking lot listed successfully : {}";
    }

    public static class ParkingMonthly{
        private ParkingMonthly(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_MONTHLY_SAVE_SUCCESS = "Parking monthly API: Parking monthly saved successfully";
        public static final String PARKING_MONTHLY_UPDATE_SUCCESS = "Parking monthly API: Parking monthly updated successfully : {}";
        public static final String PARKING_MONTHLY_DELETE_SUCCESS = "Parking monthly API: Parking monthly deleted successfully : {}";
        public static final String PARKING_MONTHLY_LIST_SUCCESS = "Parking monthly API: Parking monthly list retrieved successfully";
        public static final String PARKING_MONTHLY_FOUND_SUCCESS = "Parking monthly API: Parking monthly found successfully : {}";
        public static final String PARKING_MONTHLY_LISTED_SUCCESS = "Parking monthly API: Parking monthly listed successfully : {}";
    }

    public static class ParkingOneTime{
        private ParkingOneTime(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_ONE_TIME_SAVE_SUCCESS = "Parking one time API: Parking one time saved successfully";
        public static final String PARKING_ONE_TIME_UPDATE_SUCCESS = "Parking one time API: Parking one time updated successfully : {}";
        public static final String PARKING_ONE_TIME_DELETE_SUCCESS = "Parking one time API: Parking one time deleted successfully : {}";
        public static final String PARKING_ONE_TIME_LIST_SUCCESS = "Parking one time API: Parking one time list retrieved successfully";
        public static final String PARKING_ONE_TIME_FOUND_SUCCESS = "Parking one time API: Parking one time found successfully : {}";
        public static final String PARKING_ONE_TIME_LISTED_SUCCESS = "Parking one time API: Parking one time listed successfully : {}";
    }

    public static class ParkingPrice{
        private ParkingPrice(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_PRICE_SAVE_SUCCESS = "Parking price API: Parking price saved successfully";
        public static final String PARKING_PRICE_UPDATE_SUCCESS = "Parking price API: Parking price updated successfully : {}";
        public static final String PARKING_PRICE_DELETE_SUCCESS = "Parking price API: Parking price deleted successfully : {}";
        public static final String PARKING_PRICE_LIST_SUCCESS = "Parking price API: Parking price list retrieved successfully";
        public static final String PARKING_PRICE_FOUND_SUCCESS = "Parking price API: Parking price found successfully : {}";
        public static final String PARKING_PRICE_LISTED_SUCCESS = "Parking price API: Parking price listed successfully : {}";
    }

    public static class PaymentMethod{
        private PaymentMethod(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PAYMENT_METHOD_SAVE_SUCCESS = "Payment method API: Payment method saved successfully";
        public static final String PAYMENT_METHOD_UPDATE_SUCCESS = "Payment method API: Payment method updated successfully : {}";
        public static final String PAYMENT_METHOD_DELETE_SUCCESS = "Payment method API: Payment method deleted successfully : {}";
        public static final String PAYMENT_METHOD_LIST_SUCCESS = "Payment method API: Payment method list retrieved successfully";
        public static final String PAYMENT_METHOD_FOUND_SUCCESS = "Payment method API: Payment method found successfully : {}";
        public static final String PAYMENT_METHOD_LISTED_SUCCESS = "Payment method API: Payment method listed successfully : {}";
    }

    public static class PricingExceptional{
        private PricingExceptional(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PRICING_EXCEPTION_SAVE_SUCCESS = "Pricing exception API: Pricing exception saved successfully";
        public static final String PRICING_EXCEPTION_UPDATE_SUCCESS = "Pricing exception API: Pricing exception updated successfully : {}";
        public static final String PRICING_EXCEPTION_DELETE_SUCCESS = "Pricing exception API: Pricing exception deleted successfully : {}";
        public static final String PRICING_EXCEPTION_LIST_SUCCESS = "Pricing exception API: Pricing exception list retrieved successfully";
        public static final String PRICING_EXCEPTION_FOUND_SUCCESS = "Pricing exception API: Pricing exception found successfully : {}";
        public static final String PRICING_EXCEPTION_LISTED_SUCCESS = "Pricing exception API: Pricing exception listed successfully : {}";
    }

    public static class Vehicle{
        private Vehicle(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String VEHICLE_SAVE_SUCCESS = "Vehicle API: Vehicle saved successfully";
        public static final String VEHICLE_UPDATE_SUCCESS = "Vehicle API: Vehicle updated successfully : {}";
        public static final String VEHICLE_DELETE_SUCCESS = "Vehicle API: Vehicle deleted successfully : {}";
        public static final String VEHICLE_LIST_SUCCESS = "Vehicle API: Vehicle list retrieved successfully";
        public static final String VEHICLE_FOUND_SUCCESS = "Vehicle API: Vehicle found successfully : {}";
        public static final String VEHICLE_LISTED_SUCCESS = "Vehicle API: Vehicle listed successfully : {}";
    }
}
