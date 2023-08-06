package com.example.demo.service;

import com.example.demo.entity.PromotionDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface PromotionDetailService {

    List<PromotionDetail> getAllPromotionDetail();

    void savePromotionDetail(PromotionDetail promotionDetail);

    PromotionDetail getPromotionDetailById(UUID id);

    void deletePromotionDetail(UUID id);

    Page<PromotionDetail> findPage(int pageNo, int pageSize);
}
