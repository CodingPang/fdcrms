package com.fdcrms.controller; /**
 * author: CodingPang
 * Date: 2022/08/14 20:18
 * Description: 统计各种账单
 * Version: V1.0
 */

import com.fdcrms.service.StatisticalService;
import com.fdcrms.service.impl.StatisticalServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        if ("/oneMemBill".equals(path)){
            doOneBillByMonth(request,response,statisticalService,map);
        }
    }

    /**
     * 按月为单位，统计某个家庭成员的消费总额
     * @param request
     * @param response
     * @param statisticalService
     * @param map
     */
    private void doOneBillByMonth(HttpServletRequest request, HttpServletResponse response, StatisticalService statisticalService, HashMap<String, Object> map) {
        String memNo = request.getParameter("memNo"); // 家庭成员编号

    }
}
