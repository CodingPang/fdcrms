package com.fdcrms.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * author: CodingPang
 * Date: 2022/07/10 0:35
 * Description: 字符编码过滤器
 * Version: V1.0
 */
public class CharacterFilter implements Filter {

    String encoding = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding"); // 获取在web.xml中配置的编码参数
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (encoding != null) {
            request.setCharacterEncoding(encoding);
            response.setContentType("application/json;charset=" + encoding);
            // response.setContentType("application/json;charset=" + encoding);
        }

        /*
         * 在过滤器对象的doFilter()方法中，业务逻辑处理完成之后，
         * 需要通过FilterChain对象的doFilter()方法将请求传递到下一过滤器或目标资源，否则将出现错误。
         */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
