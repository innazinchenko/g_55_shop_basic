package app.repository;

import app.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void update(Customer customer);

    void deleteById(Long id);
}
