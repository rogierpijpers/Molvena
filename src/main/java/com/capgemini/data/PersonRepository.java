package com.capgemini.data;

import com.capgemini.domain.Person;

import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends PersonBaseRepository<Person> {
}
