package com.rbc.vendingmachine.controller;

import com.rbc.vendingmachine.model.Balance;
import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.service.BalanceService;
import com.rbc.vendingmachine.service.CashService;
import com.rbc.vendingmachine.service.ProductInfoService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log
@RequestMapping("/vm")
public class VendingMachineController {

    private final ProductInfoService productInfoService;
    private final BalanceService balanceService;
    private final CashService cashService;

    public VendingMachineController(ProductInfoService productInfoService, BalanceService balanceService, CashService cashService) {
        this.productInfoService = productInfoService;
        this.balanceService = balanceService;
        this.cashService = cashService;
    }

    @GetMapping({""})
    public String index(Model model) {

        model.addAttribute("products", productInfoService.getAllProducts());
        model.addAttribute("balance", balanceService.getBalance());

        return "vm/index";
    }

    @GetMapping({"/coin/insert"})
    public String insertCoins(Model model) {

        model.addAttribute("balance", balanceService.getBalance());
        model.addAttribute("cashes", cashService.getAcceptedCash());

        return "vm/coin/insert";
    }

    @PostMapping({"/coin/add"})
    public String saveCash(@RequestParam("coin") Integer value, RedirectAttributes redirectAttributes) {

        if (value != null ) {
            balanceService.add(value);
            cashService.addCash(value);
        } else {
            redirectAttributes.addAttribute("error", "Invalid Coin!");
        }

        return "redirect:/vm";
    }

    @RequestMapping({"/product/buy"})
    public String buyProduct(@RequestParam("code") Integer code, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", productInfoService.buyProduct(code));

        return "redirect:/vm";
    }

    @RequestMapping({"/cash/change"})
    public String getChange(RedirectAttributes redirectAttributes) {

        List<Cash> change = balanceService.returnChange();

        redirectAttributes.addFlashAttribute("message", change.toString());

        return "redirect:/vm";
    }

}
