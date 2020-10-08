/**
 * 
 */
package com.qst.supermarket.view;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.qst.supermarket.model.User;
import com.qst.supermarket.service.UserService;

/**
 * @author 12345678
 *
 */
public class LoginFrame extends JFrame{
	private JPanel mainJPanel;

	// ������ǩ �û����� ���룺 �˻����ͣ�
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAccountTye;          

	// �����ı��� ����Ҫ������Ϣ�õ�
	private JTextField tfUsername;
	private JPasswordField tfPassword;

	// ������
	private JComboBox abAccountType;

	
     JButton LoginButton,CancelButton;
     
     
//     service
     UserService userService=new UserService();
     
     
     
     
     
	public LoginFrame() {
		// TODO Auto-generated constructor stub

		// �������������ŵ���������
		Init();

		this.setSize(448, 366);
   this.setBackground(new Color(255,255,255));
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
	private void Init() {
		// TODO Auto-generated method stub
		// ����������JFrame �൱��JFrame������ JPanel�������ϵĲ�
		

		// ���ò�ʹ�ò��ֹ���
		mainJPanel.setLayout(null);

		// ��ɫnew Color(255,255,255) ��ɫnew Color(0,0,0)
		mainJPanel.setBackground(new Color(255, 255, 255));

		// ��ǩ
		lblUsername = new JLabel();
		lblPassword = new JLabel();
		lblAccountTye = new JLabel();

		// ����ı���
		tfUsername = new JTextField("�������˺ţ�");
		
		//		���ϰ��� ���� ȡ��
		
		LoginButton=new JButton("����");
		CancelButton=new JButton("ȡ��");


			
		
		

		// ������JPasswordFiled();�����ǲ�һ����
		tfPassword = new JPasswordField();
		// tfAccountTye = new JTextField();

		// ������ ����ѡ�� ����Ա ϵͳ����Ա

		abAccountType = new JComboBox(new String[] { "����Ա", "����Ա" });
		
		

		// �������ó�ͼ��
		lblUsername
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/account.png")));
		lblPassword
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/password.png")));
		lblAccountTye
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/ѡ�����.png")));

		// ���ñ�ǩ��С
		lblUsername.setBounds(100, 50, 100, 50);
		// �����ı����С
		tfUsername.setBounds(160, 50, 200, 50);

		lblPassword.setBounds(100, 100, 100, 50);
		lblPassword.requestFocus();
		tfPassword.setBounds(160, 100, 200, 50);

		lblAccountTye.setBounds(100, 150, 100, 50);
		abAccountType.setBounds(160, 150, 200, 50);
		
		
		// ��ť
		LoginButton = new JButton("��¼");
		CancelButton = new JButton("ȡ��");

		
		
		LoginButton.setBounds(170, 200,60, 30);
		CancelButton.setBounds(249, 200, 60, 30);

	
		// ��mianJpanel��������
		mainJPanel.add(lblUsername);
		mainJPanel.add(lblPassword);
		mainJPanel.add(lblAccountTye);
		mainJPanel.add(tfUsername);
		mainJPanel.add(tfPassword);
		mainJPanel.add(abAccountType);
        mainJPanel.add(LoginButton);
        mainJPanel.add(CancelButton);
        
        
        
        
        
		
//		�ı�������¼�  
//		ʹ��MouseAListenerҪʵ����������¼��ķ���������
		
//		��MouseAdaptet�����ܷ���ֻ��ʹ��һ���ͺ� 
		tfUsername.addMouseListener(new MouseAdapter() {
//			�����
			public void mouseReleased(MouseEvent e) {
				tfUsername.setText(" ");//����ͷ�ʱ�ı�������Ϊ��
 			}
			
		});
		
		
//	��¼��ť�Ӽ��� �������¼��ťִ���������	
		
		LoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				��ȡҳ��ֵ
			String username=tfUsername.getText();
			
			username.trim();
			
//			getPassword(); ���ַ�����char[]  
	 	    String password=new String(tfPassword.getPassword());
			String userType=(String)abAccountType.getSelectedItem();
			 
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(userType);
//				
			if (username==null|| username.length()==0||username.equals("�������˺ţ�")) {
				username.trim();

//				JOptionPane.showMessageDialog(parentComponent, message); parentComponent ����� message����ʾ��Ϣ
//				���ô������ �����ڲ�������ⲿ����Ҫ �����ⲿ���� LoginFrame.this
//				JOptionPane.showMessageDialog(null, "�������û���");
				JOptionPane.showMessageDialog( LoginFrame.this, "�������û���!");
				return;
			}
			

			
			if (password==null|| password.length()==0) {
			 

				JOptionPane.showMessageDialog( LoginFrame.this, "���������룡");
			}
			
			
			
			
			
			
		
			
			//			��ѯ���ݿ⣺����������û�����������бȽ�   view service  dao--->table 
					   
			User user=userService.login(username, password, userType);
		
//			   System.out.println(user);
			if (user==null) {
				JOptionPane.showMessageDialog( LoginFrame.this, "�û����������벻��ȷ");
                 return; 
			}else {
				if(userType.equals("����Ա")){
					
					System.out.println("��ӭ��������Ա����");

					//					������رյ��ǳ�����Ȼ����
					LoginFrame.this.dispose();
					
					
//					������һ������
					new CashierManagerFrame(user);
				}
				if(userType.equals("����Ա")){
					
					System.out.println("��ӭ�������Ա����");
					
					LoginFrame.this.dispose();
					new AdminManagerFrame(user);
				}
				
			}
          				
			}
		});
		
				
			
			
        
	}

	/**
	 * @param args
	 */

    public static void main(String[] args) throws Exception {

		new LoginFrame();

	}

}
