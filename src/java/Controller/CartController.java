/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CartItems;
import Model.Order;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class CartController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        String quantity = req.getParameter("quantity");
        HttpSession session = req.getSession(false); // false means do not create a new session
        if (session != null && session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isLoggedIn")) {
            // User is logged in
            User u = (User) session.getAttribute("acc");
            CartItems c = new CartItems();
            Order o = new Order();

            if (Integer.parseInt(quantity) <= 0) {

                c.delete(pid, u.getUserID());
                o.update(u.getUserID());
            } else {

                c.update(pid, u.getUserID(), quantity);
                o.update(u.getUserID());
            }
            resp.sendRedirect("cart");

        } else {
            HttpSession session1 = req.getSession();
            ArrayList<CartItems> carti;
            if (session1.getAttribute("carti") == null) {
                // The attribute is null in the session
                carti = new ArrayList<>();

            } else {
                // The attribute has a value in the session
                carti = (ArrayList<CartItems>) session1.getAttribute("carti");
            }
            int vt = timViTriCoProduct(pid, carti);;
            if (Integer.parseInt(quantity) <= 0) {
                carti.remove(vt);

            } else {
                carti.get(vt).setQuantity(quantity);
                carti.get(vt).setSubtotal(String.valueOf(Double.parseDouble(carti.get(vt).getPrice()) * Double.parseDouble(quantity)));

            }
            session1.setAttribute("carti", carti);
            resp.sendRedirect("cart");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false means do not create a new session
        if (session != null && session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isLoggedIn")) {
            // User is logged in
            User u = (User) session.getAttribute("acc");
            CartItems c = new CartItems();
            Order o = new Order();
            o.getOrderByUid(u.getUserID());
            ArrayList<CartItems> list = c.getListItemsByUid(u.getUserID());

            req.setAttribute("order", o);
            req.setAttribute("data", list);
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
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

            double subtotal = findSubtotal(carti);
            double tax = (double) (subtotal / 10);
            double total = (double) (subtotal + tax);
            int uid = -1;
            Order o = new Order(uid,subtotal, tax, total);

            req.setAttribute("order", o);
            req.setAttribute("data", carti);
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
        }
    }

    private double findSubtotal(ArrayList<CartItems> carti) {
        double subtotal = 0;
        for (int i = 0; i < carti.size(); i++) {
            subtotal += Double.parseDouble(carti.get(i).getSubtotal());
        }

        return subtotal;
    }

    private int timViTriCoProduct(String pid, ArrayList<CartItems> carti) {
        for (int i = 0; i < carti.size(); i++) {
            if (carti.get(i).getPid().equals(pid)) {
                return i;
            }
        }
        return -1;
    }

}
