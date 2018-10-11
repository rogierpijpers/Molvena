package com.capgemini.data;

import com.capgemini.domain.Guest;
import com.capgemini.domain.Reservation;
import com.capgemini.domain.Room;
import com.capgemini.domain.RoomType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Override
    @Query("SELECT r from Reservation r where isDeleted = false")
    List<Reservation> findAll();

    List<Reservation> findByIsDeletedTrue();
}
