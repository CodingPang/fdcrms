package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/08/03 1:04
 * Description:
 * Version: V1.0
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", value = "/welcomeTo/*")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI(); // 获取Uri
        path = path.substring(path.lastIndexOf("/")); // 截取对应的数据接口
        System.out.println(path);
        if ("/toWelcome".equals(path)) {
            dopageChoose(request, response);
        }
    }

    /**
     * 选择页面去向
     * @param request
     * @param response
     */
    private void dopageChoose(HttpServletRequest request, HttpServletResponse response) {
        // 这里最好使用重定向（浏览器会发送一次全新的请求。）
        // 浏览器在地址栏上发送请求，这个请求是get请求。
        try {
            //response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            request.getRequestDispatcher("/WEB-INF/login_res/welcome.jsp").forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
