/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class TaiKhoan {

    private int mataikhoan;
    private String username, matkhau, avartar;
    private QuyenHan quenhan;
    private TheThuVien the;
    private NhanVien nhanvien;

    public TaiKhoan() {
    }

    public TaiKhoan(int mataikhoan, String username, String matkhau, String avartar, QuyenHan quenhan, TheThuVien the, NhanVien nhanvien) {
        this.mataikhoan = mataikhoan;
        this.username = username;
        this.matkhau = matkhau;
        this.avartar = avartar;
        this.quenhan = quenhan;
        this.the = the;
        this.nhanvien = nhanvien;
    }

    public TaiKhoan(String username, String matkhau, String avartar, QuyenHan quenhan, NhanVien nhanvien) {

        this.username = username;
        this.matkhau = matkhau;
        this.avartar = avartar;
        this.quenhan = quenhan;

        this.nhanvien = nhanvien;
    }

    public TaiKhoan(String username, String matkhau, String avartar, QuyenHan quenhan, TheThuVien the) {

        this.username = username;
        this.matkhau = matkhau;
        this.avartar = avartar;
        this.quenhan = quenhan;
        this.the = the;

    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public QuyenHan getQuenhan() {
        return quenhan;
    }

    public void setQuenhan(QuyenHan quenhan) {
        this.quenhan = quenhan;
    }

    public TheThuVien getThe() {
        return the;
    }

    public void setThe(TheThuVien the) {
        this.the = the;
    }

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVien nhanvien) {
        this.nhanvien = nhanvien;
    }

}
