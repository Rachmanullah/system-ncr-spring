package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.priority.PriorityRequest;
import com.example.ncrsystem.ncrsystem.dto.priority.PriorityResponse;
import com.example.ncrsystem.ncrsystem.model.Priority;
import org.springframework.stereotype.Component;

@Component
public class PriorityMapper {
    public Priority toEntity(PriorityRequest request) {
        Priority priority = new Priority();
        priority.setPriorityCode(request.getPriorityCode());
        priority.setSla(request.getSla());
        return priority;
    }

    public PriorityResponse toResponse(Priority priority) {
        if (priority == null) {
            return null;
        }
        PriorityResponse response = new PriorityResponse();
        response.setPriorityId(priority.getPriorityId());
        response.setPriorityCode(priority.getPriorityCode());
        response.setSla(priority.getSla());
        return response;
    }
}
