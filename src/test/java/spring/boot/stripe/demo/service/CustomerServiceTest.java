package spring.boot.stripe.demo.service;


import com.stripe.exception.StripeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring.boot.stripe.demo.model.CustomerEntity;
import spring.boot.stripe.demo.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository customerRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetCustomerById() throws StripeException {
        //given
        var customerId = "cus_K4g8qg3gKKinrq";
        var name = "Janusz";
        var email = "janusz@grazyna.pl";

        //when
        when(customerRepository.getOrThrow(customerId)).thenReturn(new CustomerEntity(customerId, name, email));
        var customer = customerService.getFromRepository(customerId);
        //then
        assertEquals(customerId, customer.getCustomerId());
        assertEquals(name, customer.getName());
        assertEquals(email, customer.getEmail());
    }
}
