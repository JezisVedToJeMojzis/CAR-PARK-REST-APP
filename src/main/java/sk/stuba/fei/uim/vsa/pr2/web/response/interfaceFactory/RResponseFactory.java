package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.RDto;

import java.text.ParseException;

public interface RResponseFactory<R, T extends RDto>{
    T transformToDto(R entity) throws ParseException;

    R transformToEntity(T dto);
}
