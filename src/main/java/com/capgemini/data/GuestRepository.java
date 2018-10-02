package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public interface GuestRepository extends PersonBaseRepository<Guest>{
}
