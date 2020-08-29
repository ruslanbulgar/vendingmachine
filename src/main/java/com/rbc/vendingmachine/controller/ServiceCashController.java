package com.rbc.vendingmachine.controller;

import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.service.CashService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/service")
public class ServiceCashController {

    private final CashService cashService;

    public ServiceCashController(CashService cashService) {
        this.cashService = cashService;
    }

    @RequestMapping({"/cash/new"})
    public String newCash(Model model) {

        model.addAttribute("cash", Cash.builder().name("").value(0).count(0).build());
        return "/service/cash/properties";
    }

    @RequestMapping({"/cash/edit"})
    public String editCash(@RequestParam("value") Integer value, Model model) {

        model.addAttribute("cash", cashService.getCash(value));

        return "/service/cash/properties";
    }

    @PostMapping({"/cash/save"})
    public String saveCash(@RequestParam("value") Integer value,
                           @RequestParam("name") String name,
                           @RequestParam("count") Integer count) {
        if (value != 0)
            cashService.updateOrCreateCash(value, name, count);

        return "redirect:/service";
    }

    @RequestMapping({"/cash/remove"})
    public String removeCash(@RequestParam("value") Integer value, RedirectAttributes redirectAttributes) {

        cashService.removeCash(value);

        return "redirect:/service";
    }
}
