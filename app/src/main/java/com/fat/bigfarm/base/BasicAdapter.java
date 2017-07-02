package com.fat.bigfarm.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 * @param <T>
 * 
 * baseAdapter 解决异步加载问题
 */
public class BasicAdapter<T> extends BaseAdapter{
	protected Context context;
	protected ArrayList<T> list;
	
	//alt+shift+s->o:用成员变量生成构造方法
	public BasicAdapter(Context context, ArrayList<T> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
