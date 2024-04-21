package com.techsoft.repository;

import com.techsoft.entity.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends JpaRepository<BookOrder, Integer> {
}
