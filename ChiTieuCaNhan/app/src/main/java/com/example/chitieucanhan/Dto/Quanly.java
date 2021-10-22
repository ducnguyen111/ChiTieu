package com.example.chitieucanhan.Dto;

public class Quanly {
    private String khoanthukhoanchi;
    private String phanloai;

    public Quanly(String khoanthukhoanchi, String phanloai) {
        this.khoanthukhoanchi = khoanthukhoanchi;
        this.phanloai = phanloai;
    }

    public String getKhoanthukhoanchi() {
        return khoanthukhoanchi;
    }

    public void setKhoanthukhoanchi(String khoanthukhoanchi) {
        this.khoanthukhoanchi = khoanthukhoanchi;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }

    public Quanly() {
    }
}
