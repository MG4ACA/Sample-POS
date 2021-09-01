package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO{
    public boolean addCustomer(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?)",customer.getcID(),customer.getName(),customer.getAddress(),0);

    }

    public boolean deleteCustomer(String id) throws Exception {
        return CrudUtils.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    public Customer searchCustomer(String id) throws Exception {
        ResultSet rst = CrudUtils.executeQuarry("SELECT * FROM Customer where id=?", id);
        if (rst.next()) {
            return new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        }
        return null;

    }

    public boolean UpdateCustomer(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",customer.getName(),customer.getAddress(),customer.getcID());

    }

    public ArrayList<Customer> getAllCustomers() throws Exception {
        ResultSet rst = CrudUtils.executeQuarry("SELECT * FROM Customer");
        ArrayList<Customer> alCustomers = new ArrayList<>();
        while (rst.next()) {

            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));

            alCustomers.add(customer);

        }
        return alCustomers;
    }
}
