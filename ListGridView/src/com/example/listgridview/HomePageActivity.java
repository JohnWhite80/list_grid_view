
package com.example.listgridview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class HomePageActivity extends Activity {
	private TextView search,tv_find;
	private LinearLayout find_homepage,come_homepage,my_homepage;
	private ImageView findback;
	private PullToRefreshListView PullRefreshListView_homepage;
	private Homepage_ListViewAdapter listviewadapter;
	private ProgressDialog pd;
	private List<Tag> list=new ArrayList<Tag>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		init();
		pd = ProgressDialog.show(HomePageActivity.this,"", "获取中，请稍后……");
		RecommendTask task=new RecommendTask();
		task.execute();
		
	}	
	private void init() {
		// TODO Auto-generated method stub
		tv_find=(TextView)findViewById(R.id.tv_find);
		tv_find.setTextColor(getResources().getColor(R.color.bottomblue));
		
		find_homepage=(LinearLayout)findViewById(R.id.ll_find);		
		come_homepage=(LinearLayout)findViewById(R.id.ll_come);
		my_homepage=(LinearLayout)findViewById(R.id.ll_my);
		search=(TextView)findViewById(R.id.tv_search_homepage);
		findback=(ImageView)findViewById(R.id.iv_backfind);
		
		findback.setImageResource(R.drawable.find_label_press);
		PullRefreshListView_homepage=(PullToRefreshListView)findViewById(R.id.pull_refresh_homepage);		
		PullRefreshListView_homepage.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub				
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				RefreshTask task=new RefreshTask();
				task.execute();				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub				
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);				
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				RefreshTask task=new RefreshTask();
				task.execute();
			}			
		});
	}

	private class RecommendTask extends AsyncTask<Void, Void, List>{

		@Override
		protected List doInBackground(Void... arg0) {			
			return loadData();
		}
		@Override
		protected void onPostExecute(List result) {
			// TODO Auto-generated method stub
			pd.dismiss();
			list=result;
			listviewadapter=new Homepage_ListViewAdapter(HomePageActivity.this, list);
			PullRefreshListView_homepage.setAdapter(listviewadapter);
		}
	}
	
	private int tagId = 0;
	private int userTagId = 0;
	private String img = "http://10.104.151.197:8080/server/api/image/1407984798184.jpg";
	
	private List<Tag> loadData(){
		List<Tag> result = new ArrayList<Tag>();
		for(int i=0; i <10; i++){
			Tag t = new Tag();
			String tagName = String.valueOf(tagId++);
			t.setName("tag" + tagName);
			List<UserTag> userTags = new ArrayList<UserTag>();	
			for(int j=0; j <10; j++) {
				UserTag ut = new UserTag();
				ut.setName("name" + String.valueOf(userTagId));
				ut.setNickName("nickName" + String.valueOf(userTagId));
				ut.setTagname(tagName);
				ut.setId(String.valueOf(userTagId++));
				ut.setImage(img);
				userTags.add(ut);
			}
			t.setUserTags(userTags);
			result.add(t);
		}
		
		return result;
	}
	private class RefreshTask extends AsyncTask<Void, Void, List>{

		@Override
		protected List doInBackground(Void... arg0) {
			// TODO Auto-generated method stub 
			return loadData();
		}
		@Override
		protected void onPostExecute(List result) {
			// TODO Auto-generated method stub
			list.clear();
			for(int i=0;i<result.size();i++){
				list.add((Tag) result.get(i));
			}			
			listviewadapter.notifyDataSetChanged();
			PullRefreshListView_homepage.onRefreshComplete();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		  if (keyCode == KeyEvent.KEYCODE_BACK) {
		        Intent intent = new Intent(Intent.ACTION_MAIN);
		        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        intent.addCategory(Intent.CATEGORY_HOME);
		        startActivity(intent);
		        return true;
		    }
		    return super.onKeyDown(keyCode, event);
	}
}

