package qlcl.search.haui.variable;

import qlcl.search.haui.obj.Obj_KetQuaHocTap;
import qlcl.search.haui.obj.Obj_KetQuaThi;
import qlcl.search.haui.obj.Obj_LichThi;
import qlcl.search.haui.obj.Obj_Student_KetQuaHocTap;

public class Variable {
	// Lich thi

	// Ket qua thi

	public static final String KETQUATHI_LINK_DSACH = "link_danh_sach";

	// Ket qua hoc tap

	public static final String KETQUAHOCTAP_DANHSACHLOP = "danhsach_lop";
	// tham do
	public static boolean THAMDO = false;

	// ket qua hoc tap theo lop

	// sharedpreference
	public static final String FILENAME = "file_save";
	public static final String KEY_SAVE = "data";
	public static final String FILE_MK = "file_mk";
	public static final String KEY_MK = "data_mk";

	public static final String FILE_NOTIFY = "file_notify";
	public static final String KEY_NOTIFY = "key_notify";

	public static final String FILE_LICHTHI = "file_lichthi";
	public static final String FILE_KETQUATHI = "file_ketquathi";
	public static final String FILE_KETQUAHOCTAP = "file_ketquahoctap";
	public static final String FILE_TICHLUY = "file_tichluy";
	public static final String FILE_INFO= "file_info";
	

	public static final String KEY_LICHTHI = "save_lichthi";
	public static final String KEY_KETQUATHI = "save_ketquathi";
	public static final String KEY_KETQUAHOCTAP = "save_ketquahoctap";
	public static final String KEY_TICHLUY = "save_tichluy";
	public static final String KEY_INFOR = "save_info";
	
	// Notify
	public static boolean NOTIFY = false;
	public static boolean THONG_BAO = false;

	// obj
	public static Obj_LichThi obj_lichthi = null;
	public static Obj_KetQuaThi obj_ketquathi = null;
	public static Obj_KetQuaHocTap obj_ketquahoctap = null;
	public static Obj_Student_KetQuaHocTap obj_ketquahoctaptheolop = null;

	// save position in listview
	public static int POSITION_LICHTHI = 0;
	public static int POSITION_KETQUATHI = 0;
	public static int POSITION_KETQUAHOCTAP = 0;
	
	public static boolean ACTIVITY_IS_ON=false;//đánh dấu trạng thái activty có đang hiển thị hay k
	


}
