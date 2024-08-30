package com.example.dto;

import com.example.constans.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class HandelRequest {

    public static ResponseEntity<Object> createResponse(String message ,HttpStatus status ,Object response){
        Map<String,Object>  map =  new HashMap<>();
        map.put(AppConstants.Message,message);
        map.put(AppConstants.Http_Status,status.value());
        map.put(AppConstants.Data,response);

        return  ResponseEntity.ok(map);


    }

}
