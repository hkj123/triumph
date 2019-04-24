package com.accumulate.core.service.impl;

import com.accumulate.core.service.MongoGenericManager;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ChenChang on 2017/1/16.
 */
public class MongoGenericManagerImpl<T, ID extends Serializable> implements MongoGenericManager<T, ID> {
    protected MongoRepository<T, ID> repository;

    public MongoGenericManagerImpl(MongoRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public T save(T object) {
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
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public List<T> findAll(Example<T> example) {
        return repository.findAll(example);
    }

    @Override
    public List<T> findAll(Example<T> example, Sort sort) {
        return repository.findAll(example, sort);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

//    @Override
//    public List<T> findAll(Iterable<ID> ids) {
//        return IteratorUtils.toList(repository.findAll(ids).iterator());
//    }
}
