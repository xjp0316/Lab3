import dao.DAOFactory;
import dao.DeptDAO;
import bean.Dept;
import bean.Emp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
public class Main {
    public static void main(String[] args) {
        Dept dept = DAOFactory.getDeptDAO().find(20);
        System.out.println(dept);

        Emp emp = DAOFactory.getEmpDAO().find(7369);
        System.out.println(emp);
// Test: Database connection and display
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456";

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC Driver loaded successfully!");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");

            // Test: Display departments with employee info
            displayDepartment(connection);

            // Test: Move employee securely
            moveDepartmentSecure(connection, 7369, 30);

            // Test: Generic table display
            displayTable(connection, "emp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void displayDepartment(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT e.empno, e.ename, d.deptno, d.dname, d.loc " +
                        "FROM emp e JOIN dept d ON e.deptno = d.deptno");

        while (rs.next()) {
            int empno = rs.getInt("empno");
            String ename = rs.getString("ename");
            int deptno = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");

            System.out.println("Emp " + empno + " (" + ename + ") is in Dept " +
                    deptno + " (" + dname + ") located in " + loc);
        }

        rs.close();
        statement.close();
    }

    public static void moveDepartmentSecure(Connection connection, int empno, int newDeptno) throws SQLException {
        String sql = "UPDATE emp SET deptno = ? WHERE empno = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, newDeptno);
        pstmt.setInt(2, empno);
        int updated = pstmt.executeUpdate();

        if (updated > 0) {
            System.out.println("Employee moved successfully (secure).");
        } else {
            System.out.println("No employee found with empno = " + empno);
        }

        pstmt.close();
    }

    public static void displayTable(Connection connection, String tableName) throws SQLException {
        if (!isValidTableName(tableName)) {
            System.out.println("Invalid table name.");
            return;
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.print(meta.getColumnName(i) + "\t");
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }

        rs.close();
        stmt.close();
    }

    private static boolean isValidTableName(String tableName) {
        return tableName.matches("[a-zA-Z_][a-zA-Z0-9_]*"); // basic protection
    }
}
