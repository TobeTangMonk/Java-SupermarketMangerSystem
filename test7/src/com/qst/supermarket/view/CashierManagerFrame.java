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
		
		// �������������ŵ���������
		Init();

		this.setSize(800, 300);

		// ���ô������
		this.setLocationRelativeTo(null);

		// ʹ���ʧЧthis.setResizable(false)
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
		
//		��סmainPanelҪ���봰������
		this.add(mainPanel);
		
	}
	
	/**
	 * @return
	 */
	private Component getCashierPanel() {
		
		cashierPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
		
	
		lblCashier=new JLabel("����Ա");
		lblName=new JLabel(cashier.getUsername());
		
		cashierPanel.add(lblCashier);
		cashierPanel.add(lblName);
//		�����Է��ؿ�ֵ����  Exception in thread "main" java.lang.NullPointerException
		return cashierPanel;
	}
	/**
	 * @return
	 */
	private Component getInputPanel() {
		InputPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,40,30));
	    
//	��Ʒ������	
		lblBarcode=new JLabel("��Ʒ������");
		tfBarcode=new JTextField(null,null,10);
		
		
//		��Ʒ����
		lblBuynum=new JLabel("��������");
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
		lblGname=new JLabel("��Ʒ����");
		lblGnprice=new JLabel("��Ʒ����");
		lblGnum=new JLabel("�������");
		
		lblGtotal=new JLabel("��Ʒ�ܶ�");
		
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
			
			
			JOptionPane.showMessageDialog(this, "������������");
			return;
		}else{
			
//			��ѯ�������ѯ��Ʒ
	     goods=goodsService.selectedGoodsByBarcode(barcode);
			
//			System.out.println(goods);
			if (goods!=null) {
				
				
//				��Ʒ������ʾ��lblVaule��
				
				lblGnameValue.setText(goods.getGname());;
				lblGnpriceValue.setText(String.valueOf(goods.getPrice()));;
				lblGnumValue.setText(String.valueOf(goods.getNum()));;
				
				lblGtotalValue.setText("0.0");;
				
				
//				ʹ��궨λ����Ʒ�����ı���
				tfBuynum.requestFocus();
				
			}else{
				
				
				JOptionPane.showMessageDialog(this, "������������������������");
				return;
				
			}
		}
		
		if(e.getSource()==tfBuynum){
    	   
    	   String amount=tfBuynum.getText();
//    	   �ж��Ƿ�������
    	     RegexUtils.isDigits(amount);
    	   if (!RegexUtils.isDigits(amount)) {
    			JOptionPane.showMessageDialog(this, "������Ʒ������������������");
				return;
		} 
    	   
//    	   ��������
    	   int buyAmmount=Integer.parseInt(amount);
//    	   �������
    	   int repAmmount=goods.getNum();
    	  
    	   
    	   int result=repAmmount-buyAmmount;
    	   
    	   if (result<0) {
    		   JOptionPane.showMessageDialog(this, "��治������ϵ����Ա");
				return;
		   }
    	   
    	   if (result>0) { 
    	   double price=goods.getPrice();
    	   double total=price*buyAmmount;
    	   lblGtotalValue.setText(String.valueOf(total));
    		   JOptionPane.showMessageDialog(this, "�����ɹ�");
    		   return;
    	   }
    	  
			
    	   
    	   
    	   
//    	   ���涩����¼
    	   
//    	   ����ID
    	   String orderid=UUID.randomUUID().toString();
//    	   Ա��id
    	   Integer cashierName=cashier.getId();
    	   
//    	   ϵͳʱ��
    	    Date date=new Date();
    	   
// ������
    	    String barcode1=goods.getBarcode();
    	   
    	    
//    	    ���涩������
    	    Integer num=goods.getNum();
		}
	
    	   
       }
	}
	
	
	
	   
	
       
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  

