package spring.boot.stripe.demo;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StripeApplication {

	@PostConstruct
	public void setup(){
		Stripe.apiKey = "sk_test_7mJuPfZsBzc3JkrANrFrcDqC";
	}
	public static void main(String[] args) {
		SpringApplication.run(StripeApplication.class, args);
	}

}
