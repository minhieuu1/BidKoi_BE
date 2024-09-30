package com.bidkoi.koiauction.controller;

import com.bidkoi.koiauction.dto.KoiDTO;
import com.bidkoi.koiauction.payload.request.KoiCreationRequest;
import com.bidkoi.koiauction.payload.response.ApiResponse;
import com.bidkoi.koiauction.pojo.Koi;
import com.bidkoi.koiauction.service.IKoiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/koi")
@RequiredArgsConstructor
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KoiController {
    IKoiService service;


    @PostMapping("/create")
    ApiResponse<KoiDTO> create(@RequestBody KoiCreationRequest request) {
        return ApiResponse.<KoiDTO>builder()
                .data(service.createKoi(request))
                .build();
    }

//    @PostMapping("/reset-id")
//    public String resetId() {
//        service.resetKoiId();
//        return "ID generator has been reset!";
//    }

    @GetMapping
    List<Koi> getAll() {
        return service.getAllKois();
    }

//    @PostMapping("/reset-id")
//    public String resetId() {
//        resetIdGenerator();
//        return "ID generator has been reset!";
//    }
}
