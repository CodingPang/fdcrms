package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/07/30 14:46
 * Description:
 * Version: V1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdcrms.pojo.Login;
import com.fdcrms.pojo.Member;
import com.fdcrms.service.LoginService;
import com.fdcrms.service.MemberService;
import com.fdcrms.service.impl.LoginServiceImpl;
import com.fdcrms.service.impl.MemberServiceImpl;
import com.fdcrms.util.ServletJsonHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "LoginServlet", value = "/user/*")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        toExit(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI(); // 获取Uri
        path = path.substring(path.lastIndexOf("/")); // 截取对应的数据接口
        // 实例化业务逻辑层
        LoginService loginService = new LoginServiceImpl();
        // 定义map集合
        HashMap<String, Object> map = null;
        // 实例化jackson对象
        ObjectMapper mapper = new ObjectMapper();
        // 获得输出标准流对象
        PrintWriter out = response.getWriter();
        if ("/login".equals(path)) {
            toLogin(request, response, loginService, map);
        } else if ("/register".equals(path)) {
            toRegister(request, response, loginService, map);
        } else if ("/exit".equals(path)){
            toExit(request,response);
        }


    }

    /**
     * 退出登录账号
     * @param request
     * @param response
     */
    private void toExit(HttpServletRequest request, HttpServletResponse response) {
        // 获取session对象，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 手动销毁session对象
            session.invalidate();
            // 跳转到登录页面
            try {
                response.sendRedirect(request.getContextPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 注册
     *
     * @param request
     * @param response
     * @param loginService
     * @param map
     */
    private void toRegister(HttpServletRequest request, HttpServletResponse response, LoginService loginService, HashMap<String, Object> map) {
        // 取出前端传递过来的数据
        String memName = request.getParameter("memName");
        String memPhone = request.getParameter("memPhone");
        String memGender = request.getParameter("memGender");
        String memAge = request.getParameter("memAge");
        String memPwd = request.getParameter("memPwd");
        String reMemPwd = request.getParameter("reMemPwd");

        HttpSession session = request.getSession(false);
        try {
            map = loginService.register(memName, memPhone, memGender, memAge, memPwd, reMemPwd);
            if (map.get("code").equals(200)) {
                // request.setAttribute("memberMap",map.get("data"));
                // request.getRequestDispatcher("/index.jsp").forward(request,response);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else if (session == null) {
                session = request.getSession(); // 创建session
                session.setAttribute("msg", map.get("msg"));
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                session.setMaxInactiveInterval(5);      //5秒后自动销毁
            } else if (session != null) {
                session.setAttribute("msg", "请先退出登录状态才能注册！");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @param loginService
     * @param map
     */
    private void toLogin(HttpServletRequest request, HttpServletResponse response, LoginService loginService, HashMap<String, Object> map) {
        // 1、获取前端传递的参数
        String phoneNum = request.getParameter("phoneNum");
        String password = request.getParameter("password");


        HashMap<String, Object> login = null;


        try {
            login = loginService.login(phoneNum, password);
            // 登录成功/失败
            if ("登录成功".equals(login.get("msg"))) {
                // 获取session对象（这里的要求是: 必须获取到session，没有session也要新建一个session对象。）
                HttpSession session = request.getSession(); // session对象一定不为null
                Member data = (Member) login.get("data");
                String uName = data.getMemName();
                session.setAttribute("username", uName);

                // 成功，跳转到帐单列表页面
                // response.sendRedirect(request.getContextPath() + "/bill/list");
                response.sendRedirect(request.getContextPath() + "/welcomeTo/toWelcome");
            } else {

                // 失败，跳转到失败页面
                request.setAttribute("msg", login.get("msg"));
                request.setAttribute("phone",phoneNum);
                request.setAttribute("pwd",password);
                //response.sendRedirect(request.getContextPath() + "/index.jsp");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                // session.setMaxInactiveInterval(5); // 由于没有登录成功，但是在没有使用Ajax的前提下，暂用session传递信息，让原来页面的数据保存在原来的页面，5秒后session自动销毁
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
