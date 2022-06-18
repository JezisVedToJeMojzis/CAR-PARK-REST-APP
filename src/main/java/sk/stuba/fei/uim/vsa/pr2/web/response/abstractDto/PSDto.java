package sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;

import java.util.List;

public abstract class PSDto {
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected String identifier;
    protected String carParkFloor;
    protected Long carPark;
    protected Long type;
    protected boolean free;
    protected List<ReservationDto> reservations;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCarParkFloor() {
        return carParkFloor;
    }

    public void setCarParkFloor(String carParkFloor) {
        this.carParkFloor = carParkFloor;
    }

    public Long getCarPark() {
        return carPark;
    }

    public void setCarPark(Long carPark) {
        this.carPark = carPark;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
}
