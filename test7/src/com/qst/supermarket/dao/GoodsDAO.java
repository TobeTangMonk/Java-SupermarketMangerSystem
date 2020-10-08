/**
 * 
 */
package com.qst.supermarket.dao;



import java.util.List;

import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.GoodsType;
import com.qst.supermarket.utils.DBUtils;

/**
 * @author 12345678
 *
 */
public class GoodsDAO {

	/**
	 * @return
	 */
	public List<Goods> getAllgoods() {
		// TODO Auto-generated method stub
      

        String sql="select g.barcode,g.gname,g.num,g.price,g.tcode,gt.tcode,gt.tname from goods g left outer join goodstype gt  on g.tcode=gt.tcode";
		List<Goods>   goodList=DBUtils.query(Goods.class,sql);
		
		
		return  goodList;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			 }

	/**
	 * @return
	 */
	public List<Goods> selectedGoodsByName(String g_name) {
		// TODO Auto-generated method stub
		
		
		
//		g_name为条件可以有多个

		StringBuffer sql=new StringBuffer("SELECT g.barcode,g.gname,g.num,g.price,g.tcode FROM  goods g left outer join goodstype gt on gt.tcode=g.tcode where 1=1  ");
//		  where 1=1 而不是 where g.gname like 1=1; sql语句还是到Navicate里面执行一下
		List<Goods>	goodList=null;
//		有查询条件(只有一个字的条件 或者两个字 或者三个字 称为模糊查询)
		if(g_name!=null && g_name.length()>0){
			sql.append("and g.gname like ?");
			g_name="%"+g_name+"%";
			goodList=DBUtils.query(Goods.class, sql.toString(),g_name);
			
		}else {
			goodList=DBUtils.query(Goods.class, sql.toString());
		}
		
		
//		String 变为StringBuffer 用 StringBuffer string=new String(" string");
//		StringBuffer变为String 用.toString()方法
	
		
//		List<Goods>	goodList=DBUtils.query(Goods.class, sql.toString(),g_name);
		return goodList;
	}

	/**
	 * @param goods
	 * @return
	 */
	public boolean saveGoods(Goods goods) {
		
		
		
		
		
//		on duplicate key upadate num=num+values(num)遇到主键是重复的就更新就好 且数量增加为现在的加上数据库的   on duplicate  key update num=num+values(num);update 后面可以写上要更新的列用逗号可以隔开
		String sql="insert into goods(barcode,gname,tcode,num,price) values(?,?,?,?,?) on duplicate  key update num=num+values(num)";
		
	int count=DBUtils.update(sql,goods.getBarcode(),goods.getGname(),goods.getTcode(),goods.getNum(),goods.getPrice());
		
		return count>0?true:false;
	}

	/**
	 * @param barcode
	 * @return
	 */
	public boolean deleteGoodsByBarcode(String barcode) {

		String sql="delete from goods where barcode=?";
		
	int count=DBUtils.update(sql,barcode);
		
		return count>0?true:false;
	}

	/**
	 * @param barcode
	 * @return
	 */
	public Goods selectedGoodsByBarcode(String barcode) {
         
		 
	 

        String sql="select g.barcode,g.gname,g.num,g.price,g.tcode,gt.tname from goods g left outer join goodstype gt  on g.tcode=gt.tcode where g.barcode=?";
//       查询一个对象                                                              agrs代表条件 条形码是一个条件
		Goods   good=DBUtils.selectSingleObject(Goods.class,sql,barcode);
		
		
		
		return  good;
		
		
		
		
		
		
		   		
	}

	/**
	 * @param tcode
	 * @param tname
	 * @return
	 */
	public List<GoodsType> SaveGoodsCategry(String tcode, String tname) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param tcode
	 * @param tname
	 * @return
	 */
	
	

}
