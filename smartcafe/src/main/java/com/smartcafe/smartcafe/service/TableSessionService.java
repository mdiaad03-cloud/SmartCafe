package com.smartcafe.smartcafe.service;

import com.smartcafe.smartcafe.model.TableSession;

public interface TableSessionService {
    TableSession startSession(Integer tableId);
    TableSession getActiveSession(Integer tableId);
    TableSession endSession(Integer tableId);
}
