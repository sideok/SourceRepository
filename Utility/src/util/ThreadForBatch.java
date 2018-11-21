package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import etcSource.TEST001VO;

public class ThreadForBatch {
	
	final int SEP_CNT = 5000;
	
	/**
	* ���ϵ��
	* @param context
	* @param pBASE_DT String
	* @param pFile File
	* @return 
	*/
	public void putStandardData(String pBASE_DT, File pFile) throws Throwable {
		
		/**************************************
		 * @ó������ 1 
		 * @ó����� Validation
		 * @ó������ 
		 **************************************/
//		if(StringUtil.isEmpty(pBASE_DT)) {
//			throw new BizException(MsgConst.MESY0027, "��������"); /** [%s1]��(��) ��ȿ���� �ʽ��ϴ�. */
//		}
//		if(pFile == null || !pFile.exists() || !pFile.isFile()) {
//			throw new BizException(MsgConst.MESY0027, "����"); /** [%s1]��(��) ��ȿ���� �ʽ��ϴ�. */
//		} 
//		if(!pFile.getName().toLowerCase().endsWith(".csv")) {
//			throw new BizException(MsgConst.MESY0027, "��������(CSV)"); /** [%s1]��(��) ��ȿ���� �ʽ��ϴ�. */
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
			
			// ���ذ���ŭ �߶� Thread �� ����
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
			
			// �������� ������ ����
			Thread tr = new CustomThread(rowNum - (rowNum - 1)%SEP_CNT, llvTEST001VO);
			tr.start();
			trList.add(tr);
			
		} catch (Exception e) {
//			throw new BizException(MsgConst.MESY1001); /** ó������ ������ �߻��Ͽ����ϴ�. ��û ������ ó���Ǿ����� Ȯ���Ͻʽÿ�. */
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
