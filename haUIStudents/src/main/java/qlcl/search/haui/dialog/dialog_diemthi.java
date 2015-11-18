package qlcl.search.haui.dialog;
import java.util.List;

import qlcl.search.haui.adapter.adapter_DanhSachMon;
import qlcl.search.haui.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class dialog_diemthi extends Dialog {

	String title;
	Activity act;
	List<String> listMon;
	int color;
	View v;

	TextView tv_tendanhsach;
	ListView listDanhSach;
	Button btn_danhsach;

	public dialog_diemthi( String title, Activity act,
			List<String> listMon,int color) {
		super(act);
		this.title = title;
		this.act = act;
		this.listMon = listMon;
		this.color=color;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		setContentView(R.layout.dialog_diemthi);
		tv_tendanhsach = (TextView) findViewById(R.id.tv_tendanhsach);
		tv_tendanhsach.setText(title);
		tv_tendanhsach.setBackgroundColor(color);
v=findViewById(R.id.view);
v.setBackgroundColor(color);
		adapter_DanhSachMon adapter = new adapter_DanhSachMon(listMon, act);
		listDanhSach = (ListView) findViewById(R.id.listDanhsachdiem);
		listDanhSach.setAdapter(adapter);
		btn_danhsach = (Button) findViewById(R.id.btn_danhsach);
		btn_danhsach.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}

}
