package com.smartcafe.smartcafe.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.CafeTable;
public interface CafeTableRepository  extends JpaRepository<CafeTable,Integer>{
    Optional<CafeTable> findByQrToken(String qrToken);
}
