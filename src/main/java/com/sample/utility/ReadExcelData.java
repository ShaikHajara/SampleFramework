package com.sample.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static XSSFWorkbook xssfworkbook;
	public static XSSFSheet xssfsheet;
	public static XSSFRow xssfrow;
	public static FileInputStream fis;
	public static File file;
	public static int totalRows;
	public static int totalColumns;
	public static String DataFromExcel;
	public static List<String> validValueslist = new ArrayList<String>();

	public static String getCellData(XSSFCell cell) {
		Object result = null;
		try {

			final CellType type = cell.getCellTypeEnum();
			if (type == CellType.STRING) {
				System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = STRING; Value = "
						+ cell.getStringCellValue());
				result = cell.getStringCellValue();
			} else if (type == CellType.NUMERIC) {
				if (DateUtil.isCellDateFormatted(cell)) {
					System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = Date; Value = "
							+ cell.getDateCellValue());
					result = cell.getDateCellValue();
				} else {
					System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = NUMERIC; Value = "
							+ cell.getNumericCellValue());
					result = cell.getNumericCellValue();
				}

			} else if (type == CellType.BOOLEAN) {
				System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = BOOLEAN; Value = "
						+ cell.getBooleanCellValue());
				result = cell.getBooleanCellValue();
			} else if (type == CellType.BLANK) {
				System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = BLANK CELL");
				result = cell.toString();
			}
		} catch (final NullPointerException e) {
			e.printStackTrace();
			return "row " + cell.getRowIndex() + " or column " + cell.getColumnIndex() + " does not exist in xls";
		}
		return result.toString();
	}

	public static void getDataFromExcel(String Filename, String Sheetname) throws InvalidFormatException, IOException {
		file = new File(Filename);
		fis = new FileInputStream(file);
		xssfworkbook = new XSSFWorkbook(fis);
		xssfsheet = xssfworkbook.getSheet(Sheetname);
		totalRows = xssfsheet.getPhysicalNumberOfRows();
		xssfrow = xssfsheet.getRow(0);
		totalColumns = xssfrow.getPhysicalNumberOfCells();
		for (int i = 1; i <= totalRows - 1; i++) {
			for (int j = 0; j < totalColumns; j++) {

				xssfrow = xssfsheet.getRow(i);
				final XSSFCell cell = xssfrow.getCell(j);
				DataFromExcel = getCellData(cell);
				validValueslist.add(DataFromExcel);
			}
		}
		System.out.println(validValueslist.get(0));
		System.out.println(validValueslist.get(1));
	}

}
