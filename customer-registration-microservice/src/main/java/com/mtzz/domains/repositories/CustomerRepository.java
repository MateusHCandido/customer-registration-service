package com.mtzz.domains.repositories;

import com.mtzz.domains.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Boolean existsByCpf(String cpf);

    Customer findByCpf(String cpf);

    @Query("SELECT c FROM Customer c WHERE c.customer_name = :name")
    List<Customer> findAllByName(@Param("name") String name);
}
