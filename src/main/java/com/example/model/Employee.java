package com.example.model;


import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="emploees")
@Data
public class Employee {
    /*
    // id ,empname ,empdept ,createdon ,createdby ,isActive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Integer  id ;

    @NotNull(message = "Employee name should be present  please check")
    @Size(min = 3 ,max = 8 ,message = "length must be between 3 to  8 char")
    private String empname;

    @NotNull(message = "Employee  department should not be null !!")
    private String   empdept;

    @NotNull(message = "Employee should have proper email")
    @NotEmpty(message = "Employee should  be their")
    @Email(message = "enter in proerp format")
    @Column(name ="email",unique = true)
    private String email ;
    @Lob
    private byte[] photo ;
    private LocalDateTime   createdon ;
    private String createdby ;
    //@Column(name="isActive")
    @NotNull(message = "employee status must be 1 or 0 ")
    @Min(value = 0, message = "status must be 0 or 1")
    @Max(value = 1, message = "status must be 0 or 1")
    private Integer empstatus ;

    public Employee() {
        super();
    }

    public Employee(Integer id, String empname, String empdept, String email, byte[] photo, LocalDateTime createdon, String createdby, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.photo = photo;
        this.createdon = createdon;
        this.createdby = createdby;
        this.empstatus = empstatus;
    }

    public Employee(Integer id, String empname, String empdept, String email , LocalDateTime createdon, String createdby, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.createdon = createdon;
        this.createdby = createdby;
        this.empstatus = empstatus;
    }

    public Employee(Integer id, String empname, String empdept,String email, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.empstatus = empstatus;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public @NotNull(message = "Employee name should be present  please check") @Size(min = 3, max = 8, message = "length must be between 3 to  8 char") String getEmpname() {
        return empname;
    }

    public void setEmpname(@NotNull(message = "Employee name should be present  please check") @Size(min = 3, max = 8, message = "length must be between 3 to  8 char") String empname) {
        this.empname = empname;
    }

    public @NotNull(message = "Employee  department should not be null !!") String getEmpdept() {
        return empdept;
    }

    public void setEmpdept(@NotNull(message = "Employee  department should not be null !!") String empdept) {
        this.empdept = empdept;
    }

    public @NotNull(message = "Employee should have proper email") @NotEmpty(message = "Employee should  be their") @Email(message = "enter in proerp format") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Employee should have proper email") @NotEmpty(message = "Employee should  be their") @Email(message = "enter in proerp format") String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedon() {
        return createdon;
    }

    public void setCreatedon(LocalDateTime createdon) {
        this.createdon = createdon;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public @NotNull(message = "employee status must be 1 or 0 ") @Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(@NotNull(message = "employee status must be 1 or 0 ") @Min(value = 0, message = "status must be 0 or 1") @Max(value = 1, message = "status must be 0 or 1") Integer empstatus) {
        this.empstatus = empstatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empname='" + empname + '\'' +
                ", empdept='" + empdept + '\'' +
                ", email='" + email + '\'' +
                ", createdon=" + createdon +
                ", createdby='" + createdby + '\'' +
                ", empstatus=" + empstatus +
                '}';
    }

    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer  id ;

    @NotNull(message = "Employee name should be present  please check")
    @Size(min = 3 ,max = 8 ,message = "length must be between 3 to  8 char")
    private String empname;

    @NotNull(message = "Employee  department should not be null !!")
    private String   empdept;

    @NotNull(message = "Employee should have proper email")
    @NotEmpty(message = "Employee should  be their")
    @Email(message = "enter in proerp format")
    @Column(name ="email",unique = true)
    private String email ;
    @Lob
    private byte[] photo ;
    private LocalDateTime   createdon ;
    private String createdby ;
    //@Column(name="isActive")
    @NotNull(message = "employee status must be 1 or 0 ")
    @Min(value = 0, message = "status must be 0 or 1")
    @Max(value = 1, message = "status must be 0 or 1")
    private Integer empstatus ;

    public Employee() {
        super();
    }

    public Employee(Integer id, String empname, String empdept, String email, byte[] photo, LocalDateTime createdon, String createdby, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.photo = photo;
        this.createdon = createdon;
        this.createdby = createdby;
        this.empstatus = empstatus;
    }

    public Employee(Integer id, String empname, String empdept, String email , LocalDateTime createdon, String createdby, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.createdon = createdon;
        this.createdby = createdby;
        this.empstatus = empstatus;
    }

    public Employee(Integer id, String empname, String empdept,String email, Integer empstatus) {
        this.id = id;
        this.empname = empname;
        this.empdept = empdept;
        this.email = email;
        this.empstatus = empstatus;
    }
}
