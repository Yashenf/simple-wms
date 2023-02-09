package com.yashen.wms.model;

import com.yashen.wms.to.Address;
import com.yashen.wms.to.Customer;
import com.yashen.wms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean saveNewCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String address = customer.getAddress().getNo()+" , "+customer.getAddress().getStreet()+" , "+
                customer.getAddress().getCity()+" , "+customer.getAddress().getState()+" , "+customer.getAddress().getCountry()
                +" , "+customer.getAddress().getZip();

        boolean flag = CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",
                customer.getcId(), customer.getName(), customer.getTp(),address, customer.getEmail());
        return flag;
    }

    public static String getNextCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT c_id FROM customer ORDER BY(c_id) DESC LIMIT 1");
        if (rst != null){
            while (rst.next()){
                return rst.getString(1);
            }
        }
        return "C000";
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customersList = new ArrayList<>();
        if (rst != null){
            while (rst.next()){
                String address = rst.getString(4);
                String[] addArr = address.split(",");
                customersList.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3),
                        rst.getString(5),new Address(addArr[0],addArr[1],addArr[2],addArr[3],addArr[4],addArr[5])));
            }
            return customersList;
        }else {
            return null;
        }

    }

    public static Customer getSelectedCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE c_id =?",id);
        while (rst.next()){
            String address = rst.getString(4);
            String[] addArr = address.split(",");
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3),
                    rst.getString(5),new Address(addArr[0],addArr[1],addArr[2],addArr[3],addArr[4],addArr[5]));
        }
        return null;
    }

    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE c_id = ?", id);

    }

    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String address = customer.getAddress().getNo()+" , "+customer.getAddress().getStreet()+" , "+
                customer.getAddress().getCity()+" , "+customer.getAddress().getState()+" , "+customer.getAddress().getCountry()
                +" , "+customer.getAddress().getZip();

        boolean flag = CrudUtil.execute("UPDATE customer SET  c_name = ? , tp = ? , address = ? , email = ? WHERE c_id = ?",
                customer.getName(), customer.getTp(),address, customer.getEmail(),customer.getcId());
        return flag;
    }
}
/*INSERT INTO customer VALUES ('c002','Amal Perera','0119863574','{"Country":"Srilanka","Distric":"Kaluthara","town":"Mathugama","street":"temple_road","num":"304/A","zip":"12112"}','amal@gmail.com');*/
/*UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;*/