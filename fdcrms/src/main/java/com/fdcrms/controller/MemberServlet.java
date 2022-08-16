package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/07/30 14:27
 * Description:
 * Version: V1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdcrms.service.MemberService;
import com.fdcrms.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "MemberServlet", value = "/member/*")
public class MemberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI(); // 获取Uri
        path = path.substring(path.lastIndexOf("/")); // 截取对应的数据接口
        System.out.println(path);
        // 实例化业务逻辑层
        MemberService memberService = new MemberServiceImpl();
        // 定义map集合
        HashMap<String, Object> map = null;
        // 实例化jackson对象
        ObjectMapper mapper = new ObjectMapper();
        // 获得输出标准流对象
        PrintWriter out = response.getWriter();
        if ("/list".equals(path)) {
            doMemberList(request, response, memberService, map);
        } else if ("/save".equals(path)) {
            doMemberSave(request, response, memberService, map);
        } else if ("/detail".equals(path)) {
            doMemberDetail(request, response, memberService, map);
        } else if ("/modify".equals(path)) {
            doMemberModify(request, response, memberService, map);
        } else if ("/delete".equals(path)) {
            doMemberDel(request, response, memberService, map);
        }
    }

    /**
     * 删除家庭成员信息
     * @param request
     * @param response
     * @param memberService
     * @param map
     */
    private void doMemberDel(HttpServletRequest request, HttpServletResponse response, MemberService memberService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号
        try {
            map = memberService.delMember(memNo);
            if (map.get("code").equals(200)) {
                // 删除成功
                // 手动销毁session重定向到登录页面
                HttpSession session = request.getSession(false);
                session.invalidate(); // 手动销毁session
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/index.jsp");
            } else {
                // 跳转到error页面
                HttpSession session = request.getSession(false);
                session.setAttribute("msg", map.get("msg"));
                request.getRequestDispatcher("/error.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }

    /**
     * 修改家庭成员信息
     * @param request
     * @param response
     * @param memberService
     * @param map
     */
    private void doMemberModify(HttpServletRequest request, HttpServletResponse response, MemberService memberService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号
        String memName = request.getParameter("memName"); // 家庭成员姓名
        String memPhone = request.getParameter("memPhone"); // 家庭成员手机号
        String memGender = request.getParameter("memGender"); // 家庭成员性别
        String memAge = request.getParameter("memAge"); // 家庭成员年龄

        try {
            map = memberService.editMember(memNo,memName,memPhone,memGender,memAge);
            if (map.get("code").equals(200)){
                response.sendRedirect(request.getContextPath() + "/member/list");
            } else {
                //HttpSession session = request.getSession(false);
                request.setAttribute("msg", map.get("msg"));
                request.setAttribute("data", map.get("data"));
                request.getRequestDispatcher("/WEB-INF/member_res/edit.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据家庭成员编号获取家庭成员信息
     * @param request
     * @param response
     * @param memberService
     * @param map
     */
    private void doMemberDetail(HttpServletRequest request, HttpServletResponse response, MemberService memberService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号


        try {
            // 调用MemberService.selectMemberById()查询结果
            map = memberService.selectMemberById(memNo);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));
                request.getRequestDispatcher("/WEB-INF/member_res/" + request.getParameter("f") + ".jsp").forward(request, response);
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

    /**
     * 保存家庭成员信息
     * @param request
     * @param response
     * @param memberService
     * @param map
     */
    private void doMemberSave(HttpServletRequest request, HttpServletResponse response, MemberService memberService, HashMap<String, Object> map) {
        // 取出前端传递过来的数据
        String memName = request.getParameter("memName");
        String memPhone = request.getParameter("memPhone");
        String memGender = request.getParameter("memGender");
        String memAge = request.getParameter("memAge");
        String memPwd = request.getParameter("memPwd");
        String reMemPwd = request.getParameter("reMemPwd");

        HttpSession session = request.getSession(false);
        try {
            map = memberService.addOneMem(memName,memPhone,memGender,memAge,memPwd,reMemPwd);
            if (map.get("code").equals(200)){
                request.setAttribute("memberMap",map.get("data"));
                request.getRequestDispatcher("/WEB-INF/member_res/memList.jsp").forward(request,response);
            } else if (session != null){
                session.setAttribute("msg",map.get("msg"));
                request.getRequestDispatcher("/WEB-INF/member_res/memAdd.jsp").forward(request,response);
            } else if (session == null){
                session = request.getSession(); // 创建session
                session.setAttribute("msg", "请先登录！");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
                session.setMaxInactiveInterval(5);      //5秒后自动销毁
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
     * 查询所有的家庭成员信息，将家庭成员信息收集好，然后跳转到JSP做页面展示。
     * @param request
     * @param response
     * @param memberService
     * @param map
     */
    private void doMemberList(HttpServletRequest request, HttpServletResponse response, MemberService memberService, HashMap<String, Object> map) {
        try {
            map = memberService.selectAll();
            if (map.get("code").equals(200)){
                request.setAttribute("memberMap",map.get("data"));
                request.getRequestDispatcher("/WEB-INF/member_res/memList.jsp").forward(request,response);

            } else {
                HttpSession session = request.getSession(false);
                session.setAttribute("msg",map);
                request.getRequestDispatcher("/WEB-INF/member_res/message.jsp").forward(request,response);

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
