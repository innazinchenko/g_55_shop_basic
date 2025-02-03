package app.service;

import app.domain.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    List<Product> getAllActiveProducts();
    Product getById(Long id);
    void update(Product product);
    void deleteById(Long id);
    void deleteByTitle(String title);
    void restoreById(Long id);
    long getActiveProductsTotalCount();
    double getActiveProductsTotalCost();
    double getActiveProductsAveragePrice();
}
