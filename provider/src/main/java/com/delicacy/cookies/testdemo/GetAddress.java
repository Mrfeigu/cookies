package com.delicacy.cookies.testdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

/**
 * @author linzhenghui
 * @date 2020/12/28
 */
public class GetAddress {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();

        List<String> strings = importXLS();

        Map<String, String> map = new HashMap<>();

        strings.forEach(o->{
            String add = getAdd(restTemplate, o);
            map.put(o, add);
        });
        List<Dto> dtos = new ArrayList<>();
        int index = 1;
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            Dto dto = new Dto(index++, stringStringEntry.getKey(), stringStringEntry.getValue());
            dtos.add(dto);
        }

        try{
            exportExcel(dtos);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        JSONObject jsonObject = restTemplate.getForObject("https://sp0.tianyancha.com/search/suggestV2.json?key=1569-浙江教育出版社&_=1609149816157", JSONObject.class);
//
//        JSONArray data = jsonObject.getJSONArray("data");
//        JSONObject jsonObject1 = data.getJSONObject(0);
//        JSONObject sourceFields = jsonObject1.getJSONObject("sourceFields");
//        String reg_location = sourceFields.getString("reg_location");
//
//        System.out.println(reg_location);
        System.out.println("ending...");

    }

    /**
     * 抓数据
     * @param restTemplate
     * @param name
     * @return
     */
    public static String getAdd(RestTemplate restTemplate, String name) {
        try {
            String format = String.format("https://sp0.tianyancha.com/search/suggestV2.json?key=%s&_=1609149816157", name);
            JSONObject jsonObject = restTemplate.getForObject(format, JSONObject.class);
            JSONArray data = jsonObject.getJSONArray("data");
            JSONObject jsonObject1 = data.getJSONObject(0);
            JSONObject sourceFields = jsonObject1.getJSONObject("sourceFields");
            String reg_location = sourceFields.getString("reg_location");
            return reg_location;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 导入
     * @return
     */
    public static List<String> importXLS() {

        List<String> list = new ArrayList<>();

        try {
            //1、获取文件输入流
            InputStream inputStream = new FileInputStream("C:\\Users\\linzhenghui\\Desktop\\蓝畅已知债权人通信地址.xlsx");
            //2、获取Excel工作簿对象
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //3、得到Excel工作表对象
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            //4、循环读取表格数据
            for (Row row : sheetAt) {
                //首行（即表头）不读取
                if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                    continue;
                }
                String name = row.getCell(1).getStringCellValue();
                list.add(name);
            }
            // 5、关闭流
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 导出
     * @param list
     * @throws IOException
     */
    public static void exportExcel(List<Dto> list) throws IOException {

        // List<Dto> list = new ArrayList<>();

        //1.在内存中创建一个excel文件
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook();
        //2.创建工作簿
        XSSFSheet sheet = hssfWorkbook.createSheet();

        //4.遍历数据,创建数据行
        for (Dto dto : list) {

            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            XSSFRow dataRow = sheet.createRow(lastRowNum + 1);

            dataRow.createCell(0).setCellValue(dto.getK());
            dataRow.createCell(1).setCellValue(dto.getV());

        }
        // 5.创建文件名
        String fileName = "嘿.xls";
        // 6.获取输出流对象
        File file = new File("C:\\Users\\linzhenghui\\Desktop\\嘿.xlsx");
        FileOutputStream stream = new FileOutputStream(file);
        // 7. 关闭
        hssfWorkbook.write(stream);
        hssfWorkbook.close();
        stream.close();
    }

    @Data
    @AllArgsConstructor
    public static class Dto {

        private Integer index;
        private String k;
        private String v;

    }


}
