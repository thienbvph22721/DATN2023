package com.example.demo.service;

import com.example.demo.entity.Promotion;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PromotionService {

    List<Promotion> getAll();

    void savePromotion(Promotion promotion);

    Promotion getPromotionById(UUID id);

    void deletePromotion(UUID id);

    Page<Promotion> findPage(int pageNo , int pageSize);
}
