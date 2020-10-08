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

	// 声明标签 用户名： 密码： 账户类型：
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAccountTye;          

	// 声明文本框 就是要输入信息用的
	private JTextField tfUsername;
	private JPasswordField tfPassword;

	// 下拉框
	private JComboBox abAccountType;

	
     JButton LoginButton,CancelButton;
     
     
//     service
     UserService userService=new UserService();
     
     
     
     
     
	public LoginFrame() {
		// TODO Auto-generated constructor stub

		// 创建窗体主键放到方法里面
		Init();

		this.setSize(448, 366);
   this.setBackground(new Color(255,255,255));
		// 设置窗体居中
		this.setLocationRelativeTo(null);
		

		// 使最大化失效this.setResizable(false)
		// this.setResizable(false);
		this.setDefaultCloseOperation(LoginFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * 
	 */
	private void Init() {
		// TODO Auto-generated method stub
		// 容器，依赖JFrame 相当于JFrame是桌子 JPanel是桌子上的布
		

		// 设置不使用布局管理
		mainJPanel.setLayout(null);

		// 白色new Color(255,255,255) 黑色new Color(0,0,0)
		mainJPanel.setBackground(new Color(255, 255, 255));

		// 标签
		lblUsername = new JLabel();
		lblPassword = new JLabel();
		lblAccountTye = new JLabel();

		// 存放文本框
		tfUsername = new JTextField("请输入账号：");
		
		//		加上按键 登入 取消
		
		LoginButton=new JButton("登入");
		CancelButton=new JButton("取消");


			
		
		

		// 密码用JPasswordFiled();它们是不一样的
		tfPassword = new JPasswordField();
		// tfAccountTye = new JTextField();

		// 下拉框 用于选择 收银员 系统管理员

		abAccountType = new JComboBox(new String[] { "收银员", "管理员" });
		
		

		// 可以设置成图标
		lblUsername
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/account.png")));
		lblPassword
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/password.png")));
		lblAccountTye
				.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("img/选择身份.png")));

		// 设置标签大小
		lblUsername.setBounds(100, 50, 100, 50);
		// 设置文本框大小
		tfUsername.setBounds(160, 50, 200, 50);

		lblPassword.setBounds(100, 100, 100, 50);
		lblPassword.requestFocus();
		tfPassword.setBounds(160, 100, 200, 50);

		lblAccountTye.setBounds(100, 150, 100, 50);
		abAccountType.setBounds(160, 150, 200, 50);
		
		
		// 按钮
		LoginButton = new JButton("登录");
		CancelButton = new JButton("取消");

		
		
		LoginButton.setBounds(170, 200,60, 30);
		CancelButton.setBounds(249, 200, 60, 30);

	
		// 向mianJpanel中添加组件
		mainJPanel.add(lblUsername);
		mainJPanel.add(lblPassword);
		mainJPanel.add(lblAccountTye);
		mainJPanel.add(tfUsername);
		mainJPanel.add(tfPassword);
		mainJPanel.add(abAccountType);
        mainJPanel.add(LoginButton);
        mainJPanel.add(CancelButton);
        
        
        
        
        
		
//		文本框监听事件  
//		使用MouseAListener要实现所有鼠标事件的方法不方便
		
//		用MouseAdaptet方法很方便只需使用一个就好 
		tfUsername.addMouseListener(new MouseAdapter() {
//			鼠标释
			public void mouseReleased(MouseEvent e) {
				tfUsername.setText(" ");//鼠标释放时文本框内容为空
 			}
			
		});
		
		
//	登录按钮加监听 即点击登录按钮执行这个操作	
		
		LoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				获取页面值
			String username=tfUsername.getText();
			
			username.trim();
			
//			getPassword(); 是字符数组char[]  
	 	    String password=new String(tfPassword.getPassword());
			String userType=(String)abAccountType.getSelectedItem();
			 
//			System.out.println(username);
//			System.out.println(password);
//			System.out.println(userType);
//				
			if (username==null|| username.length()==0||username.equals("请输入账号：")) {
				username.trim();

//				JOptionPane.showMessageDialog(parentComponent, message); parentComponent 父组件 message是提示消息
//				设置窗体居中 匿名内部类访问外部类需要 调用外部类名 LoginFrame.this
//				JOptionPane.showMessageDialog(null, "请输入用户名");
				JOptionPane.showMessageDialog( LoginFrame.this, "请输入用户名!");
				return;
			}
			

			
			if (password==null|| password.length()==0) {
			 

				JOptionPane.showMessageDialog( LoginFrame.this, "请输入密码！");
			}
			
			
			
			
			
			
		
			
			//			查询数据库：根据输入的用户名和密码进行比较   view service  dao--->table 
					   
			User user=userService.login(username, password, userType);
		
//			   System.out.println(user);
			if (user==null) {
				JOptionPane.showMessageDialog( LoginFrame.this, "用户名或者密码不正确");
                 return; 
			}else {
				if(userType.equals("收银员")){
					
					System.out.println("欢迎进入收银员界面");

					//					主窗体关闭但是程序仍然运行
					LoginFrame.this.dispose();
					
					
//					启动另一个窗体
					new CashierManagerFrame(user);
				}
				if(userType.equals("管理员")){
					
					System.out.println("欢迎进入管理员界面");
					
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
