package com.example.demo.controller;

import com.example.demo.entity.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/hienthi")
    public String loadData(Model model) {
        return findPage(1,model);
    }

    @GetMapping("/saveForm")
    public String saveForm(Model model) {
        Promotion promotion = new Promotion();
        model.addAttribute("promotion", promotion);
        return "/promotion/saveForm";
    }

    @PostMapping("/savePromotion")
    public String savePromotion(@ModelAttribute("promotion") Promotion promotion, Model model) {
        promotionService.savePromotion(promotion);
        model.addAttribute("listPromotion", promotionService.getAll());
        return "redirect:/promotion/hienthi";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable(value = "id") UUID id , Model model) {
        Promotion promotion = promotionService.getPromotionById(id);
        model.addAttribute("promotion", promotion);
        return "/promotion/updateForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") UUID id) {
        this.promotionService.deletePromotion(id);
        return "redirect:/promotion/hienthi";
    }

    @GetMapping("/page/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo,Model model){
        int pageSize = 5;

        Page<Promotion> page = promotionService.findPage(pageNo,pageSize);
        List<Promotion> promotionList = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listPromotion",promotionList);

        return "/promotion/index";
    }

}
