package com.capgemini.data;

import com.capgemini.domain.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Long> {
}
