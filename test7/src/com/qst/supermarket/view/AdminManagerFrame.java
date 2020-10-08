/**
 * 
 */
package com.qst.supermarket.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.qst.supermarket.chart.PiecharBuilder;
import com.qst.supermarket.model.Goods;
import com.qst.supermarket.model.User;
import com.qst.supermarket.service.GoodsService;
import com.qst.supermarket.service.PieData;
import com.qst.supermarket.service.orderService;

/**
 * @author 12345678
 *
 */
public class AdminManagerFrame extends JFrame implements ActionListener {

	private User user;

	private JPanel PieChartPanel,BarChartPanel,mainPanel,dataPanel,btnPanel, buttonPanel,dataStaticManagerPanel, cardPanel, goodsManagerPanel, findPanel, operationBtnPanel;

	private JButton goodsBtn, employeeBtn, statisticBtn, btnFind;
	// ��ѯ�ı���
	private JTextField tfFind;
	private CardLayout cardLayout;

	private JButton g_addBtn, g_deleteBtn, g_modifyBtn, g_categoryBtn;
//	����ͳ��Button
    JButton employeebtn,goodsStatisticbtn;
	private JTable goodstable;
	// �������
	private int g_rowSelectedIndex;
	// ��������
	private JScrollPane jscrollPane;

	DefaultTableModel goodsTableModel;
	// service
	GoodsService goodsSevice = new GoodsService();

	public AdminManagerFrame(User user) throws HeadlessException {
		// ���ܵ�¼���û�
		this.user = user;
		// �������������ŵ���������
		Init();

		this.setSize(1500, 900);

		// ���ô������
		this.setLocationRelativeTo(null);

		// ʹ���ʧЧthis.setResizable(false)
		// this.setResizable(false);
		this.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		
		
//	һ�д�����Ͽ�ݼ� �������������    ENTER�����Բ�ѯ
//		 this.getRootPane().setDefaultButton(btnFind);
      	
//		 
	}

	/**
	 * 
	 */
	private void Init() {

		mainPanel = new JPanel(new BorderLayout());
		//
		// JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
		//
		// JButton btngoodstype=new JButton("��Ʒ����");
		// JButton btnstatics=new JButton("ͳ������");
		// JButton btngoods=new JButton("��Ʒ����");
		//
		// northPanel.add(btngoodstype);
		// northPanel.add(btnstatics);
		// northPanel.add(btngoods);
		// mainPanel.add(northPanel,BorderLayout.NORTH);

		// ���䴴����һ��������װ�ã���������һ�ε�ʹ��

		// �������������ť ������ť��װ�����getButtonPanel()�����У�add()���� ֱ������
		mainPanel.add(getButtonPanel(), BorderLayout.NORTH);

		// ��Ƭ���ֹ���������Ӵ���

		cardLayout = new CardLayout();

		cardPanel = new JPanel(cardLayout);
		mainPanel.add(cardPanel, BorderLayout.CENTER);

		cardPanel.add(getGoodsManagerPanel(), "goodsMangerPanel");
//	 cardPanel.add(getEmployeeManagerPanel(),"employeeManagerPanel");
	 cardPanel.add(getStaticMangerPanel(),"staticManagerPanel");

		this.add(mainPanel);
	}

	/**
	 * @return
	 */
	private Component getEmployeeManagerPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	private JPanel getStaticMangerPanel() {
		dataStaticManagerPanel=new JPanel(new BorderLayout());
		
		
		
//		�����ʾͼ���Panel
//		dataStaticManagerPanel.add(dataPanel,BorderLayout.CENTER);
		
		dataPanel=new JPanel();
		PieChartPanel=new JPanel();//����Ա�����з�����ͼ
		BarChartPanel=new JPanel();//������Ʒ���з�����ͼ
		
		dataPanel.add(PieChartPanel);
		dataPanel.add(BarChartPanel);
	
		
		
//		�����ʾButton��Panel
	  btnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
	 employeebtn=new JButton("Ա��ͳ������ҵ��"); 
	 goodsStatisticbtn=new JButton("��Ʒ����ҵ��");
	 
	 
	 employeebtn.addActionListener(this);
	 goodsStatisticbtn.addActionListener(this);
		
		btnPanel.add(employeebtn);
		btnPanel.add(goodsStatisticbtn);
		dataStaticManagerPanel.add(btnPanel,BorderLayout.SOUTH);
		
  		return dataStaticManagerPanel;
	}

