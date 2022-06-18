package sk.stuba.fei.uim.vsa.pr2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = CarParkFloor.TABLE_NAME)
@NamedQuery(name="CarParkFloor.findByFloorAndCarParkId", query="SELECT c FROM CarParkFloor c WHERE c.floorIdentifier = :floorIdentifier AND c.carParkId.id = :carParkId")
@NamedQuery(name="CarParkFloor.findByCarParkId", query="SELECT c FROM CarParkFloor c WHERE  c.carParkId.id = :carParkId")
@NamedQuery(name="CarParkFloor.getCarParkFloors", query="SELECT c FROM CarParkFloor c WHERE c.carParkId.id = :carParkId")
@NamedQuery(name="CarParkFloor.deleteCarParkFloor", query="DELETE FROM CarParkFloor c WHERE c.floorIdentifier = :floorIdentifier AND c.carParkId.id = :carParkId")
@NamedQuery(name="CarParkFloor.findByCarParkName", query="SELECT c FROM CarParkFloor c WHERE  c.carParkId.name = :carParkName")
public class CarParkFloor implements Serializable {
    public static final String TABLE_NAME = "CAR_PARK_FLOOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Column(unique=true,nullable = false)
    private String floorIdentifier;

    @ManyToOne
    private CarPark carParkId;

    @OneToMany
    private List<ParkingSpot> spots;

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarPark getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(CarPark carParkId) {
        this.carParkId = carParkId;
    }

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public CarParkFloor(Long id, String floorIdentifier, CarPark carParkId) {
        this.id = id;
        this.floorIdentifier = floorIdentifier;
        this.carParkId = carParkId;
    }

    @Override
    public String toString() {
        return "CarParkFloor{" +
                "id=" + id +
                ", floorIdentifier='" + floorIdentifier + '\'' +
                ", carParkId=" + carParkId +
                '}';
    }

    public CarParkFloor() {

    }
}
