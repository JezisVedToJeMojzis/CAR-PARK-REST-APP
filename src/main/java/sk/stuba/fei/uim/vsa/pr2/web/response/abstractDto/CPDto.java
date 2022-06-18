package sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto;

import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarParkFloorDto;

import java.util.List;

public abstract class CPDto {
 //?
    protected Long id;
    protected String name;
    protected String address;
    protected Integer prices;
    private List<CarParkFloorDto> floors;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CarParkFloorDto> getFloors() {
        return floors;
    }

    public void setFloors(List<CarParkFloorDto> floors) {
        this.floors = floors;
    }
}
