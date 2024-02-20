package cn.hungslabsec.controller;

import cn.hungslabsec.core.common.resp.RestResp;
import cn.hungslabsec.entity.User;
import cn.hungslabsec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author Hungs
 * @date 2024/1/22
 * @Description 用户相关 API 管理器
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public RestResp login(@RequestBody User user) {
        return userService.loginAccount(user);
    }

    @GetMapping("/admin/**")
    public RestResp admin() {
        return userService.loginInfo();
    }
}
