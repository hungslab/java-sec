package cn.hungslabsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hungs
 * @date 2024/1/24
 * @Description Description of the file.
 */

@Controller
public class SystemController {
    /**
     * 登陆页面跳转
     * */
    @GetMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 注册页面跳转
     * */
    @GetMapping("registry")
    public String registry() {
        return "registry";
    }


}
