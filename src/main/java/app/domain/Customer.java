package app.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private boolean active;
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        if (product.isActive()) {
            products.add(product);
        }
    }


    public List<Product> getAllProducts() {
        return products;
    }


    public void removeProductById(Long id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }


    public void clearCart() {
        products.clear();
    }


    public double getActiveProductsTotalCost() {
        return products
                .stream()
                .filter(x -> x.isActive())
                .mapToDouble(x -> x.getPrice())
                .sum();

    }


    public double getActiveProductsAveragePrice() {
        return products
                .stream()
                .filter(x -> x.isActive())
                .mapToDouble(x -> x.getPrice())
                .average()
                .orElse(0.0);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return active == customer.active && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(products, customer.products);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, products);
    }


    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s, active - %s.",
                id, name, active ? "yes" : "no");
    }
}
