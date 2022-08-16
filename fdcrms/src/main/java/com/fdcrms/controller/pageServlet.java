package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/08/04 22:02
 * Description:
 * Version: V1.0
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "pageServlet", value = "/page/*")
public class pageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI(); // 获取Uri
        path = path.substring(path.lastIndexOf("/")); // 截取对应的数据接口
        System.out.println(path);

        if ("/login".equals(path)){
            toLogin(request,response);
        } else if ("/register".equals(path)){
            toMemRegister(request, response);
        }
        else if ("/billAdd".equals(path)){
            toBillAdd(request,response);
        } else if ("/memAdd".equals(path)){
            toMemAdd(request,response);
        }
    }

    /**
     * 重定向到注册页面
     * @param request
     * @param response
     */
    private void toMemRegister(HttpServletRequest request, HttpServletResponse response) {
        // 重定向到注册页面
        String contextPath = request.getContextPath();
        try {
           // response.sendRedirect(contextPath + "/register.jsp");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发到家庭成员新增页面
     * @param request
     * @param response
     */
    private void toMemAdd(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getRequestDispatcher( "/WEB-INF/member_res/memAdd.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发到账单新增页面
     * @param request
     * @param response
     */
    private void toBillAdd(HttpServletRequest request, HttpServletResponse response) {
        // 转发到账单新增页面
        String contextPath = request.getContextPath();
        try {
            request.getRequestDispatcher( "/WEB-INF/bill_res/add.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重定向到登录页
     * @param request
     * @param response
     */
    private void toLogin(HttpServletRequest request, HttpServletResponse response) {
        // 重定向到列表页面
        String contextPath = request.getContextPath();
        try {
            response.sendRedirect(contextPath + "/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
