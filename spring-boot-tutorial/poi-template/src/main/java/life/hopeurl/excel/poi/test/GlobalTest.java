package life.hopeurl.excel.poi.test;

import life.hopeurl.excel.poi.utils.ExcelUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局测试类
 *
 * @author shiping.song
 * @date 2023/5/12 23:01
 */
public class GlobalTest {

    public static final String DIR = "/Users/songshiping/IdeaProjects/xiaosongstudy-code/spring-boot-tutorial/poi-template/";

    @Test
    public void testWorkWithDifferentTypeCells() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("sheet01");
        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(LocalDate.now());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(CellType.ERROR);
// Write the output to a file
        try (OutputStream fileOut = Files.newOutputStream(Paths.get("workbook.xlsx"))) {
            wb.write(fileOut);
        }
    }


    @Test
    public void testCreateDateCell() throws IOException {
        Workbook wb = new HSSFWorkbook();
//Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("sheet");
// Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet.createRow(0);
// Create a cell and put a date value in it.  The first cell is not styled
// as a date.
        Cell cell = row.createCell(0);
        cell.setCellValue(LocalDate.now());
// we style the second cell as a date (and time).  It is important to
// create a new cell style from the workbook otherwise you can end up
// modifying the built in style and effecting not only this cell but other cells.
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(LocalDateTime.now());
        cell.setCellStyle(cellStyle);
//you can also set date as java.util.Calendar
        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);
// Write the output to a file
        try (OutputStream fileOut = Files.newOutputStream(Paths.get("createDateCell.xls"))) {
            wb.write(fileOut);
        }
        wb.close();
    }


    /**
     * 测试对齐方式
     *
     * @date 2023/5/13 01:44
     */
    @Test
    public void testDemonstrates() throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);
        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
        createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
        // Write the output to a file
        try (OutputStream fileOut = Files.newOutputStream(Paths.get("testDemonstrates.xlsx"))) {
            wb.write(fileOut);
        }
        wb.close();
    }

    /**
     * Creates a cell and aligns it a certain way.
     *
     * @param wb     the workbook
     * @param row    the row to create the cell in
     * @param column the column number to create the cell in
     * @param halign the horizontal alignment for the cell.
     * @param valign the vertical alignment for the cell.
     */
    private static void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 测试单元格的值
     *
     * @date 2023/5/13 02:10
     */
    @Test
    public void testGetCellValue() throws IOException {
        Workbook wb = ExcelUtils.getWorkBook("/Users/songshiping/IdeaProjects/xiaosongstudy-code/spring-boot-tutorial/poi-template/testGetCellValue.xlsx");
        DataFormatter formatter = new DataFormatter();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");
                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(cell);
                System.out.println(text);
                // Alternatively, get the value and format it yourself
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.println(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            System.out.println(cell.getDateCellValue());
                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }
                        break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        System.out.println(cell.getCellFormula());
                        System.out.println(cell.getNumericCellValue());
                        break;
                    case BLANK:
                        System.out.println();
                        break;
                    default:
                        System.out.println();
                }
            }
        }

    }


    /**
     * 测试文本提取处理
     *
     * @throws IOException
     * @date 2023/5/13 02:56
     */
    @Test
    public void testExcelExtractor() throws IOException {
        try (InputStream inp = Files.newInputStream(Paths.get(DIR + "testExcelExtractor.xls"))) {
            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
            ExcelExtractor extractor = new ExcelExtractor(wb);
            extractor.setFormulasNotResults(true);
            extractor.setIncludeSheetNames(false);
            String text = extractor.getText();
            System.out.println(text);
        }
    }

    /**
     * 合并单元格
     *
     * @date 2023/5/13 09:33
     */
    @Test
    public void testMergeCells() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of merging");
        // 合并两列
        sheet.addMergedRegion(new CellRangeAddress(
                1, //first row (0-based)
                1, //last row  (0-based)
                1, //first column (0-based)
                2  //last column  (0-based)
        ));
// Write the output to a file
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "workbook.xlsx"))) {
            wb.write(fileOut);
        }
        wb.close();
    }


    /**
     * 测试数据格式化
     *
     * @date 2023/5/13 09:40
     */
    @Test
    public void testDataFormats() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        CellStyle style;
        DataFormat format = wb.createDataFormat();
        Row row;
        Cell cell;
        int rowNum = 0;
        int colNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("0.0"));
        cell.setCellStyle(style);
        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("#,##0.0000"));
        cell.setCellStyle(style);
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "workbook.xlsx"))) {
            wb.write(fileOut);
        }
        wb.close();
    }


    @Test
    public void testFitSheetOnPage() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        PrintSetup ps = sheet.getPrintSetup();
        sheet.setAutobreaks(true);
        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);
