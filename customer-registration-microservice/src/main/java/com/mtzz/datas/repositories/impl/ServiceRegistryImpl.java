package com.mtzz.datas.repositories.impl;

import com.mtzz.domains.models.ServiceRegistry;
import com.mtzz.domains.repositories.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRegistryImpl
{
    @Autowired
    private ServiceRecordRepository serviceRecordRepository;


    public void save(ServiceRegistry serviceRecord)
    {
        serviceRecordRepository.save(serviceRecord);
    }

    public List<ServiceRegistry> findByCustomerNameAndMonth(String name, Integer month)
    {
        return serviceRecordRepository.findByCustomerNameAndMonth(name, month);
    }
}
