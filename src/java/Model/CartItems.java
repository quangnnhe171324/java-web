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


public class CartItems extends DBContext {

    String pid, quantity, subtotal, uid, price, image, name;

    public CartItems(String pid, String quantity, String subtotal, String uid, String price, String image, String name) {
        this.pid = pid;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.uid = uid;
        this.price = price;
        this.image = image;
        this.name = name;
        connect();
    }

    public CartItems(String pid, String quantity, String subtotal, String price, String image, String name) {
        this.pid = pid;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.price = price;
        this.image = image;
        this.name = name;
        connect();
    }

    public CartItems() {
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
                System.out.println("Connect successfully to CartItems: " + pid);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public String getPid() {
        return pid;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getUid() {
        return uid;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkAlreadyInCart(int pid, int uid) {
        try {
            String strSelect = "select * from Cart "
                    + "where ProductID = ? "
                    + "and UserID = ? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, pid);
            pstm.setInt(2, uid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkAlreadyInCart:" + e.getMessage());
        }
        return false;
    }

    public int getQuantityIDs(String pid, int uid) {
        int quantity = 0;
        try {
            String strSelect = "select Quantity from Cart "
                    + "where ProductID=? "
                    + "and UserID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(pid));
            pstm.setInt(2, uid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getQuantityIDs:" + e.getMessage());
        }

        return quantity;
    }

    public double getPriceIDs(String pid, int uid) {
        double price = 0;
        try {
            String strSelect = "select Price from Cart "
                    + "where ProductID=? "
                    + "and UserID=?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(pid));
            pstm.setInt(2, uid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                price = rs.getDouble(1);
            }
        } catch (Exception e) {
            System.out.println("getQuantityIDs:" + e.getMessage());
        }

        return price;
    }

    public void addQuantity(String pid, int uid, String quantity) {
        try {
            String strUpdate = "update Cart "
                    + "set Quantity=?, "
                    + "Subtotal=? "
                    + "where ProductID = ? "
                    + "and UserID = ?";
            int q = getQuantityIDs(pid, uid);
            q += Integer.parseInt(quantity);
            double p = getPriceIDs(pid, uid);
            double subtotal = q * p;
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, q);
            pstm.setDouble(2, subtotal);
            pstm.setInt(3, Integer.parseInt(pid));
            pstm.setInt(4, uid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addQuantity: " + e.getMessage());
        }
    }

    public void addItems() {
        try {
            String strUpdate = "insert into Cart "
                    + "values(?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, Integer.parseInt(pid));
            pstm.setInt(2, Integer.parseInt(quantity));
            pstm.setDouble(3, Double.parseDouble(subtotal));
            pstm.setInt(4, Integer.parseInt(uid));
            pstm.setDouble(5, Double.parseDouble(price));
            pstm.setString(6, image);
            pstm.setString(7, name);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("addItems: " + e.getMessage());
        }
    }

    public ArrayList<CartItems> getListItemsByUid(int userID) {
        ArrayList<CartItems> data = new ArrayList<CartItems>();
        try {
            String strSelect = "select * from Cart "
                    + "where UserID = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, userID);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String pid = String.valueOf(rs.getInt(1));
                String quantity = String.valueOf(rs.getInt(2));
                String subtotal = String.valueOf(rs.getDouble(3));
                String uid = String.valueOf(rs.getInt(4));
                String price = String.valueOf(rs.getDouble(5));
                String image = rs.getString(6);
                String name = rs.getString(7);

                CartItems c = new CartItems(pid, quantity, subtotal, uid, price, image, name);
                data.add(c);
            }
        } catch (Exception e) {
            System.out.println("getListItemsByUid:" + e.getMessage());
        }
        return data;
    }

    public void delete(String pid, int userID) {

        try {
            String strUpdate = "delete from Cart "
                    + "where ProductID=? and UserID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, Integer.parseInt(pid));
            pstm.setInt(2, userID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Delete: " + e.getMessage());
        }

    }

    public void update(String pid, int uid, String quantity) {
        try {
            String strUpdate = "update Cart "
                    + "set Quantity=?, "
                    + "Subtotal=? "
                    + "where ProductID = ? "
                    + "and UserID = ?";
            int q = Integer.parseInt(quantity);
            double p = getPriceIDs(pid, uid);
            double subtotal = q * p;
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, q);
            pstm.setDouble(2, subtotal);
            pstm.setInt(3, Integer.parseInt(pid));
            pstm.setInt(4, uid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("update: " + e.getMessage());
        }
    }

    public void deleteAllByUid(int userID) {
        try {
            String strUpdate = "delete from Cart "
                    + "where  UserID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, userID);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteAllByUid: " + e.getMessage());
        }
    }

}
