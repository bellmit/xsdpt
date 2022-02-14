package com.nju.sdpt.config;


import com.nju.sdpt.model.UserContextModel;
import com.nju.sdpt.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class AccessIntrcepter implements AsyncHandlerInterceptor {

    private static final String[] ACCESS_URLS = {
            "/index.do",
            "/login",
            "bigData",
            "yysd",
            "getTitle",
            "getSssdsj",
            "getSlrcd",
            "getSdfsfb.aj",
            "getSjfypm.aj",
            "jumpToReportData",
            "chart",
            "open_api",
            "report",
            "jzsdpt",
            "sdLogin",
            "emsLogInterface",
            "/dzsd",
            "/error",
            "/editSdzt",
            "/hzzf/submit_Repair.zf",
            "/tydzsd_update_gd.aj",
            "/hzzf/submit_web_call.zf",
            "/getwbrysdInfo.do",
            "/getSsdrInfos.aj",
            "/dxtz/query_sdp_phone.zf",
            "/ssdr/input_data.cx",
            "/hzzf/web_call.zf",
            "/hzzf/submit_Message.aj",
            "/editCallData.aj",
            "/tsyysd",
    };

    /**
     * 在目标方法执行前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        String requestURI = request.getRequestURI();

        for(String url : ACCESS_URLS){
            if(StringUtil.contains(requestURI,url)){
                return true;
            }
        }
        UserContextModel user = (UserContextModel) request.getSession().getAttribute(
                "userContext");
        if(user==null){
            response.sendRedirect("/index.do");
            return false;
        }
        return true;
    }

    /**
     * 在目标方法执行后执行，但在请求返回前，我们仍然可以对 ModelAndView进行修改
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在请求已经返回之后执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 用来处理异步请求, 当Controller中有异步请求方法的时候会触发该方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @throws Exception
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

    }
}
