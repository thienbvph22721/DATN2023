package com.example.demo.service.impl;

import com.example.demo.entity.Promotion;
import com.example.demo.repository.PromotionRepository;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void savePromotion(Promotion promotion) {
        this.promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(UUID id) {
        Optional<Promotion> optional = promotionRepository.findById(id);
        Promotion promotion = null;
        if (optional.isPresent()) {
            promotion = optional.get();
        }
        return promotion;
    }

    @Override
    public void deletePromotion(UUID id) {
        this.promotionRepository.deleteById(id);
    }

    @Override
    public Page<Promotion> findPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.promotionRepository.findAll(pageable);
    }

}
