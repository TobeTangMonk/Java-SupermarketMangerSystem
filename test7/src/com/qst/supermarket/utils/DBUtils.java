package com.qst.supermarket.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtils {

	public static Connection getConnection() throws Exception {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");

		Properties properties = new Properties();

		properties.load(is);

		DataSource ds = DruidDataSourceFactory.createDataSource(properties);

		/*
		 * DruidDataSource druidDataSource = new DruidDataSource();
		 * 
		 * // 20个 -18 = 2（空闲）+ 3 = 5（最小空闲）=23 30
		 * 
		 * // 连接池初始化连接个数 druidDataSource.setInitialSize(20); //最小空闲连接数
		 * druidDataSource.setMinIdle(5); // 最大活动连接 druidDataSource.setMaxActive(30);
		 */

		// 连接池的一些常用配置
		// initialSize

		Connection conn = ds.getConnection();

		return conn;
	}

	public static void close(ResultSet rs, Statement stmt, Connection conn) {

		// 关闭时候是和打开顺序相反

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 关闭语句对象
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 关闭连接
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static <T> List<T> query(Class<T> clazz, String sql, Object... args) {// Integer Date String

		// 通过jdbc向数据库中插入一条记录
		// 1、把mysql数据库驱动程序jar包加载到项目classpath中

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;

		List<T> resultList = new ArrayList<>();

		try {

			conn = DBUtils.getConnection();

			// 执行sql
			// String sql = "select id,user_name username,birthday,resume,img from student
			// where id in (?,?,?)";

			// 4、获取Statement对象
			pre = conn.prepareStatement(sql);

			// 给占位符赋值
			if (args != null && args.length > 0) {

				for (int i = 0; i < args.length; i++) {

					// 参数占位符是从1
					pre.setObject(i + 1, args[i]);
				}
			}

			/*
			 * 思路：把每一条记录封装成一个student对象，就是把每一列的值，赋值给列对应的student对象对应的属性
			 * 
			 * Student属性 id username birthday resume img 数据列名 id user_name birthday resume
			 * img 数据记录 5 李四 2001-01-05 adfasdf  010101
			 * 
			 * Student stu = new Student(); stu.setId(5); stu.setUsername("李四");
			 * stu.setBirthday(2001-01-05); stu.setResume(adfasdf); stu.setImg(010101);
			 * 
			 * 
			 * Class clazz = Student.class; Student stu = (Student)clazz.newInstance();
			 * String methodName = "set"+Id; Method setIdMethod =
			 * clazz.getDecleardMethod(methodName); setIdMethod.invoke(stu,5);
			 * 
			 * 用一个map存储当前记录 key=列名（属性名） value=列对应的值(属性值) 结果集元数据 ResultSetMetadata
			 */

			// 执行查询
			rs = pre.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			// 获取记录列的数量
			int colNum = rsmd.getColumnCount();

			while (rs.next()) {

				// 用户存储一条记录，row = [{key1=列名 value1=列值},{key2=列名 value2=列值}]
				Map<String, Object> rowMap = new HashMap<>();

				// 每次循环处理一列
				for (int i = 0; i < colNum; i++) {

					// 获取列名 --- Student对象的属性名
					String columnName = rsmd.getColumnLabel(i + 1);
					// 获取列值----Student对象的属性值
					Object columnValue = rs.getObject(columnName);

					// 转换java.sql。Date 为 java.util.Date
					if (columnValue instanceof java.sql.Date) {

						java.sql.Date date = (java.sql.Date) columnValue;
						java.util.Date birthday = new java.util.Date(date.getTime());
						columnValue = birthday;
					}

					rowMap.put(columnName, columnValue);
				}

				// // { entry1={birthday=null},
				// entry2={resume=null},
				// entry3={img=null},
				// entry4={id=7},
				// entry5={username=king2}
				// }
				// 遍历rowMap 给Student对象赋值

				// 创建一个Bean对象 调用默认无参的构造函数
				T bean = clazz.newInstance();

				for (Map.Entry<String, Object> entry : rowMap.entrySet()) {

					String propertyName = entry.getKey();
					Object propertyValue = entry.getValue();

					// stu.setxxx(xxxx)
					if (propertyValue != null) {
						BeanUtils.setProperty(bean, propertyName, propertyValue);
					}

					// 每次循环处理一个属性，也就是给的对象的一个属性赋值
					// setId 方法名
					/*
					 * String methodName =
					 * "set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
					 * if(propertyValue!=null) {
					 * 
					 * Method method = clazz.getDeclaredMethod(methodName,
					 * propertyValue.getClass());
					 * 
					 * // 调用setXXx方法，给对象赋值 stu.setId(10); method.invoke(bean, propertyValue); }
					 */

				}
				resultList.add(bean);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// Statement stment = pre;
			DBUtils.close(rs, pre, conn);

		}

		return resultList;

	}

	public static int update(String sql, Object... args) {

		Connection conn = null;
		PreparedStatement pre = null;
		int count = -1;

		try {

			conn = DBUtils.getConnection();

			pre = conn.prepareStatement(sql);

			// 给占位符赋值
			if (args != null && args.length > 0) {

				for (int i = 0; i < args.length; i++) {

					// 参数占位符是从1
					pre.setObject(i + 1, args[i]);
				}
			}

			count = pre.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// Statement stment = pre;
			DBUtils.close(null, pre, conn);

		}

		return count;
	
	}
	
	
	public static <T> T selectSingleObject(Class<T> clazz, String sql, Object... args) {// Integer Date String

		// 通过jdbc向数据库中插入一条记录
		// 1、把mysql数据库驱动程序jar包加载到项目classpath中

		Connection conn = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		// 创建一个Bean对象 调用默认无参的构造函数
		T bean = null;

		
		try {

			conn = DBUtils.getConnection();

			// 执行sql
			// String sql = "select id,user_name username,birthday,resume,img from student
			// where id in (?,?,?)";

			// 4、获取Statement对象
			pre = conn.prepareStatement(sql);

			// 给占位符赋值
			if (args != null && args.length > 0) {

				for (int i = 0; i < args.length; i++) {

					// 参数占位符是从1
					pre.setObject(i + 1, args[i]);
				}
			}

			

			// 执行查询
			rs = pre.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			// 获取记录列的数量
			int colNum = rsmd.getColumnCount();

			while (rs.next()) {
				
				bean = clazz.newInstance();

				// 用户存储一条记录，row = [{key1=列名 value1=列值},{key2=列名 value2=列值}]
				Map<String, Object> rowMap = new HashMap<>();

				// 每次循环处理一列
				for (int i = 0; i < colNum; i++) {

					// 获取列名 --- Student对象的属性名
					String columnName = rsmd.getColumnLabel(i + 1);
					// 获取列值----Student对象的属性值
					Object columnValue = rs.getObject(columnName);

					// 转换java.sql。Date 为 java.util.Date
					if (columnValue instanceof java.sql.Date) {

						java.sql.Date date = (java.sql.Date) columnValue;
						java.util.Date birthday = new java.util.Date(date.getTime());
						columnValue = birthday;
					}

					rowMap.put(columnName, columnValue);
				}

				
				

				for (Map.Entry<String, Object> entry : rowMap.entrySet()) {

					String propertyName = entry.getKey();
					Object propertyValue = entry.getValue();

					// stu.setxxx(xxxx)
					if (propertyValue != null) {
						BeanUtils.setProperty(bean, propertyName, propertyValue);
					}

					// 每次循环处理一个属性，也就是给的对象的一个属性赋值
					// setId 方法名
					/*
					 * String methodName =
					 * "set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
					 * if(propertyValue!=null) {
					 * 
					 * Method method = clazz.getDeclaredMethod(methodName,
					 * propertyValue.getClass());
					 * 
					 * // 调用setXXx方法，给对象赋值 stu.setId(10); method.invoke(bean, propertyValue); }
					 */

				}
				
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// Statement stment = pre;
			DBUtils.close(rs, pre, conn);

		}

		return bean;

	}

}
