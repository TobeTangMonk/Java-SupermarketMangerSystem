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
	// 查询文本框
	private JTextField tfFind;
	private CardLayout cardLayout;

	private JButton g_addBtn, g_deleteBtn, g_modifyBtn, g_categoryBtn;
//	数据统计Button
    JButton employeebtn,goodsStatisticbtn;
	private JTable goodstable;
	// 单击表格
	private int g_rowSelectedIndex;
	// 表格滚动条
	private JScrollPane jscrollPane;

	DefaultTableModel goodsTableModel;
	// service
	GoodsService goodsSevice = new GoodsService();

	public AdminManagerFrame(User user) throws HeadlessException {
		// 接受登录的用户
		this.user = user;
		// 创建窗体主键放到方法里面
		Init();

		this.setSize(1500, 900);

		// 设置窗体居中
		this.setLocationRelativeTo(null);

		// 使最大化失效this.setResizable(false)
		// this.setResizable(false);
		this.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		
		
//	一行代码加上快捷键 输入框输入内容    ENTER即可以查询
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
		// JButton btngoodstype=new JButton("商品分类");
		// JButton btnstatics=new JButton("统计数据");
		// JButton btngoods=new JButton("商品分类");
		//
		// northPanel.add(btngoodstype);
		// northPanel.add(btnstatics);
		// northPanel.add(btngoods);
		// mainPanel.add(northPanel,BorderLayout.NORTH);

		// 将其创建成一个方法封装好，有利于下一次的使用

		// 北部添加三个按钮 三个按钮封装在这个getButtonPanel()方法中，add()可以 直接增加
		mainPanel.add(getButtonPanel(), BorderLayout.NORTH);

		// 卡片布局管理器中添加窗体

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
		
		
		
//		添加显示图标的Panel
//		dataStaticManagerPanel.add(dataPanel,BorderLayout.CENTER);
		
		dataPanel=new JPanel();
		PieChartPanel=new JPanel();//按照员工进行分析饼图
		BarChartPanel=new JPanel();//按照商品进行分析饼图
		
		dataPanel.add(PieChartPanel);
		dataPanel.add(BarChartPanel);
	
		
		
//		添加显示Button的Panel
	  btnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
	 employeebtn=new JButton("员工统计销售业绩"); 
	 goodsStatisticbtn=new JButton("商品销售业绩");
	 
	 
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
	// Component 是JPanel的一个父类

	// getGoodsManagerPanel里面有 查询按钮和文本框胡布局（嵌套了CardLayout布局
	private JPanel getGoodsManagerPanel() {
		goodsManagerPanel = new JPanel(new BorderLayout());
		// 此方法可以添加查询按钮和文本框以及查询按钮的监听事件
		goodsManagerPanel.add(getFindPanel(), BorderLayout.NORTH);
		// goodsManagerPanel.add(findPanel);

		// 查询所有的商品

		List<Goods> goodsList = goodsSevice.getAllgoods();

		// 为了可以添加更多行 可以加上滚动条JScrollPane

		jscrollPane = new JScrollPane(getGoodsTable(goodsList));
		jscrollPane.setPreferredSize(new Dimension(900, 600));

		goodsManagerPanel.add(jscrollPane, BorderLayout.CENTER);

		// 添加操作按钮

		operationBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

		g_addBtn = new JButton("添加商品");
//		 this.getRootPane().setDefaultButton(g_addBtn);
		g_deleteBtn = new JButton("删除商品");
		g_modifyBtn = new JButton("修改商品");
		g_categoryBtn = new JButton("商品类别");
        
//		添加监听事件
		
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
		// tFfind=new JTextField("请输入要查询的内容");
		// 查询的文本框太小了可以用一下方法
		// 添加查询文本框
		tfFind = new JTextField(60);

		// 设置文本框大小
		tfFind.setPreferredSize(new Dimension(300, 75));

		tfFind.setText("请输入要查找的商品的名称");

		// 添加查询按钮
		btnFind = new JButton("查询");
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
		
		// 重点用了this监听后这个窗口胡所有的按钮都会触发这个事件
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO Auto-generated method stub
		//
		// }

		// this可以让所有的BUtton实现监听  所有的Button监听事件都在这个类的底部
		btnFind.addActionListener(this);

		// 放在最后面返回就好

		return findPanel;
	}

	// 创建表格table 将传入数据放入表格中
	private JTable getGoodsTable(List<Goods> goodsList) {
		// TODO Auto-generated method stub
		String[] header = { "名称", "条形码", "价格", "数量", "分类编码", "分类名称" };
		Object[][] data = null;

		// 扫描数据库的集合，给数据data,赋值
		if (goodsList != null && goodsList.size() > 0) {
			// 集合的大小goodsList.size()等于表中行数
			data = new Object[goodsList.size()][header.length];

			int rowNum = 0;

			// Goods表中传过来
			for (Goods goods : goodsList) {
				Object[] row = new Object[] {

						goods.getGname(), goods.getBarcode(), goods.getPrice(), goods.getNum(), goods.getTcode(),
						goods.getTname() };
				data[rowNum++] = row;

			}

		}

		// 表格TableModel 操作数据(为一个二维数组) header(为一个一维数组)
		goodsTableModel = new DefaultTableModel(data, header);

		// 使表格不可以编辑胡方法
		goodstable= new JTable(goodsTableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		goodstable.getTableHeader().setFont(new Font("宋体", Font.BOLD, 20));
		goodstable.setFont(new Font("宋体", Font.CENTER_BASELINE, 20));
		goodstable.setRowHeight(30);

		// 表格选择模型的修改

		// 设置单行选择
		goodstable.setSelectionMode(ListSelectionModel. SINGLE_SELECTION);

		// 设置选择模型
		ListSelectionModel listSelectionModel = new DefaultListSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			// valueChanged(ListSelectionEvent e) 鼠标单击或者释放都会触发该事件
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// e.getValueIsAdjusting()可以过虑掉释放事件
				if (e.getValueIsAdjusting()) {
					
//					goodstable.getSelectedRows() 选择多行返回数组类型
//					goodstable.gerSelectedRow()  选择单行返回int
				int 	g_rowSelectedIndex = goodstable.getSelectedRow();
//					System.out.println(g_rowSelectedIndex);

					// 测试事件
					// System.out.println("000000");
				}
			}
		});
		// 表格设置row选择模型
		goodstable.setSelectionModel(listSelectionModel);

		return goodstable;
	}

	/**
	 * @return
	 */

	/**
	 * @return
	 */

	// 添加查询按钮监听事件

	private Component getButtonPanel() {

		buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

		goodsBtn = new JButton("商品分类");
		employeeBtn = new JButton("员工统计");
		statisticBtn = new JButton("统计数据"); 

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
		// 方法必须返回一个类型值 这里可以返回Button
		return buttonPanel;
	}

	public static void main(String[] args) {

		new AdminManagerFrame(null);

	}

	private void setGoodsTable(List<Goods> goodsList) {
		String[] header = { "名称", "条形码", "价格", "数量", "分类编码", "分类名称" };
		Object[][] data = null;

		// 扫描数据库的集合，给数据data,赋值
		if (goodsList != null && goodsList.size() > 0) {
			// 集合的大小goodsList.size()等于表中行数
			data = new Object[goodsList.size()][header.length];

			int rowNum = 0;

			// Goods表中传过来
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

	
//	在上面胡getFindPanel()里面的btnFind按钮实现了这个方法
	
	// AdminManagerFrame实现ActionListener接口，AdminManager按钮监听器
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//商品查询
	
//		1.使用button.setMnemonic方法，例如：jbtRemove.setMnemonic(java.awt.event.KeyEvent.VK_D);     
//		注意使用这个方法只能用alt+d才可以使用快捷键，可以看作是绑定了热键。
				
		if (e.getSource() == btnFind) {
			String g_name = tfFind.getText();
			btnFind.setMnemonic(KeyEvent.VK_D);
			if (g_name.equals("请输入要查找的商品的名称")) {
				JOptionPane.showMessageDialog(this, "请输入要查找的商品名称");
				return;
			}

			// 从数据库传来的放在集合中的数据
			
//			g_gname.trim();//移除g_gname空格
			g_name = g_name.trim();
			List<Goods> goodsList = goodsSevice.selectedGoodsByName(g_name);
//			System.out.println(g_name);

			if (goodsList == null || goodsList.size() == 0) {

				JOptionPane.showMessageDialog(this, "没有查询到符合条件的数据");

				return;
			} else {
				
//				btnFind.addKeyListener(new KeyAdapter() {
//					public void keyPressed(KeyEvent e) {
//						int keyCode=e.getKeyCode();  //获取按的键盘上的健
//						if(keyCode==KeyEvent.VK_ENTER ){  //比较
//							
//						}
//						
//						
//					}
//					
//				});
				// 将查询出来的集合显示在当前窗口
				this.setGoodsTable(goodsList);

			}
		}
		
		g_addBtn.addActionListener(this);
		g_deleteBtn.addActionListener(this);
		g_modifyBtn.addActionListener(this);
		g_categoryBtn.addActionListener(this);
//		商品添加
		if(e.getSource()==g_addBtn){

		new AddGoodsFrame(this);
		}
	
//	       商品删除
		if(e.getSource()==g_deleteBtn){
//			
			int result=JOptionPane.showConfirmDialog(this, "是否确认删除");
		    if (result==JOptionPane.OK_OPTION) { 
//				获取条形码
		    	String barcode=(String) goodsTableModel.getValueAt(g_rowSelectedIndex, 1);
		    
		    	boolean flag=goodsSevice.deleteGoodsByBarcode(barcode); 
//		    	System.out.println(barcode);
		    	if(flag){
		    		goodsTableModel.removeRow(g_rowSelectedIndex);
		      
		    		JOptionPane.showMessageDialog(this, "删除成功");
		    		
		    		return;
		    		
		    	}else {
		    		JOptionPane.showMessageDialog(this, "删除失败,请与管理员联系");
		    		return;
				}
		    	
			}
		}
		
//		修改商品
		if(e.getSource()==g_modifyBtn){

//			获取修改记录的值
//			String name=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 0);
/*			String barcode=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 0);
			Double price=(Double) goodsTableModel.getValueAt(g_rowSelectedIndex, 1);
			Integer num= (Integer) goodsTableModel.getValueAt(g_rowSelectedIndex, 2);
			String tcode=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 3);
			String gname=(String)goodsTableModel.getValueAt(g_rowSelectedIndex, 4);
			String tname=(String)goodsTableModel.getValueAt(g_rowSelectedIndex,5);
			Goods goods=new Goods( barcode,price,num ,gname,tcode,tname);
			
			*/
//			Update是AddGoodsFrame窗体复制过来的，但是Update需要修改窗体 加入goods
			new ModifyGoodsFrame(this);
			
		}
//		商品分类
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
			
			
//			员工统计数据
			if (e.getSource()==employeebtn ) {
			List<PieData>	pieDatas=orderService.getEmloyeeStaticData();
//				
		   System.out.println(pieDatas);
			//					清空内容
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
