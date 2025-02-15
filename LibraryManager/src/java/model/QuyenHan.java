/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class QuyenHan {
    private int id;
    private String tenquyen;

    public QuyenHan() {
    }

    public QuyenHan(int id, String tenquyen) {
        this.id = id;
        this.tenquyen = tenquyen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenquyen(String tenquyen) {
        this.tenquyen = tenquyen;
    }

    public int getId() {
        return id;
    }

    public String getTenquyen() {
        return tenquyen;
    }
    
    
    
}
