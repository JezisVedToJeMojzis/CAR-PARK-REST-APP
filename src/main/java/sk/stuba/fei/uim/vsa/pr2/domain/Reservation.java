package sk.stuba.fei.uim.vsa.pr2.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = Reservation.TABLE_NAME)
@NamedQuery(name="Reservation.getReservations", query="SELECT c FROM Reservation c")
@NamedQuery(name="Reservation.getReservationsByParkingSpot", query="SELECT c FROM Reservation c WHERE c.parkingSpotId = :parkingSpotId")
@NamedQuery(name="Reservation.getReservationsByParkingSpotId", query="SELECT c FROM Reservation c WHERE c.parkingSpotId.id = :parkingSpotId")
@NamedQuery(name="Reservation.getReservationsByCar", query="SELECT c FROM Reservation c WHERE c.carId.id = :carId")
public class Reservation implements Serializable {
    public static final String TABLE_NAME= "RESERVATION";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ParkingSpot parkingSpotId;

    @OneToOne
    private Car carId;

    private Date startTime;
    private Date endTime;
    private Integer price;


    public Reservation(Long id, ParkingSpot parkingSpotId, Car carId, Date startTime, Date endTime, Integer price) {
        this.id = id;
        this.parkingSpotId = parkingSpotId;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public Reservation() {

    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", parkingSpotId=" + parkingSpotId +
                ", carId=" + carId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(ParkingSpot parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
