/**
 * 
 */
package com.qst.supermarket.service;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.qst.supermarket.dao.GoodsDAO;
import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.GoodsType;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author 12345678
 *
 */
public class GoodsService {
// dao  ��ѯ��������dao
	
	private GoodsDAO gooodsDAO=new GoodsDAO();
	
	
//	  ��ѯ���е���Ʒ����
//	List ��util����
	public List<Goods>  getAllgoods() {
		// TODO Auto-generated method stub
		   return gooodsDAO.getAllgoods();
	}


	/**
	 * @param g_name
	 * @return
	 */
	public List<Goods> selectedGoodsByName(String g_name) {
		// TODO Auto-generated method stub
		return gooodsDAO.selectedGoodsByName(g_name);
	}

	public List<Goods> readXMLloaddata(File file) {
		
		
	  File filepath=file.getAbsoluteFile();
		
		
		
		
		
//		�õ����jxl.jar���ȡexcel�ļ�
					Workbook workbook=null;
					Sheet sheet=null;
					List<Goods> goodList=new ArrayList<Goods>();
					try {
//						��ȡ������
						workbook=Workbook.getWorkbook(filepath);
						
//						��ȡ��
						 sheet=workbook.getSheet(0);
						 
						 int rows=sheet.getRows();
						 
						 int columns=sheet.getColumns();
						 
						 
//						 ѭ������
						 for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								
								Cell cell=sheet.getCell(j,i);
							String barcode=	cell.getContents();
//							�ñ���е����ݺ�Goods��ƥ��
							cell=sheet.getCell(j++,i);
						
							
							
							Double price=	Double.parseDouble(cell.getContents());
							cell=sheet.getCell(j++,i);
						    Integer num=	Integer.parseInt(cell.getContents());
						    cell=sheet.getCell(j++,i);
							String gname=	cell.getContents();
							cell=sheet.getCell(j++,i);
							String tcode=	cell.getContents();
						    cell=sheet.getCell(j++,i);					

							String tname=	cell.getContents();
//						System.out.println(i+"----"+j+"---"+values);
							Goods goods=new Goods( barcode, price,  num,  gname, tcode,tname);	
							
                              goodList.add(goods);						  
							
							
							
							}
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						if (workbook!=null) {
							workbook.close();
						}
					}
					
		return goodList;
	}
	

	/**
	 * @param goods
	 * @return 
	 */
	public boolean saveGoods(Goods goods) {
		
		
		return gooodsDAO.saveGoods( goods);
		
		
		
		
		// TODO Auto-generated method stub
		
	}


	/**
	 * @param barcode
	 * @return
	 */
	public boolean deleteGoodsByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return gooodsDAO.deleteGoodsByBarcode(barcode);
	}


	/**
	 * @param goodlist
	 * @return
	 */
	public boolean saveGoods(List<Goods> goodlist) {
		boolean flag=false;
		if (goodlist!=null&&goodlist.size()>0) {
			
			for (Goods goods : goodlist) {
//   ����ɹ�Ϊtrue 				
		   flag=saveGoods(goods);
			}
		}
		return flag;
	}


	/**
	 * @param barcode
	 * @return
	 */
	public Goods selectedGoodsByBarcode(String barcode) {
		// TODO Auto-generated method stub
		return gooodsDAO.selectedGoodsByBarcode(barcode);
	}


	/**
	 * @param tcode
	 * @param tname
	 * @return
	 */
	public List<GoodsType> SaveGoodsCategry(String tcode, String tname) {
		// TODO Auto-generated method stub
		return gooodsDAO.SaveGoodsCategry(tcode,tname);
	}


	/**
	 * @param tcode
	 * @param tname
	 * @return
	 */
	

}
