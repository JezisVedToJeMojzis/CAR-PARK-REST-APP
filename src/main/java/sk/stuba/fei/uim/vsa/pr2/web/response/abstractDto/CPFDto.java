package sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto;

import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;

import java.util.List;

public abstract class CPFDto {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected String identifier;
    protected Long carPark;
    protected List<ParkingSpotDto> spots;

  //  private List<ParkingSpotDto> spots;


    public List<ParkingSpotDto> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpotDto> spots) {
        this.spots = spots;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Long getCarPark() {
        return carPark;
    }

    public void setCarPark(Long carPark) {
        this.carPark = carPark;
    }
}
