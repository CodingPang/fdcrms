package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/08/14 20:18
 * Description: 统计各种账单
 * Version: V1.0
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdcrms.service.StatisticalService;
import com.fdcrms.service.impl.StatisticalServiceImpl;
import com.fdcrms.util.ServletJsonHandler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "StatisticalServlet", value = "/statistical/*")
public class StatisticalServlet extends HttpServlet {
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
        StatisticalService statisticalService = new StatisticalServiceImpl();
        // 定义map集合
        HashMap<String, Object> map = null;
        if ("/oneBillByMonth".equals(path)) {
            doOneBillByMonth(request, response, statisticalService, map);
        } else if ("/allBillByMonth".equals(path)){
            doAllBillByMonth(request, response, statisticalService, map);
        } else if ("/typeBillByMonth".equals(path)){
            doTypeBillByMonth(request, response, statisticalService, map);
        } else if ("/oneBillByYear".equals(path)){
            doOneBillByYear(request, response, statisticalService, map);
        } else if ("/allBillByYear".equals(path)){
            doAllBillByYear(request, response, statisticalService, map);
        } else if ("/typeBillByYear".equals(path)){
            doTypeBillByYear(request, response, statisticalService, map);
        } else if ("/OneMemAllType".equals(path)){
            doOneMemAllType(request, response, statisticalService, map);
        } else if ("/OneMemOneTypeByMonth".equals(path)){
            doOneMemOneTypeByMonth(request, response, statisticalService, map);
        } else if ("/OneMemOneTypeByYear".equals(path)){
            doOneMemOneTypeByYear(request, response, statisticalService, map);
        }
    }

    /**
     * 按年为单位，统计某个家庭成员某种消费类型的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneMemOneTypeByYear(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo");
        String billType = request.getParameter("billType");
        try {
            map = statisticalService.selectOneMemOneTypeByYear(memNo,billType);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));
                System.out.println("某人的某项年消费总额====>"+map.get("data"));
                request.getRequestDispatcher("/WEB-INF/statistical/oneMemOneTypeByYear.jsp").forward(request,response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按月为单位，统计某个家庭成员某种消费类型的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneMemOneTypeByMonth(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo");
        String billType = request.getParameter("billType");
        try {
            map = statisticalService.selectOneMemOneTypeByMonth(memNo,billType);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));
                System.out.println("某人的某项月消费总额====>"+map.get("data"));
                request.getRequestDispatcher("/WEB-INF/statistical/oneMemOneTypeByMonth.jsp").forward(request,response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询某个用户的所有消费类型
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneMemAllType(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        // String json_str = ServletJsonHandler.ReadAsChars(request); // 调用工具类读取json字符串
        // System.out.println(json_str);
        String memNo = request.getParameter("memNo");
        System.out.println("memNo====>" + memNo);
        try {
            // Json字符串转换为Json
             ObjectMapper mapper = new ObjectMapper(); // 获取jackson对象
            // JsonNode node = mapper.readTree(json_str); // json字符串转换为json树对象
            // String memNo = node.get("memNo").asText(); // 通过name获取value
            map = statisticalService.selectOneMemAllType(memNo);
            if (map.get("data") != null) {
                String json = mapper.writeValueAsString(map.get("data"));
                System.out.println(json);
                PrintWriter out = response.getWriter();
                out.println(json);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按年为单位，统计某种消费类型的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doTypeBillByYear(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        try {
            map = statisticalService.selectTypeBillByYear();
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *  按年为单位，统计整个家庭的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doAllBillByYear(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        try {
            map = statisticalService.selectAllBillByYear();
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按年为单位，统计某个家庭成员的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneBillByYear(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号
        try {
            map = statisticalService.selectOneBillByYear(memNo);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按月为单位，统计某种消费类型的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doTypeBillByMonth(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        //String billType = request.getParameter("billType");
        try {
            map = statisticalService.selectTypeBillByMonth();
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按月为单位，统计整个家庭的消费总额。
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doAllBillByMonth(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        try {
            map = statisticalService.selectAllBillByMonth();
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按月为单位，统计某个家庭成员的消费总额
     *
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneBillByMonth(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号
        try {
            map = statisticalService.selectOneBillByMonth(memNo);
            if (map.get("data") != null) {
                request.setAttribute("data", map.get("data"));

                request.getRequestDispatcher("/WEB-INF/statistical/" + request.getParameter("f") + ".jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
