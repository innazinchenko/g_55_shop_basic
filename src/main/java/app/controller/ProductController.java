package app.controller;

import app.domain.Product;
import app.service.ProductService;

import java.util.List;

public class ProductController {

    private final ProductService service;


    public ProductController(ProductService service) {
        this.service = service;
    }

    public Product save(String title, double price){
        Product product = new Product(title, price);
        return service.save(product);
    }

    public List<Product> getAll() {
        return service.getAllActiveProducts();
    }

    public Product getById(Long id) {
        return service.getById(id);
    }

    public void update(Long id, double newPrice) {
        Product product = new Product(id, newPrice);
        service.update(product);
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }

    public void deleteByTitle(String title) {
        service.deleteByTitle(title);
    }

    public void restoreById(Long id) {
        service.restoreById(id);
    }


    public long getProductsCount() {
       return service.getActiveProductsTotalCount();
    }

    public double getProductsTotalCost() {
        return service.getActiveProductsTotalCost();
    }

    public  double getProductsAveragePrice() {
        return service.getActiveProductsAveragePrice();
    }
}
