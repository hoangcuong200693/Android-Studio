package qlcl.search.haui.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import qlcl.search.haui.adapter.CropOptionAdapter;
import qlcl.search.haui.adapter.Option_Adapter;
import qlcl.search.haui.dialog.Dialog_NhapMaSV;
import qlcl.search.haui.dialog.Dialog_No_Connect;
import qlcl.search.haui.dialog.Dialog_about;
import qlcl.search.haui.dialog.dialog_feedback;
import qlcl.search.haui.obj.CropOption;
import qlcl.search.haui.obj.RoundedImageView;
import qlcl.search.haui.process.SaveBitmap;
import qlcl.search.haui.process.SaveInfo;
import qlcl.search.haui.R;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment show option
 * 
 * @author Eo
 * 
 */
public class Option_Menu extends Fragment implements OnItemClickListener {

	List<Integer> listIcon;
	List<String> listTitle;
	ListView listview;
	IsOnline isOnline;
	RoundedImageView imgAvatar;
	public static TextView tvName, tvCode, tvClass;
	SaveInfo saveInfo;
	SaveBitmap saveBitmap;

	private Uri mImageCaptureUri;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int CROP_FROM_CAMERA = 2;
	private static final int PICK_FROM_FILE = 3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		View v = inflater.inflate(R.layout.optionmenu, container, false);

		tvName = (TextView) v.findViewById(R.id.tvName);
		tvCode = (TextView) v.findViewById(R.id.tvCode);
		tvClass = (TextView) v.findViewById(R.id.tvClass);
		saveInfo = new SaveInfo(getActivity());
		String st = saveInfo.readFromFile().trim();
		if (st != null) {
			String[] info = st.split("-");
			tvName.setText(info[0]);
			tvCode.setText(info[1]);
			tvClass.setText(info[2]);
		}

