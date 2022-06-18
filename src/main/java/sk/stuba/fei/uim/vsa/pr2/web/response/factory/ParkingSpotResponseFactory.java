package sk.stuba.fei.uim.vsa.pr2.web.response.factory;

import sk.stuba.fei.uim.vsa.pr2.domain.*;
import sk.stuba.fei.uim.vsa.pr2.service.CarParkService;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ParkingSpotDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.dto1.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory.PSResponseFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingSpotResponseFactory implements PSResponseFactory<ParkingSpot, ParkingSpotDto> {
    CarParkService service = new CarParkService();

    @Override
    public ParkingSpotDto transformToDto(ParkingSpot entity) {
        ParkingSpotDto dto = new ParkingSpotDto();
        dto.setId(entity.getId());
        dto.setIdentifier(entity.getSpotIdentifier());
        dto.setCarParkFloor(entity.getFloorIdentifier().getFloorIdentifier());
        dto.setCarPark(entity.getCarParkId().getId());
        dto.setType(entity.getCarType().getId());

        List<Object> resObject = service.getReservationBySpotId(entity.getId());
        if(resObject == null){
            List<ReservationDto> resDto= new ArrayList<>();
            dto.setReservations(resDto);
        }
        else{
            List<Reservation> resEntity = resObject.stream().map(element -> (Reservation)element).collect(Collectors.toList());
            ReservationResponseFactory resFactory = new ReservationResponseFactory();

            List<ReservationDto> resDto = resEntity.stream().map(resFactory::transformToDto).collect(Collectors.toList());
            dto.setReservations(resDto);
        }

        return dto;
    }

    @Override
    public ParkingSpot transformToEntity(ParkingSpotDto dto) {
        ParkingSpot entity = new ParkingSpot();
        entity.setId(dto.getId());
        entity.setSpotIdentifier(dto.getIdentifier());

        CarParkFloor cpf = new CarParkFloor();
        cpf.setFloorIdentifier(dto.getCarParkFloor());
        entity.setFloorIdentifier(cpf);

        CarPark cp = new CarPark();
        cp.setId(dto.getCarPark());
        entity.setCarParkId(cp);

        CarType ct = new CarType();
        ct.setId(dto.getType());
        entity.setCarType(ct);


        return entity;
    }
}
