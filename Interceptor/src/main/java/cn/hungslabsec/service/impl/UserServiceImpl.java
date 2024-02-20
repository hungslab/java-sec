package cn.hungslabsec.service.impl;

import cn.hungslabsec.core.common.constant.ErrorCodeEnum;
import cn.hungslabsec.core.common.exception.BusinessException;
import cn.hungslabsec.core.common.resp.RestResp;
import cn.hungslabsec.core.common.utils.JwtUtils;
import cn.hungslabsec.entity.User;
import cn.hungslabsec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Hungs
 * @date 2024/1/24
 * @Description Description of the file.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtils jwtUtils;

    @Override
    public RestResp loginAccount(User user) {
        // 判断密码是否正确
        if ( !"admin".equals(user.getUsername()) && !"admin".equals(user.getPassword())) {
            // 密码错误
             RestResp.ok(ErrorCodeEnum.USER_UN_AUTH);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        // 登录成功，生成JWT并返回
        return RestResp.ok(user.builder()
                .token(jwtUtils.generateToken(claims))
                .username(user.getUsername())
                .build());
    }

    @Override
    public RestResp loginInfo() {
        return RestResp.ok();
    }
}
