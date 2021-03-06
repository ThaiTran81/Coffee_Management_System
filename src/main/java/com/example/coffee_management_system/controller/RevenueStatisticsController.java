package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.BillDAO;
import com.example.coffee_management_system.DTO.ItemUsage;
import com.example.coffee_management_system.DTO.Profit;
import com.example.coffee_management_system.ultil.Toast;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class RevenueStatisticsController implements Initializable {

    @FXML
    private JFXButton btnStatistic;

    @FXML
    private DatePicker dpEndTine;

    @FXML
    private DatePicker dpStartTime;

    @FXML
    private VBox profitLayout;

    @FXML
    private VBox usageLayout;

    @FXML
    private ScrollPane scroll;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dpStartTime.setValue(LocalDate.now().minusDays(7));
        dpEndTine.setValue(LocalDate.now());
        btnStatistic.fire();


    }


    @FXML
    void onStatisticButtonClick(ActionEvent event) {
        LocalDate startTime = dpStartTime.getValue();
        LocalDate endTime = dpEndTine.getValue();
        String msg = "";

        if (startTime == null || endTime==null){
            // notify
            msg = "Vui lòng nhập thời gian";
        }
        else if(endTime.isBefore(startTime)){
            //notify
           msg = "Ngày kết thúc không được thấp hơn ngày bắt đầu";
        }
        else if( Period.between(startTime, endTime).getDays() > 31){
            //notify
            msg = "Hệ thống chỉ hỗ trợ hiển thị tối đa 31 ngày";
        }

        if (!msg.isBlank()){
            Toast.showToast(Toast.TOAST_WARN,btnStatistic,msg);
            return;
        }

        showStatistic(startTime, endTime);
    }

    void showStatistic(LocalDate start, LocalDate end){
        profitLayout.getChildren().clear();
        usageLayout.getChildren().clear();
        try {
            List<ItemUsage> lst_usage = BillDAO.getItemUsageByRangeDate(start, end.plusDays(1));
            List<Profit> lst_profit = BillDAO.getProfitByRangeDate(start, end.plusDays(1));

            LinkedHashMap<LocalDate, Double> map_data = new LinkedHashMap<>();

            for (Profit p: lst_profit){
                if(!map_data.containsKey(p.getCreate_time().toLocalDateTime().toLocalDate())){
                    map_data.put(p.getCreate_time().toLocalDateTime().toLocalDate(), p.getTotal());
                }
                else map_data.put(p.getCreate_time().toLocalDateTime().toLocalDate(), map_data.get(p.getCreate_time().toLocalDateTime().toLocalDate())+p.getTotal());
            }

//            for (Map.Entry<LocalDate, Double> entry : map_data.entrySet()) {
//                System.out.println(entry.getKey() + ":" + entry.getValue());
//            }

            //Defining X axis
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Thời gian");

//Defining y axis
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Số tiền thu được (VNĐ) ");

            BarChart<String, Number> linechart = new BarChart<>(xAxis, yAxis);

            XYChart.Series series = new XYChart.Series();
//            series.setName("No of schools in an year");

            xAxis.setTickLabelRotation(30);

            for (Map.Entry<LocalDate, Double> entry : map_data.entrySet()) {
                series.getData().add(new XYChart.Data(localdate2String(entry.getKey()), entry.getValue()));
            }

            //Setting the data to Line chart
            linechart.getData().add(series);

            profitLayout.getChildren().add(linechart);

            int max = 1;
            if(lst_usage.size()!=0){
                max = lst_usage.get(0).getUsage()+1;
            }

            NumberAxis uxAxis = new NumberAxis(0, max,1);
            uxAxis.setLabel("Lượng mua");

//Defining y axis
            CategoryAxis uyAxis = new CategoryAxis();
            uyAxis.setLabel("Món");
            uyAxis.tickLabelFontProperty().set(Font.font(15));

            BarChart<Number, String> bc_usage = new BarChart<Number, String>(uxAxis, uyAxis);

            XYChart.Series<Number, String> uSeries;
//            int limit = 10;

            for(ItemUsage item: lst_usage){
                uSeries = new XYChart.Series();
                uSeries.setName(item.getName());
                uSeries.getData().add(new XYChart.Data(item.getUsage(), item.getName()));
                bc_usage.getData().add(uSeries);
//                if(--limit==0) break;
            }

            usageLayout.setPrefWidth(scroll.getWidth());
            usageLayout.getChildren().add(bc_usage);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    String localdate2String(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dateString = localDate.format(formatter);

        return dateString;
    }
}
