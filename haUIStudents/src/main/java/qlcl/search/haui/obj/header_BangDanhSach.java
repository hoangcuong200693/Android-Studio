package qlcl.search.haui.obj;

import java.io.Serializable;

/**
 * 
 * @author Eo
 * 
 *
 */

public class header_BangDanhSach implements Serializable{
	
	String tenMon;
	String tinChi;
	String lop;
	String linkNext;
	
	
	public String getLinkNext() {
		return linkNext;
	}
	public void setLinkNext(String linkNext) {
		this.linkNext = linkNext;
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public String getTinChi() {
		return tinChi;
	}
	public void setTinChi(String tinChi) {
		this.tinChi = tinChi;
	}
	public String getLop() {
		return lop;
	}
	@Override
	public String toString() {
		return "header_BangDanhSach [tenMon=" + tenMon + ", tinChi=" + tinChi
				+ ", lop=" + lop + ", linkNext=" + linkNext + "]";
	}
	public void setLop(String lop) {
		this.lop = lop;
	}

	

}
