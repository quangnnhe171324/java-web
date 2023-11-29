/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Product;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class LoadDataController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        String uid = req.getParameter("uid");
        User u = new User();
        Product p = new Product();
        Category c = new Category();
        ArrayList<Category> listC = c.getListCategory();
        if(pid != null){
            p.getProductByID2(pid); 
            
            req.setAttribute("data", p);
            req.setAttribute("data2", listC);
            req.getRequestDispatcher("EditProduct.jsp").forward(req, resp); 
        }else if(uid != null){
            u.getUserByID(uid);
            
            req.setAttribute("data", u);
            req.getRequestDispatcher("EditAccount.jsp").forward(req, resp); 
        }
    
    }
    
}
