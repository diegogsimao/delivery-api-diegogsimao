package com.deliverytech.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deliverytech.delivery.DTOs.CustomerDTO;
import com.deliverytech.delivery.entity.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO> {
}
