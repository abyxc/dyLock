package com.mnsn.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.struts2.ServletActionContext;
/**
 * 
 * @author ck
 * 2015年12月10日20:17:20
 *
 */
public class OperExcels {
 /***************************************************************************
  * @param fileName EXCEL文件名称
  * @param listTitle EXCEL文件第一行列标题集合
  * @param li EXCEL文件正文数据集合
  * @return
  */
  public static String exportExcel(String fileName,String Title,String[] strs,int daynum,String[] weeks) {
     // 以下开始输出到EXCEL
	 String path = "";
	 try {   
		/** **********new************ */
		// 创建Excel工作薄  
		WritableWorkbook workbook; 
		path=ServletActionContext.getServletContext().getRealPath("/");
		path = path +  "excl_temp/temp/"+fileName;//下载到tomcat
		System.out.println(path);
		// 新建立一个jxl文件
		OutputStream oss = new FileOutputStream(path);  
		workbook=Workbook.createWorkbook(oss);  
		/** **********以上是新的*********** */  
				
		/** **********创建工作表************ */		
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		sheet.mergeCells(0,0,daynum+4,0); //合并单元格,第一行
		sheet.mergeCells(0,1,0,2); //合并单元格	
		sheet.mergeCells(1,1,1,2); //合并单元格	
		sheet.mergeCells(2,1,2,2); //合并单元格
		sheet.mergeCells(3,1,3,2); //合并单元格
		sheet.mergeCells(4,1,4,2); //合并单元格
		sheet.setRowView(0,500);//设置第一行高度
		/** **********设置纵横打印（默认为纵打）、打印纸***************** */
		jxl.SheetSettings sheetset = sheet.getSettings();
		sheetset.setProtected(false);
	   /** ************设置单元格字体************** */

	   @SuppressWarnings("unused")
	   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
	   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
	   @SuppressWarnings("unused")
	   WritableCellFormat cellFormat2 = new WritableCellFormat(BoldFont);
	   	   
	   /** ************以下设置三种单元格样式，灵活备用************ */
	  // 用于正文居左
	   WritableCellFormat cellFormat=new WritableCellFormat();
	   cellFormat.setAlignment(jxl.format.Alignment.LEFT);
	   cellFormat.setBackground(Colour.YELLOW);//含有颜色
	   cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	   cellFormat.setWrap(true);
	   // 用于标题居中
	   WritableCellFormat wcf_center = new WritableCellFormat(NumberFormats.TEXT);
	   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
	   wcf_center.setAlignment(jxl.format.Alignment.CENTRE);
	   // 用于正文居左
	   WritableCellFormat wcf_left = new WritableCellFormat(NumberFormats.TEXT);//NumberFormats.TEXT为设置文本格式
	   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
	   // 用于正文居右
	   WritableCellFormat wcf_right = new WritableCellFormat(NumberFormats.TEXT);
	   wcf_right.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐	   
	   wcf_right.setAlignment(jxl.format.Alignment.RIGHT);
	   
	   /** ***************以下是EXCEL第一行列标题********************* */

	   sheet.addCell(new Label(0, 0,Title,wcf_left));//首行
	   for (int i = 0; i < strs.length; i++) {//遍历第二行开头
		   sheet.addCell(new Label(i, 1,strs[i],wcf_center));
		   sheet.setColumnView(i,12);//设置列的宽度第一个参数代表第一列，第二个参数代表宽度
	   }
	  //遍历日期
	   for(int m=1;m<daynum+1;m++){
		       sheet.setColumnView(m+4, 7); 			   
			   sheet.addCell(new Label(m+4,1,m+"",wcf_right));
	   }
	   //遍历星期
	   for(int m=0;m<weeks.length;m++){
               if("六".equals(weeks[m])||"日".equals(weeks[m])){            	   
            	   sheet.addCell(new Label(m+5,2,weeks[m],cellFormat));
               }else{
            	   sheet.addCell(new Label(m+5,2,weeks[m],wcf_left));
               }
	   }
	   for (int i = 0; i < 50; i++) {//遍历第二行开头
		    sheet.addCell(new Label(3, i+3,"",wcf_left));
	   }
	   /** **********将以上缓存中的内容写到EXCEL文件中******** */
	   workbook.write();
	   /** *********关闭文件************* */
	   workbook.close();  
	   oss.close();
	   workbook = null;//释放资源
     }catch (Exception e) {
		  e.printStackTrace();
	 }
	  
	 return fileName;  
	 
  } 
}
