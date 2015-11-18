package qlcl.search.haui.obj;

import java.io.Serializable;
import java.util.List;

public class Obj_LichThi implements Serializable{
	
	String time;
	
	
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	List<LichThi> list_LichThi;
	Student student;
	public List<LichThi> getList_LichThi() {
		return list_LichThi;
	}
	public void setList_LichThi(List<LichThi> list_LichThi) {
		this.list_LichThi = list_LichThi;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
