package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/07/30 0:37
 * Description:
 * Version: V1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdcrms.service.BillService;
import com.fdcrms.service.impl.BillServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "BillServlet", value = "/bill/*")
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String json_str = ServletJsonHandler.ReadAsChars(request); // 调用工具类读取json字符串
        //System.out.println(json_str);

        String path = request.getRequestURI(); // 获取Uri
        path = path.substring(path.lastIndexOf("/")); // 截取对应的数据接口
        System.out.println(path);
        // 实例化业务逻辑层
        BillService billService = new BillServiceImpl();
        // 定义map集合
        HashMap<String, Object> map = null;
        // 实例化jackson对象
        ObjectMapper mapper = new ObjectMapper();
        // 获得输出标准流对象
        PrintWriter out = response.getWriter();


        if ("/list".equals(path)){
            doBillList(request,response,billService,map);
        } else if ("/save".equals(path)){
            doBillSave(request,response,billService,map);
        }else if ("/detail".equals(path)) {
            doDetail(request, response,billService,map);
        } else if ("/modify".equals(path)){
            doModify(request,response,billService,map);
        } else if ("/delete".equals(path)){
            doDel(request, response, billService, map);
        }
    }

    /**
     * 删除账单信息
     * @param request
     * @param response
     * @param billService
     * @param map
     */
    private void doDel(HttpServletRequest request, HttpServletResponse response, BillService billService, HashMap<String, Object> map) {
        String billNo = request.getParameter("billNo"); // 账单编号
        try {
            map = billService.delBill(billNo);
            if (map.get("code").equals(200)) {
                // 删除成功
                // 重定向到列表页面
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/bill/list");
            } else {
                // 跳转到error页面
                request.setAttribute("msg", map.get("msg"));
                request.getRequestDispatcher("/WEB-INF/bill_res/message.jsp").forward(request, response);
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
     * 修改账单信息
     * @param request
     * @param response
     * @param billService
     * @param map
     */
    private void doModify(HttpServletRequest request, HttpServletResponse response, BillService billService, HashMap<String, Object> map) {
        String billNo = request.getParameter("billNo"); // 账单编号
        String billDate = request.getParameter("billDate"); // 账单日期
        String billType = request.getParameter("billType"); // 消费类型
        String billMoney = request.getParameter("billMoney"); // 消费金额
        String billConsumer = request.getParameter("billConsumer"); // 消费人

        try {
            map = billService.editBill(billNo, billDate, billType, billMoney, billConsumer);
            if (map.get("code").equals(200)){
                response.sendRedirect(request.getContextPath() + "/bill/list");
            } else {
                request.setAttribute("msg", map.get("msg"));
                request.getRequestDispatcher("/WEB-INF/bill_res/message.jsp").forward(request,response);
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
     * 根据账单编号获取账单信息
     * @param request
     * @param response
     * @param billService
     * @param map
     */
    private void doDetail(HttpServletRequest request, HttpServletResponse response, BillService billService, HashMap<String, Object> map) {
        String billNo = request.getParameter("billNo");

        // 调用BillService.selectBillById()查询结果
        try {
            map =billService.selectBillById(billNo);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));
                request.getRequestDispatcher("/WEB-INF/bill_res/" + request.getParameter("f") + ".jsp").forward(request, response);
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
     * 保存账单信息
     * @param request
     * @param response
     * @param billService
     * @param map
     */
    private void doBillSave(HttpServletRequest request, HttpServletResponse response, BillService billService, HashMap<String, Object> map) {
        // 取出前端传递过来的数据
        String billDate = request.getParameter("billDate");
        System.out.println(billDate);
        String billType = request.getParameter("billType");
        String billMoney = request.getParameter("billMoney");
        String billConsumer = request.getParameter("billConsumer");

        try {
            map = billService.save(billDate, billType, billMoney, billConsumer);
            if ("新增账单记录成功".equals(map.get("msg"))) {
                // 执行成功
                // 重定向(如果使用转发，刷新一次浏览器，就会新发一次一模一样的带参数请求，数据库会新增相应的重复部门)
               // session.setAttribute("username",session.getAttribute("username"));
                response.sendRedirect(request.getContextPath() + "/bill/list");
            } else {
                HttpSession session = request.getSession(false);
                session.setAttribute("msg", map.get("msg"));
                /*request.getRequestDispatcher("/WEB-INF/bill_res/message.jsp").forward(request,response);*/
                // 转发
                request.getRequestDispatcher("/WEB-INF/bill_res/memAdd.jsp").forward(request,response);
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
     * 查询所有的消费账单信息，将账单信息信息收集好，然后跳转到JSP做页面展示。
     * @param request
     * @param response
     * @param billService
     * @param map
     */
    private void doBillList(HttpServletRequest request, HttpServletResponse response, BillService billService, HashMap<String, Object> map) {
        try {
            map = billService.selectAll();
            // 将map对象的内容转换成json字符串并输出到前端
            // String mapToJosn = mapper.writeValueAsString(map);
            //System.out.println(mapToJosn);
            // session.setAttribute("username",session.getAttribute("username"));
            request.setAttribute("billMap",map);
            request.getRequestDispatcher("/WEB-INF/bill_res/list.jsp").forward(request, response);
            //out.println(mapToJosn);
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
