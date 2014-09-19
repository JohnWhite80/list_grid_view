package com.example.listgridview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Homepage_ListViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Tag> mList;

	private class ViewHolder {
		MGridView gl;
		TextView tv;
	}

	public Homepage_ListViewAdapter(Context context, List<Tag> list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_listview_homepage, null, false);
			holder.tv = (TextView) convertView.findViewById(R.id.tv_tag);
			holder.gl = (MGridView) convertView.findViewById(R.id.my_gridview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(mList.get(position).getName());

		Homepage_GalleryAdapter adapter = new Homepage_GalleryAdapter(mContext,
				mList.get(position).getUserTags());

		LayoutParams params = new LayoutParams(mList.get(position)
				.getUserTags().size()
				* (180 + 6), LayoutParams.WRAP_CONTENT);
		holder.gl.setLayoutParams(params);
		holder.gl.setColumnWidth(180);
		holder.gl.setHorizontalSpacing(6);
		holder.gl.setStretchMode(GridView.NO_STRETCH);
		holder.gl.setNumColumns(mList.get(position).getUserTags().size());

		holder.gl.setAdapter(adapter);
		return convertView;
	}
}
