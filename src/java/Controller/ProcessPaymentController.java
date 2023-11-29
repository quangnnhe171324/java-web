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


public class ProcessPaymentController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false means do not create a new session
        if (session != null && session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isLoggedIn")) {
            // User is logged in
            User u = (User) session.getAttribute("acc");
            CartItems c = new CartItems();
            Order o = new Order();
            ShoppingHistory s1 = new ShoppingHistory();
            ShoppingSummary s2 = new ShoppingSummary();
            
            o.getOrderByUid(u.getUserID()); 
            
            ArrayList<CartItems> list = c.getListItemsByUid(u.getUserID());
            int productamount = list.size();
            System.out.println(productamount);
            s2 = new ShoppingSummary(u.getUserID(), o.getTotal(), productamount);
            s2.addItems();
            int orderID = s2.getShoppingSummary(u.getUserID());
            for(int i=0;i<list.size();i++){
                s1 = new ShoppingHistory(String.valueOf(orderID),list.get(i).getPid(), list.get(i).getQuantity(), list.get(i).getSubtotal(), list.get(i).getUid(),
                        list.get(i).getPrice(), list.get(i).getImage(), list.get(i).getName());
                s1.addItems();               
            } 
            
            o.deleteOrderByUid(u.getUserID());
            c.deleteAllByUid(u.getUserID());
            
            resp.sendRedirect("cart");
        } 
    }
    
    
}
