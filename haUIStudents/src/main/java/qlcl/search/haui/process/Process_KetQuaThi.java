package qlcl.search.haui.process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.KetQuaThi;
import qlcl.search.haui.obj.MonHoc;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Student;


public class Process_KetQuaThi {

	List<MonHoc> listKetQuaThi;
	Student student;
	String maSV;
	Obj_KetQuaThi obj_ketQuaThi;
	String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Process_KetQuaThi(String maSV) {
		super();
		this.maSV = maSV;
	}

	public void load_KetQuaThi() {

		student = new Student();
		listKetQuaThi = new ArrayList<MonHoc>();
		obj_ketQuaThi = new Obj_KetQuaThi();
		
		Process_KetQuaThi_NonRate Pro_KQT=new Process_KetQuaThi_NonRate(maSV);
		if(Pro_KQT.getKetQuaThi().size()>0){
			List<KetQuaThi> listKQT=Pro_KQT.getKetQuaThi();
			
			String b = null;
			Document doc;
			try {
				doc = Jsoup
						.connect(
								"http://qlcl.edu.vn/viewstudent/ket-qua-thi.htm?code="
										+ maSV)
						.userAgent(
								"Chrome/30.0.1599.101")
						.get();
				Elements element = doc.select("tbody");
				Elements Estrong = element.get(0).select("strong");
				Elements Etr = element.get(1).select("tr");
				student.setName(Estrong.get(0).text());
				student.setMaSv(Estrong.get(1).text());
				student.setLop(Estrong.get(2).text());

				//System.out.println(student);
				for (int i = 0; i < Etr.size(); i++) {
					MonHoc mh = new MonHoc();
					Elements Etd = Etr.get(i).select("td");
					mh.setStt(Etd.get(0).text());
					mh.setMaLopDoclap(Etd.get(1).text());
					mh.setTenMonHoc(Etd.get(2).text());
					Elements EaDanhSach = Etd.get(2).select("a");
					mh.setLinkTheoLop("http://qlcl.edu.vn/"
							+ EaDanhSach.get(0).attr("href"));
					mh.setSoTinChi(Etd.get(3).text());
					mh.setDiemTBKT(Etd.get(4).text());
					mh.setDiemThiL1(listKQT.get(i).getDiemLan1());
					mh.setDiemThiL2(listKQT.get(i).getDiemLan2());
					mh.setDiemTBC(listKQT.get(i).getDiemTBC());
					mh.setDiemTinChi(listKQT.get(i).getDiemChu());
					Elements Ea = Etd.get(9).select("a");
					if (Ea.size() > 0) {
						mh.setLinkYKien("http://qlcl.edu.vn/"
								+ Ea.get(0).attr("href"));
					} else {
						mh.setLinkYKien("");
					}
					mh.setHocKi(Etd.get(10).text());
					mh.setGhiChu(Etd.get(11).text());
					listKetQuaThi.add(mh);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			obj_ketQuaThi.setStudent(student);
			obj_ketQuaThi.setList_KetQuaThi(listKetQuaThi);
			time=thoiGian(System.currentTimeMillis());
			obj_ketQuaThi.setTime(time);
		}else{
			listKetQuaThi=new ArrayList<MonHoc>();
			student=new Student();
			time="";
			obj_ketQuaThi.setStudent(student);
			obj_ketQuaThi.setList_KetQuaThi(listKetQuaThi);
			obj_ketQuaThi.setTime(time);
		}
		

	}

	public List<MonHoc> getListKetQuaThi() {
		return listKetQuaThi;
	}

	public Student getStudent() {
		return student;
	}

	public Obj_KetQuaThi getObj_ketQuaThi() {
		return obj_ketQuaThi;
	}
	
	String thoiGian(long time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm  - dd/MM/yyyy");
		Date date = new Date(time);
		return format.format(date);
	}


}
