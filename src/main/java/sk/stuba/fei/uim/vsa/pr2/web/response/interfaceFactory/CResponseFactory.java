package sk.stuba.fei.uim.vsa.pr2.web.response.interfaceFactory;

import sk.stuba.fei.uim.vsa.pr2.web.response.abstractDto.CDto;

public interface CResponseFactory<R, T extends CDto>{
    T transformToDto(R entity);

    R transformToEntity(T dto);
}
