/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CartItems;
import Model.Order;
import Model.Product;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class AddtoCartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false means do not create a new session
        String pid = req.getParameter("pid");
        String quantity = req.getParameter("quantity");
        if (session != null && session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isLoggedIn")) {
            // User is logged in
            User u = (User) session.getAttribute("acc");
            Product p = new Product();
            p.getProductByID2(pid);
            CartItems c = new CartItems();
            Order o = new Order();
            if (c.checkAlreadyInCart(Integer.parseInt(pid), u.getUserID())) {
                c.addQuantity(pid, u.getUserID(), quantity);
            } else {
                String subtotal = String.valueOf(Double.parseDouble(p.getPrice()) * Double.parseDouble(quantity));
                c = new CartItems(pid, quantity, subtotal, String.valueOf(u.getUserID()), String.valueOf(p.getPrice()), p.getMainImg(), p.getName());
                c.addItems();
            }
            if (o.checkExistedOrder(u.getUserID())) {
                o.update(u.getUserID());
            } else {
                o.add(u.getUserID());
                o.update(u.getUserID());
            }

            resp.sendRedirect(req.getHeader("referer"));
        } else {
            // User is not logged in
            HttpSession session1 = req.getSession();
            ArrayList<CartItems> carti;
            if (session1.getAttribute("carti") == null) {
                // The attribute is null in the session
                carti = new ArrayList<>();
                
            } else {
                // The attribute has a value in the session
                carti = (ArrayList<CartItems>) session1.getAttribute("carti");
                
            }
            
            

            if (checkAlreadyInCart(pid, carti)) {
                int vt = findVTProduct(pid, carti);
                carti.get(vt).setQuantity(String.valueOf(Integer.parseInt(carti.get(vt).getQuantity()) + Integer.parseInt(quantity)));
                carti.get(vt).setSubtotal(String.valueOf(Double.parseDouble(carti.get(vt).getPrice()) * Double.parseDouble(quantity)));
            } else {
                Product p = new Product();
                p.getProductByID2(pid);
                String subtotal = String.valueOf(Double.parseDouble(p.getPrice()) * Double.parseDouble(quantity));
                CartItems c = new CartItems(pid, quantity, subtotal, p.getPrice(), p.getMainImg(), p.getName());
                carti.add(c);
            }


// Store the updated cart data back in the session object
            session1.setAttribute("carti", carti);
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    private boolean checkAlreadyInCart(String pid, ArrayList<CartItems> carti) {
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getPid().equals(pid)) {
                return true;
            }
        }
        return false;
    }

    private int findVTProduct(String pid, ArrayList<CartItems> carti) {
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getPid().equals(pid)) {
                return i;
            }
        }
        return -1;
    }

}
