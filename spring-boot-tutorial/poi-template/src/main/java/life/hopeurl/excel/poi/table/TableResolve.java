package life.hopeurl.excel.poi.table;

import life.hopeurl.excel.poi.utils.ExcelUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 表格解析调试
 *
 * @author shiping.song
 * @date 2023/5/13 12:45
 */
public class TableResolve {

    public static void main(String[] args) throws IOException {
        InputStream in = TableResolve.class.getClassLoader().getResourceAsStream("Table.xlsx");
        assert null != in;
        Workbook workbook = WorkbookFactory.create(in);
        Sheet tableSheet = workbook.getSheetAt(0);
        // 解析所有合并单元格的值, 记录所在行，记录所在列，记录值, 划分解析区域，根据解析区域分别取值
        // key ---所在行  value  ---所在列  ---值
        Map<Integer, Map<Integer, String>> mergeValueMap = new HashMap<>();
        int titleRowIndex = -1;
        int dtcBeginRowIndex = -1;
        int dtcEndRowIndex = -1;
        List<CellRangeAddress> mergedRegions = tableSheet.getMergedRegions();
        if (CollectionUtils.isNotEmpty(mergedRegions)) {
            for (CellRangeAddress mergedRegion : mergedRegions) {
                int firstColumn = mergedRegion.getFirstColumn();
                int firstRow = mergedRegion.getFirstRow();
                Row row = tableSheet.getRow(firstRow);
                Cell cell = row.getCell(firstColumn);
                String stringValue = ExcelUtils.getStringValue(cell);
                if (titleRowIndex == -1 && stringValue.startsWith("自营进口业务预报表")) {
                    titleRowIndex = firstRow;
                }

                if (dtcBeginRowIndex == -1 && stringValue.startsWith("我司供应链主要角色详情")) {
                    dtcBeginRowIndex = firstRow;
                }

                if (stringValue.startsWith("其他贸易费用")) {
                    dtcEndRowIndex = firstRow;
                    break;
                }
            }
        }
        System.out.printf("titleRowIndex=%s, dtcBeginRowIndex=%s, dtcEndRowIndex=%s\n", titleRowIndex, dtcBeginRowIndex, dtcEndRowIndex);

        if (titleRowIndex != -1 && dtcBeginRowIndex != -1 && dtcEndRowIndex != -1) {
            // 获取整个项目的全局信息
            for (int i = titleRowIndex; i < dtcBeginRowIndex; i++) {
                Row row = tableSheet.getRow(i);
                if (Objects.isNull(row)) {
                    continue;
                }
                int begin = row.getFirstCellNum();
                int end = row.getLastCellNum();
                for (int cellIndex = begin; cellIndex < end; cellIndex++) {
                    String localValue = ExcelUtils.getStringValue(row.getCell(cellIndex));
                    if ("进口货物".equals(localValue)) {
                        System.out.println("进口货物:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    } else if (localValue.startsWith("货物净重")) {
                        System.out.println("货物净重:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    } else if ("上游交割日期".equals(localValue)) {
                        System.out.println("上游交割日期:" + ExcelUtils.getLocalDateTime(row.getCell(cellIndex + 1)));
                    } else if ("借货商境外主体".equals(localValue)) {
                        System.out.println("借货商境外主体:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    } else if ("借货商境内主体".equals(localValue)) {
                        System.out.println("借货商境内主体:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    }
                }
            }
            // 解析交易上下游企业信息
            for (int i = dtcBeginRowIndex + 1; i < dtcEndRowIndex; i++) {
                Row row = tableSheet.getRow(i);
                if (isBlankLine(row)) {
                    System.out.println(i + "：开始解析下一个交易上下游企业信息：=====================================");
                }
                int begin = row.getFirstCellNum();
                int end = row.getLastCellNum();
                for (int cellIndex = begin; cellIndex < end; cellIndex++) {
                    String stringValue = ExcelUtils.getStringValue(row.getCell(cellIndex));
                    // 待解析内容
                    if ("公司名称".equals(stringValue)) {
                        System.out.println("公司名称:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    } else if ("交易角色".equals(stringValue)) {
                        System.out.println("交易角色:" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                    } else if (stringValue.contains("采购无税单价")) {
                        if (stringValue.contains("USD")) {
                            System.out.println("采购无税单价(USD):" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                        } else if (stringValue.contains("CNY")) {
                            System.out.println("采购无税单价(CNY):" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                        }
                    } else if (stringValue.contains("价格构成") && stringValue.contains("USD")) {
                        System.out.println("价格构成(USD):底价" + ExcelUtils.getStringValue(row.getCell(cellIndex + 1)));
                        System.out.println("价格构成(USD):差价" + ExcelUtils.getStringValue(row.getCell(cellIndex + 2)));
                    }
                }
            }
        }

        // 解析内贸链条明细
        // 先解析所有的合并单元格，依照合并单元格来切分出具体的链条信息
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        resolveChainSheet(workbook);

        workbook.close();
    }

    /**
     * 解析内贸链条明细
     *
     * @param workbook
     * @date 2023/5/14 00:10
     */
    private static void resolveChainSheet(Workbook workbook) {
        Sheet chainSheet = workbook.getSheetAt(1);
        List<CellRangeAddress> mergedRegions = chainSheet.getMergedRegions();
        Map<Integer, Integer> rowToColIndex = new HashMap<>();
        if (CollectionUtils.isNotEmpty(mergedRegions)) {
            for (CellRangeAddress mergedRegion : mergedRegions) {
                int firstRow = mergedRegion.getFirstRow();
                int lastRow = mergedRegion.getLastRow();
                int firstColumn = mergedRegion.getFirstColumn();
                int lastColumn = mergedRegion.getLastColumn();
                System.out.printf("firstRow=%s, lastRow=%s, firstColumn=%s, lastColumn=%s\n", firstRow, lastRow, firstColumn, lastColumn);
                String stringValue = ExcelUtils.getStringValue(chainSheet.getRow(firstRow).getCell(firstColumn));
                System.out.println(stringValue);
                if ("采购单价".equals(stringValue)) {
                    rowToColIndex.put(firstRow - 1, lastColumn + 1);
                }
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            rowToColIndex.forEach((targetRowIndex, beginColIndex) -> {
                System.out.printf("targetRowIndex=%s, beginColIndex=%s\n", targetRowIndex, beginColIndex);
            });
        }
    }

    /**
     * 校验excel行是否为空行
     *
     * @param row 待校验行
     * @return 校验结果
     * @date 2023/5/13 22:39
     */
    private static boolean isBlankLine(Row row) {
        boolean isBlankRow = true;
        if (Objects.isNull(row)) {
            return true;
        }
        int begin = row.getFirstCellNum();
        int end = row.getLastCellNum();
        for (int cellIndex = begin; cellIndex < end; cellIndex++) {
            isBlankRow = isBlankRow && StringUtils.isBlank(ExcelUtils.getStringValue(row.getCell(cellIndex)));
        }
        return isBlankRow;
    }

    /**
     * 获取sheet页的标题
     *
     * @param sheet sheet页
     * @return 标题内容
     * @date 2023/5/13 14:44
     */
    private static String getHeader(Sheet sheet) {
        CellRangeAddress mergedRegion = sheet.getMergedRegion(0);
        int firstColumn = mergedRegion.getFirstColumn();
        int firstRow = mergedRegion.getFirstRow();
        Row row = sheet.getRow(firstRow);
        String headerValue = row.getCell(firstColumn).getStringCellValue();
        System.out.println("header==>" + headerValue);
        return headerValue;
    }
}
