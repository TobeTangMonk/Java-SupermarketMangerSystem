/**
 * 
 */
package com.qst.supermarket.model;

import com.qst.supermarket.service.GoodsService;

/**
 * @author 12345678
 *
 */
public class GoodsType {
   private String tcode;
  private String tname;
public String getTcode() {
	return tcode;
}
public void setTcode(String tcode) {
	this.tcode = tcode;
}
public String getTname() {
	return tname;
}
@Override
public String toString() {
	return "GoodsType [tcode=" + tcode + ", tname=" + tname + "]";
}
public void setTname(String tname) {
	this.tname = tname;
}
  

}
