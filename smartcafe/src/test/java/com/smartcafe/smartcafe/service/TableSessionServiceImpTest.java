package com.smartcafe.smartcafe.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.smartcafe.smartcafe.model.CafeTable;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.repository.CafeTableRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
@ExtendWith(MockitoExtension.class)
public class TableSessionServiceImpTest {
    @Mock
    private TableSessionRepository tableSessionRepository;
    @Mock
    private CafeTableRepository cafeTableRepository;
    @InjectMocks
    private TableSessionServiceImp tableSessionService;
    @Test
    void startSession_createsNewActiveSession_whenNoneExists(){
        CafeTable cafeTable=new CafeTable();
        cafeTable.setId(1);
        cafeTable.setStatus("AVAILABLE");
        when(cafeTableRepository.findById(1)).thenReturn(Optional.of(cafeTable));
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.empty());
        when(tableSessionRepository.save(any(TableSession.class))).thenAnswer(inv->inv.getArgument(0));
        TableSession result=tableSessionService.startSession(1);
        assertEquals("ACTIVE", result.getStatus());
        assertEquals("OCCUPIED", cafeTable.getStatus());
        assertEquals(cafeTable, result.getCafeTable());
        verify(cafeTableRepository).save(cafeTable);
        verify(tableSessionRepository).save(any(TableSession.class));
    }
    @Test
    void startSession_returnsExistingSession_whenAlreadyActive(){
        CafeTable cafeTable=new CafeTable();
        cafeTable.setId(1);
        TableSession existing=new TableSession();
        existing.setId(10);
        existing.setStatus("ACTIVE");
        when(cafeTableRepository.findById(1)).thenReturn(Optional.of(cafeTable));
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.of(existing));
        TableSession result=tableSessionService.startSession(1);
        assertEquals(10, result.getId());
        verify(tableSessionRepository, never()).save(any());
    }
    @Test
    void getActiveSession_throws_whenNotFound(){
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()->tableSessionService.getActiveSession(1));
    }
    @Test
    void endSession_setsEndedAndTableAvailable(){
        CafeTable cafeTable=new CafeTable();
        cafeTable.setId(1);
        cafeTable.setStatus("OCCUPIED");
        TableSession tableSession=new TableSession();
        tableSession.setId(5);
        tableSession.setStatus("ACTIVE");
        tableSession.setCafeTable(cafeTable);
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.of(tableSession));
        when(tableSessionRepository.save(any(TableSession.class))).thenAnswer(inv->inv.getArgument(0));
        TableSession result=tableSessionService.endSession(1);
        assertEquals("ENDED", result.getStatus());
        assertEquals("AVAILABLE", cafeTable.getStatus());
        verify(cafeTableRepository).save(cafeTable);
        verify(tableSessionRepository).save(tableSession);
    }
}