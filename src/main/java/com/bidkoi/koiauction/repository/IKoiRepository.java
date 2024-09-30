package com.bidkoi.koiauction.repository;

import com.bidkoi.koiauction.pojo.Koi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKoiRepository extends JpaRepository<Koi,Integer> {
}
