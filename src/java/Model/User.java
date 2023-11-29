/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class User extends DBContext {

    public int UserID;
    public String account, password, email;
    public int isAdmin, isSeller;

    public User(int UserID, String account, String password, String email, int isAdmin, int isSeller) {
        this.UserID = UserID;
        this.account = account;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
        connect();
    }

    public User(String account, String password, String email, int isAdmin, int isSeller) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isSeller = isSeller;
        connect();
    }
    

    public User(String account, String password, String email) {
        this.account = account;
        this.password = password;
        this.email = email;
        connect();
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public User() {
        connect();
    }

    Connection cnn; //Dung de ket noi DB
    Statement stm; //Thuc thi cac cau lenh sql
    PreparedStatement pstm; //Ban nang cap cua stm
    ResultSet rs; //Luu tru va xu ly du lieu

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect successfully to User: " + account);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public int getIsSeller() {
        return isSeller;
    }

    public int getUserID() {
        return UserID;
    }

    public boolean checkUser() {
        try {
            String strSelect = "select * from Users "
                    + "where Account =? and "
                    + "Password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }

    public void addAccount() {
        try {
            String strUpdate = "INSERT INTO Users "
                    + "VALUES (?, ?, ?,'0','0')";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, account);
            pstm.setString(2, password);
            pstm.setString(3, email);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addAccount: " + e.getMessage());
        }
    }

    public boolean checkUserExisted(String account) {
        try {
            String strSelect = "select * from Users "
                    + "where Account='" + account + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                return true;

            }
        } catch (Exception e) {
            System.out.println("checkUserExisted:" + e.getMessage());
        }
        return false;
    }

    public void updatePassword() {
        try {
            String strUpdate = "update Users "
                    + "set Password=? "
                    + "where Account=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, password);
            pstm.setString(2, account);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatePassword: " + e.getMessage());
        }
    }

    public boolean checkInformation(String username, String email) {
        try {
            String strSelect = "select * from Users "
                    + "where Account = ? and Email = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, email);
            rs = pstm.executeQuery();

            while (rs.next()) {
                return false;

            }
        } catch (Exception e) {
            System.out.println("checkInformation:" + e.getMessage());
        }
        return true;
    }

    public void getUserByAccount(String username) {
        try {
            String strSelect = "select * from Users "
                    + "where account='" + username + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                UserID = rs.getInt(1);
                account = rs.getString(2);
                password = rs.getString(3);
                email = rs.getString(4);
                isAdmin = rs.getInt(5);
                isSeller = rs.getInt(6);
            }
        } catch (Exception e) {
            System.out.println("getUserByAccount:" + e.getMessage());
        }
    }

    public ArrayList<User> getListUser() {
        ArrayList<User> data = new ArrayList<User>();
        try {
            String strSelect = "select * from Users ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int UserID = rs.getInt(1);
                String account = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                int isAdmin = rs.getInt(5);
                int isSeller = rs.getInt(6);

                User u = new User(UserID, account, password, email, isAdmin, isSeller);
                data.add(u);
            }
        } catch (Exception e) {
            System.out.println("getListUser:" + e.getMessage());
        }
        return data;
    }

    public void update() {
        try {
            String strUpdate = "update Users "
                    + "set isAdmin=?,"
                    + "isSeller=? "
                    + "where UserID = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, isAdmin);
            pstm.setInt(2, isSeller);
            pstm.setInt(3, UserID);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            String strUpdate = "delete from Users "
                    + "where UserID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, Integer.parseInt(id));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Delete: " + e.getMessage());
        }
    }

    public void add() {
        try {
            String strUpdate = "insert into Users "
                    + "values(?,?,?,?,?)";
            pstm = cnn.prepareStatement(strUpdate);
            System.out.println(account + password + email+isAdmin+isSeller);
            pstm.setString(1, account);
            pstm.setString(2, password);
            pstm.setString(3, email);
            pstm.setInt(4, isAdmin);
            pstm.setInt(5, isSeller);
            
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add: " + e.getMessage());
        }
    }

    public void getUserByID(String uid) {
         try {
            String strSelect = "select * from Users "
                    + "where UserID='" + uid + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                UserID = rs.getInt(1);
                account = rs.getString(2);
                password = rs.getString(3);
                email = rs.getString(4);
                isAdmin = rs.getInt(5);
                isSeller = rs.getInt(6);
            }
        } catch (Exception e) {
            System.out.println("getUserByID:" + e.getMessage());
        }
    }
}
