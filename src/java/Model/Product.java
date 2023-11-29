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


public class Product extends DBContext {

    String id, name, price, mainImg, subImg1, subImg2, subImg3, description, cid, sid;

    public Product(String id, String name, String price, String mainImg, String subImg1, String subImg2, String subImg3, String description, String cid, String sid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.subImg1 = subImg1;
        this.subImg2 = subImg2;
        this.subImg3 = subImg3;
        this.description = description;
        this.cid = cid;
        this.sid = sid;
        connect();
    }

    public Product(String id, String name, String price, String mainImg, String subImg1, String subImg2, String subImg3, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.subImg1 = subImg1;
        this.subImg2 = subImg2;
        this.subImg3 = subImg3;
        this.description = description;
        connect();
    }

    public Product(String id, String name, String price, String mainImg, String subImg1, String subImg2, String subImg3, String description, String cid) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mainImg = mainImg;
        this.subImg1 = subImg1;
        this.subImg2 = subImg2;
        this.subImg3 = subImg3;
        this.description = description;
        this.cid = cid;
        connect();
    }

    public Product(String id) {
        this.id = id;
        connect();
    }

    public Product() {
        connect();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getMainImg() {
        return mainImg;
    }

    public String getSubImg1() {
        return subImg1;
    }

    public String getSubImg2() {
        return subImg2;
    }

    public String getSubImg3() {
        return subImg3;
    }

    public String getDescription() {
        return description;
    }

    public String getCid() {
        return cid;
    }

    public String getSid() {
        return sid;
    }

    Connection cnn; //Dung de ket noi DB
    Statement stm; //Thuc thi cac cau lenh sql
    PreparedStatement pstm; //Ban nang cap cua stm
    ResultSet rs; //Luu tru va xu ly du lieu

    public void connect() {
        try {
            cnn = super.connection;
            if (cnn != null) {
                System.out.println("Connect successfully to Product: " + id);
            }
        } catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }

    public void getProductByID(String id) {
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where ProductID=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(id));
            rs = pstm.executeQuery();

            while (rs.next()) {
                this.id = String.valueOf(rs.getInt(1));
                name = rs.getString(2);
                price = String.valueOf(rs.getDouble(3));
                mainImg = rs.getString(4);
                subImg1 = rs.getString(5);
                subImg2 = rs.getString(6);
                subImg3 = rs.getString(7);
                description = rs.getString(8);

            }
        } catch (Exception e) {
            System.out.println("getProductByID:" + e.getMessage());
        }
    }

    public void getProductByID2(String id) {
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where ProductID=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, Integer.parseInt(id));
            rs = pstm.executeQuery();

            while (rs.next()) {
                this.id = String.valueOf(rs.getInt(1));
                name = rs.getString(2);
                price = String.valueOf(rs.getDouble(3));
                mainImg = rs.getString(4);
                subImg1 = rs.getString(5);
                subImg2 = rs.getString(6);
                subImg3 = rs.getString(7);
                description = rs.getString(8);
                cid = String.valueOf(rs.getInt(9));
                sid = String.valueOf(rs.getInt(10));
            }
        } catch (Exception e) {
            System.out.println("getProductByID:" + e.getMessage());
        }
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select * from ProductsDetail ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProduct:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListByPage(ArrayList<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }

        return arr;
    }

    public ArrayList<Product> getLatestProduct() {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select top 8 * from ProductsDetail order by ProductID desc ";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProduct:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProductByCid(String cid) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where Cid=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProductByCid:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProductBySid(int sid) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where Sid=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, sid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProductByCid:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProductBySearch(String search) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where [Name] like ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProductBySearch:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProductBySearchAndCid(String search, String cid) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select * from ProductsDetail "
                    + "where [Name] like ? and Cid = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setString(2, cid);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProductBySearch:" + e.getMessage());
        }
        return data;
    }

    public void update() {
        try {
            String strUpdate = "update ProductsDetail "
                    + "set Name=?,"
                    + "Price=?,"
                    + "Img_Main=?,"
                    + "Img_Sub1=?,"
                    + "Img_Sub2=?,"
                    + "Img_Sub3=?,"
                    + "[Description]=?,"
                    + "Cid=?,"
                    + "[Sid]=? "
                    + "where ProductID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setDouble(2, Double.parseDouble(price));
            pstm.setString(3, mainImg);
            pstm.setString(4, subImg1);
            pstm.setString(5, subImg2);
            pstm.setString(6, subImg3);
            pstm.setString(7, description);
            pstm.setString(8, cid);
            pstm.setString(9, sid);
            pstm.setInt(10, Integer.parseInt(id));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Update: " + e.getMessage());
        }
    }

    public void delete(String id) {
        try {
            String strUpdate = "delete from ProductsDetail "
                    + "where ProductID=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setInt(1, Integer.parseInt(id));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Delete: " + e.getMessage());
        }
    }

    public void add() {
        try {
            String strUpdate = "insert into ProductsDetail "
                    + "values(?,?,?,?,?,?,?,?,?)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setDouble(2, Double.parseDouble(price));
            pstm.setString(3, mainImg);
            pstm.setString(4, subImg1);
            pstm.setString(5, subImg2);
            pstm.setString(6, subImg3);
            pstm.setString(7, description);
            pstm.setString(8, cid);
            pstm.setString(9, sid);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("Add: " + e.getMessage());
        }
    }

    public ArrayList<Product> getListRelatedProduct() {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "select top 4 * from ProductsDetail "
                    + "where Cid = ? and ProductID != ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cid);
            pstm.setString(2, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String name = rs.getString(2);
                String price = String.valueOf(rs.getDouble(3));
                String mainImg = rs.getString(4);
                String subImg1 = rs.getString(5);
                String subImg2 = rs.getString(6);
                String subImg3 = rs.getString(7);
                String description = rs.getString(8);
                String cid = rs.getString(9);
                String sid = rs.getString(10);

                Product a = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description, cid, sid);
                data.add(a);
            }
        } catch (Exception e) {
            System.out.println("getListProductBySearch:" + e.getMessage());
        }
        return data;
    }
}
