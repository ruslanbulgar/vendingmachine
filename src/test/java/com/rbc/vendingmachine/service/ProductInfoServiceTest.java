package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Cash;
import com.rbc.vendingmachine.model.Product;
import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.repository.BalanceRepository;
import com.rbc.vendingmachine.repository.CashRepository;
import com.rbc.vendingmachine.repository.ProductInfoRepository;
import com.rbc.vendingmachine.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductInfoServiceTest {

    @Autowired
    CashRepository cashRepository;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    BalanceRepository balanceRepository;

    BalanceService balanceService;
    CashService cashService;
    ProductInfoService productInfoService;

    ProductInfo productInfo;

    @BeforeEach
    public void setUp() {
        cashService = new CashService(cashRepository);
        balanceService = new BalanceService(balanceRepository, cashService);
        productInfoService = new ProductInfoService(productInfoRepository, balanceService);

        productInfo = ProductInfo.builder()
                .price(10)
                .name("name")
                .maxProducts(3)
                .build();
    }

    @Test
    public void addNewProduct() {
        ProductInfo addedPI = productInfoService.addNewProduct(productInfo.getName(), productInfo.getPrice(), productInfo.getMaxProducts());
        assertNotNull(addedPI);
    }

    @Test
    public void getAllProducts() {
        productInfoRepository.save(productInfo);
        Iterable<ProductInfo> allProductsInfo = productInfoService.getAllProducts();
        assertNotNull(allProductsInfo.iterator().next());
    }

    @Test
    public void save() {
        ProductInfo savedProductInfo = productInfoService.save(productInfo);
        assertNotNull(savedProductInfo);
        assertEquals(productInfo.getName(), savedProductInfo.getName());
    }

    @Test
    public void saveOrCreateProduct_new() {
        productInfoService.saveOrUpdateProduct(productInfo.getCode(), productInfo.getName(), productInfo.getPrice(), productInfo.getMaxProducts());
        Iterable<ProductInfo> allProductsInfo = productInfoService.getAllProducts();
        assertNotNull(allProductsInfo.iterator().next());
    }

    @Test
    public void saveOrCreateProduct_update() {
        ProductInfo savedProductInfo = productInfoRepository.save(productInfo);

        productInfoService.saveOrUpdateProduct(savedProductInfo.getCode(), savedProductInfo.getName(), 100, savedProductInfo.getMaxProducts());
        Iterable<ProductInfo> allProductsInfo = productInfoService.getAllProducts();
        ProductInfo piReturned = allProductsInfo.iterator().next();

        assertNotNull(piReturned);
        assertEquals(100, piReturned.getPrice());
    }

    @Test
    public void addNewUnit() {
        productInfo.setProducts(getProducts());
        productInfoRepository.save(productInfo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        productInfoService.addNewUnit(productInfo.getCode(), simpleDateFormat.format(new Date()));
        assertEquals(getProducts().size() + 1,
                productInfoService.getProduct(productInfo.getCode()).getProducts().size());

    }

    @Test
    public void removeProduct() {
        productInfoRepository.save(productInfo);
        productInfoService.removeProduct(productInfo.getCode());
        ProductInfo productInfo = productInfoService.getProduct(this.productInfo.getCode());
        assertNull(productInfo);
    }

    @Test
    public void isValidCode() {
        productInfoRepository.save(productInfo);
        assertTrue(productInfoService.isValidCode(productInfo.getCode()));
    }

    @Test
    public void getProduct() {
        productInfoRepository.save(productInfo);
        ProductInfo productInfo = productInfoService.getProduct(this.productInfo.getCode());
        assertNotNull(productInfo);
    }

    @Test()
    public void buyProduct() {
        balanceService.add(10);
        productInfo.setProducts(getProducts());
        ProductInfo savedProductInfo = productInfoRepository.save(productInfo);
        String result = productInfoService.buyProduct(savedProductInfo.getCode());
        assertTrue(result.startsWith("Pickup"));
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().code(10).expirationDate(LocalDate.of(2020, 12, 12)).build());
        return products;
    }
}