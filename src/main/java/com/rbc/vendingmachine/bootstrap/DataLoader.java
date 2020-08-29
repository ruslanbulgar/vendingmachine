package com.rbc.vendingmachine.bootstrap;

import com.rbc.vendingmachine.model.Balance;
import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.model.Product;
import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.service.BalanceService;
import com.rbc.vendingmachine.service.CashService;
import com.rbc.vendingmachine.service.ProductInfoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductInfoService productInfoService;
    private final BalanceService balanceService;
    private final CashService cashService;

    public DataLoader(ProductInfoService productInfoService, BalanceService balanceService, CashService cashService) {
        this.productInfoService = productInfoService;
        this.balanceService = balanceService;
        this.cashService = cashService;
    }

    @Transactional
    @Override
    public void run(String... args) {

        balanceService.save(Balance.builder().id(0).balance(0).build());

        cashService.save(Cash.builder().value(1).name("Penny").count(10).build());
        cashService.save(Cash.builder().value(5).name("Nickel").count(10).build());
        cashService.save(Cash.builder().value(10).name("Dime").count(10).build());
        cashService.save(Cash.builder().value(25).name("Quarter").count(10).build());
        cashService.save(Cash.builder().value(50).name("Half").count(10).build());

        ProductInfo p1 = productInfoService.addNewProduct("Coke", 50, 10);
        ProductInfo p2 = productInfoService.addNewProduct("Pepsi", 50, 10);
        ProductInfo p3 = productInfoService.addNewProduct("Mountain Dew", 55, 10);
        ProductInfo p4 = productInfoService.addNewProduct("Orange Juice", 25, 15);
        ProductInfo p5 = productInfoService.addNewProduct("Sprite", 40, 8);
        ProductInfo p6 = productInfoService.addNewProduct("Dr. Pepper", 45, 20);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 10);

        List<Product> p1Products = new ArrayList<>();
        List<Product> p2Products = new ArrayList<>();
        List<Product> p3Products = new ArrayList<>();
        List<Product> p4Products = new ArrayList<>();
        List<Product> p5Products = new ArrayList<>();
        List<Product> p6Products = new ArrayList<>();

        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p1Products.add(Product.builder().code(p1.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p1.setProducts(p1Products);
        productInfoService.save(p1);

        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p2Products.add(Product.builder().code(p2.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p2.setProducts(p2Products);
        productInfoService.save(p2);

        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p3Products.add(Product.builder().code(p3.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p3.setProducts(p3Products);
        productInfoService.save(p3);

        p4Products.add(Product.builder().code(p4.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p4Products.add(Product.builder().code(p4.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p4Products.add(Product.builder().code(p4.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p4Products.add(Product.builder().code(p4.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p4Products.add(Product.builder().code(p4.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p4.setProducts(p4Products);
        productInfoService.save(p4);

        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p5Products.add(Product.builder().code(p5.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p5.setProducts(p5Products);
        productInfoService.save(p5);

        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());
        p6Products.add(Product.builder().code(p6.getCode()).expirationDate(LocalDate.now().plusDays(10)).build());

        p6.setProducts(p6Products);
        productInfoService.save(p6);


    }

}
