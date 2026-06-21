package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.ncrlogs.NCRLogsRequest;
import com.example.ncrsystem.ncrsystem.model.NCRLogs;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import com.example.ncrsystem.ncrsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class NCRLogsMapper {
    public NCRLogs toEntity(NCRLogsRequest ncrLogsRequest, NCRRequest ncrRequest, User user){
        NCRLogs ncrLogs = NCRLogs.builder()
                .ncrRequest(ncrRequest)
                .user(user)
                .statusName(ncrLogsRequest.getStatusName())
                .notes(ncrLogsRequest.getNotes())
                .orderNumber(ncrLogsRequest.getOrderNumber())
                .build();
        return ncrLogs;
    }
}
