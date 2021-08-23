package spring.boot.stripe.demo.service;

import com.stripe.exception.StripeException;
import spring.boot.stripe.demo.logger.AbstractLogger;
import spring.boot.stripe.demo.model.CustomerEntity;

public interface CustomerService extends AbstractLogger {

    String get(String customerId) throws StripeException;

    String create(CustomerEntity customerEntity) throws StripeException;

    CustomerEntity getFromRepository(String id);
}
