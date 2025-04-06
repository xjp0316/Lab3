package dao;

import bean.Emp;
import bean.Dept;

import java.sql.*;
import java.util.Date;

public class EmpDAO extends DAO<Emp> {

    public EmpDAO(Connection conn) {
        super(conn);
    }

    @Override
    public Emp find(int id) {
        Emp emp = null;
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM emp WHERE empno = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setEfirst(rs.getString("efirst"));
                emp.setJob(rs.getString("job"));

                int mgrId = rs.getInt("mgr");
                if (!rs.wasNull()) {
                    emp.setMgr(new EmpDAO(connect).find(mgrId));
                }

                emp.setHiredate(rs.getDate("hiredate"));
                emp.setSal(rs.getInt("sal"));
                emp.setComm(rs.getInt("comm"));
                emp.setTel(rs.getInt("tel"));

                int deptno = rs.getInt("deptno");
                emp.setDept(new DeptDAO(connect).find(deptno));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    @Override public boolean create(Emp obj) { return false; }
    @Override public boolean update(Emp obj) { return false; }
    @Override public boolean delete(Emp obj) { return false; }
}
