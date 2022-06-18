package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.CarPark;
import sk.stuba.fei.uim.vsa.pr2.domain.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.domain.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.CarTypeDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.CTResponseFactory;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.PSResponseFactory;

public class CarTypeResponseFactory implements CTResponseFactory<CarType, CarTypeDto> {
    CarParkService service = new CarParkService();

    @Override
    public CarTypeDto transformToDto(CarType entity) {
        CarTypeDto dto = new CarTypeDto();

        dto.setId(entity.getId());
        dto.setName(entity.getType());

        return dto;
    }

    @Override
    public CarType transformToEntity(CarTypeDto dto) {
        CarType entity = new CarType();

        entity.setId(dto.getId());
        entity.setType(dto.getName());

        return entity;
    }
}
