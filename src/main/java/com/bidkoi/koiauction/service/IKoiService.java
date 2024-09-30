package com.bidkoi.koiauction.service;

import com.bidkoi.koiauction.dto.KoiDTO;
import com.bidkoi.koiauction.payload.request.KoiCreationRequest;
import com.bidkoi.koiauction.pojo.Koi;

import java.util.List;

public interface IKoiService {
    KoiDTO createKoi(KoiCreationRequest request);
    List<Koi> getAllKois();
//    void resetKoiId();
}
