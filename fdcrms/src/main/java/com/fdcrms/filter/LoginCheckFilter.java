package com.fdcrms.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author: CodingPang
 * Date: 2022/05/19 18:20
 * Description:
 * Version: V1.0
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        /**
         * 什么情况下不能拦截？
         *      目前写的路径是： /* 表示所有的请求均拦截
         *
         *      用户访问 index.jsp的时候不能拦截
         *      用户已经登陆了，这个需要放行，不能拦截。
         *      用户要去登录，这个也不能拦截。
         *      WelcomeServlet也不能拦截。
         */


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //post请求乱码问题
        request.setCharacterEncoding("UTF-8");
        //响应中文乱码问题(此处相当于全局配置了text/html，对于json格式，可能有坑)
        response.setContentType("text/html;charset=UTF-8");

        // 获取请求路径
        String servletPath = request.getServletPath();
        System.out.println(servletPath);

        // 获取不包含工程名的请求全路径
        String requestURI = request.getRequestURI();
        // System.out.println(requestURI);
        //String path = requestURI.substring(requestURI.lastIndexOf("/")); // 截取对应的数据接口
        String path = null; // 截取对应的数据接口
        if (requestURI.contains("/user/login")){
            path = requestURI.substring(requestURI.lastIndexOf("/user/login"));
        }
        else if (requestURI.contains("/page/register")){
            path = requestURI.substring(requestURI.lastIndexOf("/page/register"));
        }else if (requestURI.contains("/user/register")) {
            path = requestURI.substring(requestURI.lastIndexOf("/user/register"));
        }else if (requestURI.contains("/member/")){
            path = requestURI.substring(requestURI.lastIndexOf("/member/"));
        } else if (requestURI.contains("/user/exit")){
            path = requestURI.substring(requestURI.lastIndexOf("/user/exit"));
        }
        //System.out.println(path);

        // getPathInfo 取得 Servlet 后的 URL 名，不包括 URL 参数
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);


        // 做一个入口的验证
        // 获取session（这个session不需要新建的）
        // 只是获取当前session，获取不到返回null
        HttpSession session = request.getSession(false);
        if ("/index.jsp".equals(servletPath)
                || "/welcome".equals(servletPath)
                || "/user/login".equals(path)
                || "/page/register".equals(path)
                || "/user/register".equals(path)
                || "/member/save".equals(path)
                || "/user/exit".equals(pathInfo)
                || (session != null && session.getAttribute("username") != null)) {
            // 继续往下走
            chain.doFilter(request, response);

        }else {
            // 跳转到登录页面
            // 注意，这里有个坑，Filter过滤器当中，必须把要转发的页面写明，而不能依赖于tomcat自动去寻找index.jsp，否则会报错：Cannot open URL.Please check this URL is correct:
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }

    @Override
    public void destroy() {

    }
}
