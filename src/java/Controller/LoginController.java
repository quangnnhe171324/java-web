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


public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String repass = req.getParameter("repass");
        User u = new User();
        String result = "";
        if (req.getParameter("register") != null) {
            if (u.checkUserExisted(username)) {
                result = "Username has already existed";
                req.setAttribute("result", result);
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                u = new User(username, password, email);
                u.addAccount();
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else if (req.getParameter("reset") != null) {
            if (u.checkInformation(username, email)) {
                result = "Wrong username or email";
                req.setAttribute("result", result);
                req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
            } else {
                if (!password.equals(repass)) {
                    result = "Repass is different from password";
                    req.setAttribute("result", result);
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("forgotPassword.jsp").forward(req, resp);
                } else {
                    u = new User(username, password);
                    u.updatePassword();
                    req.setAttribute("username", username);
                    req.setAttribute("password", password);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = new User();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String result = req.getParameter("result");

        if (req.getParameter("submit") != null) {
            u = new User(username, password);
            if (!u.checkUser()) {
                result = "Username or password is wrong";
                req.setAttribute("result", result);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                u.getUserByAccount(username);
                HttpSession session = req.getSession();
                session.setAttribute("acc", u);
                session.setAttribute("isLoggedIn", true);
                HttpSession session1 = req.getSession();
                ArrayList<CartItems> carti;
                if (session1.getAttribute("carti") == null) {
                    // The attribute is null in the session
                    carti = new ArrayList<>();
                    req.getRequestDispatcher("home").forward(req, resp);
                } else {
                    // The attribute has a value in the session
                    carti = (ArrayList<CartItems>) session1.getAttribute("carti");
                    for (int i = 0; i < carti.size(); i++) {
                    Product p = new Product();
                    p.getProductByID2(carti.get(i).getPid());
                    CartItems c = new CartItems();
                    Order o = new Order();
                    if (c.checkAlreadyInCart(Integer.parseInt(carti.get(i).getPid()), u.getUserID())) {
                        c.addQuantity(carti.get(i).getPid(), u.getUserID(), carti.get(i).getQuantity());
                    } else {
                        String subtotal = String.valueOf(Double.parseDouble(p.getPrice()) * Double.parseDouble(carti.get(i).getQuantity()));
                        c = new CartItems(carti.get(i).getPid(), carti.get(i).getQuantity(), subtotal, String.valueOf(u.getUserID()), String.valueOf(p.getPrice()), p.getMainImg(), p.getName());
                        c.addItems();
                    }
                    if (o.checkExistedOrder(u.getUserID())) {
                        o.update(u.getUserID());
                    } else {
                        o.add(u.getUserID());
                        o.update(u.getUserID());
                    }
                     }
                    req.getRequestDispatcher("cart").forward(req, resp);
                }

                
                
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

}
