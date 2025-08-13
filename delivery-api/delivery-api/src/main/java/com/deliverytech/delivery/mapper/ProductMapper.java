package com.deliverytech.delivery.mapper;

import org.mapstruct.Mapper;

import com.deliverytech.delivery.DTOs.ProductDTO;
import com.deliverytech.delivery.entity.Product;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {

}
