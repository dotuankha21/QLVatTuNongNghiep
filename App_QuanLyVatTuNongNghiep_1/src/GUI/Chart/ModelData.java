/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Chart;

/**
 *
 * @author tuank
 */
public class ModelData {
    private String month;
    private double soluong;

    public ModelData() {
    }

    public ModelData(String month, double soluong) {
        this.month = month;
        this.soluong = soluong;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getSoluong() {
        return soluong;
    }

    public void setSoluong(double soluong) {
        this.soluong = soluong;
    }
    
}
