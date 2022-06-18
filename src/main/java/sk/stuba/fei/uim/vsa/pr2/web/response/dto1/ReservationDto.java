package sk.stuba.fei.uim.vsa.pr2.web.response.dto1;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.RDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationDto extends RDto {
    private Long id;
    private Date start;
    private Date end;
    private Integer prices;
    private Long car;
    private Long spot;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public Integer getPrices() {
        return prices;
    }

    @Override
    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    @Override
    public Long getCar() {
        return car;
    }

    @Override
    public void setCar(Long car) {
        this.car = car;
    }

    @Override
    public Long getSpot() {
        return spot;
    }

    @Override
    public void setSpot(Long spot) {
        this.spot = spot;
    }
}
