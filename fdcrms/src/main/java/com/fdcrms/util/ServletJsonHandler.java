package com.fdcrms.util;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * author: CodingPang
 * Date: 2022/07/30 00:30
 * Description: ServletJson处理程序，用于将前端POST处理request请求中的json
 * Version: V1.0
 */
public class ServletJsonHandler {

    // 字符串读取
    // 方法一
    public static String ReadAsChars(ServletRequest request)
    {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
