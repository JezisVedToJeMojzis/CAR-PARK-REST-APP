package sk.stuba.fei.uim.vsa.pr2.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = ParkingSpot.TABLE_NAME)
@NamedQuery(name="ParkingSpot.findSpotsByCarParkId", query="SELECT c FROM ParkingSpot c WHERE c.carParkId.id = :carParkId")
@NamedQuery(name="ParkingSpot.findSpotsByCarParkIdAndSpotId", query="SELECT c FROM ParkingSpot c WHERE c.carParkId.id = :carParkId AND c.spotIdentifier = :spotIdentifier")
@NamedQuery(name="ParkingSpot.findSpotsByCarParkName", query="SELECT c FROM ParkingSpot c WHERE c.carParkId.name = :carParkName")
@NamedQuery(name="ParkingSpot.findSpotsById", query="SELECT c FROM ParkingSpot c WHERE c.id = :parkingSpotId")
@NamedQuery(name="ParkingSpot.findSpotsByFloorAndCarParkId", query="SELECT c FROM ParkingSpot c WHERE  c.carParkId.id = :carParkId AND c.floorIdentifier.floorIdentifier = :floorIdentifier")
@NamedQuery(name="ParkingSpot.findByType", query="SELECT c FROM ParkingSpot c WHERE c.carType = :carType")
public class ParkingSpot implements Serializable {
    public static final String TABLE_NAME = "PARKING_SPOT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(unique = true)
    private String spotIdentifier;

    @ManyToOne
    private CarParkFloor floorIdentifier;

    @ManyToOne
    private CarPark carParkId;

    @ManyToOne
    private CarType carType;


    public ParkingSpot() {

    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "id=" + id +
                ", spotIdentifier='" + spotIdentifier + '\'' +
                ", floorIdentifier=" + floorIdentifier +
                ", carParkId=" + carParkId +
                ", carType=" + carType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotIdentifier() {
        return spotIdentifier;
    }

    public void setSpotIdentifier(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }

    public CarParkFloor getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(CarParkFloor floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public CarPark getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(CarPark carParkId) {
        this.carParkId = carParkId;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public ParkingSpot(Long id, String spotIdentifier, CarParkFloor floorIdentifier, CarPark carParkId, CarType carType) {
        this.id = id;
        this.spotIdentifier = spotIdentifier;
        this.floorIdentifier = floorIdentifier;
        this.carParkId = carParkId;
        this.carType = carType;
    }
}
