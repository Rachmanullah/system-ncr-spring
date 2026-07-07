package com.example.ncrsystem.ncrsystem.service.inbox;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;

import java.util.List;

public interface InboxService {
    List<NCRRequestResponse> findAll();
    NCRRequestResponse approve(NCRRequestDto ncrRequestDto);
    NCRRequestResponse reject(NCRRequestDto ncrRequestDto);
}
