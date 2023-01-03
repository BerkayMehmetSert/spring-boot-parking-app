package com.bms.parkingapp.helper.message;

public class BusinessLogMessage {

    private BusinessLogMessage() {
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
    }

    public static class Customer{
        private Customer(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String CUSTOMER_SAVE_SUCCESS = "Customer saved successfully";
        public static final String CUSTOMER_UPDATE_SUCCESS = "Customer updated successfully : {}";
        public static final String CUSTOMER_DELETE_SUCCESS = "Customer deleted successfully : {}";
        public static final String CUSTOMER_LIST_SUCCESS = "Customer list retrieved successfully";
        public static final String CUSTOMER_FOUND_SUCCESS = "Customer found successfully : {}";
        public static final String CUSTOMER_NOT_FOUND = "Customer not found : {}";
        public static final String CUSTOMER_LIST_EMPTY = "Customer list is empty";
        public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists : {}";
    }

    public static class Offer{
        private Offer(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String OFFER_SAVE_SUCCESS = "Offer saved successfully";
        public static final String OFFER_UPDATE_SUCCESS = "Offer updated successfully : {}";
        public static final String OFFER_DELETE_SUCCESS = "Offer deleted successfully : {}";
        public static final String OFFER_LIST_SUCCESS = "Offer list retrieved successfully";
        public static final String OFFER_FOUND_SUCCESS = "Offer found successfully : {}";
        public static final String OFFER_NOT_FOUND = "Offer not found : {}";
        public static final String OFFER_LIST_EMPTY = "Offer list is empty";
        public static final String OFFER_ALREADY_EXISTS = "Offer already exists : {}";
    }

    public static class ParkingLot{
        private ParkingLot(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_LOT_SAVE_SUCCESS = "Parking lot saved successfully";
        public static final String PARKING_LOT_UPDATE_SUCCESS = "Parking lot updated successfully : {}";
        public static final String PARKING_LOT_DELETE_SUCCESS = "Parking lot deleted successfully : {}";
        public static final String PARKING_LOT_LIST_SUCCESS = "Parking lot list retrieved successfully";
        public static final String PARKING_LOT_FOUND_SUCCESS = "Parking lot found successfully : {}";
        public static final String PARKING_LOT_NOT_FOUND = "Parking lot not found : {}";
        public static final String PARKING_LOT_LIST_EMPTY = "Parking lot list is empty";
        public static final String PARKING_LOT_ALREADY_EXISTS = "Parking lot already exists : {}";
    }

    public static class ParkingMonthly{
        private ParkingMonthly(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_MONTHLY_SAVE_SUCCESS = "Parking monthly saved successfully";
        public static final String PARKING_MONTHLY_UPDATE_SUCCESS = "Parking monthly updated successfully : {}";
        public static final String PARKING_MONTHLY_DELETE_SUCCESS = "Parking monthly deleted successfully : {}";
        public static final String PARKING_MONTHLY_LIST_SUCCESS = "Parking monthly list retrieved successfully";
        public static final String PARKING_MONTHLY_FOUND_SUCCESS = "Parking monthly found successfully : {}";
        public static final String PARKING_MONTHLY_NOT_FOUND = "Parking monthly not found : {}";
        public static final String PARKING_MONTHLY_LIST_EMPTY = "Parking monthly list is empty";
        public static final String PARKING_MONTHLY_ALREADY_EXISTS = "Parking monthly already exists : {}";
    }

    public static class ParkingOneTime{
        private ParkingOneTime(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_ONE_TIME_SAVE_SUCCESS = "Parking one time saved successfully";
        public static final String PARKING_ONE_TIME_UPDATE_SUCCESS = "Parking one time updated successfully : {}";
        public static final String PARKING_ONE_TIME_DELETE_SUCCESS = "Parking one time deleted successfully : {}";
        public static final String PARKING_ONE_TIME_LIST_SUCCESS = "Parking one time list retrieved successfully";
        public static final String PARKING_ONE_TIME_FOUND_SUCCESS = "Parking one time found successfully : {}";
        public static final String PARKING_ONE_TIME_NOT_FOUND = "Parking one time not found : {}";
        public static final String PARKING_ONE_TIME_LIST_EMPTY = "Parking one time list is empty";
        public static final String PARKING_ONE_TIME_ALREADY_EXISTS = "Parking one time already exists : {}";
    }

