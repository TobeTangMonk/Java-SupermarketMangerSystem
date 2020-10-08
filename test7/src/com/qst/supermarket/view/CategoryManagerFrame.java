/**
 * 
 */
package com.qst.supermarket.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.GoodsType;
import com.qst.supermarket.model.User;
import com.qst.supermarket.service.GoodsService;

/**
 * @author 12345678
 *
 */
public class CategoryManagerFrame  extends JFrame implements ActionListener{
	private User user;

	private GoodsService goodsService=new GoodsService();
     private JButton TaddBtn,TcancelBtn;
	
	 private JPanel mainPanel;
	 private JTextField tfcode,tfname;

	
	public CategoryManagerFrame() {
		
		// ���ܵ�¼���û�
				this.user = user;
				// �������������ŵ���������
				Init();

				this.setSize(999, 666);

				// ���ô������
				this.setLocationRelativeTo(null);
				
				this.setTitle("��Ʒ����");

				// ʹ���ʧЧthis.setResizable(false)
				// this.setResizable(false);
				this.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
				this.setVisible(true);

				
				
//				һ�д�����Ͽ�ݼ� �������������    ENTER�����Բ�ѯ
//				 this.getRootPane().setDefaultButton(btnFind);
//				 this.getRootPane().setDefaultButton(g_addBtn);
				 
		
	}
	
	
	
	/**
	 * 
	 */
	private void Init() {
		mainPanel = new JPanel(new BorderLayout());
		
		
		
		
		mainPanel.add(getTcodeTnamePanel(), BorderLayout.NORTH);

		
         
		
		mainPanel.add(getTypeaddorCancel(), BorderLayout.CENTER);

		
		// cardPanel.add(getEmployeeManagerPanel(),"employeeManagerPanel");
		// cardPanel.add(getStaticMangerPanel(),"staticManagerPanel");

		this.add(mainPanel);
		
	}



	/**
	 * @return
	 */
	private Component getTypeaddorCancel() {
		JPanel TypeaddCancel=new JPanel(new FlowLayout(FlowLayout.LEFT,200,30));
		TaddBtn=new JButton("���");
		 TcancelBtn=new JButton("ɾ��");
		 
		 
		TaddBtn.setFont(new Font("����",Font.BOLD,50));
		TcancelBtn.setFont(new Font("����",Font.BOLD,50));
		
		TaddBtn.addActionListener(this);
		TcancelBtn.addActionListener(this);
		TypeaddCancel.add(TaddBtn);
		TypeaddCancel.add(TcancelBtn);
		mainPanel.add(TypeaddCancel);
		return TypeaddCancel;
	}



	/**
	 * @return
	 */
	private Component getTcodeTnamePanel() {
		
		JPanel TcodenamePanel=new JPanel(new FlowLayout(FlowLayout.LEFT,40,30));
		
		JLabel Tcode=new JLabel("��Ʒ����");
	   tfcode=new JTextField(null,null,30);
		
		
		JLabel Tname=new JLabel("��Ʒ������");
		
	    tfname=new JTextField(null,null,30);
		tfname.setSize(new Dimension(99,99));
		
//		��Ʒ���������������Զ�������Ʒ������
	
		
		tfname.requestFocus();
		Tcode.requestFocus();
		TcodenamePanel.add(Tcode);
		TcodenamePanel.add(tfcode);
		TcodenamePanel.add(Tname);
		TcodenamePanel.add(tfname);
		
		
		return TcodenamePanel;
	}



	public static void main(String[] args) {
		new CategoryManagerFrame();
	}



	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==TaddBtn  ) {
		String tcode=tfcode.getText().toString();
		String tname=tfname.getText().toString();
			if(tcode==null || tcode.length()==0 ){
				
				JOptionPane.showMessageDialog(this, "�����������,��Ʒ����Ϊ��");
				return;
//			GoodsType goodsType=new GoodsType(tcode,tname);
			 
			
//			     
			}
		   if (tname==null||tname.length()==0) {

				JOptionPane.showMessageDialog(this, "�����������,��Ʒ���Ͳ�����Ϊ��");
				return;
			}else {
//				GoodsType goodsType=new GoodsType(tcode, tname);
//			  List<GoodsType>    goodsTypes=goodsService.SaveGoodsCategry(tcode,tname);
//				
//				
				
				
			}
			
			
			
	
		
			
		
		}	
		if (e.getSource()==TcancelBtn) {
		this.dispose();
		
		}
		
		
		
		
	}
	
     
}
