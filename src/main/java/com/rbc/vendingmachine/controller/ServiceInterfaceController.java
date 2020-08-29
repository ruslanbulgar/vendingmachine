package com.rbc.vendingmachine.controller;

import com.rbc.vendingmachine.service.CashService;
import com.rbc.vendingmachine.service.ProductInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/service")
public class ServiceInterfaceController {

    private final CashService cashService;
    private final ProductInfoService productInfoService;

    public ServiceInterfaceController(CashService cashService, ProductInfoService productInfoService) {
        this.cashService = cashService;
        this.productInfoService = productInfoService;
    }

    @RequestMapping({""})
    public String service(Model model) {

        model.addAttribute("products", productInfoService.getAllProducts());
        model.addAttribute("cashes", cashService.getAcceptedCash());

        return "service/index";
    }


}
