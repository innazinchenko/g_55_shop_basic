package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFounException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repository.ProductRepository;

import java.util.List;

// Это сервис продуктов. Он является частью третьего слоя нашего приложения.
// Его задача - содержать бизнес-логику приложения, то есть код,
// который выполняет те задачи, ради которых и создаётся приложение.
// При этом сервис не должен принимать никаких запросов, поступивших
// в приложение извне (это задача 4 слоя), а ещё он никогда
// не должен обращаться напрямую в базу данных (это задача 2 слоя).

// Для запросов в базу данных сервис должен взаимодействовать
// со вторым слоем - с репозиторием. Получается, что сервис должен
// обращаться к объекту репозитория и вызывать его методы.
// Поэтому здесь мы определяем поле, которое и будет содержать объект репозитория,
// чтобы сервис в своих методах мог к нему обращаться.
// Данное поле имеет тип ProductRepository, а это интерфейс.
// Это позволяет нам в это поле передавать не только объект какого-то конкретного
// класса, а объект любого класса, который реализует интерфейс ProductRepository.
// Принцип слабой связности (loose coupling principle) говорит нам о том,
// что классы не должны зависеть от других классов.
// Классы должны зависеть от абстракции, то есть от интерфейсов.


public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {

        if (product == null) {
            throw new ProductSaveException("Product can not be null");
        }

        String title = product.getTitle();
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new ProductSaveException("Product title should be at least 3 characters long");
        }

        if (product.getPrice() < 0) {
            throw new ProductSaveException("Price can not be negative");
        }

        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return repository.findAll()
                .stream()
                .filter(x -> x.isActive())
                .toList();
    }

    @Override
    public Product getById(Long id) {
        Product product = repository.findById(id);

        if (product == null || !product.isActive()) {
            throw new ProductNotFounException("Product with id " + id + " not found");
        }

        return product;
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new ProductUpdateException("Product cannot be null");
        }


        Long id = product.getId();
        if (id == null || id < 1) {
            throw new ProductUpdateException("Product should be positive");
        }

        if (product.getPrice() < 0) {
            throw new ProductUpdateException("Product price cannot be negative");
        }

        repository.update(product);
    }


    @Override
    public void deleteById(Long id) {
        getById(id).setActive(false);
    }

    @Override
    public void deleteByTitle(String title) {
       Product product = getAllActiveProducts()
               .stream()
               .filter(x -> x.getTitle().equals(title))
               .findFirst()
               .orElse(null);

       if (product == null) {
           throw new ProductNotFounException("Product with title " + title + " not fiund");
       }

       product.setActive(false);
    }


    @Override
    public void restoreById(Long id) {
        Product product = repository.findById(id);

        if (product == null) {
            throw new ProductNotFounException("Product with id " + id +"not found");
        }
        product.setActive(true);
    }

    @Override
    public long getActiveProductsTotalCount() {
        return getAllActiveProducts().size();
    }

    @Override
    public double getActiveProductsTotalCost() {
        return getAllActiveProducts()
                .stream()
                .mapToDouble(x -> x.getPrice())
                .sum();
    }

    @Override
    public double getActiveProductsAveragePrice() {
       long productCount = getActiveProductsTotalCount();
       if (productCount == 0) {
           return 0;
       }

       return getActiveProductsTotalCost() / productCount;
    }
}
