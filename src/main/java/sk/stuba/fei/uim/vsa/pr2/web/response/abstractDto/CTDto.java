package sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto;

public abstract class CTDto {
    protected Long id;
    protected String name;

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