	/**
	 * @return
	 */
	// Component ��JPanel��һ������

	// getGoodsManagerPanel������ ��ѯ��ť���ı�������֣�Ƕ����CardLayout����
	private JPanel getGoodsManagerPanel() {
		goodsManagerPanel = new JPanel(new BorderLayout());
		// �˷���������Ӳ�ѯ��ť���ı����Լ���ѯ��ť�ļ����¼�
		goodsManagerPanel.add(getFindPanel(), BorderLayout.NORTH);
		// goodsManagerPanel.add(findPanel);

		// ��ѯ���е���Ʒ

		List<Goods> goodsList = goodsSevice.getAllgoods();

		// Ϊ�˿�����Ӹ����� ���Լ��Ϲ�����JScrollPane

		jscrollPane = new JScrollPane(getGoodsTable(goodsList));
		jscrollPane.setPreferredSize(new Dimension(900, 600));

		goodsManagerPanel.add(jscrollPane, BorderLayout.CENTER);

		// ��Ӳ�����ť

		operationBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

		g_addBtn = new JButton("�����Ʒ");
//		 this.getRootPane().setDefaultButton(g_addBtn);
		g_deleteBtn = new JButton("ɾ����Ʒ");
		g_modifyBtn = new JButton("�޸���Ʒ");
		g_categoryBtn = new JButton("��Ʒ���");
        
//		��Ӽ����¼�
		
		g_addBtn.addActionListener(this);
		g_deleteBtn.addActionListener(this);
		g_modifyBtn.addActionListener(this);
		g_categoryBtn.addActionListener(this);
		
		
		
		
		
		operationBtnPanel.add(g_addBtn);
		operationBtnPanel.add(g_deleteBtn);
		operationBtnPanel.add(g_modifyBtn);
		operationBtnPanel.add(g_categoryBtn);

		goodsManagerPanel.add(operationBtnPanel, BorderLayout.SOUTH);

		return goodsManagerPanel;
	}

	/**
	 * @param goodList
	 * @return
	 */

	private JPanel getFindPanel() {

		findPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		// tFfind=new JTextField("������Ҫ��ѯ������");
		// ��ѯ���ı���̫С�˿�����һ�·���
		// ��Ӳ�ѯ�ı���
		tfFind = new JTextField(60);

		// �����ı����С
		tfFind.setPreferredSize(new Dimension(300, 75));

		tfFind.setText("������Ҫ���ҵ���Ʒ������");

		// ��Ӳ�ѯ��ť
		btnFind = new JButton("��ѯ");
		// btnFind.setSize(30,30);

		findPanel.add(tfFind);
		findPanel.add(btnFind);

		tfFind.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {

				tfFind.setText(" ");

			}

		});
		
