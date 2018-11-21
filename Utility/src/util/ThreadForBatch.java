package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import etcSource.TEST001VO;

public class ThreadForBatch {
	
	final int SEP_CNT = 5000;
	
	/**
	* 파일등록
	* @param context
	* @param pBASE_DT String
	* @param pFile File
	* @return 
	*/
	public void putStandardData(String pBASE_DT, File pFile) throws Throwable {
		
		/**************************************
		 * @처리절차 1 
		 * @처리기능 Validation
		 * @처리내용 
		 **************************************/
//		if(StringUtil.isEmpty(pBASE_DT)) {
//			throw new BizException(MsgConst.MESY0027, "기준일자"); /** [%s1]이(가) 유효하지 않습니다. */
//		}
//		if(pFile == null || !pFile.exists() || !pFile.isFile()) {
//			throw new BizException(MsgConst.MESY0027, "파일"); /** [%s1]이(가) 유효하지 않습니다. */
//		} 
//		if(!pFile.getName().toLowerCase().endsWith(".csv")) {
//			throw new BizException(MsgConst.MESY0027, "파일형식(CSV)"); /** [%s1]이(가) 유효하지 않습니다. */
//		}
		
		FileReader loFr = null;
		BufferedReader loBR = null;
				
		try {
			loFr =  new FileReader(pFile);
			loBR = new BufferedReader(loFr);
			String lsData = null;
			int rowNum = 1;
			ArrayList<TEST001VO> llvTEST001VO = new ArrayList<TEST001VO>();
			ArrayList<Thread> trList = new ArrayList<Thread>();
			
			// 기준값만큼 잘라서 Thread 로 전달
			while ((lsData = loBR.readLine()) != null) {
				String[] splitStr = lsData.split(",");
				if(splitStr.length != 8) {
					continue;
				} 
				if(rowNum == 1) {
					rowNum++;
					continue;
				}
				TEST001VO inputVo = new TEST001VO();
				inputVo.setCOL1(splitStr[0]);
				inputVo.setCOL2(splitStr[1]);
				inputVo.setCOL3(splitStr[2]);
				inputVo.setCOL4(splitStr[3]);
				inputVo.setCOL5(splitStr[4]);
				inputVo.setCOL6(splitStr[5]);
				inputVo.setCOL7(splitStr[6]);
				inputVo.setCOL8(splitStr[7]);
				llvTEST001VO.add(inputVo);
				
				if(rowNum%SEP_CNT == 0) {
					Thread tr = new CustomThread(rowNum - (rowNum - 1)%SEP_CNT, llvTEST001VO);
					tr.start();
					trList.add(tr);
					llvTEST001VO = new ArrayList<TEST001VO>();
				}
				
				rowNum++;
			}
			
			// 나머지값 쓰레드 실행
			Thread tr = new CustomThread(rowNum - (rowNum - 1)%SEP_CNT, llvTEST001VO);
			tr.start();
			trList.add(tr);
			
		} catch (Exception e) {
//			throw new BizException(MsgConst.MESY1001); /** 처리도중 오류가 발생하였습니다. 요청 내용이 처리되었는지 확인하십시요. */
		}
		
		
	}
	
	private class CustomThread extends Thread {
		private int startNum = 0;
		private ArrayList<TEST001VO> mlvTEST001VO;
		
		public CustomThread(int pNum, ArrayList<TEST001VO> pTEST001VO) {
			this.startNum = pNum;
			this.mlvTEST001VO = pTEST001VO;
		}
		
		@Override
		public void run() {
			for(TEST001VO vo : mlvTEST001VO) {
//				try {
//					getDBSession(context).insert("TEST_001", vo);
//				} catch (PersistenceException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				System.out.println(vo.toString());
			}
		}
		
	}
}
