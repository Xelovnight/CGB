package cgb.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgb.transfer.entity.Customer;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}