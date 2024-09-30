package com.bidkoi.koiauction.service;

import com.bidkoi.koiauction.dto.KoiDTO;
import com.bidkoi.koiauction.mapper.IKoiMapper;
import com.bidkoi.koiauction.payload.request.KoiCreationRequest;
import com.bidkoi.koiauction.pojo.Koi;
import com.bidkoi.koiauction.repository.IKoiRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KoiService implements IKoiService {
//    private AtomicInteger koiIdGenerator = new AtomicInteger();

    IKoiRepository repository;
    IKoiMapper mapper;

    @Override
    public KoiDTO createKoi(KoiCreationRequest request) {
        Koi koi = mapper.toKoi(request);
//        koi.setId(koiIdGenerator.incrementAndGet());
        return mapper.toKoiDTO(repository.save(koi));
//        return mapper.toKoiDTO(koi);
    }

//    @Override
//    public void resetKoiId() {
//        koiIdGenerator.set(0);  // Đặt lại ID bắt đầu từ 99, koiID tiếp theo sẽ là 100
//    }

    @Override
    public List<Koi> getAllKois() {
        return repository.findAll();
    }


}
