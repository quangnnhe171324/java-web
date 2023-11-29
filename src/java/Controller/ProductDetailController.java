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


public class ProductDetailController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Product p = new Product();
        Category c = new Category();
        p.getProductByID2(id);
        String cname = c.getCnamebyCID(p.getCid());
        ArrayList<Product> list = p.getListRelatedProduct();
        req.setAttribute("p", p);
        req.setAttribute("data", list);
        req.setAttribute("cate", cname);
        req.getRequestDispatcher("products-detail.jsp").forward(req, resp);
    }

}
