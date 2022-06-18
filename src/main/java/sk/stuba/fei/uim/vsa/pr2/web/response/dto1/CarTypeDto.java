package sk.stuba.fei.uim.vsa.pr2.web.response.dto1;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CTDto;

public class CarTypeDto extends CTDto {
    private Long id;
    private String name;

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
}
