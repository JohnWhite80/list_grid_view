package com.example.listgridview;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class Homepage_GalleryAdapter extends BaseAdapter {
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private Context mContext;
	private List<UserTag> mGist;

	public Homepage_GalleryAdapter(Context context, List<UserTag> gist) {
		this.mContext = context;
		this.mGist = gist;
		initImageLoader(context);
	}

	private void initImageLoader(Context context) {
		// TODO Auto-generated method stub
		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO);
		if (BuildConfig.DEBUG) {
			builder.writeDebugLogs();
		}
		ImageLoader.getInstance().init(builder.build());

		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.empty_photo)
				.showImageForEmptyUri(R.drawable.empty_photo)
				.showImageOnFail(R.drawable.empty_photo).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.ARGB_8888)
				.build();
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return mGist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mGist.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.adapter_gallery, null, false);
			holder.headpic = (ImageView) convertView
					.findViewById(R.id.headpic_gallery);
			holder.nikename = (TextView) convertView
					.findViewById(R.id.nikename_gallery);
			holder.theme = (TextView) convertView
					.findViewById(R.id.desscripe_gallery);

			holder.chat_left = (LinearLayout) convertView
					.findViewById(R.id.ll_chat_left);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final String theme1 = mGist.get(position).getName();
		final String nikename1 = mGist.get(position).getNickName();
		final String tagname1 = mGist.get(position).getTagname();
		final String image1 = mGist.get(position).getImage();
		final String userId = mGist.get(position).getUserId();
		holder.theme.setText(theme1);
		holder.nikename.setText(nikename1);
		imageLoader.displayImage(image1, holder.headpic, options);

		return convertView;
	}

	private class ViewHolder {
		LinearLayout chat_left;
		TextView nikename;
		ImageView headpic;
		TextView theme;
	}
}