//		JTextArea textArea = new JTextArea();
//		tfFind.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if((char)e.getKeyChar()==KeyEvent.VK_ENTER) {
//					System.out.println("ENTER");
//				}
//			}
//		});
		
		// �ص�����this������������ں����еİ�ť���ᴥ������¼�
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO Auto-generated method stub
		//
		// }

		// this���������е�BUttonʵ�ּ���  ���е�Button�����¼����������ĵײ�
		btnFind.addActionListener(this);

		// ��������淵�ؾͺ�

		return findPanel;
	}

	// �������table ���������ݷ�������
	private JTable getGoodsTable(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		String[] header = { "����", "������", "�۸�", "����", "�������", "��������" };
		Object[][] data = null;

		// ɨ�����ݿ�ļ��ϣ�������data,��ֵ
		if (goodsList != null && goodsList.size() > 0) {
			// ���ϵĴ�СgoodsList.size()���ڱ�������
			data = new Object[goodsList.size()][header.length];

			int rowNum = 0;

			// Goods���д�����
			for (Goods goods : goodsList) {
				Object[] row = new Object[] {

						goods.getGname(), goods.getBarcode(), goods.getPrice(), goods.getNum(), goods.getTcode(),
						goods.getTname() };
				data[rowNum++] = row;

			}

		}

		// ���TableModel ��������(Ϊһ����ά����) header(Ϊһ��һά����)
		goodsTableModel = new DefaultTableModel(data, header);

		// ʹ��񲻿��Ա༭������
		goodstable= new JTable(goodsTableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		goodstable.getTableHeader().setFont(new Font("����", Font.BOLD, 20));
		goodstable.setFont(new Font("����", Font.CENTER_BASELINE, 20));
		goodstable.setRowHeight(30);

		// ���ѡ��ģ�͵��޸�

		// ���õ���ѡ��
		goodstable.setSelectionMode(ListSelectionModel. SINGLE_SELECTION);

		// ����ѡ��ģ��
		ListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			// valueChanged(ListSelectionEvent e) ��굥�������ͷŶ��ᴥ�����¼�
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// e.getValueIsAdjusting()���Թ��ǵ��ͷ��¼�
				if (e.getValueIsAdjusting()) {
					
//					goodstable.getSelectedRows() ѡ����з�����������
//					goodstable.gerSelectedRow()  ѡ���з���int
				int 	g_rowSelectedIndex = goodstable.getSelectedRow();
//					System.out.println(g_rowSelectedIndex);

					// �����¼�
					// System.out.println("000000");
				}
			}
		});
		// �������rowѡ��ģ��
		goodstable.setSelectionModel(listSelectionModel);

		return goodstable;
	}

	/**
	 * @return
	 */

	/**
	 * @return
	 */

	// ��Ӳ�ѯ��ť�����¼�

	private Component getButtonPanel() {

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

		goodsBtn = new JButton("��Ʒ����");
		employeeBtn = new JButton("Ա��ͳ��");
		statisticBtn = new JButton("ͳ������"); 

		buttonPanel.add(goodsBtn);
		buttonPanel.add(employeeBtn);
//		PieDataSet dataset=getPieDateSet();
//		JFreeChart chart=ContentHandlerFactory.
//		
//		PieDataSet dataset=getPiaDataSet();
//		 JFreeChart chart=
		
		

		buttonPanel.add(statisticBtn);
		
		
		goodsBtn.addActionListener(this);
		employeeBtn.addActionListener(this);
		statisticBtn.addActionListener(this);
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		// �������뷵��һ������ֵ ������Է���Button
		return buttonPanel;
	}

	public static void main(String[] args) {

		new AdminManagerFrame(null);

	}

	private void setGoodsTable(List<Goods> goodsList) {
		String[] header = { "����", "������", "�۸�", "����", "�������", "��������" };
		Object[][] data = null;

		// ɨ�����ݿ�ļ��ϣ�������data,��ֵ
		if (goodsList != null && goodsList.size() > 0) {
			// ���ϵĴ�СgoodsList.size()���ڱ�������
			data = new Object[goodsList.size()][header.length];

			int rowNum = 0;

			// Goods���д�����
			for (Goods goods : goodsList) {
				Object[] row = new Object[] {

						goods.getGname(), goods.getBarcode(), goods.getPrice(), goods.getNum(), goods.getTcode(),
						goods.getTname() };
				data[rowNum++] = row;

			}
			goodsTableModel = new DefaultTableModel(data, header);
			goodstable.setModel(goodsTableModel);

		}
        return;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	
//	�������getFindPanel()�����btnFind��ťʵ�����������
	
	// AdminManagerFrameʵ��ActionListener�ӿڣ�AdminManager��ť������
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//��Ʒ��ѯ
	
//		1.ʹ��button.setMnemonic���������磺jbtRemove.setMnemonic(java.awt.event.KeyEvent.VK_D);     
//		ע��ʹ���������ֻ����alt+d�ſ���ʹ�ÿ�ݼ������Կ����ǰ����ȼ���
				
		if (e.getSource() == btnFind) {
			String g_name = tfFind.getText();
			btnFind.setMnemonic(KeyEvent.VK_D);
			if (g_name.equals("������Ҫ���ҵ���Ʒ������")) {
				JOptionPane.showMessageDialog(this, "������Ҫ���ҵ���Ʒ����");
				return;
			}

			// �����ݿ⴫���ķ��ڼ����е�����
			
//			g_gname.trim();//�Ƴ�g_gname�ո�
			g_name = g_name.trim();
			List<Goods> goodsList = goodsSevice.selectedGoodsByName(g_name);
//			System.out.println(g_name);

			if (goodsList == null || goodsList.size() == 0) {

				JOptionPane.showMessageDialog(this, "û�в�ѯ����������������");

				return;
			} else {
				
//				btnFind.addKeyListener(new KeyAdapter() {
//					public void keyPressed(KeyEvent e) {
//						int keyCode=e.getKeyCode();  //��ȡ���ļ����ϵĽ�
//						if(keyCode==KeyEvent.VK_ENTER ){  //�Ƚ�
//							
//						}
//						
//						
//					}
//					
//				});
				// ����ѯ�����ļ�����ʾ�ڵ�ǰ����
				this.setGoodsTable(goodsList);

			}
		}
		
		g_addBtn.addActionListener(this);
		g_deleteBtn.addActionListener(this);
		g_modifyBtn.addActionListener(this);
		g_categoryBtn.addActionListener(this);
//		��Ʒ���
		if(e.getSource()==g_addBtn){

		new AddGoodsFrame(this);
		}
	
//	       ��Ʒɾ��
		if(e.getSource()==g_deleteBtn){
//			
			int result=JOptionPane.showConfirmDialog(this, "�Ƿ�ȷ��ɾ��");
		    if (result==JOptionPane.OK_OPTION) { 
//				��ȡ������
		    	String barcode=(String) goodsTableModel.getValueAt(g_rowSelectedIndex, 1);
		    
		    	boolean flag=goodsSevice.deleteGoodsByBarcode(barcode); 
//		    	System.out.println(barcode);
		    	if(flag){
		    		goodsTableModel.removeRow(g_rowSelectedIndex);
		      
		    		JOptionPane.showMessageDialog(this, "ɾ���ɹ�");
		    		
		    		return;
		    		
		    	}else {
		    		JOptionPane.showMessageDialog(this, "ɾ��ʧ��,�������Ա��ϵ");
		    		return;
				}
		    	
			}
		}
		
//		�޸���Ʒ
		if(e.getSource()==g_modifyBtn){

//			��ȡ�޸ļ�¼��ֵ
//			String name=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 0);
/*			String barcode=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 0);
			Double price=(Double) goodsTableModel.getValueAt(g_rowSelectedIndex, 1);
			Integer num= (Integer) goodsTableModel.getValueAt(g_rowSelectedIndex, 2);
			String tcode=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 3);
			String gname=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 4);
			String tname=(String)goodsTableModel.getValueAt(g_rowSelectedIndex,5);
			Goods goods=new Goods( barcode,price,num ,gname,tcode,tname);
			
			*/
//			Update��AddGoodsFrame���帴�ƹ����ģ�����Update��Ҫ�޸Ĵ��� ����goods
			new ModifyGoodsFrame(this);
			
		}
//		��Ʒ����
		if(e.getSource()==g_categoryBtn){
			
			new CategoryManagerFrame();
			
			
		}
	 
		if (e.getSource()==statisticBtn) {
			
			 cardPanel.add(getStaticMangerPanel(),"staticManagerPanel");
			 cardLayout.show(cardPanel, "staticManagerPanel");
			
		
		}
	
		
			if (e.getSource()==goodsBtn) {
				cardPanel.add(getGoodsManagerPanel(), "goodsMangerPanel");
				cardPanel.add(getGoodsManagerPanel(), "goodsMangerPanel");
				cardLayout.show(cardPanel, "goodsManagerPanel");
			}
			if (e.getSource()==employeeBtn) {
					cardPanel.add(getEmployeeManagerPanel(),"employeeManagerPanel");
					cardLayout.show(cardPanel, "employeeManagerPanel");

			}
			
			
//			Ա��ͳ������
			if (e.getSource()==employeebtn ) {
			List<PieData>	pieDatas=orderService.getEmloyeeStaticData();
//				
		   System.out.println(pieDatas);
			//					�������
					PieChartPanel.removeAll();
					PieChartPanel.add(new PiecharBuilder(pieDatas).getPieChartPanel());
				    
					dataPanel.removeAll();
				    dataPanel.add(PieChartPanel);
				    dataPanel.updateUI();
				   
			}
			if (e.getSource()==goodsStatisticbtn) {
				
			}
		
		
	}

	/**
	 * @param goodsList
	 */

	
}
