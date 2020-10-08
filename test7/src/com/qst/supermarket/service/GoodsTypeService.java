/**
 * 
 */
package com.qst.supermarket.service;

import java.util.Map;

import com.qst.supermarket.dao.GoodsTypeDAO;

/**
 * @author 12345678
 *
 */
public class GoodsTypeService {
	private GoodsTypeDAO goodsTypeSevice=new GoodsTypeDAO();

	/**
	 * @return
	 */
	public Map<String, String> getAllGoodsType() {
		// TODO Auto-generated method stub
		return goodsTypeSevice. getAllGoodsType();
	}

}
