package com.louis.springbootinit.controller;

import cn.hutool.core.io.FileUtil;
import com.louis.springbootinit.common.BaseResponse;
import com.louis.springbootinit.common.ErrorCode;
import com.louis.springbootinit.common.ResultUtils;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.louis.springbootinit.service.UserService;
import com.louis.springbootinit.utils.AliOssUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件接口
 *
 * @author louis
 * @from 
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    //请求中要携带上需要上传的文件
    public BaseResponse<String> saveOss(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("nullFile");
        }
        try {
            //获取原始的文件名
            String originalFilename = file.getOriginalFilename();
            //在oss中存储名字就是UUID + 文件的后缀名
            String objectName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            String resultURL = aliOssUtil.upload(file.getBytes(), objectName);
            return ResultUtils.success(resultURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
