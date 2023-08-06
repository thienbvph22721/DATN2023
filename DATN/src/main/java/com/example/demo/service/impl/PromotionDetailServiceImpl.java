package com.example.demo.service.impl;

import com.example.demo.entity.Promotion;
import com.example.demo.entity.PromotionDetail;
import com.example.demo.repository.PromotionDetailRepository;
import com.example.demo.service.PromotionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PromotionDetailServiceImpl implements PromotionDetailService {

    @Autowired
    private PromotionDetailRepository promotionDetailRepository;


    @Override
    public List<PromotionDetail> getAllPromotionDetail() {
        return promotionDetailRepository.findAll();
    }

    @Override
    public void savePromotionDetail(PromotionDetail promotionDetail) {
        this.promotionDetailRepository.save(promotionDetail);
    }

    @Override
    public PromotionDetail getPromotionDetailById(UUID id) {
        Optional<PromotionDetail> optional = promotionDetailRepository.findById(id);
        PromotionDetail promotionDetail = null;
        if (optional.isPresent()) {
            promotionDetail = optional.get();
        }
        return promotionDetail;
    }

    @Override
    public void deletePromotionDetail(UUID id) {
        this.promotionDetailRepository.deleteById(id);
    }

    @Override
    public Page<PromotionDetail> findPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.promotionDetailRepository.findAll(pageable);
    }
}
