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


public class Order extends DBContext {

    int uid;
    double subtotal, tax, total;

    public Order(int uid, double subtotal, double tax, double total) {
        this.uid = uid;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        connect();
    }

    public Order(double subtotal, double tax, double total) {
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
        connect();
    }

    public Order() {
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
                System.out.println("Connect successfully to Order: " + uid);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public int getUid() {
        return uid;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public void update(int userID) {
        try {
            String strUpdate = "update OrderSummary "
                    + "set Subtotal = ?,"
                    + "Tax=?,"
                    + "Total=? "
                    + "where UserID = ?";
            CartItems c = new CartItems();
            ArrayList<CartItems> list = c.getListItemsByUid(userID);
            double subtotal = 0, tax = 0, total = 0;
            for (int i = 0; i < list.size(); i++) {
                subtotal += Double.parseDouble(list.get(i).getSubtotal());
            }
            tax = (double) (subtotal / 10);
            total = (double) (subtotal + tax);

            pstm = cnn.prepareStatement(strUpdate);
            pstm.setDouble(1, subtotal);
            pstm.setDouble(2, tax);
            pstm.setDouble(3, total);
            pstm.setInt(4, userID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public boolean checkExistedOrder(int userID) {
        try {
            String strSelect = "select * from OrderSummary "
                    + "where UserID = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, userID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkExistedOrder:" + e.getMessage());
        }
        return false;
    }

    public void add(int userID) {
        try {
            String strUpdate = "INSERT INTO OrderSummary "
                    + "Values(?,'0','0','0')";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, userID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("add: " + e.getMessage());
        }
    }

    public void getOrderByUid(int userID) {
        try {
            String strSelect = "select * from OrderSummary "
                    + "where UserID = ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, userID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                this.uid = rs.getInt(1);
                subtotal = rs.getDouble(2);
                tax = rs.getDouble(3);
                total = rs.getDouble(4);

            }
        } catch (Exception e) {
            System.out.println("getOrderByUid:" + e.getMessage());
        }
    }

    public void deleteOrderByUid(int userID) {
        try {
            String strUpdate = "delete from OrderSummary "
                    + "where  UserID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, userID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteOrderByUid: " + e.getMessage());
        }
    }

}
