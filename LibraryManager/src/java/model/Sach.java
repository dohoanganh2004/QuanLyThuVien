/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */

public class Sach {
    private String masach;
    private String nxb;
    private String tensach;
    private String mota;
    private String tacgia;
    private TheLoai theloai;
    private String namxb,ngonngu,anhbia,trangthai;
    private int gia;
    private int soluong;

    public Sach() {
    }

    public Sach(String masach, String nxb, String tensach, String mota, String tacgia, TheLoai theloai, String namxb, String ngonngu, String anhbia, String trangthai, int gia, int soluong) {
        this.masach = masach;
        this.nxb = nxb;
        this.tensach = tensach;
        this.mota = mota;
        this.tacgia = tacgia;
        this.theloai = theloai;
        this.namxb = namxb;
        this.ngonngu = ngonngu;
        this.anhbia = anhbia;
        this.trangthai = trangthai;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public TheLoai getTheloai() {
        return theloai;
    }

    public void setTheloai(TheLoai theloai) {
        this.theloai = theloai;
    }

    public String getNamxb() {
        return namxb;
    }

    public void setNamxb(String namxb) {
        this.namxb = namxb;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public String getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(String anhbia) {
        this.anhbia = anhbia;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
    
    
    
}
