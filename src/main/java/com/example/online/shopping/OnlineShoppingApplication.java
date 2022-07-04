package com.example.online.shopping;

import com.example.online.shopping.repositories.*;
import com.example.online.shopping.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@OpenAPIDefinition(info = @Info(title = "Online Shopping API", version = "1.0.0", description = "API Reference Example"))
public class OnlineShoppingApplication implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final CategoryRepository categoryRepository;
    private final DigitalProductRepository digitalProductRepository;
    private final PhysicalProductRepository physicalProductRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final LineItemRepository lineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final BankAccountRepository bankAccountRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final WebOrderRepository webOrderRepository;

    public OnlineShoppingApplication(AppUserRepository appUserRepository,
                                     CategoryRepository categoryRepository,
                                     DigitalProductRepository digitalProductRepository,
                                     PhysicalProductRepository physicalProductRepository,
                                     ShoppingCartRepository shoppingCartRepository,
                                     LineItemRepository lineItemRepository,
                                     ShoppingCartService shoppingCartService,
                                     BankAccountRepository bankAccountRepository,
                                     PaymentRepository paymentRepository,
                                     PaymentMethodRepository paymentMethodRepository,
                                     WebOrderRepository webOrderRepository) {

        this.appUserRepository = appUserRepository;
        this.categoryRepository = categoryRepository;
        this.digitalProductRepository = digitalProductRepository;
        this.physicalProductRepository = physicalProductRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.lineItemRepository = lineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.bankAccountRepository = bankAccountRepository;
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.webOrderRepository = webOrderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoppingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

       /* AppUser appUser = AppUser.builder()
                .email("johnsmith@gmail.com")
                .password("johnsmith")
                .firstName("John")
                .lastName("Smith")
                .role(Role.USER)
                .build();

        AppUser appUserAdmin = AppUser.builder()
                .email("johndoe@gmail.com")
                .password("johndoe")
                .firstName("John")
                .lastName("Doe")
                .role(Role.ADMIN)
                .build();

        appUserRepository.save(appUser);
        appUserRepository.save(appUserAdmin);

        Category electricHomeVehicle = Category.builder()
                .categoryName("Electric Home Vehicle")
                .build();

        Category smartPhone = Category.builder()
                .categoryName("Smart Phone")
                .build();

        Category eBook = Category.builder()
                .categoryName("E Book")
                .build();

        categoryRepository.save(eBook);
        categoryRepository.save(electricHomeVehicle);
        categoryRepository.save(smartPhone);

        PhysicalProduct toothBrush = PhysicalProduct.builder()
                .productName("Toothbrush")
                .shippingCategory(ShippingCategory.STANDARD)
                .category(electricHomeVehicle)
                .productDiscount(10)
                .basePrice(100)
                .description("High quality product with multi functional usage")
                .imageUrl("product url")
                .weight(200)
                .build();

        PhysicalProduct iphoneX = PhysicalProduct.builder()
                .productName("Iphone X")
                .shippingCategory(ShippingCategory.PREMIUM)
                .category(smartPhone)
                .productDiscount(10)
                .basePrice(1000)
                .description("Amazing user friendly high tech")
                .imageUrl("product url")
                .weight(200)
                .build();

        DigitalProduct eDictionary = DigitalProduct.builder()
                .productName("E Dictionary")
                .category(eBook)
                .productDiscount(10)
                .basePrice(100)
                .description("Eng-Nl dictionary with rich vocabulary and idioms")
                .imageUrl("product url")
                .build();

        physicalProductRepository.save(iphoneX);
        physicalProductRepository.save(toothBrush);
        digitalProductRepository.save(eDictionary);

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .lineItems(new ArrayList<>())
                .build();

        LineItem lineItem = LineItem.builder()
                .product(iphoneX)
                .quantity(2)
                .build();

        LineItem lineItem1 = LineItem.builder()
                .product(toothBrush)
                .quantity(3)
                .build();

        shoppingCart.getLineItems().add(lineItem);
        shoppingCart.getLineItems().add(lineItem1);

        lineItemRepository.save(lineItem);
        lineItemRepository.save(lineItem1);
        shoppingCartRepository.save(shoppingCart);

        var value = shoppingCartService.getTotalCost(shoppingCart);

        PaymentMethod paymentMethod = new BankAccount(1235465234);
        paymentMethodRepository.save(paymentMethod);

        Payment payment = new Payment(value, paymentMethod);
        paymentRepository.save(payment);

        WebOrder webOrder = WebOrder.builder()
                .user(appUser)
                .payment(payment)
                .shoppingCart(shoppingCart)
                .build();

        webOrderRepository.save(webOrder);*/
    }
}
