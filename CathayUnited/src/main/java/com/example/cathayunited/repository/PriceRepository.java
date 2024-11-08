package com.example.cathayunited.repository;

import com.example.cathayunited.entity.Price;
import com.example.cathayunited.vo.PriceVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByDate(Long date);

    Price findFirstByDate(Long date);

    Optional<Price> findByIdAndDate(String id, Long date);

    void deleteByIdAndDate(String id, Long date);
}
