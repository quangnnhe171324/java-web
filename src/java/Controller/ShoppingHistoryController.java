/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CartItems;
import Model.Order;
import Model.ShoppingHistory;
import Model.ShoppingSummary;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class ShoppingHistoryController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // false means do not create a new session
         User u = (User) session.getAttribute("acc");
            System.out.println("hi");
            
            ShoppingSummary s1 = new ShoppingSummary();
            
            ArrayList<ShoppingSummary> list = s1.getListByUID(u.getUserID());
            
            req.setAttribute("data", list);
            req.getRequestDispatcher("ShoppingHistory.jsp").forward(req, resp);
        } 
        
        
        
        
        
    }
    
    

