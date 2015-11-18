package qlcl.search.haui.obj;

import java.io.Serializable;

public class diemthi_theolop implements Serializable{

	String ten;
	String diem1;
	String diem2;
	String ghiChu;

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiem1() {
		return diem1;
	}

	public void setDiem1(String diem1) {
		this.diem1 = diem1;
	}

	public String getDiem2() {
		return diem2;
	}

	public void setDiem2(String diem2) {
		this.diem2 = diem2;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "diemthi_theolop [ten=" + ten + ", diem1=" + diem1 + ", diem2="
				+ diem2 + ", ghiChu=" + ghiChu + "]";
	}

}
