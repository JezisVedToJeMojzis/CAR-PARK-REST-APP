package sk.stuba.fei.uim.vsa.pr2.service;

import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.service.AbstractCarParkService;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CarParkService extends AbstractCarParkService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    public CarPark saveCarPark(CarPark carPark) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> carparks = getCarParks();
        List<CarPark> carParkEntities = carparks.stream().map(element -> (CarPark)element).collect(Collectors.toList());
        for(CarPark cp : carParkEntities){
//            System.out.println(cp.getName()+ "1");
//            System.out.println(carPark.getName()+ "2");
            if(Objects.equals(cp.getName(), carPark.getName())){
//                System.out.println(cp.getName());
//                System.out.println(carPark.getName());
             //   em.close();
                return null;
            }
        }
        try {
//            System.out.println("null1");
            em.persist(carPark);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
      //  em.close();
        return carPark;
    }

    public CarParkFloor saveCarParkFloor(CarParkFloor carParkFloor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> carparkfloors = getCarParkFloors(carParkFloor.getCarParkId().getId());
        List<CarParkFloor> carParkFloorEntities = carparkfloors.stream().map(element -> (CarParkFloor)element).collect(Collectors.toList());
        for(CarParkFloor cpf : carParkFloorEntities){
            if(Objects.equals(cpf.getFloorIdentifier(), carParkFloor.getFloorIdentifier())){
                if(cpf.getCarParkId().getId() == carParkFloor.getCarParkId().getId()){
                    return null;
                }
            }
        }
        try {
            em.persist(carParkFloor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
     //   em.close();
        return carParkFloor;
    }

    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> parkingSpots = getParkingSpots(parkingSpot.getCarParkId().getId(),parkingSpot.getFloorIdentifier().getFloorIdentifier());
        List<ParkingSpot> parkingSpotEntities = parkingSpots.stream().map(element -> (ParkingSpot)element).collect(Collectors.toList());
        for(ParkingSpot ps : parkingSpotEntities){
            if(Objects.equals(ps.getFloorIdentifier().getFloorIdentifier(), parkingSpot.getFloorIdentifier().getFloorIdentifier())){
                if(ps.getCarParkId().getId() == parkingSpot.getCarParkId().getId()){
                    if(ps.getSpotIdentifier() == parkingSpot.getSpotIdentifier()) {
                        return null;
                    }
                }
            }
        }
        try {
            em.merge(parkingSpot);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        em.close();
        return parkingSpot;
    }

    public User saveUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> users = getUsers();
        List<User> userEntities = users.stream().map(element -> (User)element).collect(Collectors.toList());
        for(User us : userEntities){
            if(Objects.equals(us.getEmail(), user.getEmail())){
                return null;
            }
        }
        try {
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        //  em.close();
        return user;
    }

    public Car saveCar(Car car) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> cars = getCars();
        List<Car> carEntities = cars.stream().map(element -> (Car)element).collect(Collectors.toList());
        for(Car c : carEntities){
            if(Objects.equals(c.getVehicleRegistrationPlate(), car.getVehicleRegistrationPlate())){
                return null;
            }
        }
        try {
            em.persist(car);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        //  em.close();
        return car;
    }

    public Reservation saveRes(Reservation res) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> reservations = getReservations();
        List<Reservation> reservationEntities = reservations.stream().map(element -> (Reservation)element).collect(Collectors.toList());
        for(Reservation r : reservationEntities){
            if(Objects.equals(r.getParkingSpotId().getId(), res.getParkingSpotId().getId())){
                return null;
            }
            if(Objects.equals(r.getCarId().getId(), res.getCarId().getId())){
                return null;
            }
        }
        try {
            em.persist(res);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        }
        //  em.close();
        return res;
    }



    /**
     * Vytvorenie nového parkovacieho domu
     *
     * @param name         názov parkovacieho domu
     * @param address      adresa parkovacieho domu
     * @param pricePerHour cena za hodinu parkovania
     * @return objekt entity parkovacieho domu
     */
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        em.getTransaction().begin();

        CarPark carPark = new CarPark();

        carPark.setName(name);
        carPark.setAddress(address);
        carPark.setPricePerHour(pricePerHour);

        TypedQuery<CarPark> q = em.createNamedQuery("CarPark.findByName", CarPark.class);
        q.setParameter("carParkName", name);
        List<CarPark> names = q.getResultList();
        if (names.isEmpty()) {
            em.persist(carPark);
            em.getTransaction().commit();
            return carPark;

        }
        em.getTransaction().commit();
        return null;
    }

    /**
     * Získanie entity parkovacieho domu podľa ID
     *
     * @param carParkId id parkovacieho domu
     * @return objekt entity parkovacieho domu
     */
    @Override
    public Object getCarPark(Long carParkId) {
        em.getTransaction().begin();
        CarPark carPark = em.find(CarPark.class, carParkId);

        if (carPark == null) {
            em.getTransaction().commit();
            return null;
        }
        //  System.out.println(carPark);
        em.getTransaction().commit();
        return carPark;
    }

    /**
     * Získanie entity parkovacieho domu podľa názvu domu
     *
     * @param carParkName názov parkovacieho domu
     * @return objekt entity parkovacieho domu
     */
    @Override
    public Object getCarPark(String carParkName) {
        em.getTransaction().begin();
        TypedQuery<CarPark> q = em.createNamedQuery("CarPark.findByName", CarPark.class);
        q.setParameter("carParkName", carParkName);
        List<CarPark> carPark = q.getResultList();
        if (carPark.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        em.getTransaction().commit();
        return carPark;
    }

    /**
     * Získanie zoznamu všetkých parkovacích domov
     *
     * @return zoznam entít parkovacích domov
     */
    @Override
    public List<Object> getCarParks() {
        em.getTransaction().begin();
        TypedQuery<CarPark> q = em.createNamedQuery("CarPark.getCarParks", CarPark.class);
        List<CarPark> carParks = q.getResultList();
        if (carParks.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        //  List<Object> objectCarParks = Collections.singletonList(carParks);
        //    System.out.println(Arrays.asList(carParks.toArray());
        em.getTransaction().commit();
        return Arrays.asList(carParks.toArray());
    }

    /**
     * Vymazanie parkovacieho domu podľa id
     *
     * @param carParkId id parkovacieho domu
     * @return objekt vymazaného parkovacieho domu
     */
    @Override
    public Object deleteCarPark(Long carParkId) {
        em.getTransaction().begin();

        CarPark toDeleteCarPark = em.find(CarPark.class, carParkId);

        if (toDeleteCarPark == null) {
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        List<CarParkFloor> deleteFloors = q.getResultList();

        for (CarParkFloor floor : deleteFloors) {//delete its spots to park
            em.getTransaction().commit();
            deleteCarParkFloor(carParkId, floor.getFloorIdentifier());
            em.getTransaction().begin();
        }

        em.remove(toDeleteCarPark);
        //  System.out.println(toDeleteCarPark);
        em.getTransaction().commit();

        return toDeleteCarPark;
    }

    // Poschodia parkovacieho domu

    /**
     * Vytvorenie poschodia parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia. Môže byť číslo podlažia, alebo iná skratka pre poschodie.
     *                        Musí byť unikátna v rámci parkovacieho domu.
     * @return objekt entity poschodia
     */

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        em.getTransaction().begin();
        CarPark carPark = em.find(CarPark.class, carParkId);
        if (carPark == null) {
            em.getTransaction().commit();
            return null;
        }
        CarParkFloor carParkFloor = new CarParkFloor();

        carParkFloor.setFloorIdentifier(floorIdentifier);
        carParkFloor.setCarParkId(carPark);

        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByFloorAndCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<CarParkFloor> floors = q.getResultList();
        if (floors.isEmpty()) {
            em.persist(carParkFloor);
            em.getTransaction().commit();
            //System.out.println("OK");
            return carParkFloor;
        }
        em.getTransaction().commit();
        return null;

    }

    /**
     * Získanie entity poschodia parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @return objekt entity poschodia
     */
    @Override
    public Object getCarParkFloor(Long carParkId, String floorIdentifier) {
        em.getTransaction().begin();
        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByFloorAndCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<CarParkFloor> floor = q.getResultList();
        if (floor.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        //   System.out.println(floor);
        em.getTransaction().commit();

        return floor;
    }

    /**
     * Získanie zoznamu entít všetkých poschodí v parkovacom dome
     *
     * @param carParkId id parkovacieho domu
     * @return zoznam entít poschodí
     */

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        em.getTransaction().begin();
        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.getCarParkFloors", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        List<CarParkFloor> floors = q.getResultList();
        if (floors.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        // List<Object> objectCarParks = Collections.singletonList(floors);
        //  System.out.println(Arrays.asList(carParks.toArray());
        em.getTransaction().commit();
        return Arrays.asList(floors.toArray());
    }

    @Override
    public Object deleteCarParkFloor(Long carParkId, String floorIdentifier) {
        em.getTransaction().begin();

        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByFloorAndCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<CarParkFloor> returnFloor = q.getResultList();
        if (returnFloor.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<ParkingSpot> q2 = em.createNamedQuery("ParkingSpot.findSpotsByFloorAndCarParkId", ParkingSpot.class);
        q2.setParameter("carParkId", carParkId);
        q2.setParameter("floorIdentifier", floorIdentifier);
        List<ParkingSpot> spots = q2.getResultList();

        for (ParkingSpot spot : spots) {//delete its spots to park
            em.getTransaction().commit();
            deleteParkingSpot(spot.getId());
            em.getTransaction().begin();
        }

        TypedQuery<CarParkFloor> q1 = em.createNamedQuery("CarParkFloor.deleteCarParkFloor", CarParkFloor.class);
        q1.setParameter("carParkId", carParkId);
        q1.setParameter("floorIdentifier", floorIdentifier);
        q1.executeUpdate();


        // System.out.println(returnFloor);
        em.getTransaction().commit();

        return returnFloor;
    }

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
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        em.getTransaction().begin();
        CarPark carPark = em.find(CarPark.class, carParkId);
        if (carPark == null) {
            //System.out.println("NULL1");
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<ParkingSpot> g = em.createNamedQuery("ParkingSpot.findSpotsByCarParkIdAndSpotId", ParkingSpot.class);
        g.setParameter("carParkId", carParkId);
        g.setParameter("spotIdentifier", spotIdentifier);
        List<ParkingSpot> spots = g.getResultList();


        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByFloorAndCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<CarParkFloor> floor = q.getResultList();
        if (floor.isEmpty()) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        ParkingSpot parkingSpot = new ParkingSpot();

        //type
        TypedQuery<CarType> q1 = em.createNamedQuery("CarType.getCarTypes", CarType.class);
        List<CarType> types = q1.getResultList();


        int typeExists = 0;
        for (CarType type : types) {
            if (type.getType().equals("Petrol")) {
                parkingSpot.setCarType(type); //default value for type of car
                typeExists = 1;
            }
        }
        if (typeExists == 0) {
            em.getTransaction().commit();
            createCarType("Petrol");
            em.getTransaction().begin();
            TypedQuery<CarType> q2 = em.createNamedQuery("CarType.getCarTypes", CarType.class);
            List<CarType> typess = q2.getResultList();
            for (CarType type1 : typess) {
                if (type1.getType() == "Petrol") {
                    parkingSpot.setCarType(type1); //default value for type of car
                    typeExists = 1;
                }
            }
        }

        parkingSpot.setSpotIdentifier(spotIdentifier);
        parkingSpot.setCarParkId(carPark);
        for (CarParkFloor x : floor) {
            parkingSpot.setFloorIdentifier(x);
        }


        if(spots.isEmpty()){
            for (CarParkFloor x : floor) {
                parkingSpot.setFloorIdentifier(x);
            }
            em.persist(parkingSpot);
            em.getTransaction().commit();
            //System.out.println(parkingSpot);
            return parkingSpot;
        }

        em.getTransaction().commit();
        return null;

    }

    /**
     * Získanie parkovacieho miesta
     *
     * @param parkingSpotId id parkovacieho miesta
     * @return objekt entity parkovacieho miesta
     */
    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        em.getTransaction().begin();
        ParkingSpot spot = em.find(ParkingSpot.class, parkingSpotId);

        if (spot == null) {
            em.getTransaction().commit();
            return null;
        }
        //  System.out.println(spot);
        em.getTransaction().commit();
        return spot;
    }

    /**
     * Získanie zoznamu parkovacích miest na poschodí parkovacieho domu
     *
     * @param carParkId       id parkovacieho domu
     * @param floorIdentifier identifikátor poschodia
     * @return object entity parkovacieho miesta
     */
    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        em.getTransaction().begin();
        TypedQuery<ParkingSpot> q = em.createNamedQuery("ParkingSpot.findSpotsByFloorAndCarParkId", ParkingSpot.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<ParkingSpot> spots = q.getResultList();
        if (spots.isEmpty()) {
            em.getTransaction().commit();
            // System.out.println("NULL");
            return null;
        }
        // List<Object> objectSpots = Collections.singletonList(spots);
        //System.out.println(Arrays.asList(carParks.toArray());
        em.getTransaction().commit();
        return Arrays.asList(spots.toArray());

    }

    /**
     * Získanie zoznamu parkovacích miest v parkovacom dome
     *
     * @param carParkId id parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam parkovacích miest na danom poschodí.
     */
    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        em.getTransaction().begin();

        Map<String, List<Object>> mSpots = new HashMap<>();

        TypedQuery<CarParkFloor> q1 = em.createNamedQuery("CarParkFloor.findByCarParkId", CarParkFloor.class);
        q1.setParameter("carParkId", carParkId);
        List<CarParkFloor> floors = q1.getResultList();

        TypedQuery<ParkingSpot> q2 = em.createNamedQuery("ParkingSpot.findSpotsByCarParkId", ParkingSpot.class);
        q2.setParameter("carParkId", carParkId);
        List<ParkingSpot> spots = q2.getResultList();


        List<ParkingSpot> spotsOfFloor = new ArrayList<ParkingSpot>();

        for (CarParkFloor floor : floors) {
            for (ParkingSpot spot : spots) {
                if (spot.getFloorIdentifier() == floor) {
                    spotsOfFloor.add(spot);

                }
            }
            mSpots.put(floor.getFloorIdentifier(), Arrays.asList(spotsOfFloor.toArray()));
        }

        em.getTransaction().commit();
        return mSpots;
    }


    /**
     * Získanie zoznamu parkovacích miest, ktoré sú dostupné, t.j. nie je na nich zaparkované auto.
     *
     * @param carParkName názov parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam voľných parkovacích miest na danom poschodí.
     */
    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        em.getTransaction().begin();

        Map<String, List<Object>> mSpots = new HashMap<>();

        TypedQuery<CarParkFloor> q1 = em.createNamedQuery("CarParkFloor.findByCarParkName", CarParkFloor.class);
        q1.setParameter("carParkName", carParkName);
        List<CarParkFloor> floors = q1.getResultList();

        TypedQuery<ParkingSpot> q2 = em.createNamedQuery("ParkingSpot.findSpotsByCarParkName", ParkingSpot.class);
        q2.setParameter("carParkName", carParkName);
        List<ParkingSpot> spots = q2.getResultList();


        List<ParkingSpot> spotsOfFloor = new ArrayList<ParkingSpot>();

        for (CarParkFloor floor : floors) {
            for (ParkingSpot spot : spots) {
                if (spot.getFloorIdentifier() == floor) {
                    TypedQuery<Reservation> q3 = em.createNamedQuery("Reservation.getReservationsByParkingSpot", Reservation.class);
                    q3.setParameter("parkingSpotId", spot);
                    List<Reservation> reservations = q3.getResultList();

                    for (Reservation reservation : reservations) {
                        if (reservation.getParkingSpotId() == spot && reservation.getEndTime() != null) {
                            spotsOfFloor.add(spot);
                        }
                        if (reservations.stream().anyMatch(s -> s.getParkingSpotId().equals(spot))) {
                            System.out.println(spot);
                        }
                    }


                    TypedQuery<Reservation> q4 = em.createNamedQuery("Reservation.getReservationsByParkingSpot", Reservation.class);
                    q4.setParameter("parkingSpotId", spot);
                    List<Reservation> reservationss = q4.getResultList();

                    for (Reservation reservationn : reservationss) {
                        if (reservationss.stream().noneMatch(s -> s.getParkingSpotId().equals(spot))) {
                            System.out.println(spot);
                        }
                    }


                }
            }
            mSpots.put(floor.getFloorIdentifier(), Arrays.asList(spotsOfFloor.toArray()));
        }

        if (mSpots.isEmpty()) {
            return null;
        }
        em.getTransaction().commit();
        return mSpots;
    }

    /**
     * Získanie zoznamu parkovacích miest, ktoré sú obsadené, t.j. je na nich zaparkované auto.
     *
     * @param carParkName názov parkovacieho domu
     * @return zoznam parkovacích miest. Kľúč mapy je identifikátor poschodia a hodnota je zoznam obsadených parkovacích miest na danom poschodí.
     */
    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        em.getTransaction().begin();

        Map<String, List<Object>> mSpots = new HashMap<>();

        TypedQuery<CarParkFloor> q1 = em.createNamedQuery("CarParkFloor.findByCarParkName", CarParkFloor.class);
        q1.setParameter("carParkName", carParkName);
        List<CarParkFloor> floors = q1.getResultList();

        TypedQuery<ParkingSpot> q2 = em.createNamedQuery("ParkingSpot.findSpotsByCarParkName", ParkingSpot.class);
        q2.setParameter("carParkName", carParkName);
        List<ParkingSpot> spots = q2.getResultList();


        List<ParkingSpot> spotsOfFloor = new ArrayList<ParkingSpot>();

        for (CarParkFloor floor : floors) {
            for (ParkingSpot spot : spots) {
                if (spot.getFloorIdentifier() == floor) {
                    TypedQuery<Reservation> q3 = em.createNamedQuery("Reservation.getReservationsByParkingSpot", Reservation.class);
                    q3.setParameter("parkingSpotId", spot);
                    List<Reservation> reservations = q3.getResultList();
                    for (Reservation reservation : reservations) {
                        if (reservations.isEmpty() != true && reservation.getEndTime() == null) {
                            spotsOfFloor.add(spot);
                        }
                    }
                }
            }
            mSpots.put(floor.getFloorIdentifier(), Arrays.asList(spotsOfFloor.toArray()));
        }
        if (mSpots.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return mSpots;
    }

    /**
     * Vymazanie parkovacieho miesta
     *
     * @param parkingSpotId id parkovacieho miesta
     * @return vymazané parkovacie miesto
     */
    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        em.getTransaction().begin();

        ParkingSpot toDeleteParkingSpot = em.find(ParkingSpot.class, parkingSpotId);
        if (toDeleteParkingSpot == null) {
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<Reservation> q1 = em.createNamedQuery("Reservation.getReservationsByParkingSpot", Reservation.class);
        q1.setParameter("parkingSpotId", toDeleteParkingSpot);
        List<Reservation> reservations = q1.getResultList();

        for (Reservation reservation : reservations) {//end reservations
            em.getTransaction().commit();
            endReservation(reservation.getId());
            em.getTransaction().begin();
            reservation.setParkingSpotId(null);
        }

        em.remove(toDeleteParkingSpot);
        em.getTransaction().commit();
        return toDeleteParkingSpot;
    }

    @Override
    public Object updateParkingSpot(Long parkingSpotId, Long carParkId, Long floorIdentifierId, String spotIdentifier, Long carTypeId) {
        em.getTransaction().begin();

        ParkingSpot parkingSpot = em.find(ParkingSpot.class, parkingSpotId);

        CarPark carPark = em.find(CarPark.class, carParkId);

        CarType carType = em.find(CarType.class, carTypeId);

        CarParkFloor carParkFloor = em.find(CarParkFloor.class, floorIdentifierId);

        if (parkingSpot == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        if (floorIdentifierId == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        if (carPark == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        if (carType == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        parkingSpot.setCarParkId(carPark);
        parkingSpot.setFloorIdentifier(carParkFloor);
        parkingSpot.setSpotIdentifier(spotIdentifier);
        parkingSpot.setSpotIdentifier(spotIdentifier);
        parkingSpot.setCarType(carType);

        em.getTransaction().commit();
        //System.out.println(car);
        return parkingSpot;
    }

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
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user == null) {
            //  System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        Car car = new Car();

        TypedQuery<CarType> q1 = em.createNamedQuery("CarType.getCarTypes", CarType.class);
        List<CarType> types = q1.getResultList();

        int typeExists = 0;
        for (CarType type : types) {
            if (type.getType() == "Petrol") {
                car.setCarType(type); //default value for type of car
                typeExists = 1;
            }
        }
        if (typeExists == 0) {
            em.getTransaction().commit();
            createCarType("Petrol");
            em.getTransaction().begin();
            TypedQuery<CarType> q2 = em.createNamedQuery("CarType.getCarTypes", CarType.class);
            List<CarType> typess = q2.getResultList();
            for (CarType type1 : typess) {
                if (type1.getType() == "Petrol") {
                    car.setCarType(type1); //default value for type of car
                    typeExists = 1;
                }
            }
        }
        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
        car.setUserId(user);


        TypedQuery<Car> q = em.createNamedQuery("Car.getCars", Car.class);
        // q.setParameter("userId", userId);
        q.setParameter("vehicleRegistrationPlate", vehicleRegistrationPlate);
        List<Car> cars = q.getResultList();
        if (cars.isEmpty()) {
            em.persist(car);
            em.getTransaction().commit();
            //System.out.println(car);
            return car;
        }


        em.getTransaction().commit();
        //System.out.println(car);
        return null;
    }

    /**
     * Získanie entity auta podľa id
     *
     * @param carId id auta
     * @return objekt entity auta
     */
    @Override
    public Object getCar(Long carId) {
        em.getTransaction().begin();
        Car car = em.find(Car.class, carId);

        if (car == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        //  System.out.println(car);
        em.getTransaction().commit();
        return car;
    }

    @Override
    public Object updateCar(Long carId, Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        em.getTransaction().begin();

        Car car = em.find(Car.class, carId);

        User user = em.find(User.class, userId);

        CarType carType = em.find(CarType.class, carTypeId);

        if (car == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        if (user == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        if (carType == null) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        car.setUserId(user);
        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
        car.setCarType(carType);

        em.getTransaction().commit();
        //System.out.println(car);
        return car;
    }

    /**
     * Získanie entity auta podľa EČV
     *
     * @param vehicleRegistrationPlate evidenčné číslo vozidla
     * @return objekt entity auta
     */
    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        em.getTransaction().begin();
        TypedQuery<Car> q = em.createNamedQuery("Car.findByVehicleRegistrationPlate", Car.class);
        q.setParameter("vehicleRegistrationPlate", vehicleRegistrationPlate);
        List<Car> car = q.getResultList();
        if (car.isEmpty()) {
            // System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        // System.out.println(car);
        em.getTransaction().commit();

        return car;
    }

    @Override
    public List<Object> getCars(Long userId) {
        em.getTransaction().begin();
        TypedQuery<Car> q = em.createNamedQuery("Car.getUsersCars", Car.class);
        q.setParameter("userId", userId);
        List<Car> cars = q.getResultList();
        if (cars.isEmpty()) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        // List<Object> objectCarParks = Collections.singletonList(floors);
        //System.out.println(Arrays.asList(cars.toArray()));
        em.getTransaction().commit();
        return Arrays.asList(cars.toArray());
    }


    public List<Object> getCars() {
        em.getTransaction().begin();
        TypedQuery<Car> q = em.createNamedQuery("Car.getJustCars", Car.class);
        List<Car> cars = q.getResultList();
        if (cars.isEmpty()) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        // List<Object> objectCarParks = Collections.singletonList(floors);
        //System.out.println(Arrays.asList(cars.toArray()));
        em.getTransaction().commit();
        return Arrays.asList(cars.toArray());
    }

    /**
     * Vymazanie auta
     *
     * @param carId id auta
     * @return vymazaná entita auta
     */
    @Override
    public Object deleteCar(Long carId) {
        em.getTransaction().begin();

        Car toDeleteCar = em.find(Car.class, carId);

        if (toDeleteCar == null) {
            em.getTransaction().commit();
            return null;
        }
        TypedQuery<Reservation> q1 = em.createNamedQuery("Reservation.getReservationsByCar", Reservation.class);
        q1.setParameter("carId", toDeleteCar);
        List<Reservation> reservations = q1.getResultList();
        if (reservations.isEmpty()) {
            em.remove(toDeleteCar);
            em.getTransaction().commit();
            return null;
        }

        for (Reservation reservation : reservations) {//end reservations
            em.getTransaction().commit();
            endReservation(reservation.getId());
            em.getTransaction().begin();
            reservation.setCarId(null);
        }


        em.remove(toDeleteCar);
        em.getTransaction().commit();
        // System.out.println(toDeleteCar);
        return toDeleteCar;
    }

    /**
     * Vytvorenie používateľa / zákazníka
     *
     * @param firstname krstné meno
     * @param lastname  priezvisko
     * @param email     emailová adresa. Musí byť unikátna
     * @return objekt entity používateľa
     */
    @Override
    public Object createUser(String firstname, String lastname, String email) {
        em.getTransaction().begin();

        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        TypedQuery<User> q = em.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("email", email);
        List<User> users = q.getResultList();
        if (users.isEmpty()) {
            em.persist(user);
            em.getTransaction().commit();
            //System.out.println(user);
            return user;
        }

        em.getTransaction().commit();

        return null;
    }

    /**
     * Získanie používateľa podľa id
     *
     * @param userId id používateľa
     * @return objekt entity používateľa
     */
    @Override
    public Object getUser(Long userId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);

        if (user == null) {
            em.getTransaction().commit();
            //System.out.println("NULL");
            return null;
        }
        //System.out.println(user);
        em.getTransaction().commit();
        return user;
    }

    /**
     * Získanie používateľa podľa emailovej adresy
     *
     * @param email emailová adresa používateľa
     * @return objekt entity používateľa
     */
    @Override
    public Object getUser(String email) {
        em.getTransaction().begin();
        TypedQuery<User> q = em.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("email", email);
        List<User> user = q.getResultList();

        if (user.isEmpty()) {
            em.getTransaction().commit();
            //System.out.println("NULL");
            return null;
        }
        //System.out.println(user);
        em.getTransaction().commit();
        return user;
    }

    /**
     * Získanie zoznamu všetkých používateľov
     *
     * @return zoznam entít používateľov
     */
    @Override
    public List<Object> getUsers() {
        em.getTransaction().begin();
        TypedQuery<User> q = em.createNamedQuery("User.getUsers", User.class);
        List<User> users = q.getResultList();
        if (users.isEmpty()) {
            //System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }
        //List<Object> objectUsers = Collections.singletonList(users);
        //System.out.println(Arrays.asList(users.toArray()));
        em.getTransaction().commit();
        return Arrays.asList(users.toArray());
    }

    @Override
    public Object deleteUser(Long userId) {
        em.getTransaction().begin();

        User toDeleteUser = em.find(User.class, userId);

        if (toDeleteUser == null) {
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<Car> q1 = em.createNamedQuery("Car.getUsersCars", Car.class);
        q1.setParameter("userId", userId);
        List<Car> cars = q1.getResultList();
        for (Car car : cars) {//delete users car
            em.remove(car);
        }

        em.remove(toDeleteUser);

        em.getTransaction().commit();
        //  System.out.println(toDeleteUser);
        return toDeleteUser;
    }


    /**
     * Vytvorenie rezervácie pre zaparkované auto. Pri vytvorení rezervácie je do nej zapísaný dátum a čas začatia rezervácie.
     *
     * @param parkingSpotId id parkovacieho miesta
     * @param carId         id auta
     * @return objekt rezervácie
     */
    @Override
    public Object createReservation(Long parkingSpotId, Long carId) {
        em.getTransaction().begin();

        Date date = new Date();

        ParkingSpot parkingSpot = em.find(ParkingSpot.class, parkingSpotId);
        Car car = em.find(Car.class, carId);

        if (car == null || parkingSpot == null) {
            // System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        if (parkingSpot.getCarType() != car.getCarType()) { //car cant park on different carType of parkingSpot
            // System.out.println("Kde parkuješ??");
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservations", Reservation.class);
        List<Reservation> reservations = q.getResultList();
        for (Reservation res : reservations) {
            if (res.getEndTime() == null && res.getCarId() == car || res.getEndTime() == null && res.getParkingSpotId() == parkingSpot) {
                // System.out.println("NULL");
                em.getTransaction().commit();
                return null;
            }
        }
        Reservation reservation = new Reservation();

        reservation.setParkingSpotId(parkingSpot);
        reservation.setCarId(car);
        reservation.setStartTime(date);
        reservation.setPrice(null);
        reservation.setEndTime(null);


        em.persist(reservation);
        em.getTransaction().commit();
        //System.out.println(reservation);
        return reservation;
    }

    /**
     * Ukončenie rezervácie / parkovanie auta. Pri ukončení parkovania je zapísaný čas ukončenia rezervácie a vypočítaná celková cena za parkovanie.
     *
     * @param reservationId id rezervácie
     * @return objekt entity rezervácie
     */
    @Override
    public Object endReservation(Long reservationId) {
        em.getTransaction().begin();

        Date dateEnd = new Date();

        Reservation reservation = em.find(Reservation.class, reservationId);

        if (reservation == null) {
            em.getTransaction().commit();
            return null;
        }
        if (reservation.getEndTime() != null) {
            em.getTransaction().commit();
            return null;
        }

        reservation.setEndTime(dateEnd);

        long startTime = reservation.getStartTime().getTime();//miliseconds from 1970 january 1st until now
        long endTime = reservation.getEndTime().getTime(); //miliseconds from 1970 january 1st until now

        long totalTime = endTime - startTime;
        double totalTimeDouble = (double) totalTime;

        totalTimeDouble = totalTimeDouble / 1000; //seconds
        totalTimeDouble = totalTimeDouble / 60; //minutes

        int pricePerHour = reservation.getParkingSpotId().getCarParkId().getPricePerHour();
        int totalPrice;

        if (totalTimeDouble < 60 && totalTimeDouble != 0) {
            totalPrice = pricePerHour;
        } else {
            int totalTimeInt = (int) totalTimeDouble;
            totalPrice = pricePerHour * (totalTimeInt / 60) + pricePerHour;
        }

        reservation.setPrice(totalPrice);
        // System.out.println(reservation);
        em.getTransaction().commit();
        return reservation;
    }

    /**
     * Získanie zoznamu všetkých rezervácií pre parkovacieho miesto začaté v daný deň.
     *
     * @param parkingSpotId id parkovacieho miesta
     * @param date          dátum rezervácii
     * @return zoznam entít rezervácií
     */
    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        em.getTransaction().begin();

        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");

        ParkingSpot spot = em.find(ParkingSpot.class, parkingSpotId);

        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservationsByParkingSpot", Reservation.class);
        q.setParameter("parkingSpotId", spot);
        List<Reservation> reservations = q.getResultList();
        if (reservations.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        List<Reservation> reservationsOfTheDay = new ArrayList<Reservation>();

        for (Reservation reservation : reservations) {
            if (ft.format(date).equals(ft.format(reservation.getStartTime()))) {
                reservationsOfTheDay.add(reservation);
            }
        }
        if (reservationsOfTheDay.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        // System.out.println(Arrays.asList(reservationsOfTheDay.toArray()));
        return Arrays.asList(reservationsOfTheDay.toArray());
    }


    public List<Object> getReservations() {
        em.getTransaction().begin();

        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");


        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservations", Reservation.class);
        List<Reservation> reservations = q.getResultList();
        if (reservations.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        em.getTransaction().commit();
        return Arrays.asList(reservations.toArray());
    }

    public Object getReservationById(Long id) {
        em.getTransaction().begin();
        Reservation res = em.find(Reservation.class, id);

        if (res == null) {
            em.getTransaction().commit();
            return null;
        }

        em.getTransaction().commit();
        return res;
    }

    public List<Object> getReservationByCarId(Long id) {
        em.getTransaction().begin();

        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservationsByCar", Reservation.class);
        q.setParameter("carId", id);
        List<Reservation> reservations = q.getResultList();

        if (reservations.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        em.getTransaction().commit();
        return Arrays.asList(reservations.toArray());
    }



    public List<Object> getReservationBySpotId(Long id) {
        em.getTransaction().begin();

        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservationsByParkingSpotId", Reservation.class);
        q.setParameter("parkingSpotId", id);
        List<Reservation> reservations = q.getResultList();

        if (reservations.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        em.getTransaction().commit();
        return Arrays.asList(reservations.toArray());
    }

    /**
     * Získanie zoznamu aktívnych / neukončených rezervácií pre daného používateľa.
     *
     * @param userId id používateľa
     * @return zoznam entít rezervácií
     */
    @Override
    public List<Object> getMyReservations(Long userId) {
        em.getTransaction().begin();

        TypedQuery<Reservation> q = em.createNamedQuery("Reservation.getReservations", Reservation.class);
        List<Reservation> reservations = q.getResultList();
        if (reservations.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }

        User user = em.find(User.class, userId);
        if (user == null) {
            em.getTransaction().commit();
            return null;
        }

        List<Reservation> usersReservations = new ArrayList<Reservation>();

        for (Reservation reservation : reservations) {
            if (reservation.getCarId().getUserId() == user) {
                usersReservations.add(reservation);
            }
        }

        em.getTransaction().commit();
        // System.out.println(Arrays.asList(usersReservations.toArray()));
        return Arrays.asList(usersReservations.toArray());
    }

    // Skupina B

    /**
     * Vytvorenie typu auta
     *
     * @param name názov typu auta
     * @return objekt entity typu auta
     */
    @Override
    public Object createCarType(String name) {
        em.getTransaction().begin();

        CarType carType = new CarType();

        carType.setType(name);

        TypedQuery<CarType> q = em.createNamedQuery("CarType.findByType", CarType.class);
        q.setParameter("name", name);
        List<CarType> types = q.getResultList();

        for (CarType typee : types) {
            if (Objects.equals(typee.getType(),carType.getType())) {
                em.getTransaction().commit();
                //  System.out.println("NULL");
                return null;
            }
        }

        em.persist(carType);
        em.getTransaction().commit();
        return carType;
    }

    /**
     * Získanie zoznamu všetkých typov áut
     *
     * @return zoznam entít typov áut
     */
    @Override
    public List<Object> getCarTypes() {
        em.getTransaction().begin();
        TypedQuery<CarType> q = em.createNamedQuery("CarType.getCarTypes", CarType.class);
        List<CarType> carTypes = q.getResultList();
        if (carTypes.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        // System.out.println(Arrays.asList(carTypes.toArray()));
        em.getTransaction().commit();
        return Arrays.asList(carTypes.toArray());
    }


    @Override
    public Object getCarType(Long carTypeId) {
        em.getTransaction().begin();

        CarType carType = em.find(CarType.class, carTypeId);
        if (carType == null) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return carType;
    }

    @Override
    public Object getCarType(String name) {
        em.getTransaction().begin();

        TypedQuery<CarType> q1 = em.createNamedQuery("CarType.findByType", CarType.class);
        q1.setParameter("name", name);
        List<CarType> type = q1.getResultList();

        if (type.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return type;
    }


    /**
     * Vymazanie typu auta
     *
     * @param carTypeId id typu auta
     * @return vymazaná entita typu auta
     */
    @Override
    public Object deleteCarType(Long carTypeId) {
        em.getTransaction().begin();

        CarType toDeleteCarType = em.find(CarType.class, carTypeId);

        if (toDeleteCarType == null) {
            em.getTransaction().commit();
            //  System.out.println("NULL");
            return null;
        }

        if (toDeleteCarType.getType() == "Petrol") { //impossible to delete default car type
            em.getTransaction().commit();
            //   System.out.println("NULL1");
            return null;
        }

        TypedQuery<Car> q1 = em.createNamedQuery("Car.findByType", Car.class);
        q1.setParameter("carType", toDeleteCarType);
        List<Car> cars = q1.getResultList();


        TypedQuery<ParkingSpot> q2 = em.createNamedQuery("ParkingSpot.findByType", ParkingSpot.class);
        q2.setParameter("carType", toDeleteCarType);
        List<ParkingSpot> spots = q2.getResultList();


        TypedQuery<CarType> q = em.createNamedQuery("CarType.findByType", CarType.class);
        q.setParameter("name", "Petrol");
        List<CarType> petrolCar = q.getResultList();


        for (ParkingSpot spot : spots) {
            for (CarType type : petrolCar) {
                spot.setCarType(type);
            }
        }

        for (Car car : cars) {
            for (CarType type : petrolCar) {
                car.setCarType(type);
            }
        }

        em.remove(toDeleteCarType);
        em.getTransaction().commit();
        return toDeleteCarType;
    }

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
    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate, Long carTypeId) {
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        if (user == null) {
            //  System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        CarType carType = em.find(CarType.class, carTypeId);
        if (carType == null) {
            //  System.out.println("NULL");
            em.getTransaction().commit();
            return null;
        }

        Car car = new Car();

        car.setBrand(brand);
        car.setModel(model);
        car.setColour(colour);
        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
        car.setUserId(user);
        car.setCarType(carType);

        TypedQuery<Car> q = em.createNamedQuery("Car.getCars", Car.class);
        // q.setParameter("userId", userId);
        q.setParameter("vehicleRegistrationPlate", vehicleRegistrationPlate);
        List<Car> cars = q.getResultList();
        if (cars.isEmpty()) {
            em.persist(car);
            em.getTransaction().commit();
            //System.out.println(car);
            return car;
        }


                em.getTransaction().commit();
                return null;

    }

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
    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier, Long carTypeId){
        em.getTransaction().begin();
        CarPark carPark = em.find(CarPark.class, carParkId);
        if(carPark == null){
           //   System.out.println("N1ULL");
            em.getTransaction().commit();
            return null;
        }
        CarType carType = em.find(CarType.class, carTypeId);
        if(carType == null){
           //   System.out.println("NUL2L");
            em.getTransaction().commit();
            return null;
        }

        TypedQuery<ParkingSpot> g = em.createNamedQuery("ParkingSpot.findSpotsByCarParkIdAndSpotId", ParkingSpot.class);
        g.setParameter("carParkId", carParkId);
        g.setParameter("spotIdentifier", spotIdentifier);
        List<ParkingSpot> spots = g.getResultList();


        TypedQuery<CarParkFloor> q = em.createNamedQuery("CarParkFloor.findByFloorAndCarParkId", CarParkFloor.class);
        q.setParameter("carParkId", carParkId);
        q.setParameter("floorIdentifier", floorIdentifier);
        List<CarParkFloor> floor = q.getResultList();
        if(floor.isEmpty()){
           // System.out.println("NULL2");
            em.getTransaction().commit();
            return null;
        }

        ParkingSpot parkingSpot = new ParkingSpot();

        parkingSpot.setSpotIdentifier(spotIdentifier);
        parkingSpot.setCarParkId(carPark);
        parkingSpot.setCarType(carType);
        //spot.getCarParkId() == parkingSpot.getCarParkId() &&
        if(spots.isEmpty()){
            for(CarParkFloor x : floor){
                parkingSpot.setFloorIdentifier(x);
            }

            em.persist(parkingSpot);
            em.getTransaction().commit();
            //System.out.println(parkingSpot);
            return parkingSpot;
        }

        em.getTransaction().commit();
        return null;


    }
}
