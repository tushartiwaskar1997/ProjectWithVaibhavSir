package com.example.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeeResponsedto {

    private String employee_name;

    private String   Departmentofemployee ;

    private LocalDateTime DateOfcreation;

    private Integer Active_status ;

}
