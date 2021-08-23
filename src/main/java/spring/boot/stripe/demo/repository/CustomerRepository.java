package spring.boot.stripe.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.stripe.demo.model.CustomerEntity;

import javax.persistence.EntityNotFoundException;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    default CustomerEntity getOrThrow(String id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id: " + id + " does not exist."));
    }
}
