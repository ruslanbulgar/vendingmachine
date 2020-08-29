package com.rbc.vendingmachine.service;

import com.rbc.vendingmachine.model.Product;
import com.rbc.vendingmachine.model.ProductInfo;
import com.rbc.vendingmachine.repository.ProductInfoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class ProductInfoService {

    private final ProductInfoRepository productInfoRepository;
    private final BalanceService balanceService;

    public ProductInfoService(ProductInfoRepository productInfoRepository, BalanceService balanceService) {
        this.productInfoRepository = productInfoRepository;
        this.balanceService = balanceService;
    }


    public ProductInfo addNewProduct(String name, Integer price, int maxProducts) {
        ProductInfo productInfo = new ProductInfo(name, price, maxProducts);
        return productInfoRepository.save(productInfo);
    }

    public Iterable<ProductInfo> getAllProducts() {
        return productInfoRepository.findAll();
    }

    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    public void saveOrUpdateProduct(Integer code, String name, Integer price, Integer maxProducts) {
        ProductInfo productInfo = getProduct(code);
        if (productInfo != null) {
            productInfo.setName(name);
            productInfo.setPrice(price);
            productInfo.setMaxProducts(maxProducts);
            save(productInfo);
        } else {
            addNewProduct(name, price, maxProducts);
        }

    }

    public void addNewUnit(Integer code, String expirationDate) {
        System.out.println(expirationDate);
        if (expirationDate != null && !expirationDate.isEmpty()) {
            ProductInfo productInfo = getProduct(code);

            LocalDate localDate = LocalDate.parse(expirationDate);

            productInfo.getProducts().add(Product.builder().code(code).expirationDate(localDate).build());
            save(productInfo);
        }
    }

    public void removeProduct(Integer code) {

        if (code != null)
            productInfoRepository.deleteById(code);
    }

    public boolean isValidCode(Integer code) {
        if (code == null) {
            return false;
        }
        ProductInfo productInfo = productInfoRepository.findById(code).orElse(null);
        return productInfo != null;
    }

    public ProductInfo getProduct(Integer code) {
        if (isValidCode(code)) {
            return productInfoRepository.findById(code).orElse(null);
        }
        return null;
    }

    public String buyProduct(Integer code) {

        ProductInfo productInfo = getProduct(code);

        if (productInfo == null)
            return "Incorrect Code!";

        if (productInfo.getProducts().size() == 0)
            return "Product is Out of Stock!";

        if (balanceService.getBalance() < productInfo.getPrice())
            return "Not enough money!";

        balanceService.reduce(productInfo.getPrice());

        productInfo.getProducts().remove(0);
        save(productInfo);

        return String.format("Pickup your [%s]. Your balance is : $%f", productInfo.getName(), ((float) (balanceService.getBalance()) / 100));
    }

}
