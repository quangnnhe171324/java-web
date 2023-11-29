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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ManageProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String mainImg = req.getParameter("mainImg");
        String subImg1 = req.getParameter("subImg1");
        String subImg2 = req.getParameter("subImg2");
        String subImg3 = req.getParameter("subImg3");
        String description = req.getParameter("description");
        String cate = req.getParameter("cate");
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("acc");
        String sid = String.valueOf(u.getUserID());
        Product p = new Product(id, name, price, mainImg, subImg1, subImg2, subImg3, description, cate, sid);
        if (req.getParameter("add") != null) {
            p.add();
        } else if (req.getParameter("save") != null) {
            p.update();
        }

        resp.sendRedirect("managep");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("acc");
        int sid = u.getUserID();
        Product p = new Product();
        Category c = new Category();

        String pid = req.getParameter("pid");
        if (pid != null) {
            p.delete(pid);
        }
        ArrayList<Category> listC = c.getListCategory();
        ArrayList<Product> list = p.getListProductBySid(sid);

        req.setAttribute("data2", listC);
        req.setAttribute("list", list);
        req.getRequestDispatcher("ManageProduct.jsp").forward(req, resp);
    }

}
