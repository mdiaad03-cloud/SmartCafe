package com.smartcafe.smartcafe.service;
import com.smartcafe.smartcafe.model.CafeTable;
public interface TableService {
    CafeTable getByQrToken(String qrToken); 
    CafeTable addCafeTable(CafeTable cafeTable);
}
