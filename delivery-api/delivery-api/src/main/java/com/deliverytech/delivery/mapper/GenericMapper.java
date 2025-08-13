package com.deliverytech.delivery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Entity;

import org.mapstruct.factory.Mappers;

@SuppressWarnings("hiding")
public interface GenericMapper<Entity, DTO> {

    DTO toTarget(Entity source); // Retorna um DTO

    Entity toSource(DTO target); // Retorna uma Entidade

    static <Entity, DTO> GenericMapper<Entity, DTO> of(Class<GenericMapper<Entity, DTO>> mapperClass) {
        return Mappers.getMapper(mapperClass);
    }

    default List<DTO> toTargetList(List<Entity> sourceList) {
        if (sourceList == null)
            return null;
        return sourceList.stream()
                .map(this::toTarget)
                .collect(Collectors.toList());
    }

    default List<Entity> toSourceList(List<DTO> targetList) {
        if (targetList == null)
            return null;
        return targetList.stream()
                .map(this::toSource)
                .collect(Collectors.toList());
    }
}
