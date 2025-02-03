package client;

import app.controller.ProductController;
import app.domain.Product;
import app.repository.ProductRepository;
import app.repository.ProductRepositoryList;
import app.service.ProductService;
import app.service.ProductServiceImpl;

import java.util.Scanner;

public class Client {

    private static Scanner scanner;
    private static ProductController productController;


    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepositoryList();
        ProductService productService = new ProductServiceImpl(productRepository);
        productController  = new ProductController(productService);

        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Choose action:");
                System.out.println("1. with products");
                System.out.println("2. with customers");
                System.out.println("0. exit");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        productOperations();
                        break;
                    case 2:
                        customerOperations();
                        break;
                    case 3:
                        System.out.println("Enter id of product: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        Product foundProduct = productController.getById(id);
                        System.out.println("The found product is ");
                        System.out.println(foundProduct);
                        break;
                    case 4:
                        System.out.println("Enter id of product: ");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println();
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        productController.update(id, newPrice);
                        break;
                    case 5:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case 6:
                        System.out.println("Введите наименование продукта:");
                        String title = scanner.nextLine();
                        productController.deleteByTitle(title);
                        break;
                    case 7:
                        System.out.println("Введите идентификатор продукта:");
                        id = Long.parseLong(scanner.nextLine());
                        productController.restoreById(id);
                        break;
                    case 8:
                        System.out.println("Количество продуктов - " + productController.getProductsCount());
                        break;
                    case 9:
                        System.out.println("Общая стоимость продуктов - " + productController.getProductsTotalCost());
                        break;
                    case 10:
                        System.out.println("Средняя цена продукта - " + productController.getProductsAveragePrice());
                        break;
                    case 0:
                        return;
                    default:
                        System.err.println("Incorrect!");
                        break;
                }


            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void productOperations() {
        while (true) {
            try {
                System.out.println("Choose action with products:");
                System.out.println("1 - save all products");
                System.out.println("2 - getting all products:");
                System.out.println("3 - getting one product:");
                System.out.println("4 - changing one product:");
                System.out.println("5 - delete product by ID");
                System.out.println("6 - delete product by name:");
                System.out.println("7 - recovering product by ID");
                System.out.println("8 - getting number of products:");
                System.out.println("9 - getting total cost all products:");
                System.out.println("10 - getting average cost all products");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("enter name product");
                        String title = scanner.nextLine();
                        System.out.println("Enter price");
                        double price = Double.parseDouble(scanner.nextLine());
                        Product savedProduct = productController.save(title, price);
                        System.out.println("Saved product:");
                        System.out.println(savedProduct);
                        break;
                    case 2:
                        productController.getAll().forEach(x -> System.out.println(x));
                        break;
                    case 3:
                    case 0:
                        return;
                    default:
                        System.out.println("Incorrect enter!");
                        break;
                }


            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }

    public static void customerOperations() {

    }


}
