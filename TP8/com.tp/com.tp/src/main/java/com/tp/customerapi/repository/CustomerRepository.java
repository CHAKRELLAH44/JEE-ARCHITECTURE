package com.tp.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tp.customerapi.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    void deleteByIdentityRef(String identityRef);
    Customer findByIdentityRef(String identityRef);
}