    public static class ParkingPrice{
        private ParkingPrice(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PARKING_PRICE_SAVE_SUCCESS = "Parking price saved successfully";
        public static final String PARKING_PRICE_UPDATE_SUCCESS = "Parking price updated successfully : {}";
        public static final String PARKING_PRICE_DELETE_SUCCESS = "Parking price deleted successfully : {}";
        public static final String PARKING_PRICE_LIST_SUCCESS = "Parking price list retrieved successfully";
        public static final String PARKING_PRICE_FOUND_SUCCESS = "Parking price found successfully : {}";
        public static final String PARKING_PRICE_NOT_FOUND = "Parking price not found : {}";
        public static final String PARKING_PRICE_LIST_EMPTY = "Parking price list is empty";
        public static final String PARKING_PRICE_ALREADY_EXISTS = "Parking price already exists : {}";
    }

    public static class PaymentMethod{
        private PaymentMethod(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PAYMENT_METHOD_SAVE_SUCCESS = "Payment method saved successfully";
        public static final String PAYMENT_METHOD_UPDATE_SUCCESS = "Payment method updated successfully : {}";
        public static final String PAYMENT_METHOD_DELETE_SUCCESS = "Payment method deleted successfully : {}";
        public static final String PAYMENT_METHOD_LIST_SUCCESS = "Payment method list retrieved successfully";
        public static final String PAYMENT_METHOD_FOUND_SUCCESS = "Payment method found successfully : {}";
        public static final String PAYMENT_METHOD_NOT_FOUND = "Payment method not found : {}";
        public static final String PAYMENT_METHOD_LIST_EMPTY = "Payment method list is empty";
        public static final String PAYMENT_METHOD_ALREADY_EXISTS = "Payment method already exists : {}";
    }

    public static class PricingExceptional{
        private PricingExceptional(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PRICING_EXCEPTION_SAVE_SUCCESS = "Pricing exception saved successfully";
        public static final String PRICING_EXCEPTION_UPDATE_SUCCESS = "Pricing exception updated successfully : {}";
        public static final String PRICING_EXCEPTION_DELETE_SUCCESS = "Pricing exception deleted successfully : {}";
        public static final String PRICING_EXCEPTION_LIST_SUCCESS = "Pricing exception list retrieved successfully";
        public static final String PRICING_EXCEPTION_FOUND_SUCCESS = "Pricing exception found successfully : {}";
        public static final String PRICING_EXCEPTION_NOT_FOUND = "Pricing exception not found : {}";
        public static final String PRICING_EXCEPTION_LIST_EMPTY = "Pricing exception list is empty";
        public static final String PRICING_EXCEPTION_ALREADY_EXISTS = "Pricing exception already exists : {}";
    }

    public static class Vehicle{
        private Vehicle(){
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String VEHICLE_SAVE_SUCCESS = "Vehicle saved successfully";
        public static final String VEHICLE_UPDATE_SUCCESS = "Vehicle updated successfully : {}";
        public static final String VEHICLE_DELETE_SUCCESS = "Vehicle deleted successfully : {}";
        public static final String VEHICLE_LIST_SUCCESS = "Vehicle list retrieved successfully";
        public static final String VEHICLE_FOUND_SUCCESS = "Vehicle found successfully : {}";
        public static final String VEHICLE_NOT_FOUND = "Vehicle not found : {}";
        public static final String VEHICLE_LIST_EMPTY = "Vehicle list is empty";
        public static final String VEHICLE_ALREADY_EXISTS = "Vehicle already exists : {}";
    }
}
