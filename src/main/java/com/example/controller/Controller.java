package com.example.controller;


import com.example.constans.MessageConfig;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponsedto;
import com.example.dto.HandelRequest;
import com.example.email.EmailService;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.*;

@Data
@RestController
@RequestMapping("/base")
public class Controller {

    ///please create databse base befor the start of application db name = empdbtest
    @Autowired
    private EmployeeService empserv;

    @Autowired
    private ObjectMapper objectmapper;

    @Autowired
    private EmailService emailService ;

    @PostMapping("/saveemp")
    public ResponseEntity<Object> savethestudent(@Valid @RequestBody EmployeeRequestDto empRequeset) {
        String response = ChekcValidationAtDtoLevel(empRequeset);
        if (response == null) {
            Employee employee = new Employee();
            employee.setEmpname(empRequeset.getEmployee_name());
            employee.setEmpdept(empRequeset.getDptd());
            employee.setEmail(empRequeset.getEmail());
            employee.setEmpstatus(Integer.parseInt(empRequeset.getStatus()));
            return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.CREATED, empserv.savetheemployeeeindb(employee));
        } else {
            return HandelRequest.createResponse(MessageConfig.ERROR_MESSAGE, HttpStatus.BAD_REQUEST, null);
        }
    }

    // this type of request is send by http://localhost:8080/base/getbuyid?id=3
    @GetMapping("/getbuyid")
    public ResponseEntity<Object> gettheemployeebyId(@RequestParam("id") String id) {
        Optional<Employee> emp = empserv.getbyidemployee(Integer.parseInt(id));
        if (emp.isPresent()) {
            return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.ACCEPTED, emp.get());
        }
        return HandelRequest.createResponse(MessageConfig.NOT_FOUND, HttpStatus.NOT_FOUND, null);
    }

    @GetMapping("/deletebyid/{id}")
    public ResponseEntity<Object> deletefromempid(@PathVariable String id) {
        Optional<Employee> emp = empserv.getbyidemployee(Integer.parseInt(id));
        if (emp.isPresent()) {
            return HandelRequest.createResponse(MessageConfig.EMP_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED, empserv.deletefromempid(Integer.parseInt(id)));
        } else {
            return HandelRequest.createResponse(MessageConfig.NOT_FOUND, HttpStatus.NOT_FOUND, null);
        }
    }

    @GetMapping("/getlist")
    public ResponseEntity<Object> getthelistofEmployees() {
        List<Employee> listofemployee = empserv.getalltheemployees();
        List<EmployeeResponsedto> response = new ArrayList<>();

        for (Employee emp : listofemployee) {
            EmployeeResponsedto dtoobject = new EmployeeResponsedto();
            dtoobject.setEmployee_name(emp.getEmpname());
            dtoobject.setDepartmentofemployee(emp.getEmpdept());
            dtoobject.setActive_status(emp.getEmpstatus());
            dtoobject.setDateOfcreation(emp.getCreatedon());
            response.add(dtoobject);
        }
        return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.OK, response);
    }

    @PutMapping("/update_emp")
    public ResponseEntity<Object> updateemployees(@RequestParam String id, @RequestBody EmployeeRequestDto empdto) {
        Optional<Employee> emp = empserv.getbyidemployee(Integer.parseInt(id));
        System.out.println(emp);
        if (emp.isPresent()) {
            Employee empupdate = emp.get();
            empupdate.setEmpname(empdto.getEmployee_name());
            empupdate.setEmpdept(empdto.getDptd());
            empupdate.setEmail(empdto.getEmail());
            empupdate.setEmpstatus(Integer.parseInt(empdto.getStatus()));
            empserv.savetheemployeeeindb(empupdate);
            return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.ACCEPTED, empupdate);
        }
        return HandelRequest.createResponse(MessageConfig.NOT_FOUND, HttpStatus.NOT_ACCEPTABLE, null);
    }

    @PutMapping("/saveImage")
    public ResponseEntity<Object> AddTheImageToEmployee(@RequestParam("id") String id, @RequestParam("image") MultipartFile file)
            throws IOException {
        Employee emp_repose = empserv.savetheimageofemployee(Integer.parseInt(id), file);
        return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.CREATED, emp_repose);
    }

    @PostMapping("/saveempusingobjmapper")
    public ResponseEntity<Object> SaveTheEmployeeUsingObjectMapper(@RequestParam("pojo") String employee_pojo, @RequestParam("image") MultipartFile file) throws IOException {
        try {
            EmployeeRequestDto dto = objectmapper.readValue(employee_pojo, EmployeeRequestDto.class);
            String response = ChekcValidationAtDtoLevel(dto);

            if (response == null) {
                Employee employee = new Employee();
                employee.setEmpname(dto.getEmployee_name());
                employee.setEmpdept(dto.getDptd());
                employee.setEmail(dto.getEmail());
                employee.setEmpstatus(Integer.parseInt(dto.getStatus()));
                employee.setPhoto(file.getBytes());
                return HandelRequest.createResponse(MessageConfig.SUCCESS_MESSAGE, HttpStatus.CREATED, empserv.savetheemployeeeindb(employee));
            } else {
                return HandelRequest.createResponse(MessageConfig.ERROR_MESSAGE, HttpStatus.BAD_REQUEST, response);
            }
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(MessageConfig.INVALID_JSON_fORMAT + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getImagerbyid")
    public ResponseEntity<?> DispalyTheImageOfuser(@RequestParam("id") String id) {
        Optional<Employee> empopt = empserv.getbyidemployee(Integer.parseInt(id));

        if (empopt.isPresent()) {
            Employee emp = empopt.get();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "").contentType(MediaType.IMAGE_PNG).body(emp.getPhoto());
        }
        return HandelRequest.createResponse(MessageConfig.NOT_FOUND, HttpStatus.NOT_FOUND, null);
    }

    @GetMapping("/sendmail")
    public String sendtheemail(){
        emailService.sendthesimpleemailmess();
        return  MessageConfig.SUCCESS_MESSAGE;
    }
    @GetMapping("/sendmailv1")
    public String sendthecomplexemail(@RequestParam("txt") String message) throws MessagingException {
        emailService.sendthecomplexmain(message);
        return  MessageConfig.SUCCESS_MESSAGE;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodtopushvaildatiotofrontend(MethodArgumentNotValidException ErrorGenerated) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allerrors = ErrorGenerated.getBindingResult().getAllErrors();
        for (ObjectError error : allerrors) {
            String fieldname = ((org.springframework.validation.FieldError) error).getField();
            String errormessage = error.getDefaultMessage();
            errors.put(fieldname, errormessage);
        }
        return errors;
    }

    public String ChekcValidationAtDtoLevel(EmployeeRequestDto empldto) {
        if (empldto.getEmployee_name() == null || empldto.getEmployee_name().isEmpty()) {
            return MessageConfig.EMP_NAME_NEEDE;
        }
        if (empldto.getEmail() == null || empldto.getEmail().isEmpty()) {
            return MessageConfig.Emp_EMAIL_NEEDED;
        }

        if (empldto.getDptd() == null || empldto.getDptd().isEmpty()) {
            return MessageConfig.EMP_DEPT_NEEDED;
        }

        Optional<Employee> empobject = empserv.findouttheremialispersentornot(empldto.getEmail());
        if (empobject.isPresent()) {
            return MessageConfig.EMP_EMAIL_ALREADY_PRESETNT;
        }
        return null;
    }
}



