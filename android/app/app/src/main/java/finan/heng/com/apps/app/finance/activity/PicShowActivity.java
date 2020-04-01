package finan.heng.com.apps.app.finance.activity;
/*
 * Created by hhm on 2017/5/3.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import finan.heng.com.apps.R;
import finan.heng.com.apps.base.BaseActivity;

public class PicShowActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ArrayList<Integer> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_show);
        setUpToolbar();
        getSupportActionBar().setTitle("");
        setBarTitle("头像选择");
        initView();
        init();
    }

    private void init() {
        list = new ArrayList<>();
        list.add(R.mipmap.priest_001);
        list.add(R.mipmap.priest_002);
        list.add(R.mipmap.priest_003);
        list.add(R.mipmap.priest_004);
        list.add(R.mipmap.priest_005);
        list.add(R.mipmap.priest_006);
        list.add(R.mipmap.priest_008);
        list.add(R.mipmap.priest_010);
        list.add(R.mipmap.priest_013);
        list.add(R.mipmap.priest_014);
        list.add(R.mipmap.priest_015);
        list.add(R.mipmap.priest_016);
        list.add(R.mipmap.priest_019);
        list.add(R.mipmap.priest_022);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, list);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(this);
    }

    private void initView() {
        gridView = findViewById(R.id.activity_pic_show_gridview);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, BigPicActivity.class);
        intent.putExtra("num", "" + (position + 1));
        intent.putExtra("size", "" + list.size());
        int[] in = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            in[i] = list.get(i);
        }
        intent.putExtra("list", in);
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            setResult(resultCode);
            finish();
        }
    }

    private class GridViewAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<Integer> list;

        GridViewAdapter(Context context, ArrayList<Integer> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.pic_show_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.iv.setImageResource(list.get(position));
            return viewHolder.view;
        }

        class ViewHolder {
            private View view;
            private ImageView iv;

            public ViewHolder(View view) {
                this.view = view;
                iv = view.findViewById(R.id.pic_show_item_iv);
            }
        }
    }
}
