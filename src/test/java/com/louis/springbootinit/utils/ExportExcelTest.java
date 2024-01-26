package com.louis.springbootinit.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.louis.springbootinit.mapper.UserMapper;
import com.louis.springbootinit.model.entity.User;
import com.louis.springbootinit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/23 16:56
 */
@SpringBootTest
public class ExportExcelTest {

    @Resource
    private UserMapper userMapper;
    @Test
    public void exportExcel(HttpServletResponse response) throws ClassNotFoundException, IOException {
        ExcelWriter writer = ExcelUtil.getWriter();
        List<User> employees = userMapper.selectList(new QueryWrapper<User>());

        List<Map<String, Object>> rows = employees.stream().map(item -> {
            Map<String, Object> maps = new HashMap<>();
            maps.put("id", item.getUser_id().toString());
            maps.put("name", item.getUser_name());
            maps.put("age", item.getUser_gender());
            maps.put("phone", item.getUser_tel());
            maps.put("account",item.getUser_acct());
            maps.put("pwd",item.getUser_pwd());
            maps.put("createTime",item.getCreateTime().toString());
            maps.put("updateTime", item.getUpdateTime().toString());
            return maps;
        }).collect(Collectors.toList());

        // Title
        int columns = Class.forName("com.louis.springbootinit.model.entity.User").getDeclaredFields().length;
        writer.merge(columns - 1, "普通用户信息");

        // Header
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("createTime", "创建时间");
        writer.addHeaderAlias("updateTime", "更新时间");
        writer.addHeaderAlias("account", "账号");
        writer.addHeaderAlias("pwd", "密码");

        // Body
        writer.setColumnWidth(0, 30);
        writer.setColumnWidth(1, 30);
        writer.setColumnWidth(2, 30);
        writer.setColumnWidth(3, 30);
        writer.setColumnWidth(4, 30);
        writer.setColumnWidth(5, 30);
        writer.setColumnWidth(6, 30);
        writer.setColumnWidth(7, 30);
        writer.write(rows, true);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("用户信息表-" + DateUtil.today() + ".xls", "utf-8"));

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(out);
    }

}

