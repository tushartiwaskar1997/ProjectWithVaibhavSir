package com.example.service;

import com.example.constans.MessageConfig;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empreop;

    public Employee savetheemployeeeindb(Employee emp )
    {
        Employee saveemp =  emp;
        saveemp.setCreatedby(emp.getEmpname());
        saveemp.setCreatedon(LocalDateTime.now()); //we can do by default setting on the sql tables
        return  empreop.save(saveemp);
    }
    public String  deletefromempid(Integer id){
        Optional<Employee> emp =  empreop.findById(id);
        if(emp.isPresent()){
            empreop.deleteById(id);
            return  MessageConfig.EMP_DELETED_SUCCESSFULLY +emp.get().toString();
        }else
        {
            return MessageConfig.Please_Enter_correct_Id ;
        }
    }

    public List<Employee>  getalltheemployees(){
        return empreop.findAll();
    }

    public Optional<Employee> getbyidemployee(Integer id){
        return empreop.findById(id);
    }

    public Optional<Employee> findouttheremialispersentornot(String email){
        return  empreop.findByEmail(email);
    }

    public Employee savetheimageofemployee(Integer id , MultipartFile file) throws IOException{
            Optional<Employee>  empobj =  empreop.findById(id) ;
            if(empobj.isPresent()){
                Employee emp = empobj.get();
                emp.setPhoto(file.getBytes());
                return  empreop.save(emp);
            }
            return null;
    }
}
