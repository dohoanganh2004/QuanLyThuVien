/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
/*
MaMuonSach INT NOT NULL,
    MaSach INT NOT NULL,
    NgayMuon DATE NULL,
    NgayHenTra DATE NULL,
    NgayTra DATE NULL,
    TrangThai NVARCHAR(100) NULL,
    SoLuong INT NULL,
    -- Khoá chính kép
    PRIMARY KEY (MaMuonSach, MaSach),
    -- Ràng buộc khoá ngoại liên kết với MuonTraSach
    FOREIGN KEY (MaMuonSach) REFERENCES MuonTraSach(MaMuonSach),
    
*/
public class MuonTraSachChiTiet {
    private MuonTraSach muontra;
    private Sach sach;
    private String ngaymuon,ngayhentra, ngaytra,trangthai;
    private int soluong;

    public MuonTraSachChiTiet() {
    }

    public MuonTraSachChiTiet(MuonTraSach muontra, Sach sach, String ngaymuon, String ngayhentra, String ngaytra, String trangthai, int soluong) {
        this.muontra = muontra;
        this.sach = sach;
        this.ngaymuon = ngaymuon;
        this.ngayhentra = ngayhentra;
        this.ngaytra = ngaytra;
        this.trangthai = trangthai;
        this.soluong = soluong;
    }
public MuonTraSachChiTiet( Sach sach, String ngaymuon, String ngayhentra, String ngaytra, String trangthai, int soluong) {
        this.sach = sach;
        this.ngaymuon = ngaymuon;
        this.ngayhentra = ngayhentra;
        this.ngaytra = ngaytra;
        this.trangthai = trangthai;
        this.soluong = soluong;
    }
    public MuonTraSach getMuontra() {
        return muontra;
    }

    public void setMuontra(MuonTraSach muontra) {
        this.muontra = muontra;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgayhentra() {
        return ngayhentra;
    }

    public void setNgayhentra(String ngayhentra) {
        this.ngayhentra = ngayhentra;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
    
}
