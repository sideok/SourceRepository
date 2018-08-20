package util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ConvertExcel {
	public static void main(String[] args) {
		/**
		 * 엑셀파일을 POI 라이브러리로 수정하는 UTILITY
		 */
		
		// 머리글/꼬리글 수정
		try {
			
			File targetFolder = new File("E:\\temp\\excel_test");
			for(File f : targetFolder.listFiles()) {
				if(f.getName().toLowerCase().endsWith("xls") || f.getName().toLowerCase().endsWith("xlsx")) {
					
			        Workbook wb = WorkbookFactory.create(f);
					// 변경할 시트 1
					Sheet sheet1 = wb.getSheet("공통 체크리스트");
					
					if(sheet1 != null) {
						// 헤더세팅
						sheet1.getHeader().setLeft("");
						sheet1.getHeader().setCenter("");
						sheet1.getHeader().setRight("");
						
						// 푸터세팅
						sheet1.getFooter().setLeft("");
						sheet1.getFooter().setCenter("");
						sheet1.getFooter().setRight("Copyright ⓒ by IBKSYSTEM Co., Ltd All Rights Reserved.");
						
						// 내용수정
						sheet1.getRow(1).getCell(4).setCellValue("진영선");
						sheet1.getRow(1).getCell(6).setCellValue("2018-06-28");
						
					}
					
					// 변경할 시트 1-2
					Sheet sheet1_2 = wb.getSheet("단위테스트케이스 설계서");
					
					if(sheet1_2 != null) {
						// 헤더세팅
						sheet1_2.getHeader().setLeft("");
						sheet1_2.getHeader().setCenter("");
						sheet1_2.getHeader().setRight("");
						
						// 푸터세팅
						sheet1_2.getFooter().setLeft("");
						sheet1_2.getFooter().setCenter("");
						sheet1_2.getFooter().setRight("Copyright ⓒ by IBKSYSTEM Co., Ltd All Rights Reserved.");
						
						// 내용수정
						sheet1_2.getRow(0).getCell(8).setCellValue("진영선");
						sheet1_2.getRow(0).getCell(10).setCellValue("진영선");
						
					}
					
					// 변경할 시트 2
					Sheet sheet2 = wb.getSheetAt(0);
					sheet2.getRow(4).getCell(12).setCellValue("VFSK IT시스템 구축");
					sheet2.getRow(8).getCell(12).setCellValue("문서번호 : DV01");
					sheet2.getRow(9).getCell(12).setCellValue("2018.06.28");
					sheet2.getRow(10).getCell(12).setCellValue("Version 1.0");
					
					sheet2.getRow(13).getCell(10).setCellValue("진영선");
					sheet2.getRow(14).getCell(10).setCellValue("진영선");
					sheet2.getRow(15).getCell(10).setCellValue("여종훈");
					

						
//					wb.removeSheetAt(0);
//					Sheet new_sheet1 = wb.createSheet("표지");
//					wb.setSheetOrder("표지", 0);
//					
//					Workbook wb_tmp = WorkbookFactory.create(new File("E:\\temp\\excel_test\\templet\\tmp.xls"));
//					Sheet tmp_sheet = wb_tmp.getSheet("표지");
//					
//					wb.
					
					// 변경할 시트 3
					Sheet sheet3 = wb.getSheetAt(1);
					sheet3.getRow(4).getCell(0).setCellValue("1.0");
					sheet3.getRow(4).getCell(1).setCellValue("2018.06.28");
					sheet3.getRow(4).getCell(2).setCellValue("진영선");
					sheet3.getRow(4).getCell(3).setCellValue("작성");
					sheet3.getRow(4).getCell(4).setCellValue("최초작성");
					sheet3.getRow(4).getCell(5).setCellValue("");
					sheet3.getRow(4).getCell(6).setCellValue("");
					sheet3.getRow(5).getCell(0).setCellValue("");
					sheet3.getRow(5).getCell(1).setCellValue("");
					sheet3.getRow(5).getCell(2).setCellValue("");
					sheet3.getRow(5).getCell(3).setCellValue("");
					sheet3.getRow(5).getCell(4).setCellValue("");
					sheet3.getRow(6).getCell(0).setCellValue("");
					sheet3.getRow(6).getCell(1).setCellValue("");
					sheet3.getRow(6).getCell(2).setCellValue("");
					sheet3.getRow(6).getCell(3).setCellValue("");
					sheet3.getRow(6).getCell(4).setCellValue("");
					
					
					File convertDir = new File(targetFolder.getPath() + "\\convert");
					if(!convertDir.exists()) {
						convertDir.mkdir();
					}
					
					File outputFile = new File(targetFolder.getPath() + "\\convert\\" + f.getName());
					FileOutputStream fos = new FileOutputStream(outputFile);
					wb.write(fos);
					
					fos.close();
					wb.close();

				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
