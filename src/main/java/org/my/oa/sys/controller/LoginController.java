package org.my.oa.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.my.oa.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    /** 1.定义业务层对象 */
    @Autowired
    private SysService sysService;

    @ResponseBody // 异步请求的响应结果
    @RequestMapping(value = "/loginAjax", produces = "application/json; charset=UTF-8")
    public Map<String, Object> login(@RequestParam("mobile") String mobile, @RequestParam("password") String password,
            @RequestParam("vcode") String vcode, HttpSession session) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("mobile", mobile);
            params.put("password", password);
            params.put("vcode", vcode);
            params.put("session", session);
            // 响应数据啊,写回去数据
            Map<String, Object> result = sysService.login(params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
