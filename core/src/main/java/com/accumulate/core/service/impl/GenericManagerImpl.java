package com.accumulate.core.service.impl;

import com.accumulate.core.repository.GenericRepository;
import com.accumulate.core.service.GenericManager;
import com.accumulate.core.web.util.EntityUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @System: 车贷金融
 * @Auther: hukaijia
 * @Description:
 * @Modified By:
 */
@Transactional
public class GenericManagerImpl<T, ID extends Serializable> implements GenericManager<T, ID> {
    protected GenericRepository<T, ID> repository;

    public GenericManagerImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public T saveSelective(T object) {
        object = (T) EntityUtil.emptyValueToNull(object);
        return repository.save(object);
    }

    @Override
    public Iterable<T> save(Iterable<T> entities) {
        return repository.save(entities);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Example<T> example) {
        return repository.findAll(example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Example<T> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Specification<T> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }


}
