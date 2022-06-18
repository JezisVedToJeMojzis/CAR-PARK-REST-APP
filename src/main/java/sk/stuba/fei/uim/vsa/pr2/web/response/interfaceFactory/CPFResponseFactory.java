package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CPFDto;


public interface CPFResponseFactory<R, T extends CPFDto>{
    T transformToDto(R entity);

    R transformToEntity(T dto);
}
