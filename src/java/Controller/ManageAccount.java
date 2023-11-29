/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ManageAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String Email = req.getParameter("Email");
        int isAdmin = Integer.parseInt(req.getParameter("isAdmin"));
        int isSeller = Integer.parseInt(req.getParameter("isSeller"));
        
        if(req.getParameter("add") != null){
            User u = new User(username, password, Email, isAdmin, isSeller);
            System.out.println(username + password + Email+isAdmin+isSeller);

            u.add();
        }else if(req.getParameter("save") != null){
            int uid = Integer.parseInt(req.getParameter("uid"));
            User u = new User(uid, username, password, Email, isAdmin, isSeller);
            u.update();
        }
        
        resp.sendRedirect("managea");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u2 = (User) session.getAttribute("acc");
        User u = new User();
        String uid = req.getParameter("uid");
        if (uid != null) {
            u.delete(uid);
        }
        ArrayList<User> list2 = u.getListUser();

        req.setAttribute("data", list2);
        req.getRequestDispatcher("ManageAccount.jsp").forward(req, resp);
    }

}
