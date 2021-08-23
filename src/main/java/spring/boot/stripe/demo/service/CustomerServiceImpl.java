package spring.boot.stripe.demo.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.StripeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.stripe.demo.model.CustomerEntity;
import spring.boot.stripe.demo.repository.CustomerRepository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Value("${stripe.apikey}")
    private String stripeKey;

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @Override
    public String create(CustomerEntity customerEntity) throws StripeException {
        var customer = mapCustomer(customerEntity);
        customerEntity.setCustomerId(customer.getId());
        customerRepository.save(customerEntity);

        StripeResponse response = null;
        response = customer.getLastResponse();
        if (response != null) {
            return response.body();
        } else
            return null;
    }

    @Override
    public CustomerEntity getFromRepository(String id) {
        return customerRepository.getOrThrow(id);
    }

    @Override
    public String get(String customerId) throws StripeException {
        var customer = Customer.retrieve(customerId);
        StripeResponse response = null;
        response = customer.getLastResponse();
        if (response != null) {
            return response.body();
        } else
            return null;
    }

    private Customer mapCustomer(CustomerEntity customerEntity) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("name", customerEntity.getName());
        customerParams.put("email", customerEntity.getEmail());
        return Customer.create(customerParams);
    }
}