/**
 * 
 */
package com.qst.supermarket.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.GoodsType;
import com.qst.supermarket.utils.DBUtils;

/**
 * @author 12345678
 *
 */
public class GoodsTypeDAO {

	/**
	 * @return
	 */
	public Map<String, String> getAllGoodsType() {
		  String sql="select tcode,tname from goodstype";
			List<GoodsType>   goodList=DBUtils.query(GoodsType.class,sql);
			
			
		  Map<String, String> goodsTypeMap=null;
			
		  if (goodList!=null&& goodList.size()>0) {
			  goodsTypeMap=new HashMap<String,String>();
			
			  for (GoodsType goodsType : goodList) {
//				  Map.put(key,value) ���԰Ѽ��ϵ�һЩֵ�ŵ�����ȥ
				  goodsTypeMap.put(goodsType.getTcode(),goodsType.getTname());
			  }
		}
			
		return goodsTypeMap;
	}

	

}
