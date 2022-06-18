package sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto;

import java.util.Date;

public abstract class RDto {
    protected Long id;
    protected Date start;
    protected Date end;
    protected Integer prices;
    protected Long car;
    protected Long spot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }

    public Long getSpot() {
        return spot;
    }

    public void setSpot(Long spot) {
        this.spot = spot;
    }
}
