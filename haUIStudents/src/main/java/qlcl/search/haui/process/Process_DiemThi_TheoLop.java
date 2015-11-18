package qlcl.search.haui.process;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.Obj_DiemThi_TheoLop;
import qlcl.search.haui.obj.Obj_Student_KetQuaHocTap;
import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.obj.diemthi_theolop;
import qlcl.search.haui.obj.header_BangDanhSach;
/**
 * 
 * @author Eo Cuong
 *Class thực hiện việc lấy điểm thi theo lớp
 */

public class Process_DiemThi_TheoLop {

	String url;
	header_BangDanhSach header;
	List<diemthi_theolop> list;

	Obj_DiemThi_TheoLop obj = new Obj_DiemThi_TheoLop();

	/**
	 * Khỏi tạo
	 * tham số truyền vào là 1 link danh sách điểm theo lớp
	 * @param url
	 */
	public Process_DiemThi_TheoLop(String url) {
		super();
		this.url = url;
	}

	/**
	 * 
	 * @param truyền vào 1 String
	 * @return một tín chỉ đã đc chuẩn hóa
	 */
	String chuanHoaTinChi(String st) {
		int i = st.indexOf("(");

		return st.substring(0, i);

	}

	public void load() {
		header = new header_BangDanhSach();
		 list = new ArrayList<diemthi_theolop>();

		Document doc;
		// http://qlcl.edu.vn/student/viewexamresultclass/7579/nhap-mon-tin-hoc.htm

		try {

			doc = Jsoup
					.connect(url)
					.userAgent(
							"Chrome/30.0.1599.101")
					.get();

			Elements tbody = doc.select("tbody");
			Elements EStrong = tbody.select("strong");
			header.setTenMon(EStrong.get(0).text());
			header.setTinChi(chuanHoaTinChi(EStrong.get(3).text()));
			header.setLop(EStrong.get(5).text());

			Element EButton = tbody.get(1);
			Elements EtdButton = EButton.select("td");
			Element Etd_Link = EtdButton.get(4);
			Elements E_aLink = Etd_Link.select("a");
			if (E_aLink.size() > 0) {
				Element E_href = E_aLink.get(0);
				header.setLinkNext(E_aLink.get(0).attr("href"));
			} else {
				header.setLinkNext("");
			}

			//System.out.println(header.toString());

			Element EDiem = tbody.get(2);
			Elements Etr = EDiem.select("tr");

			for (int i = 0; i < Etr.size(); i++) {

				diemthi_theolop diemthi = new diemthi_theolop();
				Elements Etd = Etr.get(i).select("td");

				diemthi.setTen(Etd.get(2).text());
				diemthi.setDiem1(Etd.get(3).text());
				diemthi.setDiem2(Etd.get(4).text());
				diemthi.setGhiChu(Etd.get(5).text());

				list.add(diemthi);

			}

			obj.setHeader(header);
			obj.setListDanhSachDiemThi(list);

		} catch (Exception e) {

		}

	}

	public header_BangDanhSach getHeader() {
		return header;
	}

	public List<diemthi_theolop> getList() {
		return list;
	}

	public Obj_DiemThi_TheoLop getObj() {
		return obj;
	}

}
