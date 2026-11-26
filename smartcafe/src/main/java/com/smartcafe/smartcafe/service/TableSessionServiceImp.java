package com.smartcafe.smartcafe.service;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.smartcafe.smartcafe.model.CafeTable;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.repository.CafeTableRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
import lombok.AllArgsConstructor;
@Service 
@AllArgsConstructor 
public class TableSessionServiceImp implements TableSessionService{
    private final TableSessionRepository tableSessionRepository;
    private final CafeTableRepository cafeTableRepository;
    @Override 
    public TableSession startSession(Integer tableId){
       CafeTable cafeTable= cafeTableRepository.findById(tableId).orElseThrow(()->new RuntimeException("table Not Found"));
       TableSession tableSession=new TableSession();
       TableSession old=tableSessionRepository.findByCafeTableIdAndStatus(tableId, "ACTIVE").orElse(null);
       if(old!=null){
        return old;
       }
        tableSession.setCafeTable(cafeTable);
        tableSession.setCreatedAt(LocalDateTime.now());
        tableSession.setStatus("ACTIVE");
        cafeTable.setStatus("OCCUPIED");
        cafeTableRepository.save(cafeTable);
         return tableSessionRepository.save(tableSession);
    }
    @Override 
    public  TableSession getActiveSession(Integer tableId){
        return tableSessionRepository.findByCafeTableIdAndStatus(tableId, "ACTIVE").orElseThrow(()->new RuntimeException("session not found"));
    }
    @Override 
    public  TableSession endSession(Integer tableId){
        TableSession endSession= tableSessionRepository.findByCafeTableIdAndStatus(tableId, "ACTIVE").orElseThrow(()->new RuntimeException("session not found"));
        endSession.setStatus("ENDED");
        CafeTable cafeTable=endSession.getCafeTable();
        cafeTable.setStatus("AVAILABLE");
        cafeTableRepository.save(cafeTable);
        return tableSessionRepository.save(endSession);
    }
}
