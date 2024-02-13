package com.louis.springbootinit.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.louis.springbootinit.mapper.HcMapper;
import com.louis.springbootinit.model.entity.Hc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * EasyExcel 测试
 *
 * @author louis
 * @from 
 */
@SpringBootTest
public class EasyExcelTest {
    @Resource
    private HcMapper hcMapper;

    /**
     * 读取excel文件（设置为相对路径 存放在resource目录下）
     * @throws FileNotFoundException
     */
    @Test
    public void doImport() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test_excel.xlsx");
        List<Map<Integer, String>> list = EasyExcel.read(file)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet()
                .headRowNumber(0)
                .doReadSync();
        System.out.println(list);
    }

    /**
     * 导出文件（采用easyExcel导出数据excel文件）
     * @throws FileNotFoundException
     */
    @Test
    public void doExport() throws FileNotFoundException {
        // 设定导出文件路径
        String destPath = "D:\\excel\\hcsInfo.xlsx";
        List<Hc> hcs = hcMapper.selectList(null);
        // 导出文件的数据格式（列名——类属性）
        EasyExcel.write(destPath,Hc.class).sheet("危化品信息")
                .doWrite(hcs);
    }

}