package spring.boot.stripe.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    private String id;
}



