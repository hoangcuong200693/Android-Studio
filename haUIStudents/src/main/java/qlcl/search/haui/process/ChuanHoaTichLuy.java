package qlcl.search.haui.process;
import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.obj.MonHoc;

/**
 * 
 * @author Eo Cuong
 *
 *class cho thực hiên việc lại bỏ môn học dư thừa trong list Môn học (cải thiện hoặc là học lại)
 */
public class ChuanHoaTichLuy {

	List<MonHoc> list;
boolean hopLe=true;
	/**
	 * 
	 * @param list
	 * Khởi tạo
	 */
	public ChuanHoaTichLuy(List<MonHoc> list) {
		super();
		this.list = list;
	}

	
	
	




	public boolean isHopLe() {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getGhiChu().trim().equals("Chưa nộp tiền văn phòng phẩm phục vụ thi")){
				hopLe=false;
			}
		}
		
		return hopLe;
	}








	/**
	 * 
	 * @return trả về danh sách môn thì đã được loại bỏ dư thừa
	 */
	public List<MonHoc> getListTichLuy() {

		List<MonHoc> myList = new ArrayList<MonHoc>();
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				if(list.get(i).getMaLopDoclap().indexOf("QS")<0 && list.get(i).getTenMonHoc().indexOf("Giáo dục thể chất")<0){
					myList.add(list.get(i));
				}
				
			} else {
				MonHoc mh = list.get(i);
				String diem = mh.getDiemTBC();
				String tenMon = mh.getTenMonHoc().trim();
				String maLop=mh.getMaLopDoclap();
				if (!diem.trim().equals("") && !diem.equals("**") && ( maLop.indexOf("QS")<0 && tenMon.indexOf("Giáo dục thể chất")<0))  {
					double diemSo = Double.parseDouble(diem);
					int chiso=contain(myList, tenMon);
					if(chiso>-1){
						if(Double.parseDouble(myList.get(chiso).getDiemTBC().toString())<diemSo){
							myList.set(chiso, mh);
						}
					}else{
						myList.add(mh);
					}
				}
			}
		}

		return myList;

	}
/**
 * 
 * @param list
 * @param tenmon
 * @return đưa ra chỉ số của môn học trong mảng, nếu k có thì trả về -1
 */
	int contain(List<MonHoc> list, String tenmon) {
		int chiso = -1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTenMonHoc().trim().equals(tenmon.trim())) {
				chiso = i;

			}
		}
		return chiso;

	}

}
