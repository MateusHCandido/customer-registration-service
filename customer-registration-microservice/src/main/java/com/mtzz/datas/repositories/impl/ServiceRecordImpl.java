package com.mtzz.datas.repositories.impl;

import com.mtzz.domains.models.ServiceRecord;
import com.mtzz.domains.repositories.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRecordImpl
{
    @Autowired
    private ServiceRecordRepository serviceRecordRepository;


    public void save(ServiceRecord serviceRecord)
    {
        serviceRecordRepository.save(serviceRecord);
    }

    public List<ServiceRecord> findByCustomerNameAndMonth(String name, Integer month)
    {
        return serviceRecordRepository.findByCustomerNameAndMonth(name, month);
    }
}
