package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.InboxApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestDto;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import com.example.ncrsystem.ncrsystem.service.inbox.InboxService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InboxController implements InboxApi {
    private final InboxService inboxService;

    @Override
    public ResponseEntity<?> findAll(){
        return ResponseHandler.success(inboxService.findAll());
    }

    @Override
    public ResponseEntity<?> approveNcr(@RequestBody NCRRequestDto ncrRequest){
        return ResponseHandler.success(inboxService.approve(ncrRequest));
    }

    @Override
    public ResponseEntity<?> rejectNcr(@RequestBody NCRRequestDto ncrRequest){
        return ResponseHandler.success(inboxService.reject(ncrRequest));
    }
}
