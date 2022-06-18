package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.PSDto;

public interface PSResponseFactory<R, T extends PSDto>{
    T transformToDto(R entity);

    R transformToEntity(T dto);
}
