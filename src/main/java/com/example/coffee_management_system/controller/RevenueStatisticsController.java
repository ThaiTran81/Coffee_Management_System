package com.example.coffee_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class RevenueStatisticsController implements Initializable {

    @FXML
    private BarChart<String, Number> bcUsage;

    @FXML
    private JFXButton btnStatistic;

    @FXML
    private DatePicker dpEndTine;

    @FXML
    private DatePicker dpStartTime;

    @FXML
    private LineChart<String, Number> lcProfit;

    @FXML
    private PieChart pcUsage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final NumberAxis xaxis = new NumberAxis(2008,2018,1);
        final NumberAxis yaxis = new NumberAxis(10,80,5);

        //Defining Label for Axis
        xaxis.setLabel("Year");
        yaxis.setLabel("Price");

        //Creating the instance of linechart with the specified axis
        LineChart linechart = new LineChart(xaxis,yaxis);

        //creating the series
        XYChart.Series series = new XYChart.Series();

        //setting name and the date to the series
        series.setName("Stock Analysis");
        series.getData().add(new XYChart.Data(2009,25));
        series.getData().add(new XYChart.Data(2010,15));
        series.getData().add(new XYChart.Data(2011,68));
        series.getData().add(new XYChart.Data(2012,60));
        series.getData().add(new XYChart.Data(2013,35));
        series.getData().add(new XYChart.Data(2014,55));
        series.getData().add(new XYChart.Data(2015,45));
        series.getData().add(new XYChart.Data(2016,67));
        series.getData().add(new XYChart.Data(2017,78));

        //adding series to the linechart
        lcProfit.getData().add(series);

    }
}
