package com.bidkoi.koiauction.mapper;


import com.bidkoi.koiauction.dto.KoiDTO;
import com.bidkoi.koiauction.payload.request.KoiCreationRequest;
import com.bidkoi.koiauction.pojo.Koi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IKoiMapper {
    Koi toKoi(KoiCreationRequest request);
    KoiDTO toKoiDTO(Koi koi);
}
