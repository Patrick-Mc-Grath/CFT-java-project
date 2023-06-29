package org.CFT.db;

import org.CFT.cli.DeliveryEmployee;
import org.CFT.cli.DeliveryEmployeeRequest;
import org.CFT.cli.SalesEmployee;
import org.CFT.cli.SalesEmployeeRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<DeliveryEmployee> getAllDeliveryEmployees() throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, salary, national_insurance_number, bank_account_number FROM DeliveryEmployees;");

        List<DeliveryEmployee> delivery_employee_list = new ArrayList<>();

        while (rs.next()) {
            DeliveryEmployee delivery_employee = new DeliveryEmployee (
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("national_insurance_number"),
                    rs.getString("bank_account_number")
            );

            delivery_employee_list.add(delivery_employee);
        }
        return delivery_employee_list;
    }
    public DeliveryEmployee getDeliveryEmployeeById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT id, name, salary, national_insurance_number, bank_account_number" +
                " FROM DeliveryEmployees WHERE id=" + id);

        while (rs.next()) {
            return new DeliveryEmployee (
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("salary"),
                    rs.getString("national_insurance_number"),
                    rs.getString("bank_account_number")
            );
        }
        return null;
    }
    public int createDeliveryEmployee(DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO SalesEmployees (id, name, salary, national_insurance_number, bank_account_number) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, delivery_employee.getName());
        st.setDouble(2, delivery_employee.getSalary());
        st.setString(3, delivery_employee.getNational_insurance_number());
        st.setString(4, delivery_employee.getBank_account_number());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void updateDeliveryEmployee(int id, DeliveryEmployeeRequest delivery_employee) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE DeliveryEmployees SET name = ?, salary = ?, national_insurance_number = ?, bank_account_number = ? WHERE id = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, delivery_employee.getName());
        st.setDouble(2, delivery_employee.getSalary());
        st.setString(3, delivery_employee.getNational_insurance_number());
        st.setString(4, delivery_employee.getBank_account_number());
        st.setInt(5, id);

        st.executeUpdate();
    }

}
