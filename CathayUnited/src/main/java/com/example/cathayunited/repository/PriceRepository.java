package com.example.cathayunited.repository;

import com.example.cathayunited.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByDate(Long date);


    @Query("SELECT p FROM Price p WHERE p.date BETWEEN :startTimestamp AND :endTimestamp ORDER BY p.date DESC")
    List<Price> findClosingPriceByDate(@Param("startTimestamp") Long startTimestamp, @Param("endTimestamp") Long endTimestamp);

    Optional<Price> findByIdAndDate(String id, Long date);

    void deleteByIdAndDate(String id, Long date);
}
