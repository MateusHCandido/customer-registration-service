package com.mtzz.domains.repositories;

import com.mtzz.domains.models.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long>
{
    @Query(
            "SELECT service FROM ServiceRecord service JOIN service.customer customer "
            + "WHERE UPPER(customer.customerName) LIKE UPPER(:customerName) AND MONTH(service.serviceDate) =:month"
    )
    List<ServiceRecord> findByCustomerNameAndMonth
            (
                    @Param("customerName") String name, @Param("month") Integer month
            );
}
