package com.example.kyrgyzstancentralmedicalcard.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class BaseMapper<E, R, S> {

    public abstract E toEntity(R request);
    public abstract S toResponse(E entity);

    protected <Source, Destination> Destination mapFields(Source source, Destination destination) {
        if (source == null || destination == null) {
            return null;
        }

        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        Arrays.stream(destinationClass.getDeclaredFields()).forEach(destinationField -> {
            try {
                Field sourceField = sourceClass.getDeclaredField(destinationField.getName());
                if (sourceField.getType().equals(destinationField.getType())) {
                    sourceField.setAccessible(true);
                    destinationField.setAccessible(true);
                    Object value = sourceField.get(source);
                    destinationField.set(destination, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
            }
        });

        return destination;
    }
}