		final String[] items = new String[] { "Chụp ảnh", "Chọn từ thư viện" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.select_dialog_item, items);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("Chọn ảnh");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) { // pick from
																	// camera
				if (item == 0) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

					mImageCaptureUri = Uri.fromFile(new File(Environment
							.getExternalStorageDirectory(), "tmp_avatar_"
							+ String.valueOf(System.currentTimeMillis())
							+ ".jpg"));

					intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
							mImageCaptureUri);

					try {
						intent.putExtra("return-data", true);

						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (ActivityNotFoundException e) {
						e.printStackTrace();
					}
				} else { // pick from file
					Intent intent = new Intent();

					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);

					startActivityForResult(Intent.createChooser(intent,
							"Hoàn thành thao tác với"), PICK_FROM_FILE);
				}
			}
		});

		final AlertDialog dialog = builder.create();

		saveBitmap = new SaveBitmap(getActivity());
		Bitmap bm = saveBitmap.getThumbnail("avatar.png");
		imgAvatar = (RoundedImageView) v.findViewById(R.id.imgAvatar);
		if (bm != null) {
			imgAvatar.setImageBitmap(bm);
		}
		imgAvatar.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				dialog.show();
					

			}
		});

		isOnline = new IsOnline(getActivity());
		listview = (ListView) v.findViewById(R.id.list_option);
		listview.setOnItemClickListener(this);

		listIcon = new ArrayList<Integer>();
		listTitle = new ArrayList<String>();

		/*
		 * Add icon to listIcon
		 */
		listIcon.add(R.drawable.home);
		listIcon.add(R.drawable.people);
		// listIcon.add(R.drawable.tichluy);
		listIcon.add(R.drawable.setting);
		listIcon.add(R.drawable.face);
		listIcon.add(R.drawable.feedback);
		listIcon.add(R.drawable.help);
		listIcon.add(R.drawable.about);

		/*
		 * Add title to listTitle
		 */
		listTitle.add("Trang chủ");
		listTitle.add("Quản lí mã SV");
		// listTitle.add("Điểm tích lũy");
		listTitle.add("Cài đặt");
		listTitle.add("Chia sẻ qua Facebook");
		listTitle.add("Phản hồi");
		listTitle.add("Trợ giúp");
		listTitle.add("Thông tin phần mềm");

		LayoutInflater layoutinflater = (LayoutInflater) getActivity()
				.getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);

		/*
		 * Create new adapter option
		 */
		Option_Adapter adapter1 = new Option_Adapter(listIcon, listTitle,
				getActivity());

		listview.setAdapter(adapter1);
		return v;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int vt, long arg3) {
		switch (vt) {

		case 1:
			/*
			 * Intent show dialog put Student code
			 */
			Dialog_NhapMaSV dialog_nhap = new Dialog_NhapMaSV(getActivity());
			dialog_nhap.show();

			break;
		case 3:

			/*
			 * Intent Share to facebook
			 */

			Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent
					.putExtra(android.content.Intent.EXTRA_TEXT,
							"https://play.google.com/store/apps/details?id=qlcl.search.haui_qlcl");
			PackageManager pm = getActivity().getPackageManager();
			List<ResolveInfo> activityList = pm.queryIntentActivities(
					shareIntent, 0);
			for (final ResolveInfo app : activityList) {
				if ((app.activityInfo.name).contains("facebook")) {
					final ActivityInfo activity = app.activityInfo;
					final ComponentName name = new ComponentName(
							activity.applicationInfo.packageName, activity.name);
					shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
					shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					shareIntent.setComponent(name);
					getActivity().startActivity(shareIntent);
					break;
				}
			}

			break;
		case 4:

			/*
			 * Show dialog feedback
			 */
			dialog_feedback dl = new dialog_feedback(getActivity());
			dl.show();
			break;
		case 5:
			/*
			 * Intent help
			 */
			Intent it = new Intent(getActivity(), activity_help.class);
			startActivity(it);
			break;
		case 6:
			/*
			 * Show dialog about
			 */
			Dialog_about dialog_about = new Dialog_about(getActivity());
			dialog_about.show();

			break;

		case 2:
			/*
			 * Intent Setting
			 */
			Intent itst = new Intent(getActivity(), activity_Setting.class);
			startActivity(itst);

			break;

		case 0:
			/*
			 * Intent home
			 */

			if (!isOnline.checkOnline()) {
				Dialog_No_Connect dlhome = new Dialog_No_Connect(getActivity(),
						"Không có kết nối Internet\n Vui lòng kiểm tra lại ");
				dlhome.show();
			} else {
				Intent it_home = new Intent(getActivity(),
						activity_webView.class);
				startActivity(it_home);
			}

			break;

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != getActivity().RESULT_OK)
			return;

		switch (requestCode) {
		case PICK_FROM_CAMERA:
			doCrop();

			break;

		case PICK_FROM_FILE:
			mImageCaptureUri = data.getData();

			doCrop();

			break;

		case CROP_FROM_CAMERA:
			Bundle extras = data.getExtras();

			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
			//	saveBitmap.saveImageToExternalStorage(photo);
				saveBitmap.saveImageToInternalStorage(photo);

				imgAvatar.setImageBitmap(photo);
			}

			File f = new File(mImageCaptureUri.getPath());

			if (f.exists())
				f.delete();

			break;

		}
	}

	private void doCrop() {
		final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");

		List<ResolveInfo> list = getActivity().getPackageManager()
				.queryIntentActivities(intent, 0);

		int size = list.size();

		if (size == 0) {
			Toast.makeText(getActivity(), "Can not find image crop app",
					Toast.LENGTH_SHORT).show();

			return;
		} else {
			intent.setData(mImageCaptureUri);

			intent.putExtra("outputX", 200);
			intent.putExtra("outputY", 200);
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("scale", true);
			intent.putExtra("return-data", true);

			if (size == 1) {
				Intent i = new Intent(intent);
				ResolveInfo res = list.get(0);

				i.setComponent(new ComponentName(res.activityInfo.packageName,
						res.activityInfo.name));

				startActivityForResult(i, CROP_FROM_CAMERA);
			} else {
				for (ResolveInfo res : list) {
					final CropOption co = new CropOption();

					co.title = getActivity().getPackageManager()
							.getApplicationLabel(
									res.activityInfo.applicationInfo);
					co.icon = getActivity().getPackageManager()
							.getApplicationIcon(
									res.activityInfo.applicationInfo);
					co.appIntent = new Intent(intent);

					co.appIntent
							.setComponent(new ComponentName(
									res.activityInfo.packageName,
									res.activityInfo.name));

					cropOptions.add(co);
				}

				CropOptionAdapter adapter = new CropOptionAdapter(
						getActivity(), cropOptions);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle("Choose Crop App");
				builder.setAdapter(adapter,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								startActivityForResult(
										cropOptions.get(item).appIntent,
										CROP_FROM_CAMERA);
							}
						});

				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {

						if (mImageCaptureUri != null) {
							getActivity().getContentResolver().delete(
									mImageCaptureUri, null, null);
							mImageCaptureUri = null;
						}
					}
				});

				AlertDialog alert = builder.create();

				alert.show();
			}
		}
	}

}
