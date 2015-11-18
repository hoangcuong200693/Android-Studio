package qlcl.search.haui.obj;

import java.io.Serializable;

public class Student implements Serializable {
	
	String name;
	String maSv;
	String lop;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaSv() {
		return maSv;
	}
	public void setMaSv(String maSv) {
		this.maSv = maSv;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", maSv=" + maSv + ", lop=" + lop
				+ "]";
	}
	

}
