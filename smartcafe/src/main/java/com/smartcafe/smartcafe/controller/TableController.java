package com.smartcafe.smartcafe.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcafe.smartcafe.model.CafeTable;
import com.smartcafe.smartcafe.service.TableService;
import lombok.AllArgsConstructor;
@AllArgsConstructor 
@RestController 
@RequestMapping ("/api/tables")
public class TableController {
    private final TableService tableService;
    @GetMapping ("/{qrToken}")
    public CafeTable getByQrToken(@PathVariable  String qrToken){
        return tableService.getByQrToken(qrToken);
    }
    @PostMapping ("/addTable")
    public CafeTable addCafeTable(@RequestBody CafeTable cafeTable){
        tableService.addCafeTable(cafeTable);
        return cafeTable; 

    }
}
