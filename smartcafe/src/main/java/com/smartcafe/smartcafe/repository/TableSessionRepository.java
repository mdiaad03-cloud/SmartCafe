package com.smartcafe.smartcafe.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.TableSession;
public interface TableSessionRepository extends JpaRepository<TableSession, Integer> {
     Optional<TableSession> findByCafeTableIdAndStatus(Integer tableid, String status);
}
