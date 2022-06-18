package sk.stuba.fei.uim.vsa.pr2.web.response.dto1;


import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CPDto;

import java.util.List;

public class CarParkDto extends CPDto {

    private Long id;
    private String name;
    private String address;
    private Integer prices;
    private List<CarParkFloorDto> floors;

    public List<CarParkFloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<CarParkFloorDto> floors) {
        this.floors = floors;
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

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }
}
