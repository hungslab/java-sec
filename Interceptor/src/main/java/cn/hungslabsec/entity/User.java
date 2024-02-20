package cn.hungslabsec.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hungs
 * @date 2024/2/19
 * @Description Description of the file.
 */

@Data
@Builder
public class User {
    private String username;
    private String password;
    private String token;
}
