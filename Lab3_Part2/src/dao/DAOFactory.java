package dao;

import java.sql.Connection;

public class DAOFactory {
    protected static final Connection conn = DBConnection.getInstance();

    public static DeptDAO getDeptDAO() {
        return new DeptDAO(conn);
    }

    public static EmpDAO getEmpDAO() {
        return new EmpDAO(conn);
    }
}
