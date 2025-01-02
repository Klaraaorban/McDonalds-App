package UI;
import Controller.OfferController;
import Controller.OrderController;
import Controller.ProductController;
import Controller.UserController;
import Enums.*;
import Model.*;
import Repository.*;
import Repository.FRepository.*;
import Service.OfferService;
import Service.OrderService;
import Service.ProductService;
import Service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestConsole {
    public static void main(String[] args) {
        IRepository<Client> clientRepo = new ClientFileRepository("src/Files/clients.txt");
        IRepository<Manager> managerRepo = new ManagerFileRepository("src/Files/managers.txt");
        IRepository<User> userRepo = new UserFileRepository("src/Files/users.txt");
        IRepository<Employee> employeeRepo = new EmployeeFileRepository("src/Files/employee.txt");
        IRepository<Desserts> dessertRepo = new DessertFileRepository("src/Files/desserts.txt");
        IRepository<Drinks> drinkRepo = new DrinkFileRepository("src/Files/drinks.txt");
        IRepository<MainDish> mainsRepo = new MainsFileRepository("src/Files/mains.txt");
        IRepository<SideDish> sidesRepo = new SidesFileRepository("src/Files/sides.txt");
        IRepository<Product> prodsRepo = new ProductFileRepository("src/Files/prods.txt");
        ProductService productService = new ProductService(prodsRepo, mainsRepo, sidesRepo, dessertRepo, drinkRepo);
        ProductController productController = new ProductController(productService);


        IRepository<Offer> offerRepo = new OfferFileRepository("src/Files/offers.txt", (ProductFileRepository) prodsRepo);

        IRepository<Location> locationRepo = new LocationFileRepository("src/Files/locations.txt");
        IRepository<Order> orderRepo = new OrderFileRepository("src/Files/orders.txt", (UserFileRepository) userRepo, (ProductFileRepository) prodsRepo, (LocationFileRepository) locationRepo);
        UserService userService = new UserService(userRepo, clientRepo, managerRepo, employeeRepo);
        UserController userController = new UserController(userService);

        OrderService orderService = new OrderService(orderRepo, locationRepo);
        OrderController orderController = new OrderController(orderService);

//        UserService userService = new UserService(clientRepo);

        // Example usage
//        Manager manager = new Manager("manager@example.com", "John Doe", 1, "password", ManagerRank.Senior);
//        managerRepo.create(manager);
        managerRepo.getAll().forEach(System.out::println);

//        ProductService productService = new ProductService(prodsRepo, mainsRepo, sidesRepo, dessertRepo, drinkRepo);
//        ProductController productController = new ProductController(productService);

        OfferService offerService = new OfferService(offerRepo);
        OfferController offerController = new OfferController(offerService);

//        productController.createMainDish("Hamburger", 12, 1307, DishSize.MEDIUM);
//        productController.createMainDish("Cheeseburger", 13, 1350, DishSize.MEDIUM);
//        productController.createMainDish("Big Mac", 15, 1500, DishSize.LARGE);

//        productController.createSideDish("French Fries", 5, DishSize.MEDIUM);
//        productController.createSideDish("Chicken McNuggets", 8, DishSize.MEDIUM);
//
//        productController.createDrink("Sprite", 3, DrinkVolume._300ML);
//        productController.createDrink("Lipton", 3, DrinkVolume._200ML);
        List<Product> offerList = new ArrayList<>();
        offerList.add(productController.getProduct("Lipton"));

        offerController.add(3, offerList);

//        System.out.println(prodsRepo.read(1));
//        userController.signUpManager("klara.orban@yahoo.com", "Orban Klara", "1234", ManagerRank.Senior);
//        userController.signUpClient("chira.carla@gmail.com", "Chira Carla", "5678");
//        orderController.createLocation(Locations.Bucuresti, userService.readManager(1));

        managerRepo.getAll().forEach(System.out::println);

        System.out.println(userRepo.read(1));

        System.out.println(prodsRepo.read(1));

//        orderController.createOrder(userController.signIn("chira.carla@gmail.com", "5678"), );

//        orderController.createLocation(Locations.Bucuresti, userService.readManager(1));
        System.out.println(locationRepo.read(1));

        orderController.createOrder(clientRepo.read(2), locationRepo.read(1), offerList, Optional.empty() ,false);

    }
}
