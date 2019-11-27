package com.example.testsecurity.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.testsecurity.model.CodeMessage;

@Service
public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException{
        HttpServletResponse response = event.getResponse();
//        HttpServletRequest request = event.getRequest();
//        判断是否为ajax 请求
//        boolean isAjax = request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONObject.toJSONString(CodeMessage.LoginExpired));
        response.flushBuffer();
    }
}
