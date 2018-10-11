package com.capgemini.data;

import com.capgemini.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public interface PersonBaseRepository<T extends Person> extends CrudRepository<T, Long> {
    @Override
    List<T> findAll();

    T findByMail(String mail);
}
