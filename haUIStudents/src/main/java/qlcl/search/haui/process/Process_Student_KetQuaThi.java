package qlcl.search.haui.process;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import qlcl.search.haui.obj.Obj_Student_KetQuaHocTap;
import qlcl.search.haui.obj.Student_KetQuaHocTap;
import qlcl.search.haui.obj.header_BangDanhSach;


public class Process_Student_KetQuaThi {

	String url;
	header_BangDanhSach header;
	List<Student_KetQuaHocTap> list;

	Obj_Student_KetQuaHocTap obj = new Obj_Student_KetQuaHocTap();

	public Process_Student_KetQuaThi(String url) {
		super();
		this.url = url;
	}

	String chuanHoaTinChi(String st) {
		int i = st.indexOf("(");

		return st.substring(0, i);

	}

	public void load() {

		header = new header_BangDanhSach();
		list = new ArrayList<Student_KetQuaHocTap>();
		Document doc;

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

			System.out.println(header.toString());
			Element EDiem = tbody.get(2);
			Elements Etr = EDiem.select("tr");

			for (int i = 0; i < Etr.size(); i++) {

				Student_KetQuaHocTap kq = new Student_KetQuaHocTap();
				Elements Etd = Etr.get(i).select("td");
				kq.setMaSV(Etd.get(1).text());
				kq.setTen(Etd.get(2).text());
				kq.setDiem1(Etd.get(3).text());
				kq.setDiem2(Etd.get(4).text());
				kq.setDiem3(Etd.get(5).text());
				kq.setDiem4(Etd.get(6).text());
				kq.setDiem5(Etd.get(7).text());
				kq.setDiem6(Etd.get(8).text());
				kq.setDiemGiuaHocPhan(Etd.get(9).text());
				kq.setSoTietNghi(Etd.get(10).text());
				kq.setDiemChuyenCan(Etd.get(11).text());
				kq.setDiemTB(Etd.get(12).text());
				kq.setDieuKienThi(Etd.get(13).text());
				list.add(kq);

			}

			obj.setHeader(header);
			obj.setListDanhSach(list);
		} catch (Exception e) {
		}
	}

	public header_BangDanhSach getHeader() {
		return header;
	}

	public List<Student_KetQuaHocTap> getList() {
		return list;
	}

	public Obj_Student_KetQuaHocTap getObj() {
		return obj;
	}

}
