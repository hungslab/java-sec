package cn.hungslabsec.controller;

import cn.hungslabsec.core.common.constant.ErrorCodeEnum;
import cn.hungslabsec.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Hungs
 * @date 2024/2/22
 * @Description 文件上传控制器
 */

@Slf4j
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public RestResp upload(MultipartFile file) {
        // 检查是否上传了文件
        if (file.isEmpty()) {
            return RestResp.fail(ErrorCodeEnum.USER_UPLOAD_FILE_ERROR);
        }
        try {
            // 保存文件到指定路径，这里为了简单起见直接打印文件名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + fileExtension;

            file.transferTo(new File("/Users/admin/Desktop/upload" + fileName));

            return RestResp.ok("文件上传成功：" + fileName);
        } catch (Exception e) {
            return RestResp.fail(ErrorCodeEnum.USER_UPLOAD_FILE_ERROR);
        }
    }
}
