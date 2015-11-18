package qlcl.search.haui.obj;

import java.io.Serializable;
import java.util.List;

public class Obj_KetQuaThi implements Serializable{
	
	List<MonHoc> list_KetQuaThi;
	Student student;
	String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<MonHoc> getList_KetQuaThi() {
		return list_KetQuaThi;
	}
	public void setList_KetQuaThi(List<MonHoc> list_KetQuaThi) {
		this.list_KetQuaThi = list_KetQuaThi;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

}
