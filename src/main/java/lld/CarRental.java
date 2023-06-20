package lld;

import java.util.*;

enum BillItemType {
    BASE_CHARGE, ADDITIONAL_SERVICE, FINE, OTHER
}

enum VehicleLogType {
    ACCIDENT, FUELING, CLEANING_SERVICE, OIL_CHANGE, REPAIR, OTHER
}

enum VanType {
    PASSENGER, CARGO
}

enum CarType {
    ECONOMY, COMPACT, INTERMEDIATE, STANDARD, FULL_SIZE, PREMIUM, LUXURY
}

enum VehicleStatus {
    AVAILABLE, RESERVED, LOANED, LOST, BEING_SERVICED, OTHER
}

enum ReservationStatus {
    ACTIVE, PENDING, CONFIRMED, COMPLETED, CANCELLED, NONE
}

enum CarRentalAccountStatus {
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED
}

enum PaymentStatus {
    UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
}

class CarRentalAddress {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

class CarRentalPerson {
    private String name;
    private CarRentalAddress address;
    private String email;
    private String phone;
}

abstract class CarRentalAccount {
    private String id;
    private String password;
    private AccountStatus status;
    private Person person;

    public boolean resetPassword() {return true;}
}

class Member extends CarRentalAccount {
    private int totalVehiclesReserved;
    private List<VehicleReservation> reservations;
    public List<VehicleReservation> getReservations() {return reservations; }
}

class Receptionist extends CarRentalAccount {
    private Date dateJoined;

    //public List<Member> searchMember(String name);
}

class AdditionalDriver {
    private String driverID;
    private Person person;
}

class CarRentalLocation {
    private String name;
    private CarRentalAddress location;
    public CarRentalAddress getLocation() { return location; }
}

class CarRental {
    private String name;

    private List<RentalVehicle> rentalVehicles;

    private List<CarRentalLocation> locations;

    public boolean addNewLocation(CarRentalLocation location) {return locations.add(location);}
}

abstract class RentalVehicle {
    private String licenseNumber;
    private String stockNumber;
    private int passengerCapacity;
    private String barcode;
    private boolean hasSunroof;
    private VehicleStatus status;
    private String model;
    private String make;
    private int manufacturingYear;
    private int mileage;

    private List<VehicleLog> log;

    public boolean reserveVehicle() {return  status == VehicleStatus.RESERVED; }

    public boolean returnVehicle() {return status == VehicleStatus.AVAILABLE; }
}

class RentalCar extends RentalVehicle {
    private CarType type;
}

class RentalVan extends RentalVehicle {
    private VanType type;
}

class RentalTruck extends RentalVehicle {
    private String type;
}

class VehicleLog {
    private String id;
    private VehicleLogType type;
    private String description;
    private Date creationDate;

    Map<VehicleLogType, List<String>> vehicleLogs;

    public boolean update(VehicleLogType vtype, String description) {
        List<String> list = vehicleLogs.getOrDefault(vtype, new ArrayList<>());
        list.add(description);
        vehicleLogs.put(type, list);
        return true;
    }

    public List<String> searchByLogType(VehicleLogType type) {
        return vehicleLogs.get(type);

    }
}

class Bill {}

class Notification{}
class RentalInsurance{}
class Equipment {}
class Service {}

class VehicleReservation {
    private String reservationNumber;
    private Date creationDate;
    private ReservationStatus status;
    private Date dueDate;
    private Date returnDate;
    private String pickupLocationName;
    private String returnLocationName;
    private int customerID;
    private Vehicle vehicle;
    private Bill bill;
    private List<AdditionalDriver> additionalDrivers;
    private List<Notification> notifications;
    private List<RentalInsurance> insurances;
    private List<Equipment> equipments;
    private List<Service> services;

    //public static VehicleReservation fetchReservationDetails(String reservationNumber);

    //public List<Passenger> getAdditionalDrivers();
}

interface Search {
    public List<Vehicle> searchByType(String type);
    public List<Vehicle> searchByModel(String model);
}

class VehicleInventory implements Search {
    private HashMap<String, List<Vehicle>> vehicleTypes;
    private HashMap<String, List<Vehicle>> vehicleModels;

    public List<Vehicle> searchByType(String query) {
        // return all vehicles of the given type.
        return vehicleTypes.get(query);
    }

    public List<Vehicle> searchByModel(String query) {
        // return all vehicles of the given model.
        return vehicleModels.get(query);
    }
}

