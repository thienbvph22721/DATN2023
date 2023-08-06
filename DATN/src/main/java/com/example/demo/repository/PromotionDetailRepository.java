package com.example.demo.repository;

import com.example.demo.entity.PromotionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionDetailRepository extends JpaRepository<PromotionDetail, UUID> {
}
