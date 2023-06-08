package life.hopeurl.excel.poi.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * excel工具类
 *
 * @author shiping.song
 * @date 2023/5/12 22:35
 */
public class ExcelUtils {

    private ExcelUtils() {
    }

    /**
     * 从文件路径获取工作簿
     *
     * @param filePath 文件路径
     * @return 工作簿
     * @throws IOException
     * @date 2023/5/12 22:56
     */
    public static Workbook getWorkBook(String filePath) throws IOException {
        return WorkbookFactory.create(new File(filePath));
    }

    /**
     * 获取字符串类型的单元格值
     *
     * @param cell 字符串单元格
     * @return
     * @date 2023/5/13 15:03
     */
    public static String getStringValue(Cell cell) {
        String value = "";
        if (Objects.isNull(cell)) {
            return value;
        }
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (!DateUtil.isCellDateFormatted(cell)) {
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
//                System.out.println(cell.getCellFormula());
//                System.out.println(cell.getNumericCellValue());
                value = String.valueOf(cell.getNumericCellValue());
                break;
            default:
        }
        return value;
    }

    /**
     * 获取时间单元格的值
     *
     * @param cell 时间单元格
     * @return 时间值
     * @date 2023/5/13 15:02
     */
    public static LocalDateTime getLocalDateTime(Cell cell) {
        if (Objects.nonNull(cell) && CellType.NUMERIC.equals(cell.getCellType()) && DateUtil.isCellDateFormatted(cell)) {
            return cell.getLocalDateTimeCellValue();
        }
        return null;
    }


    /**
     * 读取excel单元格，同时捕获异常
     *
     * @param cellConsumer      单元格处理流程
     * @param cell              当前操作单元格
     * @param throwExceptionClz 捕获到当前类型到异常时则直接抛出
     * @date 2023/6/8 00:11
     */
    public void readCellAndWrapException(Consumer<Cell> cellConsumer, Cell cell, Class<? extends Exception> throwExceptionClz) {
        try {
            cellConsumer.accept(cell);
        } catch (Exception e) {
            e.printStackTrace();
            if (Objects.nonNull(throwExceptionClz) && e.getClass() == throwExceptionClz) {
                throw e;
            }
        }
    }

}
