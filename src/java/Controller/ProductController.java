/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ProductController extends HttpServlet {

    private ArrayList<Product> sortAsc(ArrayList<Product> list) {
        Collections.sort(list, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                if (Double.parseDouble(p1.getPrice()) > Double.parseDouble(p2.getPrice())) {
                    return 1;
                } else if (Double.parseDouble(p1.getPrice()) < Double.parseDouble(p2.getPrice())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }

    private ArrayList<Product> sortDesc(ArrayList<Product> list) {
        Collections.sort(list, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                if (Double.parseDouble(p1.getPrice()) > Double.parseDouble(p2.getPrice())) {
                    return -1;
                } else if (Double.parseDouble(p1.getPrice()) < Double.parseDouble(p2.getPrice())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        ArrayList<Product> list = new ArrayList<>();
        String cate = req.getParameter("cate");
        String search = req.getParameter("search");
        
        
        
        if (search != null && !cate.equals("0")) {
            list = p.getListProductBySearchAndCid(search, cate);
        }else if(search != null && cate.equals("0")){ 
            list = p.getListProductBySearch(search);       
        } else if (cate.equals("0")) {
            list = p.getListProduct();
        } else {
            list = p.getListProductByCid(cate);
        }



    
        int size = list.size();
        ArrayList<Product> list1 = new ArrayList<>();
        String option = req.getParameter("option");
        if (option == null) {
            option = "0";
        }
        if (option.equals("1")) {
            list1 = sortAsc(list);
        } else if (option.equals("2")) {
            list1 = sortDesc(list);
        } else {
            list1 = list;
        }

        while (size % 4 != 0) {
            list1.add(p);
            size++;
        }

        int page, numberPerPage = 12;
        size = list.size();
        int numPages = (size % numberPerPage == 0 ? (size / numberPerPage) : ((size / numberPerPage) + 1));
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(start + numberPerPage, size);

        ArrayList<Product> list2 = p.getListByPage(list1, start, end);
        ArrayList<Category> listC = c.getListCategory();
        req.setAttribute("data2", listC);
        req.setAttribute("data", list2);
        req.setAttribute("page", page);
        req.setAttribute("num", numPages);
        req.setAttribute("option", option);
        req.setAttribute("cate", cate);
        req.setAttribute("size", size);
        req.setAttribute("search", search);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        Category c = new Category();
        ArrayList<Product> list = new ArrayList<>();
        String cate = req.getParameter("cate");
        String search = req.getParameter("search");

        if (cate == null) {
            cate = "0";
        }
         if (search != null && !cate.equals("0")) {
            list = p.getListProductBySearchAndCid(search, cate);
        }else if(search != null && cate.equals("0")){ 
            list = p.getListProductBySearch(search);       
        } else if (cate.equals("0")) {
            list = p.getListProduct();
        } else {
            list = p.getListProductByCid(cate);
        }

 

        int size = list.size();
        ArrayList<Product> list1 = new ArrayList<>();
        String option = req.getParameter("option");
        if (option == null) {
            option = "0";
        }
        if (option.equals("1")) {
            list1 = sortAsc(list);
        } else if (option.equals("2")) {
            list1 = sortDesc(list);
        } else {
            list1 = list;
        }

        while (size % 4 != 0) {
            list1.add(p);
            size++;
        }

        int page, numberPerPage = 12;
        size = list.size();
        int numPages = (size % numberPerPage == 0 ? (size / numberPerPage) : ((size / numberPerPage) + 1));
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }

        int start, end;
        start = (page - 1) * numberPerPage;
        end = Math.min(start + numberPerPage, size);

        ArrayList<Product> list2 = p.getListByPage(list1, start, end);
        ArrayList<Category> listC = c.getListCategory();
        req.setAttribute("data2", listC);
        req.setAttribute("data", list2);
        req.setAttribute("page", page);
        req.setAttribute("num", numPages);
        req.setAttribute("option", option);
        req.setAttribute("cate", cate);
        req.setAttribute("size", size);
        req.setAttribute("search", search);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

}
