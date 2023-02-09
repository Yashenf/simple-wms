package com.yashen.wms.model;

import com.yashen.wms.to.Account;
import com.yashen.wms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountModel {
   public static boolean signUpAcc(Account acc) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("INSERT INTO loggin_details VALUES (?,?,?,?,?,?,?)",acc.getFirstName(),acc.getLastname(),acc.getUserName(),
               acc.getPassword(),acc.getEmail(),acc.getMobile(),acc.getAccType());
   }

    public static ArrayList<String> getUserNames() throws SQLException, ClassNotFoundException {
       ArrayList<String> userNamesList = new ArrayList();
        ResultSet rst = CrudUtil.execute("SELECT user_name FROM loggin_details");
        while (rst.next()){
            userNamesList.add(rst.getString(1));
        }
        return userNamesList;
    }

    public static Account getUser(String un) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM loggin_details WHERE user_name =?", un);
        while (rst.next()){
            return new Account(rst.getString(1),rst.getString(2), rst.getString(3),
                    rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7) );
        }
        return null;
    }

    public static boolean deleteAccount(String value) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("DELETE FROM loggin_details WHERE user_name=?",value);
    }

    public static boolean updateAccount(Account account) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("UPDATE loggin_details SET first_name = ? , last_name = ? , password = ? , email = ?,mobile=?," +
               "accountType = ?",account.getFirstName(),account.getLastname(),account.getPassword(),account.getEmail(),account.getMobile(),account.getAccType());
    }

}
