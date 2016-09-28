package com.mnsn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.inject.util.Strings;


/**
 * @AUTHER 刘龙龙
 * @SAY <h3>优雅的代码需要愉快的心情</h3>
 * @Time 2015-1-29下午07:00:17
 * @URL http://www.cnblogs.com/mvilplss/
 */
public class XlsUtils {
	
	private HSSFSheet sheet;
	private Integer index;
	private Integer beginCell;
	private Integer lastRow;

	public static HSSFWorkbook getHSSFWorkbook(File excelFile) throws Exception {
		FileInputStream s = new FileInputStream(excelFile);
		HSSFWorkbook wb = new HSSFWorkbook(s);
		s.close();
		return wb;
	}

	public static HSSFSheet getHSSFSheet(File excelFile, Integer sheetNum)
			throws Exception {
		HSSFSheet sheet = getHSSFWorkbook(excelFile).getSheetAt(sheetNum);
		return sheet;
	}

	public XlsUtils(File excelFile, Integer sheetNum, Integer beginRow,
			Integer beginCell) throws Exception {
		this.sheet = getHSSFWorkbook(excelFile).getSheetAt(sheetNum);
		this.index = beginRow - 1;
		this.beginCell = beginCell;
		this.lastRow = sheet.getLastRowNum();
	}

	@SuppressWarnings("deprecation")
	public List<Obean> getObeans() throws Exception {
		List<Obean> obs = new ArrayList<Obean>();
		HSSFRow row = sheet.getRow(index);
		int cellNum = row.getLastCellNum();
		for (int j = beginCell; j < cellNum; j++) {
			Obean ob = new Obean("");
			HSSFCell cell = row.getCell(j);
			if (cell == null) {
				obs.add(ob);
				continue;
			}
			if (cell.getCellType() == 1) {
				ob.setObj(cell.getStringCellValue());
			} else if (cell.getCellType() == 0) {
				BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
				String phone = bd.toString();
				ob.setObj(phone);
			} else {
				ob.setObj("");
				//throw new RuntimeException("单元格类型不匹配");
			}
			obs.add(ob);
		}
		return obs;
	}

	public boolean hasMore() {
		index = index + 1;
		boolean result = true;
		while (sheet.getRow(index) == null||sheet.getRow(index).getLastCellNum()==-1) {
			index = index + 1;
			if (index > lastRow) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
//		File excelFile = new File(
//				"C:/Users/龙龙/Desktop/应急预案管理.xls");
//		XlsUtils iu = new XlsUtils(excelFile, 0, 2, 0);
//		while (iu.hasMore()) {
//			List<Obean> os = iu.getObeans();
//				System.out.println(os.get(15).getObj());
//		}
		
		String str1 = null;
		System.out.println(StringUtils.isNotEmpty(str1));
		String str2 = "";
		String str3 = "a";
		System.out.println(StringUtils.isNotEmpty(str3));
		System.out.println(StringUtils.isNotEmpty(str2));
		
	}
	
	
}