package bean;

import java.util.Date;

public class Emp {
    private int empno;
    private String ename;
    private String efirst;
    private String job;
    private Emp mgr; // 递归引用：上级经理是另一个员工
    private Date hiredate;
    private int sal;
    private int comm;
    private int tel;
    private Dept department; // 外键，关联部门

    public Emp() {}

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEfirst() {
        return efirst;
    }

    public void setEfirst(String efirst) {
        this.efirst = efirst;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Emp getMgr() {
        return mgr;
    }

    public void setMgr(Emp mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Dept getDepartment() {
        return department;
    }

    public void setDepartment(Dept department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "bean.Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", efirst='" + efirst + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + (mgr != null ? mgr.getEmpno() : null) +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", tel=" + tel +
                ", department=" + (department != null ? department.getDeptno() : null) +
                '}';
    }

    public void setDept(Dept dept) {
    }
}
