package sk.stuba.fei.uim.vsa.pr2.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = Car.TABLE_NAME)
@NamedQuery(name="Car.getUsersCars", query="SELECT c FROM Car c WHERE c.userId.id = :userId")
@NamedQuery(name="Car.getCars", query="SELECT c FROM Car c WHERE c.vehicleRegistrationPlate= :vehicleRegistrationPlate")
@NamedQuery(name="Car.getJustCars", query="SELECT c FROM Car c")
@NamedQuery(name="Car.getCarsById", query="SELECT c FROM Car c WHERE c.id= :cardId")
@NamedQuery(name="Car.findByVehicleRegistrationPlate", query="SELECT c FROM Car c WHERE c.vehicleRegistrationPlate = :vehicleRegistrationPlate")
@NamedQuery(name="Car.findByType", query="SELECT c FROM Car c WHERE c.carType = :carType")
public class Car implements Serializable {
    public static final String TABLE_NAME= "CAR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    private String brand;

    private String model;
    private String colour;


   // @Column(unique=true,nullable = false)
    private String vehicleRegistrationPlate;

    @ManyToOne
    private CarType carType;

    public Car() {

    }

    public Car(Long id, User userId, String brand, String model, String colour, String vehicleRegistrationPlate, CarType carType) {
        this.id = id;
        this.userId = userId;
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", userId=" + userId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", vehicleRegistrationPlate='" + vehicleRegistrationPlate + '\'' +
                ", carType=" + carType +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
