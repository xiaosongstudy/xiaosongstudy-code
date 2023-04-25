package com.gitee.xiaosongstudy.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * poi处理器
 *
 * @author shiping.song
 * @date 2023/4/15 12:04
 * @since 1.0.0
 */
public class PoiHandler {

    @Test
    public void testReadExcel() throws IOException {
        Path path = Paths.get("C:\\Users\\hopeurl\\Desktop\\excel\\poi\\1.xlsx");
        try (InputStream inputStream = Files.newInputStream(path)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (Sheet sheet : workbook) {
                int lastRowNum = sheet.getLastRowNum();
                for (int i = 0; i < lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    short minColIx = row.getFirstCellNum();
                    short maxColIx = row.getLastCellNum();
                    for (short colIx = minColIx; colIx < maxColIx; colIx++) {
                        Cell cell = row.getCell(colIx);
                        if (cell == null) {
                            continue;
                        }
                        System.out.println(cell.getStringCellValue());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
