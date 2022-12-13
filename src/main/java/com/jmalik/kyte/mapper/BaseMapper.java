package com.jmalik.kyte.mapper;

public interface BaseMapper<T, S> {

  S mapToDto(T entity);

  T mapToEntity(S dto);
}
