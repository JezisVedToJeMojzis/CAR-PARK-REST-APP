package sk.stuba.fei.uim.vsa.pr2.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = CarPark.TABLE_NAME)
@NamedQuery(name="CarPark.findByName", query="SELECT c FROM CarPark c WHERE c.name = :carParkName")
@NamedQuery(name="CarPark.getCarParks", query="SELECT c FROM CarPark c")
@NamedQuery(name="CarPark.deleteCarPark", query="DELETE FROM CarPark c WHERE c.id = :carParkId")
public class CarPark implements Serializable {
    public static final String TABLE_NAME= "CAR_PARK";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(unique=true,nullable = false) unique nefunguje v CLI a nechapem preco (resp hadze error aj ked je osetreny)
    private String name;

    private String address;
    private Integer pricePerHour;

    @OneToMany
   // @JoinColumn(name = "CAR_PARK_ID_CAR_PARK_FLOOR_ID")
    private List<CarParkFloor> floors;

    public List<CarParkFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<CarParkFloor> floors) {
        this.floors = floors;
    }

    public CarPark() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public CarPark(Long id, String name, String address, Integer pricePerHour) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", pricePerHour=" + pricePerHour +
                '}';
    }
}
