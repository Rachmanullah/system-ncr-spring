package com.example.ncrsystem.ncrsystem.service.priority;


import com.example.ncrsystem.ncrsystem.dto.priority.PriorityRequest;
import com.example.ncrsystem.ncrsystem.dto.priority.PriorityResponse;

import java.math.BigInteger;
import java.util.List;

public interface PriorityService {
    List<PriorityResponse> findAll();
    PriorityResponse create(PriorityRequest request);
    PriorityResponse update(BigInteger priorityId, PriorityRequest request);
    PriorityResponse delete(BigInteger priorityId);
}
