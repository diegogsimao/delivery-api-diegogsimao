package com.deliverytech.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deliverytech.delivery.DTOs.Requests.ProductDTO;
import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;
import com.deliverytech.delivery.entity.Product;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {
    ProductResponseDTO toProductResponseDTO(Product target);

    List<ProductResponseDTO> toProductResponseDTOList(List<Product> target);
}
