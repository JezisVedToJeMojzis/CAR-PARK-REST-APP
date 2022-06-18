package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;

import java.util.Date;

public class ConsoleInterface {

    public void window() {
        CarParkService worker = new CarParkService();
        System.out.println("WELCOME TO THE SYSTEM!");
        System.out.println("********************************************************************************");
        System.out.println("\nMANUAL: ");
        System.out.println("Write number that stands for method you want to execute and press enter button.");
        System.out.println("Then type in values for its arguments and press enter after each.");
        System.out.println("If you wish to end the execution, type in - end.");
        System.out.println("If you wish display methods and their numbers again, type in - methods.");
        System.out.println("Note: If you execute methods in incorrect order (or simply logically wrong) you might get NULL as your query result.");
        System.out.println("\n********************************************************************************");
        System.out.println("\nMETHODS: ");
        System.out.println("1 = create CarPark");
        System.out.println("2 = get CarPark by ID");
        System.out.println("3 = get CarPark by name");
        System.out.println("4 = get CarParks");
        System.out.println("5 = delete CarPark");
        System.out.println("6 = create CarParkFloor");
        System.out.println("7 = get CarParkFloor");
        System.out.println("8 = get CarParkFloors");
        System.out.println("9 = delete CarParkFloor");
        System.out.println("10 = create ParkingSpot");
        System.out.println("11 = get ParkingSpot");
        System.out.println("12 = update ParkingSpot (my own method)");
        System.out.println("13 = get ParkingSpots");
        System.out.println("14 = get ParkingSpots (map)");
        System.out.println("15 = get ParkingAvailableSpots (map)");
        System.out.println("16 = get ParkingOccupiedSpots (map)");
        System.out.println("17 = delete ParkingSpot");
        System.out.println("18 = create User");
        System.out.println("19 = get User by ID");
        System.out.println("20 = get User by email");
        System.out.println("21 = get Users");
        System.out.println("22 = delete User");
        System.out.println("23 = create Car");
        System.out.println("24 = get Car by ID");
        System.out.println("25 = update Car (my own method)");
        System.out.println("26 = get Car by Vehicle registration plate");
        System.out.println("27 = get Users Cars");
        System.out.println("28 = delete Car");
        System.out.println("29 = create Reservation");
        System.out.println("30 = end Reservation");
        System.out.println("31 = get Reservations for Parking Spot of today");
        System.out.println("32 = get Users Reservations");
        System.out.println("33 = create CarType");
        System.out.println("34 = get CarTypes");
        System.out.println("35 = get CarType by ID");
        System.out.println("36 = get CarType by name");
        System.out.println("37 = delete CarType");
        System.out.println("38 = create Car with specific CarType");
        System.out.println("39 = create ParkingSpot with specific CarType");

        System.out.println("\n********************************************************************************");
        while (true) {
            String methodInput = KeyboardInput.readString("\n\nNumber of method:  ");

            switch (methodInput) {
                case "methods":
                    System.out.println("\nMETHODS: ");
                    System.out.println("1 = create CarPark");
                    System.out.println("2 = get CarPark by ID");
                    System.out.println("3 = get CarPark by name");
                    System.out.println("4 = get CarParks");
                    System.out.println("5 = delete CarPark");
                    System.out.println("6 = create CarParkFloor");
                    System.out.println("7 = get CarParkFloor");
                    System.out.println("8 = get CarParkFloors");
                    System.out.println("9 = delete CarParkFloor");
                    System.out.println("10 = create ParkingSpot");
                    System.out.println("11 = get ParkingSpot");
                    System.out.println("12 = update ParkingSpot (my own method)");
                    System.out.println("13 = get ParkingSpots");
                    System.out.println("14 = get ParkingSpots (map)");
                    System.out.println("15 = get ParkingAvailableSpots (map)");
                    System.out.println("16 = get ParkingOccupiedSpots (map)");
                    System.out.println("17 = delete ParkingSpot");
                    System.out.println("18 = create User");
                    System.out.println("19 = get User by ID");
                    System.out.println("20 = get User by email");
                    System.out.println("21 = get Users");
                    System.out.println("22 = delete User");
                    System.out.println("23 = create Car");
                    System.out.println("24 = get Car by ID");
                    System.out.println("25 = update Car (my own method)");
                    System.out.println("26 = get Car by Vehicle registration plate");
                    System.out.println("27 = get Users Cars");
                    System.out.println("28 = delete Car");
                    System.out.println("29 = create Reservation");
                    System.out.println("30 = end Reservation");
                    System.out.println("31 = get Reservations for Parking Spot of today");
                    System.out.println("32 = get Users Reservations");
                    System.out.println("33 = create CarType");
                    System.out.println("34 = get CarTypes");
                    System.out.println("35 = get CarType by ID");
                    System.out.println("36 = get CarType by name");
                    System.out.println("37 = delete CarType");
                    System.out.println("38 = create Car with specific CarType");
                    System.out.println("39 = create ParkingSpot with specific CarType");
                    continue;
                case "1":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("1 = create CarPark / Arguments: String name, String address, Integer pricePerHour");
                    System.out.println("\nQuery: "+worker.createCarPark(KeyboardInput.readString("\nName: "), KeyboardInput.readString("Address: "), KeyboardInput.readInt("Price per hour: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "2":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("2 = get CarPark / Arguments: Long carParkId");
                    System.out.println("\nQuery: "+worker.getCarPark(KeyboardInput.readLong("CarPark ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "3":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("3 = get CarPark / Arguments: String carParkName");
                    System.out.println("\nQuery: "+worker.getCarPark(KeyboardInput.readString("CarPark Name: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "4":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("4 = get CarParks / Arguments: none");
                    System.out.println("\nQuery: "+worker.getCarParks());
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "5":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("5 = delete CarPark / Arguments: Long carParkId");
                    System.out.println("\nQuery: "+worker.deleteCarPark(KeyboardInput.readLong("CarPark ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "6":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("6 = create CarParkFloor / Arguments: Long carParkId, String floorIdentifier");
                    System.out.println("\nQuery: "+worker.createCarParkFloor(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "7":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("7 = get CarParkFloor / Arguments: Long carParkId, String floorIdentifier");
                    System.out.println("\nQuery: "+worker.getCarParkFloor(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "8":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("8 = get CarParkFloors / Arguments: Long carParkId");
                    System.out.println("\nQuery: "+worker.getCarParkFloors(KeyboardInput.readLong("CarPark ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "9":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("9 = delete CarParkFloor / Arguments: Long carParkId, String floorIdentifier");
                    System.out.println("\nQuery: "+worker.deleteCarParkFloor(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "10":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("10 = create ParkingSpot / Arguments: Long carParkId, String floorIdentifier, String spotIdentifier");
                    System.out.println("\nQuery: "+worker.createParkingSpot(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: "),KeyboardInput.readString("SpotIdentifier: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "11":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("11 = get ParkingSpot / Arguments: Long parkingSpotId");
                    System.out.println("\nQuery: "+worker.getParkingSpot(KeyboardInput.readLong("ParkingSpot ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "12":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("12 = update ParkingSpot / Arguments: Long parkingSpotId, Long carParkId, Long floorIdentifierId, String spotIdentifier, Long carTypeId");
                    System.out.println("\nQuery: "+worker.updateParkingSpot(KeyboardInput.readLong("ParkingSpot ID: "),KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readLong("Floor ID: "),KeyboardInput.readString("SpotIdentifier: "),KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "13":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("13 = get ParkingSpots / Arguments: Long carParkId, String floorIdentifier");
                    System.out.println("\nQuery: "+worker.getParkingSpots(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "14":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("14 = get ParkingSpots (map) / Arguments: Long carParkId");
                    System.out.println("\nQuery: "+worker.getParkingSpots(KeyboardInput.readLong("CarPark ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "15":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("15 = get ParkingAvailableSpots (map) / Arguments: String carParkName");
                    System.out.println("\nQuery: "+worker.getAvailableParkingSpots(KeyboardInput.readString("CarPark Name: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "16":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("16 = get ParkingOccupiedSpots (map) / Arguments: Long carParkId");
                    System.out.println("\nQuery: "+worker.getOccupiedParkingSpots(KeyboardInput.readString("CarPark Name: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "17":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("17 = delete ParkingSpot / Arguments: Long parkingSpotId");
                    System.out.println("\nQuery: "+worker.deleteParkingSpot(KeyboardInput.readLong("ParkingSpot ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "18":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("18 = create User / Arguments: String firstname, String lastname, String email");
                    System.out.println("\nQuery: "+worker.createUser(KeyboardInput.readString("First name: "),KeyboardInput.readString("Last name: "),KeyboardInput.readString("Email: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "19":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("19 = get User by ID / Arguments: Long userId");
                    System.out.println("\nQuery: "+worker.getUser(KeyboardInput.readLong("User ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "20":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("20 = get User by email / Arguments: String email");
                    System.out.println("\nQuery: "+worker.getUser(KeyboardInput.readString("Email: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "21":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("21 = get Users / Arguments: none");
                    System.out.println("\nQuery: "+worker.getUsers());
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "22":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("22 = delete User / Arguments: Long userId");
                    System.out.println("\nQuery: "+worker.getUser(KeyboardInput.readLong("User ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "23":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("23 = create Car / Arguments: Long userId, String brand, String model, String colour, String vehicleRegistrationPlate");
                    System.out.println("\nQuery: "+worker.createCar(KeyboardInput.readLong("User ID: "),KeyboardInput.readString("Brand: "),KeyboardInput.readString("Model: "),KeyboardInput.readString("Colour: "),KeyboardInput.readString("Vehicle registration plate: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "24":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("24 = get Car by ID / Arguments: Long carId");
                    System.out.println("\nQuery: "+worker.getCar(KeyboardInput.readLong("Car ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "25":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("25 = update of Car (my own method) / Arguments: Long carId, Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId");
                    System.out.println("\nQuery: "+worker.updateCar(KeyboardInput.readLong("Car ID: "),KeyboardInput.readLong("User ID: "),KeyboardInput.readString("Brand: "),KeyboardInput.readString("Model: "),KeyboardInput.readString("Colour: "),KeyboardInput.readString("Vehicle registration plate: "),KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "26":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("26 = get Car by Vehicle registration plate / Arguments: String vehicleRegistrationPlate");
                    System.out.println("\nQuery: "+worker.getCar(KeyboardInput.readString("Vehicle registration plate: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "27":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("27 = get Users Cars / Arguments: Long userId");
                    System.out.println("\nQuery: "+worker.getCars(KeyboardInput.readLong("User ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "28":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("28 = delete Car / Arguments: Long carId");
                    System.out.println("\nQuery: "+worker.getCars(KeyboardInput.readLong("Car ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "29":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("29 = create Reservation / Arguments: Long parkingSpotId, Long carId");
                    System.out.println("\nQuery: "+worker.createReservation(KeyboardInput.readLong("Parking Spot ID: "),KeyboardInput.readLong("Car ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "30":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("30 = end Reservation / Arguments: Long reservationId");
                    System.out.println("\nQuery: "+worker.endReservation(KeyboardInput.readLong("Reservation ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "31":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("31 = get Reservations for Parking Spot of today / Arguments: Long parkingSpotId, Date date");
                    System.out.println("\nQuery: "+worker.getReservations(KeyboardInput.readLong("ParkingSpot ID: "),new Date()));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "32":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("32 = get Users Reservations / Arguments: Long userId");
                    System.out.println("\nQuery: "+worker.getMyReservations(KeyboardInput.readLong("User ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "33":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("33 = create CarType / Arguments: String name");
                    System.out.println("\nQuery: "+worker.createCarType(KeyboardInput.readString("CarType: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "34":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("34 = get CarTypes / Arguments: none");
                    System.out.println("\nQuery: "+worker.getCarTypes());
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "35":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("35 = get CarType by ID / Arguments: Long carTypeId");
                    System.out.println("\nQuery: "+worker.getCarType(KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "36":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("36 = get CarType by name / Arguments: String name");
                    System.out.println("\nQuery: "+worker.getCarType(KeyboardInput.readString("CarType name: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "37":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("37 = delete CarType / Arguments: Long carTypeId");
                    System.out.println("\nQuery: "+worker.deleteCarType(KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "38":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("38 = create Car with specific CarType / Arguments: Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId");
                    System.out.println("\nQuery: "+worker.createCar(KeyboardInput.readLong("User ID: "),KeyboardInput.readString("Brand: "),KeyboardInput.readString("Model: "),KeyboardInput.readString("Colour: "),KeyboardInput.readString("Vehicle registration plate: "),KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "39":
                    System.out.println("\n--------------------------------------------------------------------------------------");
                    System.out.println("39 = create ParkingSpot with specific CarType / Arguments: Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId");
                    System.out.println("\nQuery: "+worker.createParkingSpot(KeyboardInput.readLong("CarPark ID: "),KeyboardInput.readString("FloorIdentifier: "),KeyboardInput.readString("SpotIdentifier: "),KeyboardInput.readLong("CarType ID: ")));
                    System.out.println("--------------------------------------------------------------------------------------");
                    continue;
                case "end":
                    return;
            }
        }
    }
}
