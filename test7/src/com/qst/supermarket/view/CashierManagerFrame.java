/**
 * 
 */
package com.qst.supermarket.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.User;
import com.qst.supermarket.service.GoodsService;
import com.qst.supermarket.utils.RegexUtils;

/**
 * @author 12345678
 *
 */
public class CashierManagerFrame extends JFrame implements ActionListener {
	private static User cashier;
	
	private JPanel mainPanel,infoPanel,InputPanel,cashierPanel;
	private JLabel 	lblCashier,lblName,lblGname,lblGnprice,lblGnum,lblGtotal,lblGnameValue,lblGnpriceValue,lblGnumValue,lblGtotalValue;
	

	private JLabel 	lblBarcode, lblBuynum;
	private JTextField tfBarcode,tfBuynum;
	private GoodsService goodsService=new GoodsService();
	
	private Goods goods;
	public CashierManagerFrame( User cashier) {
		this.cashier=cashier;
		
		// 创建窗体主键放到方法里面
		Init();

		this.setSize(800, 300);

		// 设置窗体居中
		this.setLocationRelativeTo(null);

		// 使最大化失效this.setResizable(false)
		// this.setResizable(false);
		this.setDefaultCloseOperation(LoginFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}
	/**
	 * 
	 */
	private void Init() {

		mainPanel=new JPanel(new BorderLayout());
		mainPanel.add(getInfoPanel(),BorderLayout.NORTH);
     	mainPanel.add(getInputPanel(),BorderLayout.CENTER);
     	mainPanel.add(getCashierPanel(),BorderLayout.SOUTH);
		
//		记住mainPanel要加入窗体里面
		this.add(mainPanel);
		
	}
	
	/**
	 * @return
	 */
	private Component getCashierPanel() {
		
		cashierPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
		
	
		lblCashier=new JLabel("收银员");
		lblName=new JLabel(cashier.getUsername());
		
		cashierPanel.add(lblCashier);
		cashierPanel.add(lblName);
//		不可以返回空值否则  Exception in thread "main" java.lang.NullPointerException
		return cashierPanel;
	}
	/**
	 * @return
	 */
	private Component getInputPanel() {
		InputPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,40,30));
	    
//	商品条形码	
		lblBarcode=new JLabel("商品条形码");
		tfBarcode=new JTextField(null,null,10);
		
		
//		商品数量
		lblBuynum=new JLabel("购买数量");
		tfBuynum=new JTextField(null,null,10);
		
		tfBarcode.addActionListener(this);
		tfBuynum.addActionListener(this);
		
		 
		InputPanel.add(lblBarcode);
		InputPanel.add(tfBarcode);
		
		InputPanel.add(lblBuynum);
		InputPanel.add(tfBuynum);
		
		return InputPanel;
	}
	/**
	 * @return
	 */
	private Component getInfoPanel() {
		infoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,40,30));
		lblGname=new JLabel("商品名称");
		lblGnprice=new JLabel("商品单价");
		lblGnum=new JLabel("库存数量");
		
		lblGtotal=new JLabel("商品总额");
		
		lblGnameValue=new JLabel();
		lblGnpriceValue=new JLabel();
		lblGnumValue=new JLabel();
		
		lblGtotalValue=new JLabel();
		
		
		infoPanel.add(lblGname);
		infoPanel.add(lblGnameValue);
		infoPanel.add(lblGnprice);
		infoPanel.add(lblGnpriceValue);
		infoPanel.add(lblGnum);
		infoPanel.add(lblGnumValue);
		infoPanel.add(lblGtotal);
		infoPanel.add(lblGtotalValue);
		
		
		
		return infoPanel;
	}
	public static void main(String[] args) {
		User cashier=new User();
		cashier.setUsername("hou");
		new CashierManagerFrame(cashier);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		
		String barcode=tfBarcode.getText();
		if(barcode==null||barcode.length()==0){
			
			
			JOptionPane.showMessageDialog(this, "请输入条形码");
			return;
		}else{
			
//			查询条形码查询商品
	     goods=goodsService.selectedGoodsByBarcode(barcode);
			
//			System.out.println(goods);
			if (goods!=null) {
				
				
//				商品数据显示在lblVaule中
				
				lblGnameValue.setText(goods.getGname());;
				lblGnpriceValue.setText(String.valueOf(goods.getPrice()));;
				lblGnumValue.setText(String.valueOf(goods.getNum()));;
				
				lblGtotalValue.setText("0.0");;
				
				
//				使光标定位到商品数量文本框
				tfBuynum.requestFocus();
				
			}else{
				
				
				JOptionPane.showMessageDialog(this, "输入的条形码错误，请重新输入");
				return;
				
			}
		}
		
		if(e.getSource()==tfBuynum){
    	   
    	   String amount=tfBuynum.getText();
//    	   判断是否是数字
    	     RegexUtils.isDigits(amount);
    	   if (!RegexUtils.isDigits(amount)) {
    			JOptionPane.showMessageDialog(this, "输入商品数量有误请重新输入");
				return;
		} 
    	   
//    	   购买数量
    	   int buyAmmount=Integer.parseInt(amount);
//    	   库存数量
    	   int repAmmount=goods.getNum();
    	  
    	   
    	   int result=repAmmount-buyAmmount;
    	   
    	   if (result<0) {
    		   JOptionPane.showMessageDialog(this, "库存不足请联系管理员");
				return;
		   }
    	   
    	   if (result>0) { 
    	   double price=goods.getPrice();
    	   double total=price*buyAmmount;
    	   lblGtotalValue.setText(String.valueOf(total));
    		   JOptionPane.showMessageDialog(this, "订单成功");
    		   return;
    	   }
    	  
			
    	   
    	   
    	   
//    	   保存订单记录
    	   
//    	   订单ID
    	   String orderid=UUID.randomUUID().toString();
//    	   员工id
    	   Integer cashierName=cashier.getId();
    	   
//    	   系统时间
    	    Date date=new Date();
    	   
// 条形码
    	    String barcode1=goods.getBarcode();
    	   
    	    
//    	    保存订单数量
    	    Integer num=goods.getNum();
		}
	
    	   
       }
	}
	
	
	
	   
	
       
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  

