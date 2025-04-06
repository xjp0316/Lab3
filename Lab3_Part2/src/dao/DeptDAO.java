package dao;

import bean.Dept;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeptDAO extends DAO<Dept> {

    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Dept find(int id) {
        Dept dept = null;
        try {
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM dept WHERE deptno = " + id;
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept = new Dept(deptno, dname, loc);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dept;
    }

    @Override
    public boolean create(Dept obj) {
        // 暂不实现
        return false;
    }

    @Override
    public boolean update(Dept obj) {
        // 暂不实现
        return false;
    }

    @Override
    public boolean delete(Dept obj) {
        // 暂不实现
        return false;
    }
}
