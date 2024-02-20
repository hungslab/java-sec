package cn.hungslabsec.service;


import cn.hungslabsec.core.common.resp.RestResp;
import cn.hungslabsec.entity.User;

/**
 * @author admin
 * @date 2024/1/22
 * @Description Description of the file.
 */

public interface UserService {
    /**
     * 注册账号
     * @param username
     * @param password
     */
    RestResp loginAccount(User user);

    RestResp loginInfo();
}
