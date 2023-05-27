package com.mtzz.domains.repositories;

import com.mtzz.domains.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Boolean existsByCpf(String cpf);

    Customer findByCpf(String cpf);
}
