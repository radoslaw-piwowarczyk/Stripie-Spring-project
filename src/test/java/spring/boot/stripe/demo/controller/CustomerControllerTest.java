package spring.boot.stripe.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnCustomerById() throws Exception {
        //given
        var customerId = "cus_K4g8qg3gKKinrq";
        var url = "/v1/customers/{customerId}";
        var name = "Janusz";
        var email = "janusz@grazyna.pl";

        //when
        mockMvc.perform(get(url, customerId))

                //then
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.email").value(email));
    }
}
