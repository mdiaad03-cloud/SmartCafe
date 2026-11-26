package com.smartcafe.smartcafe.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.service.TableSessionService;
import lombok.AllArgsConstructor;
@RestController 
@RequestMapping ("/api/session")
@AllArgsConstructor 
public class TableSessionController {
    private final  TableSessionService tableSessionService;
    @PostMapping("start/{tableId}")
    public TableSession addSession(@PathVariable  Integer tableId){
        return tableSessionService.startSession(tableId);
    }
    @PutMapping ("end/{tableId}")
    public TableSession endSession(@PathVariable Integer tableId){
        return tableSessionService.endSession(tableId);
    }
    @GetMapping("active/{tableId}")
    public TableSession activSession(@PathVariable Integer tableId){
        return tableSessionService.getActiveSession(tableId);
    }
}
