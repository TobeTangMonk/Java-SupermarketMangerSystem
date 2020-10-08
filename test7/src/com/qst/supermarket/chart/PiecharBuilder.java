/**
 * 
 */
package com.qst.supermarket.chart;

import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.qst.supermarket.service.PieData;

/**
 * @author 12345678
 *
 */
public class PiecharBuilder {
	private List<PieData> pieDataList;
	private JPanel panel;
   
	public PiecharBuilder(List<PieData> pieDataList) {
	
		pieDataList = this.pieDataList;
	}


	public JPanel getPieChartPanel() {
		
//		用于处理数据的类PieDataset
		PieDataset dataset=getPieDataset();
		
		JFreeChart chart=ChartFactory.createPieChart3D("员工统计图", dataset, true, true, false);
		
//		将创建的图标生成JPanel
		panel=new ChartPanel(chart, true);
		return panel;
	}


	private PieDataset getPieDataset(){
		// TODO Auto-generated method stub
	//	PieDataset 
		DefaultPieDataset dataset=new  DefaultPieDataset(); 
		
		if (pieDataList!=null && pieDataList.size()>=0) {
			
			for (PieData pieData : pieDataList) {
				dataset.setValue(pieData.getUsername(), pieData.getTotal());
			}
			
		}
		
		return dataset;
	}

}
