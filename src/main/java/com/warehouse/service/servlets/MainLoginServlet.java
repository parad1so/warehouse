package com.warehouse.service.servlets;

import com.warehouse.dao.domain.JdbcDaoImpl.UserCrudDaoImpl;
import com.warehouse.dao.domain.documentsDaoJdbc.UserAccountDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(name = "Home", value = "")
public class MainLoginServlet extends HttpServlet {
    /** Поле аккаунт пользователя */
    private UserAccountDao userAccount;
    /** Поле сессии */
    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardLogin(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardLogin(req, resp);
    }


    /**
     * Если пользователь авторизовался, то входит в систему.
     * Пользователю выдаются куки, для доступа к веб-приложению, если пользователь
     * удалит куки, то он вернется на стартовую страницую.
     * Проверка аутентификации пользователя происходит непосредственно с базой данных.
     * @param req принимает на вход запрос.
     * @param resp отправляет ответ пользователю.
     * @throws IOException если целевой ресурс выдает это исключение.
     * @throws ServletException если целевой ресурс выдает это исключение.
     */
    private void forwardLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        userAccount = new UserCrudDaoImpl();
        String name = req.getParameter("Login");
        String password = req.getParameter("password");
        Cookie cookie = new Cookie("Login", "yes");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies
            ) {
                if (c.getName().equals("Login") && session!=null) {
                    resp.sendRedirect(req.getContextPath()+"/window/");
                    return;
            }
            }
        }
        try {
            if (name != null && password != null) {
                if (userAccount.isExist(name, password)) {
                    session = req.getSession();
                    session.setAttribute("userAccount", name);
                    resp.addCookie(cookie);
                    resp.sendRedirect(req.getContextPath()+"/window/");
                } else {
                    req.getServletContext().getRequestDispatcher("/view/html/Login.html").forward(req, resp);
                }
            } else if (session.getAttribute("userAccount") != null) {
                HttpSession session = req.getSession();
                session.removeAttribute("userAccount");
                req.getServletContext().getRequestDispatcher("/view/html/Login.html").forward(req, resp);
            } else req.getServletContext().getRequestDispatcher("/view/html/Login.html").forward(req, resp);
        } catch (NullPointerException e) {
            req.getServletContext().getRequestDispatcher("/view/html/Login.html").forward(req, resp);
        }
    }
}