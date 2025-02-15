/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class TheThuVien {

    private String sothe;
    private DocGia docgia;
    private String ngaycap, ngayhethan;
    private String trangthai;
    private int sosachduocmuon, sosachdangmuon;

    public TheThuVien() {
    }

    public TheThuVien(String sothe, DocGia docgia, String ngaycap, String ngayhethan, String trangthai, int sosachduocmuon, int sosachdangmuon) {
        this.sothe = sothe;
        this.docgia = docgia;
        this.ngaycap = ngaycap;
        this.ngayhethan = ngayhethan;
        this.trangthai = trangthai;
        this.sosachduocmuon = sosachduocmuon;
        this.sosachdangmuon = sosachdangmuon;
    }
       public TheThuVien(String sothe, DocGia docgia, String ngaycap, String ngayhethan, String trangthai) {
        this.sothe = sothe;
        this.docgia = docgia;
        this.ngaycap = ngaycap;
        this.ngayhethan = ngayhethan;
        this.trangthai = trangthai;
      
    }
   
    
    public String getSothe() {
        return sothe;
    }

    public void setSothe(String sothe) {
        this.sothe = sothe;
    }

    public DocGia getDocgia() {
        return docgia;
    }

    public void setDocgia(DocGia docgia) {
        this.docgia = docgia;
    }

    public String getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(String ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getSosachduocmuon() {
        return sosachduocmuon;
    }

    public void setSosachduocmuon(int sosachduocmuon) {
        this.sosachduocmuon = sosachduocmuon;
    }

    public int getSosachdangmuon() {
        return sosachdangmuon;
    }

    public void setSosachdangmuon(int sosachdangmuon) {
        this.sosachdangmuon = sosachdangmuon;
    }

    
   
}
