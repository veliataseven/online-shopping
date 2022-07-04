package com.example.online.shopping.services;

import com.example.online.shopping.dtos.WebOrder.WebOrderDTO;
import com.example.online.shopping.exceptions.AppUser.AppUserNotFoundException;
import com.example.online.shopping.exceptions.Payment.PaymentNotFoundException;
import com.example.online.shopping.exceptions.ShoppingCart.ShoppingCartNotFoundException;
import com.example.online.shopping.models.AppUser;
import com.example.online.shopping.models.Payment;
import com.example.online.shopping.models.ShoppingCart;
import com.example.online.shopping.models.WebOrder;
import com.example.online.shopping.repositories.AppUserRepository;
import com.example.online.shopping.repositories.PaymentRepository;
import com.example.online.shopping.repositories.ShoppingCartRepository;
import com.example.online.shopping.repositories.WebOrderRepository;
import org.springframework.stereotype.Service;

import static com.example.online.shopping.constants.ResponseConstants.*;

@Service
public class WebOrderService implements WebOrderInterface {

    private final WebOrderRepository webOrderRepository;
    private final AppUserRepository appUserRepository;
    private final PaymentRepository paymentRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public WebOrderService(WebOrderRepository webOrderRepository, AppUserRepository appUserRepository,
                           PaymentRepository paymentRepository, ShoppingCartRepository shoppingCartRepository) {
        this.webOrderRepository = webOrderRepository;
        this.appUserRepository = appUserRepository;
        this.paymentRepository = paymentRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public WebOrder createWebOrder(WebOrderDTO webOrderDTO) {

        AppUser appUser = webOrderDTO.getUser();
        Payment payment = webOrderDTO.getPayment();
        ShoppingCart shoppingCart = webOrderDTO.getShoppingCart();

        boolean isAppUserExist = appUserRepository.existsById(appUser.getId());
        if(!isAppUserExist) throw new AppUserNotFoundException(UNSUCCESSFUL_GET_USER_MESSAGE + appUser.getId());

        boolean isPaymentExist = paymentRepository.existsById(payment.getId());
        if(!isPaymentExist) throw new PaymentNotFoundException(UNSUCCESSFUL_GET_PAYMENT_MESSAGE + payment.getId());

        boolean isShoppingCartExist = shoppingCartRepository.existsById(shoppingCart.getId());
        if(!isShoppingCartExist) throw new ShoppingCartNotFoundException(
                UNSUCCESSFUL_GET_SHOPPING_CART_MESSAGE + shoppingCart.getId());

        WebOrder webOrder = WebOrder.builder()
                .user(appUser)
                .payment(payment)
                .shoppingCart(shoppingCart)
                .build();
        return webOrderRepository.save(webOrder);
    }
}
