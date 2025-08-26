package com.deliverytech.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deliverytech.delivery.DTOs.Requests.CustomerDTO;
import com.deliverytech.delivery.DTOs.Response.CustomerResponseDTO;
import com.deliverytech.delivery.entity.Customer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper extends GenericMapper<Customer, CustomerDTO> {

    CustomerResponseDTO toCustomerResponseDTO(Customer target);

    List<CustomerResponseDTO> toCustomerResponseDTOList(List<Customer> target);
}
