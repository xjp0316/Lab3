package com.isep.testjpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Emp {
    @Id
    private Long empno;

    private String ename;
    private String efirst;
    private String job;
    private Long mgr;
    private Long sal;
}
