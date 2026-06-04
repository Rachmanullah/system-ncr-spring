package com.example.ncrsystem.ncrsystem.service.priority;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.PriorityMapper;
import com.example.ncrsystem.ncrsystem.dto.priority.PriorityRequest;
import com.example.ncrsystem.ncrsystem.dto.priority.PriorityResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.model.Priority;
import com.example.ncrsystem.ncrsystem.repository.PriorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class PriorityLmpl implements PriorityService{
    private final PriorityRepository priorityRepository;
    private final PriorityMapper priorityMapper;

    public PriorityLmpl(PriorityRepository priorityRepository, PriorityMapper priorityMapper) {
        this.priorityRepository = priorityRepository;
        this.priorityMapper = priorityMapper;
    }

    @Override
    public List<PriorityResponse> findAll() {
        return priorityRepository.findAll()
                .stream()
                .map(priorityMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PriorityResponse create(PriorityRequest request) {
        Priority priority = priorityMapper.toEntity(request);
        priority = priorityRepository.save(priority);
        return priorityMapper.toResponse(priority);
    }

    @Override
    @Transactional
    public PriorityResponse update(BigInteger priorityId, PriorityRequest request) {
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new RuntimeException("Priority not found"));
        priority.setPriorityCode(request.getPriorityCode());
        priority.setSla(request.getSla());
        priority = priorityRepository.save(priority);
        return priorityMapper.toResponse(priority);
    }

    @Override
    @Transactional
    public PriorityResponse delete(BigInteger priorityId) {
        Priority priority = priorityRepository.findById(priorityId).orElseThrow(() -> new RuntimeException("Priority not found"));
        priority.setDeleted(StatusConstant.DELETED);
        priority = priorityRepository.save(priority);
        return priorityMapper.toResponse(priority);
    }
}
