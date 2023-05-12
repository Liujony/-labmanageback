package com.example.common;

import com.example.util.RedisUtil;
import com.example.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: ZC0
 * @Date: 2023/05/10/20:27
 * @Description: 代码完成
 */
@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private TokenUtil tokenUtil;
    @Autowired
    private RedisUtil redisUtil;
    private final Long refreshTime=10L;
    private final long expiresTime=1000 * 60 * 60 * 24 * 7;
    /**
     * 权限认证的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        String token="";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("TOKEN")){
                token=cookie.getValue();
                break;
            }
        }
        log.info(token);
        //为空就返回错误
//        String token = httpServletRequest.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            throw new BizException(ExceptionEnum.TOKEN_INVALID);
        }
        log.info("==============token:" + token);
        Map<String, String> map = tokenUtil.parseToken(token);
        String userId = map.get("userId");
        Integer auth = TokenUtil.getInfo(token);
        log.info("UUID:"+userId);
        log.info("auth:"+auth);
        httpServletRequest.setAttribute("auth",auth);
        log.info(String.valueOf(httpServletRequest.getAttribute("auth")));
        return true;
    }
}
