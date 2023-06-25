package com.mtzz.domains.repositories;

import com.mtzz.domains.models.ServiceRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRegistry, Long>
{
    @Query(
            "SELECT service FROM ServiceRegistry service JOIN service.customer customer "
            + "WHERE UPPER(customer.customerName) LIKE UPPER(:customerName) AND MONTH(service.serviceDate) =:month"
    )
    List<ServiceRegistry> findByCustomerNameAndMonth
            (
                    @Param("customerName") String name, @Param("month") Integer month
            );
}
