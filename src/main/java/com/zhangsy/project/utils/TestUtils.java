package com.zhangsy.project.utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Auther: zhangsy
 * @Date: 2019/1/17 14:59
 * @Description:
 */
public class TestUtils {

    public static HashMap<String, String> getToken(CloseableHttpResponse response, String jsonPath) throws Exception {
        HashMap<String, String> responseToken = new HashMap<String, String>();
        String stringEntity = EntityUtils.toString(response.getEntity(), "UTF-8");
        ReadContext context = JsonPath.parse(stringEntity);
        String token = context.read(jsonPath);
        if (null == token || "".equals(token)) {
            throw new Exception("Token 不存在");
        }
        responseToken.put("x-ba-token", token);
        return responseToken;
    }

    //    返回值定义为Object[][],以便DataProvider使用
    public static Object[][] transformData(String filePath, int sheetId) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetId);
//        行数
        int numberRow = xssfSheet.getPhysicalNumberOfRows();
//        列数
        int numberCell = xssfSheet.getRow(0).getLastCellNum();
        Object[][] excelData = new Object[numberRow][numberRow];
        for (int i = 0; i < numberRow; i++) {
            if (null == xssfSheet.getRow(i) || "".equals(xssfSheet.getRow(i))) {
                continue;
            }
            for (int j = 0; j < numberCell; j++) {
                if (null == xssfSheet.getRow(i).getCell(j) || "".equals(xssfSheet.getRow(i).getCell(j))) {
                    continue;
                }
                XSSFCell cell = xssfSheet.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                excelData[i][j] = cell.getStringCellValue();
            }
        }
        return excelData;
    }
}
