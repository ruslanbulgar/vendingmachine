package com.rbc.vendingmachine.controller;

import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.service.ProductInfoService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/service")
public class ServiceProductController {
    private final ProductInfoService productInfoService;

    public ServiceProductController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @RequestMapping({"/product/new"})
    public String newProduct(Model model) {
        model.addAttribute("product", ProductInfo.builder().name("").price(0).maxProducts(0).build());
        return "service/product/properties";
    }

    @RequestMapping({"/product/edit"})
    public String editProduct(@RequestParam("code") Integer code, Model model) {

        model.addAttribute("product", productInfoService.getProduct(code));

        return "service/product/properties";
    }

    @PostMapping({"/product/save"})
    public String saveProduct(@RequestParam("code") Integer code,
                              @RequestParam("name") String name,
                              @RequestParam("price") Integer price,
                              @RequestParam("maxprods") Integer maxProducts) {

        productInfoService.saveOrUpdateProduct(code, name, price, maxProducts);

        return "redirect:/service";
    }

    @RequestMapping({"/product/remove"})
    public String removeProduct(@RequestParam("code") Integer code, RedirectAttributes redirectAttributes) {

        productInfoService.removeProduct(code);

        return "redirect:/service";
    }

    @RequestMapping({"/product/stock"})
    public String stockForProduct(@RequestParam("code") Integer code, Model model) {

        model.addAttribute("product", productInfoService.getProduct(code));

        return "service/product/stock";
    }
}