// Create various cells and rows for spreadsheet.
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "workbook.xlsx"))) {
            wb.write(fileOut);
        }
        wb.close();
    }


    /**
     * 测试创建超链接
     *
     * @date 2023/5/13 09:53
     */
    @Test
    public void testCreateHyperLinks() throws IOException {
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
//cell style for hyperlinks
//by default hyperlinks are blue and underlined
        CellStyle hlink_style = wb.createCellStyle();
        Font hlink_font = wb.createFont();
        hlink_font.setUnderline(Font.U_SINGLE);
        hlink_font.setColor(IndexedColors.BLUE.getIndex());
        hlink_style.setFont(hlink_font);
        Cell cell;
        Sheet sheet = wb.createSheet("Hyperlinks");
//URL
        cell = sheet.createRow(0).createCell(0);
        cell.setCellValue("URL Link");
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("https://poi.apache.org/");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
//link to a file in the current directory
        cell = sheet.createRow(1).createCell(0);
        cell.setCellValue("File Link");
        link = createHelper.createHyperlink(HyperlinkType.FILE);
        link.setAddress(DIR + "workbook.xlsx");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
//e-mail link
        cell = sheet.createRow(2).createCell(0);
        cell.setCellValue("Email Link");
        link = createHelper.createHyperlink(HyperlinkType.EMAIL);
//note, if subject contains white spaces, make sure they are url-encoded
        link.setAddress("mailto:poi@apache.org?subject=Hyperlinks");
        cell.setHyperlink(link);
        cell.setCellStyle(hlink_style);
//link to a place in this workbook
//create a target sheet and cell
        Sheet sheet2 = wb.createSheet("Target Sheet");
        sheet2.createRow(0).createCell(0).setCellValue("Target Cell");
        cell = sheet.createRow(3).createCell(0);
        cell.setCellValue("Worksheet Link");
        Hyperlink link2 = createHelper.createHyperlink(HyperlinkType.DOCUMENT);
        link2.setAddress("'Target Sheet'!A1");
        cell.setHyperlink(link2);
        cell.setCellStyle(hlink_style);
        try (OutputStream out = Files.newOutputStream(Paths.get(DIR + "testCreateHyperLinks.xlsx"))) {
            wb.write(out);
        }
        wb.close();
    }


    @Test
    public void testReadHyperLinks() throws IOException {
        Workbook workbook = ExcelUtils.getWorkBook(DIR + "testCreateHyperLinks.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        Cell cell = sheet.getRow(3).getCell(0);
        Hyperlink link = cell.getHyperlink();
        if (link != null) {
            System.out.println(link.getAddress());
        }
        workbook.close();
    }

    @Test
    public void testValidation() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Data Validation-1");
        CellRangeAddressList addressList = new CellRangeAddressList(
                0, 0, 0, 0);
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(
                new String[]{"10", "20", "30"});
        DataValidation dataValidation = new HSSFDataValidation
                (addressList, dvConstraint);
        dataValidation.setSuppressDropDownArrow(true);
        dataValidation.setErrorStyle(DataValidation.ErrorStyle.STOP);
        dataValidation.createErrorBox("Box Title", "Message Text");
        sheet.addValidationData(dataValidation);
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "testValidation.xls"))) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    @Test
    public void testSetCellProperties() throws IOException {
        Workbook workbook = new XSSFWorkbook();  // OR new HSSFWorkbook()
        Sheet sheet = workbook.createSheet("Sheet1");
        Map<String, Object> properties = new HashMap<String, Object>();
// border around a cell
        properties.put(CellUtil.BORDER_TOP, BorderStyle.MEDIUM);
        properties.put(CellUtil.BORDER_BOTTOM, BorderStyle.MEDIUM);
        properties.put(CellUtil.BORDER_LEFT, BorderStyle.MEDIUM);
        properties.put(CellUtil.BORDER_RIGHT, BorderStyle.MEDIUM);
// Give it a color (RED)
        properties.put(CellUtil.TOP_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.BOTTOM_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.LEFT_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.RIGHT_BORDER_COLOR, IndexedColors.RED.getIndex());
// Apply the borders to the cell at B2
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        CellUtil.setCellStyleProperties(cell, properties);
// Apply the borders to a 3x3 region starting at D4
        for (int ix = 3; ix <= 5; ix++) {
            row = sheet.createRow(ix);
            for (int iy = 3; iy <= 5; iy++) {
                cell = row.createCell(iy);
                CellUtil.setCellStyleProperties(cell, properties);
            }
        }
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "testSetCellProperties.xlsx"))) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    /**
     * 测试设置单元格背景颜色
     *
     * @date 2023/5/16 22:36
     */
    @Test
    public void testSetCellBackgroundColor() throws IOException {
        // 创建一个工作簿对象
        Workbook workbook = new XSSFWorkbook();

// 创建一个工作表对象
        Sheet sheet = workbook.createSheet("testSetCellBackgroundColor");

// 创建一个行对象
        Row row = sheet.createRow(0);

// 创建一个单元格对象
        Cell cell = row.createCell(0);

// 创建一个单元格样式对象
        CellStyle style = workbook.createCellStyle();

// 设置背景颜色为红色
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

// 将单元格样式应用到单元格
        cell.setCellStyle(style);

        Cell twoCell = row.createCell(1);
        try (OutputStream fileOut = Files.newOutputStream(Paths.get(DIR + "testSetCellBackgroundColor.xlsx"))) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    /**
     * 获取单元格样式
     *
     * @date 2023/5/16 23:04
     */
    @Test
    public void testGetCellStyle() throws IOException{
        Workbook workbook = WorkbookFactory.create(new File(DIR + "testSetCellBackgroundColor.xlsx"));
        Row row = workbook.getSheetAt(0).getRow(0);
        CellStyle templateCellStyle = row.getCell(0).getCellStyle();
        Cell twoCell = row.createCell(2);
        twoCell.setCellStyle(templateCellStyle);
        twoCell.setCellValue("我是测试数据");
        workbook.write(Files.newOutputStream(Paths.get(DIR + "testSetCellBackground.xlsx")));
        workbook.close();
    }
}
