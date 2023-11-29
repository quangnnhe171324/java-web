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


public class Category extends DBContext {

    String cid;
    String cname;

    public Category(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
        connect();
    }

    public Category() {
        connect();
    }

    public String getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    Connection cnn; //Dung de ket noi DB
    Statement stm; //Thuc thi cac cau lenh sql
    PreparedStatement pstm; //Ban nang cap cua stm
    ResultSet rs; //Luu tru va xu ly du lieu

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect successfully to" + cid);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public ArrayList<Category> getListCategory() {
        ArrayList<Category> data = new ArrayList<Category>();
        try {
            String strSelect = "select * from Category ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String cid = String.valueOf(rs.getInt(1));
                String cname = rs.getString(2);

                Category c = new Category(cid, cname);
                data.add(c);
            }
        } catch (Exception e) {
            System.out.println("getListCategory:" + e.getMessage());
        }
        return data;
    }

    public String getCnamebyCID(String cid) {
        String cname = " ";
        try {
            String strSelect = "select Name from Category "
                    + "where CategoryID = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(cid));
            rs = pstm.executeQuery();

            while (rs.next()) {
                cname = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("getListCategory:" + e.getMessage());
        } 
        return cname;
    }
}
