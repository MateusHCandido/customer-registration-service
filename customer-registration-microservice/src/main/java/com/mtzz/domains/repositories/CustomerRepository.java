package com.mtzz.domains.repositories;

import com.mtzz.domains.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Boolean existsByCpf(String cpf);

    Customer findByCpf(String cpf);
}
