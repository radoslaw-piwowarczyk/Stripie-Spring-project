package spring.boot.stripe.demo.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import spring.boot.stripe.demo.model.CustomerEntity;
import spring.boot.stripe.demo.service.CustomerService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    private final CustomerService customerService;
    @Value("${stripe.apikey}")
    private String stripeKey;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @PostMapping("/customers")
    public String createCustomer(@RequestBody @Valid CustomerEntity customerEntity) throws StripeException {
        return customerService.create(customerEntity);
    }

    @GetMapping("/customers/{customerId}")
    public String getCustomer(@PathVariable String customerId) throws StripeException {
        return customerService.get(customerId);
    }
}
