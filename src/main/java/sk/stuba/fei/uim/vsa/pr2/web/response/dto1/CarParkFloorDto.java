package sk.stuba.fei.uim.vsa.pr2.web.response.dto1;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CPFDto;

import java.util.List;

public class CarParkFloorDto extends CPFDto {
    private Long id;
    private String identifier;
    private Long carPark;
    private List<ParkingSpotDto> spots;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ParkingSpotDto> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpotDto> spots) {
        this.spots = spots;
    }
}
