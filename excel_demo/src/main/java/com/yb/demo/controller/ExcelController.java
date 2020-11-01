package com.yb.demo.controller;

import com.yb.demo.result.CommonResult;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author yb
 * @description excel处理控制器
 * @data 2020/10/20
 */
@RestController
public class ExcelController {
    @PostMapping("/execl/upload")
    public CommonResult<Object> excelUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        //获取后缀
        String suffix = filename.split("\\.")[1];
        if (suffix.equals("xlsx") || suffix.equals("xls")) {
            if (suffix.equals("xls")) {
                return null;
            }
            if (suffix.equals("xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet1 = workbook.getSheet("sheet1");
                //获取首行head
                XSSFRow row = sheet1.getRow(0);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    System.out.println(cellIterator.next().getStringCellValue());
                }
                //将姓名列提取到sheet2
                XSSFSheet sheet2 = workbook.createSheet("姓名");
                XSSFRow sheet2Row = sheet2.createRow(0);
                XSSFCell sheet2RowCell = sheet2Row.createCell(0);
                sheet2RowCell.setCellValue("姓名");
                //写入新文件
                FileOutputStream fileOutputStream = new FileOutputStream("test1.xlsx");
                workbook.write(fileOutputStream);
                System.out.println("写入成功");
                return null;
            }
            return null;
        } else {
            return new CommonResult("400", "文件格式错误");
        }
    }

    //http://www.maxlaw.cn/tools/showresult?ztype=1&money=20000
    @PostMapping("/excel/upload2")
    public CommonResult excelUpload2(@RequestParam("file") MultipartFile file,
                                     @RequestParam("sheetName") String sheetName,
                                     @RequestParam(value = "start", required = false, defaultValue = "1") int start,
                                     @RequestParam(value = "end", required = false) int end,
                                     @RequestParam(value = "column") String columnName) throws IOException {
        //获取工作表
        XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream());
        //获取sheet
        XSSFSheet sheet = workBook.getSheet(sheetName);
        //获取标题行
        XSSFRow row = sheet.getRow(0);
        //获取指定列名的列数
        Iterator<Cell> cellIterator = row.cellIterator();
        int cellIndex = 0;
        while (cellIterator.hasNext()) {
            if (columnName.equals(cellIterator.next().getStringCellValue())) {
                break;
            }
            cellIndex++;
        }
        //遍历指定的行
        start = start < 1 ? 0 : start - 1;
        for (int i = start; i < end; i++) {
            XSSFRow tempRow = sheet.getRow(i);
            Object money = tempRow.getCell(cellIndex).getRawValue();
            //调用远程接口 获取诉讼金额
            String result = getResult(money);
            //写入到 诉讼金额列
            tempRow.createCell(cellIndex + 2).setCellValue(result);
        }
//        Iterator<Row> iterator = sheet.rowIterator();
//        iterator.next();
//        while (iterator.hasNext()) {
//            Row next = iterator.next();
//            double money = next.getCell(cellIndex).getNumericCellValue();
//            //调用远程接口 获取诉讼金额
//            String result = getResult(money);
//            //写入到 诉讼金额列
//            next.createCell(cellIndex+2).setCellValue(result);
//        }
        //写入新文件
        FileOutputStream fileOutputStream = new FileOutputStream("诉讼2.xlsx");
        workBook.write(fileOutputStream);
        System.out.println("写入成功");
        return null;
    }

    private String getResult(Object money) throws IOException {
        if (!"金额".equals(money)) {
            String url = "http://www.maxlaw.cn/tools/showresult?ztype=1&money=" + String.valueOf(money);
            //String url = "https://api.apiopen.top/getJoke?page=1&count=2&type=video";
            OkHttpClient httpClient = new OkHttpClient();
            Request request = new Request.Builder().get().url(url).build();
            Call call = httpClient.newCall(request);
            Response result = call.execute();
            //System.out.println(result.body().string());
            String str = result.body().string();
            String[] split = str.split("<p>受理费：<i>");
            String result_money = split[1].split("元")[0];
            System.out.println(result_money);
            return result_money;
        }
        return null;
    }
}
