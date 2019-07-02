package org.my.common.interceptor;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.my.common.authorization.annotation.Authorization;
import org.my.common.domain.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    /**
     * redids中request存储对象的键， 存放登录用户模型Key的Request Key
     */
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";
    public static final String REQUEST_CURRENT_USER = "REQUEST_CURRENT_USER";

    public static final int ExpireTime = 600 * 1000;// token有效期，600秒

    @SuppressWarnings("rawtypes")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("拦截登录请求 {}", request.getRequestURL());
        // 登录认证参数获取
        String uid = request.getParameter("uid");
        if (StringUtils.isBlank(uid)) {
            uid = request.getHeader("uid");
        }
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            token = request.getHeader("token");
        }
        String tsp = request.getParameter("tsp");// 时间戳
        if (StringUtils.isBlank(tsp)) {
            tsp = request.getHeader("tsp");
        }
        logger.info("uid={},token={},tsp={}", uid, token, tsp);

        // 用户机型等参数获取解析
        String userAgent = request.getHeader("User-Agent");
        if (userAgent == null) {
            userAgent = "";
        }
        int versionCode = 0;// app版本号
        int deviceType = 1;// 机型默认安卓，1安卓 2iOS
        if (userAgent.indexOf("iPhone") >= 0 || userAgent.indexOf("iPad") >= 0) {
            deviceType = 2;
        }
        try {
            if (userAgent.length() > 0 && userAgent.indexOf("\\") >= 0) {
                if (deviceType == 1) {
                    String temp = userAgent.substring(userAgent.lastIndexOf("\\") + 1, userAgent.length());
                    if (StringUtils.isNumeric(temp)) {
                        versionCode = Integer.parseInt(temp);
                    }
                } else {
                    String temp = userAgent.substring(0, userAgent.lastIndexOf("\\"));
                    String temp2 = temp.substring(temp.lastIndexOf("\\") + 1, temp.length());
                    if (StringUtils.isNumeric(temp2)) {
                        versionCode = Integer.parseInt(temp2);
                    }
                }
            }
            if (versionCode == 0 && userAgent.length() > 0 && userAgent.indexOf("/") >= 0) {
                if (deviceType == 1) {
                    String temp = userAgent.substring(userAgent.lastIndexOf("/") + 1, userAgent.length());
                    if (StringUtils.isNumeric(temp)) {
                        versionCode = Integer.parseInt(temp);
                    }
                } else {
                    String temp = userAgent.substring(0, userAgent.lastIndexOf("/"));
                    String temp2 = temp.substring(temp.lastIndexOf("/") + 1, temp.length());
                    if (StringUtils.isNumeric(temp2)) {
                        versionCode = Integer.parseInt(temp2);
                    }
                }
            }
            if (versionCode == 0 && request.getParameter("versionCode") != null) {
                String temp = request.getParameter("versionCode");
                if (StringUtils.isNumeric(temp)) {
                    versionCode = Integer.parseInt(temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (uid != null && uid.length() > 0 && token != null && token.length() > 0) {
            // 验证token
            String key = "uid:" + uid;
            String userInfo = "get from redis";
            logger.info("{}={}", key, userInfo);
//            if (userInfo != null) {
//                User user = JSON.parseObject(userInfo, User.class);
//                if (user != null && user.getUserToken() != null) {
//                    String tokenSrv = user.getUserToken().getToken();
//
//                    boolean passValid = false;// token验证成功
//                    // 新的认证方式
//                    long curTsp = System.currentTimeMillis();
//                    long reqTsp = Long.parseLong(tsp);
//                    // 判断过期时间和token合法性
//                    if ((curTsp - ExpireTime) < reqTsp && (curTsp + ExpireTime) > reqTsp
//                            && token.equals(StringUtils.toMD5(uid + tokenSrv + tsp))) {
//                        logger.info("认证成功,uid:{},token:{},tsp:{}", uid, token, tsp);
//                        passValid = true;
//                    }
//
//                    if (passValid) {// token验证成功，将token对应的用户id存在request中，便于之后注入
//                        // 用户机型信息放入user
//                        if (user.getUserEntry() == null) {
//                            UserEntry userEntry = new UserEntry();
//                            userEntry.setYid(Integer.parseInt(uid));
//                            userEntry.setCurrentVersion(versionCode);
//                            userEntry.setUserAgent(userAgent);
//                            if (deviceType == 1) {
//                                userEntry.setAndroidVersion(versionCode);
//                                userEntry.setAndroidLastLogin(new Date());
//                            } else if (deviceType == 2) {
//                                userEntry.setIosVersion(versionCode);
//                                userEntry.setIosLastLogin(new Date());
//                            }
//                            user.setUserEntry(userEntry);
//                        }
//                        //
//                        request.setAttribute(REQUEST_CURRENT_KEY, key);
//                        request.setAttribute(REQUEST_CURRENT_USER, user);
//                        return true;
//                    } else {
//                        logger.info("认证失败,uid:{},token:{},tsp:{}", uid, token, tsp);
//                    }
//                }
//            }
        }

        // 如果验证token失败，并且方法注明了Authorization，返回错误
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getMethodAnnotation(Authorization.class) != null // 查看方法上是否有注解
                    || ((HandlerMethod) handler).getBeanType().getAnnotation(Authorization.class) != null // 查看方法所在的Controller是否有注解
            ) {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                BaseResult result = new BaseResult(0, "login");
                writer.write(JSON.toJSONString(result));
                writer.close();
                return false;
            }
        }

        // 为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

    }
}
