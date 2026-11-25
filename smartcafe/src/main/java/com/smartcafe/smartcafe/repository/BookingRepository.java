package com.smartcafe.smartcafe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.Booking;
public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
