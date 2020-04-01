package finan.heng.com.apps.app.my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import finan.heng.com.apps.R;

/**
 * Created by Administrator on 2016/9/20.
 */
public class InputPawAdapter extends BaseAdapter {
    private Context context;
    private int[] images = {R.mipmap.one,R.mipmap.two,R.mipmap.three,R.mipmap.four,R.mipmap.five,
            R.mipmap.six,R.mipmap.seven,R.mipmap.eight,R.mipmap.nine,R.mipmap.zero,R.mipmap.delete};

    public InputPawAdapter(Context context) {
        this.context =context;
    }

    @Override
    public int getCount() {
        return images.length+1;
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
        if (convertView==null){
            convertView= View.inflate(context, R.layout.item_input_key,null);
        }
        ImageView imageView = convertView.findViewById(R.id.iv_number);
        if (position<=8){
            imageView.setImageResource(images[position]);
        }else if (position==9){
            imageView.setBackgroundColor(context.getResources().getColor(R.color.color_eeeeee));
        }else{
            imageView.setImageResource(images[position-1]);
        }
        convertView.setTag(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                if (numberItemClick!=null){
                    if (position<=8){
                        numberItemClick.itemOnClick(position+1);
                    }else if (position==10){
                        numberItemClick.itemOnClick(0);
                    }else if (position==11){
                        numberItemClick.itemOnClick(11);
                    }

                }
            }
        });
        return convertView;
    }

    private NumberItemClick numberItemClick;
    public interface  NumberItemClick{
        void itemOnClick(int position);
    }

    public void setNumberItemClick(NumberItemClick numberItemClick) {
        this.numberItemClick = numberItemClick;
    }
}
