package com.rbc.vendingmachine.controller;

import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/service")
public class ServiceUnitController {
    private final ProductInfoService productInfoService;

    public ServiceUnitController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @RequestMapping({"/unit/remove"})
    public String removeUnit(@RequestParam("code") Integer code,
                             @RequestParam("id") Integer id) {

        ProductInfo productInfo = productInfoService.getProduct(code);
        productInfo.getProducts().removeIf(e -> id.equals(e.getId()));
        productInfoService.save(productInfo);

        return "redirect:/service/product/stock?code=" + code;
    }

    @RequestMapping({"/unit/add"})
    public String addUnit(@RequestParam("code") Integer code, Model model) {

        model.addAttribute("product", productInfoService.getProduct(code));

        return "service/product/unit";
    }

    @PostMapping({"/unit/save"})
    public String unitSave(@RequestParam("code") Integer code,
                           @RequestParam("expirationDate") String expirationDate) {

        productInfoService.addNewUnit(code, expirationDate);

        return "redirect:/service/product/stock?code=" + code;
    }
}
