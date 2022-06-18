package sk.stuba.fei.uim.vsa.pr2.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class AbstractCarParkService {

    protected EntityManagerFactory emf;

    protected AbstractCarParkService() {
        this.emf = Persistence.createEntityManagerFactory("default");
    }

    protected void close() {
        emf.close();
    }

    // Parkovací dom

    /**
     * Vytvorenie nového parkovacieho domu
     *
     * @param name         názov parkovacieho domu
     * @param address      adresa parkovacieho domu
     * @param pricePerHour cena za hodinu parkovania
     * @return objekt entity parkovacieho domu
     */
    public abstract Object createCarPark(String name, String address, Integer pricePerHour);

    /**
     * Získanie entity parkovacieho domu podľa ID
     *
     * @param carParkId id parkovacieho domu
     * @return objekt entity parkovacieho domu
     */
    public abstract Object getCarPark(Long carParkId);

    /**
     * Získanie entity parkovacieho domu podľa názvu domu
     *
     * @param carParkName názov parkovacieho domu
     * @return objekt entity parkovacieho domu
     */
    public abstract Object getCarPark(String carParkName);

    /**
     * Získanie zoznamu všetkých parkovacích domov
     *
     * @return zoznam entít parkovacích domov
     */
    public abstract List<Object> getCarParks();

    /**
     * Vymazanie parkovacieho domu podľa id
     *
     * @param carParkId id parkovacieho domu
     * @return objekt vymazaného parkovacieho domu
     */
    public abstract Object deleteCarPark(Long carParkId);


    // Poschodia parkovacieho domu

    /**
     * Vytvorenie poschodia parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia. Môže byť číslo podlažia, alebo iná skratka pre poschodie.
     *                        Musí byť unikátna v rámci parkovacieho domu.
     * @return objekt entity poschodia
     */
    public abstract Object createCarParkFloor(Long carParkId, String floorIdentifier);

    /**
     * Získanie entity poschodia parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @return objekt entity poschodia
     */
    public abstract Object getCarParkFloor(Long carParkId, String floorIdentifier);

    /**
     * Získanie zoznamu entít všetkých poschodí v parkovacom dome
     *
     * @param carParkId id parkovacieho domu
     * @return zoznam entít poschodí
     */
    public abstract List<Object> getCarParkFloors(Long carParkId);

    /**
     * Vymazanie poschodia v parkovacom dome
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @return vymazaná entita poschodia
     */
    public abstract Object deleteCarParkFloor(Long carParkId, String floorIdentifier);


    // Parkovacie miesto

    /**
     * Vytvorenie parkovacieho miesta na poschodí parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @param spotIdentifier  identifikátor parkovacieho miesta. Môže byť poradové číslo, alebo iná skratka pre označenie miesta.
     *                        Musí byť unikátna v rámci parkovacieho domu.
     * @return objekt entity parkovacieho miesta
     */
    public abstract Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier);

    /**
     * Získanie parkovacieho miesta
     *
     * @param parkingSpotId id parkovacieho miesta
     * @return objekt entity parkovacieho miesta
     */
    public abstract Object getParkingSpot(Long parkingSpotId);

    //Updating parking spot
    public abstract Object updateParkingSpot(Long parkingSpotId, Long carParkId, Long floorIdentifierId, String spotIdentifier, Long carTypeId);
    /**
     * Získanie zoznamu parkovacích miest na poschodí parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @return object entity parkovacieho miesta
     */
    public abstract List<Object> getParkingSpots(Long carParkId, String floorIdentifier);

    /**
     * Získanie zoznamu parkovacích miest v parkovacom dome
     *
     * @param carParkId id parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam parkovacích miest na danom poschodí.
     */
    public abstract Map<String, List<Object>> getParkingSpots(Long carParkId);

    /**
     * Získanie zoznamu parkovacích miest, ktoré sú dostupné, t.j. nie je na nich zaparkované auto.
     *
     * @param carParkName názov parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam voľných parkovacích miest na danom poschodí.
     */
    public abstract Map<String, List<Object>> getAvailableParkingSpots(String carParkName);

    /**
     * Získanie zoznamu parkovacích miest, ktoré sú obsadené, t.j. je na nich zaparkované auto.
     *
     * @param carParkName názov parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam obsadených parkovacích miest na danom poschodí.
     */
    public abstract Map<String, List<Object>> getOccupiedParkingSpots(String carParkName);

    /**
     * Vymazanie parkovacieho miesta
     *
     * @param parkingSpotId id parkovacieho miesta
     * @return vymazané parkovacie miesto
     */
    public abstract Object deleteParkingSpot(Long parkingSpotId);


    // Auto

    /**
     * Vytvorenie nového auta
     *
     * @param userId                   id používateľa/zákazníka
     * @param brand                    značka auta
     * @param model                    model auta
     * @param colour                   farba karosérie auta
     * @param vehicleRegistrationPlate evidenčné číslo vozidla
     * @return objekt entity auta
     */
    public abstract Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate);

    /**
     * Získanie entity auta podľa id
     *
     * @param carId id auta
     * @return objekt entity auta
     */
    public abstract Object getCar(Long carId);

    /**
     * Získanie entity auta podľa EČV
     *
     * @param vehicleRegistrationPlate evidenčné číslo vozidla
     * @return objekt entity auta
     */

    //update of car
    public abstract Object updateCar(Long carId, Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId);

    /**
     * Získanie zoznamu áut používateľa/zákazníka
     *

     * @return zoznam entít áut
     */
    public abstract Object getCar(String vehicleRegistrationPlate);

    /**
     * Získanie zoznamu áut používateľa/zákazníka
     *
     * @param userId id používateľa/zákazníka
     * @return zoznam entít áut
     */
    public abstract List<Object> getCars(Long userId);

    /**
     * Vymazanie auta
     *
     * @param carId id auta
     * @return vymazaná entita auta
     */
    public abstract Object deleteCar(Long carId);


    // Používateľ / Zákazník

    /**
     * Vytvorenie používateľa / zákazníka
     *
     * @param firstname krstné meno
     * @param lastname  priezvisko
     * @param email     emailová adresa. Musí byť unikátna
     * @return objekt entity používateľa
     */
    public abstract Object createUser(String firstname, String lastname, String email);

    /**
     * Získanie používateľa podľa id
     *
     * @param userId id používateľa
     * @return objekt entity používateľa
     */
    public abstract Object getUser(Long userId);

    /**
     * Získanie používateľa podľa emailovej adresy
     *
     * @param email emailová adresa používateľa
     * @return objekt entity používateľa
     */
    public abstract Object getUser(String email);

    /**
     * Získanie zoznamu všetkých používateľov
     *
     * @return zoznam entít používateľov
     */
    public abstract List<Object> getUsers();

    /**
     * Vymazanie používateľa
     *
     * @param userId id používateľa
     * @return vymazaná entita používateľa
     */
    public abstract Object deleteUser(Long userId);


    // Rezervácia / Parkovanie

    /**
     * Vytvorenie rezervácie pre zaparkované auto. Pri vytvorení rezervácie je do nej zapísaný dátum a čas začatia rezervácie.
     *
     * @param parkingSpotId id parkovacieho miesta
     * @param carId        id auta
     * @return objekt rezervácie
     */
    public abstract Object createReservation(Long parkingSpotId, Long carId);

    /**
     * Ukončenie rezervácie / parkovanie auta. Pri ukončení parkovania je zapísaný čas ukončenia rezervácie a vypočítaná celková cena za parkovanie.
     *
     * @param reservationId id rezervácie
     * @return objekt entity rezervácie
     */
    public abstract Object endReservation(Long reservationId);

    /**
     * Získanie zoznamu všetkých rezervácií pre parkovacieho miesto začaté v daný deň.
     *
     * @param parkingSpotId id parkovacieho miesta
     * @param date          dátum rezervácii
     * @return zoznam entít rezervácií
     */
    public abstract List<Object> getReservations(Long parkingSpotId, Date date);

    /**
     * Získanie zoznamu aktívnych / neukončených rezervácií pre daného používateľa.
     *
     * @param userId id používateľa
     * @return zoznam entít rezervácií
     */
    public abstract List<Object> getMyReservations(Long userId);


    // Skupina B

    /**
     * Vytvorenie typu auta
     *
     * @param name názov typu auta
     * @return objekt entity typu auta
     */
    public abstract Object createCarType(String name);

    /**
     * Získanie zoznamu všetkých typov áut
     *
     * @return zoznam entít typov áut
     */
    public abstract List<Object> getCarTypes();

    /**
     * Získanie objektu entity typu auta.
     *
     * @param carTypeId id typu auta
     * @return objekt entity typu auta
     */
    public abstract Object getCarType(Long carTypeId);

    /**
     * Získanie objektu entity typu auta podľa názvu typu.
     *
     * @param name názov typu auta
     * @return objekt entity typu auta
     */
    public abstract Object getCarType(String name);

    /**
     * Vymazanie typu auta
     *
     * @param carTypeId id typu auta
     * @return vymazaná entita typu auta
     */
    public abstract Object deleteCarType(Long carTypeId);

    /**
     * Vytvorenie nového auta aj s definovaným typom.
     *
     * @param userId                   id používateľa/zákazníka
     * @param brand                    značka auta
     * @param model                    model auta
     * @param colour                   farba karosérie auta
     * @param vehicleRegistrationPlate evidenčné číslo vozidla
     * @param carTypeId                id typu auta
     * @return objekt entity auta
     */
    public abstract Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId);


    /**
     * Vytvorenie parkovacieho miesta na poschodí parkovacieho domu aj s typom auta, ktoré môže na ňom parkovať.
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @param spotIdentifier  identifikátor parkovacieho miesta. Môže byť poradové číslo, alebo iná skratka pre označenie miesta.
     *                        Musí byť unikátna v rámci parkovacieho domu.
     * @param carTypeId       id typu auta
     * @return objekt entity parkovacieho miesta
     */
    public abstract Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId);

}
