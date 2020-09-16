package org.mairie.comores.web;

import java.awt.Font;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.mairie.comores.metier.IEmployeMetier;
import org.mairie.comores.metier.UserMetierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MakeLineAndShapeChartController {
	
	
	@Autowired
	private UserMetierImpl userMetierImpl;

	@Autowired
	private IEmployeMetier employeMetierImpl;

	
	@RequestMapping("/makeLineAndShapeChart")
	public String makeLineAndShapeChart(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		
		try {
			// Recuperer l'utilisateur connecté
			EmployeController.ChargerUserConnection(model, userMetierImpl, employeMetierImpl);
			EmployeController.dateDujours(model);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		// Define chart object data, data
				DefaultCategoryDataset  dataset  =createDataset();
				JFreeChart  chart = ChartFactory.createLineChart(
					"Broken line diagram", // chart title
		             "time", // domain axis label
		             "Sales volume(Million)", // range axis label
		             dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips
					false // urls
						);
				
				CategoryPlot plot = chart.getCategoryPlot();
				// Set icon font 
				chart.getTitle().setFont(new Font("Song style", Font.BOLD, 22)); 
				//Set font for horizontal axis
				 CategoryAxis categoryAxis = plot.getDomainAxis();
				 categoryAxis.setLabelFont(new Font("Song style", Font.BOLD, 22));//x-axis title font 
				 categoryAxis.setTickLabelFont(new Font("Song style", Font.BOLD, 18));//x-axis scale font

				 //The following two lines set the font for the legend
				LegendTitle legend = chart.getLegend(0);
				 legend.setItemFont(new Font("Song style", Font.BOLD, 14));
				 //Set font for vertical axis
					NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
				 rangeAxis.setLabelFont(new Font("Song style" , Font.BOLD , 22)); //Set font for axis
				 rangeAxis.setTickLabelFont(new Font("Song style" , Font.BOLD , 22));
				
				 rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Remove the vertical axis font display incomplete
				 rangeAxis.setAutoRangeIncludesZero(true);
				 rangeAxis.setUpperMargin(0.20);
				 rangeAxis.setLabelAngle(Math.PI / 2.0);
				 
				// 6. Convert graphics to pictures and send them to the front desk
					String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
					String chartURL = request.getContextPath() + "/chart?filename=" + fileName;
					model.addAttribute("makeLineAndShapeChart", chartURL);
					
				 return "makeLineAndShapeChart";
				}
	
	// Generate data
			public static DefaultCategoryDataset createDataset() {
			  DefaultCategoryDataset linedataset = new DefaultCategoryDataset();

			 // Name of each curve
			  String [] series= {"Refrigerator","Color TV","Washing machine"};
			 // Horizontal axis name (column name)
			  String [] month = {"1 month","2 month","3 month","4 month"};
			 //Specific data
			 double [] num = {4,5,6,10,10,15,20,16,10,18,25,19};
			 
			 int l1 = num.length/series.length; //4
			 int l2 = month.length;
			 int j=0;
			 for(int i=0;i<num.length;i++) {
				 linedataset.addValue(num[i], series[i/l1], month[j]);	
				 j++;
				 if(j==month.length)
					 j=0;
			 }			 
			 return linedataset;
			}

}
