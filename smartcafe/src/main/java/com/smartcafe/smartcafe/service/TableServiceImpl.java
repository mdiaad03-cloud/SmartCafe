package com.smartcafe.smartcafe.service;
import org.springframework.stereotype.Service;
import com.smartcafe.smartcafe.model.CafeTable;
import com.smartcafe.smartcafe.repository.CafeTableRepository;
import lombok.AllArgsConstructor;
@Service 
@AllArgsConstructor 
public class TableServiceImpl implements TableService{
    private final CafeTableRepository cafeTableRepository;
    @Override
    public CafeTable getByQrToken(String qrToken) {
       return cafeTableRepository.findByQrToken(qrToken).orElseThrow(()->new RuntimeException("Table not found"));
    }
    @Override 
    public CafeTable addCafeTable(CafeTable cafeTable){
       return cafeTableRepository.save(cafeTable);
    }
}