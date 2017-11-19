package cn.edu.gdmec.android.mobileguard.m5virusscan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cn.edu.gdmec.android.mobileguard.m5virusscan.entity.ScanAppinfo;


/**
 * Created by hh on 2017/11/18.
 */

public class ScanVirusAdapter extends BaseAdapter {
    private List<ScanAppinfo> mScanAppInfos;
    private Context context;
    public ScanVirusAdapter(List<ScanAppinfo> scanAppinfos,Context context){
        super();
        mScanAppInfos = scanAppinfos;
        this.context=context;
    }
    static class ViewHolder{
        ImageView mAppIconImgv;
        TextView mAppNameTv;
        ImageView mScanIconImgv;
    }
    @Override
    public int getCount() {
        return mScanAppInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return  mScanAppInfos.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.item_list_applock,null);
            holder = new ViewHolder();
            holder.mAppIconImgv=(ImageView) view.findViewById(R.id.img_appicon);
            holder.mAppNameTv=(TextView) view.findViewById(R.id.tv_appname);
            holder.mScanIconImgv=(ImageView) view.findViewById(R.id.imgv_lock);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        ScanAppinfo scanAppinfo = mScanAppInfos.get(i);
        if (!scanAppinfo.isVirus){
            holder.mScanIconImgv.setBackgroundResource(R.drawable.blue_right_icon);
            holder.mAppNameTv.setTextColor(context.getResources().getColor(R.color.black));
            holder.mAppNameTv.setText(scanAppinfo.appName);
        }else{
            holder.mAppNameTv.setTextColor(context.getResources().getColor(R.color.bright_red));
            holder.mAppNameTv.setText(scanAppinfo.appName+"("+scanAppinfo.description+")");
        }
        holder.mAppIconImgv.setImageDrawable(scanAppinfo.appicon);
        return  view;
    }
}
