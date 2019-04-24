package com.accumulate.core.service;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;

/**
 *@System: 车贷金融
 *@Auther: hukaijia
 *@Description:
 *@Modified By:
*/
public interface GenericManager<T, ID extends Serializable> {

    boolean exists(ID id);

    T findOne(ID id);

    T save(T object);

    T saveSelective(T object);

    Iterable<T> save(Iterable<T> entities);

    void delete(T object);

    void delete(ID id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    List<T> findAll(Iterable<ID> ids);

    List<T> findAll(Example<T> example);

    List<T> findAll(Specification<T> spec);

    List<T> findAll(Example<T> example, Sort sort);

    List<T> findAll(Specification<T> spec, Sort sort);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Example<T> example, Pageable pageable);

    Page<T> findAll(Specification<T> spec, Pageable pageable);

}
