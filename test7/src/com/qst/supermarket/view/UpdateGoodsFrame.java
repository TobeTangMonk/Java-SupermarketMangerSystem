/**
 * 
 */
package com.qst.supermarket.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qst.supermarket.model.Goods;
import com.qst.supermarket.service.GoodsService;
import com.qst.supermarket.service.GoodsTypeService;

/**
 * @author 12345678
 *
 */
public class UpdateGoodsFrame  extends JFrame implements ActionListener  {
  
//	����һ�������壬
	private AdminManagerFrame adminManagerFrame;
	private Goods goods;
	
	private JPanel mainPanel,btnPanel ;
	private JComboBox goodstypeCombox;
	private JTextField g_barcode,g_name,g_price,g_num;
	private JButton addBtn,batchBtn,CancelBtn,ModifBtn;
	
	private Map<String, String> goodsTypeMap;
    private GoodsTypeService goodsTypeSevice=new GoodsTypeService();
    private GoodsService goodsService=new GoodsService();
	public UpdateGoodsFrame(AdminManagerFrame adminManagerFrame,Goods goods) {

		this.adminManagerFrame=adminManagerFrame;
		
		// �������������ŵ���������
		Init();

		this.setSize(448, 366);
         
		
//		���ñ���
		this.setTitle("�����Ʒ");
		// ���ô������
		this.setLocationRelativeTo(null);

		// ʹ���ʧЧthis.setResizable(false)
		// this.setResizable(false);
		this.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		
	}

	/**
	 * 
	 */
	public UpdateGoodsFrame() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private void Init() {
		// TODO Auto-generated method stub
		
		
		
//		�����ݿ��е����ݿ�goodsType�����ݵ��뵽ҳ����
         goodsTypeMap=goodsTypeSevice.getAllGoodsType();
		
	    Collection<String> values=goodsTypeMap.values();
//	    String[]  type=new String[values.size()];
	    String[] typeNameArray=new String[values.size()];
	    
	    if (values!=null||values.size()>0) {
			
	    	int i=0;
	    	for (String  goodsTypeName : values) {
	    		
	    		
	    		typeNameArray[i]=goodsTypeName;
	    		
	    		i++;
	    		
	    	}
		} 
		mainPanel = new JPanel(new GridLayout(6,1,0,20));
		
		goodstypeCombox=new JComboBox<String>(typeNameArray);
		goodstypeCombox.setSize(60, 40);
		  
		g_barcode=new JTextField("��Ʒ������");
		
		g_name=new JTextField("��Ʒ����");
		g_price=new JTextField("��Ʒ�۸�");
		g_num=new JTextField("��Ʒ����");
		
		
//		����ֵ
		goodstypeCombox.setSelectedItem(goods.getTcode());
		g_barcode.setText(goods.getBarcode());
		g_name.setText(goods.getGname());
		g_price.setText(String.valueOf(goods.getPrice()));
		g_num.setText(String.valueOf(goods.getNum()));
		
		
		
		
	    btnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,20,2));
	    ModifBtn=new JButton("�޸�");
	    CancelBtn=new JButton("ȡ��");
		
	    
	    ModifBtn.addActionListener(this);
	    CancelBtn.addActionListener(this);
		
		
		mainPanel.add(goodstypeCombox);
		
		mainPanel.add(g_barcode);
		mainPanel.add(g_name);
		mainPanel.add(g_price);
		mainPanel.add(g_num);
		
		btnPanel.add(ModifBtn);
		btnPanel.add(CancelBtn);
		mainPanel.add(btnPanel);
		
		
//		�����˼�����鲼����ǰҳ��
		this.add(mainPanel);
		
	}
	
	
	 public static void main(String[] args) {
		new UpdateGoodsFrame(null,null);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==addBtn) {
//			������
		String tname=(String)goodstypeCombox.getSelectedItem();
		
//		ͨ��tname��ȡtcode
		String tcode=null;
		
		for ( Map.Entry<String, String> entry : goodsTypeMap.entrySet()) {
			
			String typeName=entry.getValue();
			
			if(typeName.equals(tname)){
				
				tcode=entry.getKey();
				
				
			}
			
			
			
	}		
			
			
			
		
		
		String barcode=g_barcode.getText();
	    String name=g_name.getText();
//	        ���ı��������ֵ����Ϊ��Ӧ���͵�ֵ
		double	price=Double.parseDouble(g_price.getText());
	    Integer num=Integer.valueOf(g_num.getText().trim() );
			
		Goods goods=new Goods(barcode,price,num,name,tcode,tname);	
//		��������
		boolean flag=goodsService.saveGoods(goods);
		
//		���뵽�����
		if (flag) {
//			����������Ӧ
			  Object[]  rowData=new Object[]{name,barcode,price,num,tcode,tname}; 
			  this.adminManagerFrame.goodsTableModel.addRow(rowData);
//			  while (true) {
				
			  JOptionPane.showMessageDialog(this, "��ӳɹ�");
//		   break;
//			}	
			 return;
			
		}else{
			
			JOptionPane.showMessageDialog(this, "�������ݴ����������Ա��ϵ");
		}
		
		
	}
			
			
			
			
			
							
		if (e.getSource()==batchBtn) {
			JFileChooser fc=new JFileChooser();
			int result=fc.showOpenDialog(UpdateGoodsFrame.this);
	//		System.out.println(result);
	
			if(result==JFileChooser.APPROVE_OPTION){
				
//				��ȡ�ļ�
				File file=fc.getSelectedFile();
		        List<Goods>  goodlist=goodsService.readXMLloaddata(file);
		        boolean flag=goodsService.saveGoods(goodlist);
				  
				
				if (flag) {
					  JOptionPane.showMessageDialog(this, "����ɹ�");
						return;
				}else {
					  JOptionPane.showMessageDialog(this, "����ʧ��");
						return;
				}
				
			}else if(result==JFileChooser.CANCEL_OPTION){
			  JOptionPane.showMessageDialog(this, "û��ѡ���ļ�");
			return;
		}	else {
						JOptionPane.showMessageDialog(this, "����ʧ���������Ա��ϵ");
			return;

		}
			
			
			
		
	
	}	
	
}			
		
   		
	
}	

	

