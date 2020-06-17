package com.company.eventApp.service.interfaces;

import java.util.List;

public interface BaseService<T, J>{
    T create(J entity) throws Exception;
    T getById(Long id);
    List<T> getAll();
    void delete(Long id) ;
}
