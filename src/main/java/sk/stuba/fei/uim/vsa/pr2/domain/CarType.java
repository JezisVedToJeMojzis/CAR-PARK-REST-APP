package sk.stuba.fei.uim.vsa.pr2.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = CarType.TABLE_NAME)
@NamedQuery(name="CarType.findByType", query="SELECT c FROM CarType c WHERE c.type= :name")
@NamedQuery(name="CarType.getCarTypes", query="SELECT c FROM CarType c")
public class CarType implements Serializable {
    public static final String TABLE_NAME= "CAR_TYPE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  @Column(unique=true,nullable = false)
    private String type;


    public CarType() {

    }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public CarType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
