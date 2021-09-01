package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtils;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.model.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?)",customer.getcID(),customer.getName(),customer.getAddress(),0);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtils.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public Customer search(String id) throws Exception {
        ResultSet rst = CrudUtils.executeQuarry("SELECT * FROM Customer where id=?", id);
        if (rst.next()) {
            return new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        }
        return null;    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtils.executeUpdate("UPDATE Customer SET name=?, address=? WHERE id=?",customer.getName(),customer.getAddress(),customer.getcID());
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet rst = CrudUtils.executeQuarry("SELECT * FROM Customer");
        ArrayList<Customer> alCustomers = new ArrayList<>();
        while (rst.next()) {

            Customer customer = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));

            alCustomers.add(customer);
        }
        return alCustomers;    }
}
