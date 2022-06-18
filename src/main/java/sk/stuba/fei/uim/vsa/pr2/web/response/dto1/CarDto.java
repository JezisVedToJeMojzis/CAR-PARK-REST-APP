package sk.stuba.fei.uim.vsa.pr2.web.response.dto1;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CDto;

import java.util.List;

public class CarDto extends CDto {

    private Long id;
    private String brand;
    private String model;
    private String vrp;
    private String colour;
    private Long type;
    private Long owner;
    private List<ReservationDto> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getVrp() {
        return vrp;
    }

    public void setVrp(String vrp) {
        this.vrp = vrp;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public Long getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Long owner) {
        this.owner = owner;
    }


    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
