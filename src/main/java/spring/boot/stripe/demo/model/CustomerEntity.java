package spring.boot.stripe.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class CustomerEntity {

    @Id
    private String customerId;

    @NotEmpty
    @Size(min = 4, message = "customer name should have at least 4 characters")
    private String name;

    @NotEmpty
    @Email
    private String email;

    public CustomerEntity(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public CustomerEntity() {

    }
}
