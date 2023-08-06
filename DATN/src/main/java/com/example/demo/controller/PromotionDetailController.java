package com.example.demo.controller;

import com.example.demo.entity.Promotion;
import com.example.demo.entity.PromotionDetail;
import com.example.demo.service.ProductDetailService;
import com.example.demo.service.PromotionDetailService;
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
@RequestMapping("/promotionDetail")
public class PromotionDetailController {

    @Autowired
    private PromotionDetailService promotionDetailService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductDetailService productDetailService;


    @GetMapping("/hienthi")
    public String loadDataPromotionDetail(Model model) {
        return findPage(1, model);
    }

    @GetMapping("/saveForm")
    public String saveForm(Model model) {
        PromotionDetail promotionDetail = new PromotionDetail();
        model.addAttribute("promotionDetail", promotionDetail);
        model.addAttribute("listPromotion", promotionService.getAll());
        model.addAttribute("listProductDetail", productDetailService.getAll());
        return "/promotiondetail/saveForm";
    }

    @PostMapping("/savePromotionDetail")
    public String savePromotionDetail(@ModelAttribute("promotiondetail") PromotionDetail promotiondetail, Model model) {
        promotionDetailService.savePromotionDetail(promotiondetail);
        model.addAttribute("listpromotiondetail", promotionDetailService.getAllPromotionDetail());
        return "redirect:/promotionDetail/hienthi";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable(value = "id") UUID id, Model model) {
        PromotionDetail promotionDetail = promotionDetailService.getPromotionDetailById(id);
        model.addAttribute("promotionDetail", promotionDetail);
        model.addAttribute("listPromotion", promotionService.getAll());
        model.addAttribute("listProductDetail", productDetailService.getAll());
        return "/promotiondetail/updateForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") UUID id, Model model) {
        this.promotionDetailService.deletePromotionDetail(id);
        return "redirect:/promotionDetail/hienthi";
    }

    @GetMapping("/page/{pageNo}")
    public String findPage(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<PromotionDetail> page = promotionDetailService.findPage(pageNo, pageSize);
        List<PromotionDetail> promotionDetailList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listpromotiondetail", promotionDetailList);

        return "/promotiondetail/index";
    }
}
