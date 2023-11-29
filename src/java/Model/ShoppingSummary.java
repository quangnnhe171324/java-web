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


public class ShoppingSummary extends DBContext {

    int OrderID;
    int UserID;
    double total;
    int ProductAmount;

    public ShoppingSummary(int OrderID, int UserID, double total, int ProductAmount) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.total = total;
        this.ProductAmount = ProductAmount;
        connect();
    }

    public ShoppingSummary(int UserID, double total, int ProductAmount) {
        this.UserID = UserID;
        this.total = total;
        this.ProductAmount = ProductAmount;
        connect();
    }

    public ShoppingSummary() {
        connect();
    }

    public int getOrderID() {
        return OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public double getTotal() {
        return total;
    }

    public int getProductAmount() {
        return ProductAmount;
    }

    Connection cnn; //Dung de ket noi DB
    Statement stm; //Thuc thi cac cau lenh sql
    PreparedStatement pstm; //Ban nang cap cua stm
    ResultSet rs; //Luu tru va xu ly du lieu

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect successfully to ShoppingSummary: " + UserID);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public void addItems() {
        try {
            String strUpdate = "insert into ShoppingSummary "
                    + "values(?,?,?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, UserID);
            pstm.setDouble(2, total);
            pstm.setInt(3, ProductAmount);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addItems: " + e.getMessage());
        }
    }

    public int getShoppingSummary(int userID) {
        int order = 0;
        try {
            String strSelect = "select top 1 * from ShoppingSummary "
                    + "where UserID = ? order by OrderID desc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, userID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                order = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getQuantityIDs:" + e.getMessage());
        }

        return order;

    }

    public ArrayList<ShoppingSummary> getListByUID(int userID) {
        ArrayList<ShoppingSummary> data = new ArrayList<ShoppingSummary>();
        try {
            String strSelect = "select * from ShoppingSummary "
                    + "where UserID=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, userID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                int oid = rs.getInt(1);
                int uid = rs.getInt(2);
                double total = rs.getDouble(3);
                int pamount = rs.getInt(4);

                ShoppingSummary s = new ShoppingSummary(oid, uid, total, pamount);
                data.add(s);
            }
        } catch (Exception e) {
            System.out.println("getListByUID:" + e.getMessage());
        }
        return data;
    }

}
