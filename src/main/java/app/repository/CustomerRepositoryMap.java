package app.repository;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryMap implements CustomerRepository {

    private Map<Long, Customer> database = new HashMap<>();
    private long currentId = 0;

    @Override
    public Customer save(Customer customer) {
        customer.setId(++currentId);
        database.put(currentId, customer);
        return customer;
    }

    @Override
    public Customer findById(Long id) {
        return database.get(id);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void update(Customer customer) {
        Long id = customer.getId();
        String newName = customer.getName();

        Customer existedCustomer = findById(id);

        if (existedCustomer != null) {
            existedCustomer.setName(newName);
        }
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }
}
