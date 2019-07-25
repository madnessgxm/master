package com.toolutils.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.toolutils.R;

import java.util.List;

/**
 * User: guoxiaomeng
 * Date: 2019-07-25
 * Time: 10:48
 */
public class AppMsgListAdapter extends BaseAdapter {
    public Context mContext;
    private List<String> msglst;
    public AppMsgListAdapter(Context context,List<String> tmpmsglst)
    {
        mContext = context;
        msglst = tmpmsglst;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MsgItem msgItem;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.app_msg_list_item_layout,null);
            msgItem = new MsgItem();
            msgItem.tvMsg = (TextView)convertView.findViewById(R.id.tv_list_item);
            convertView.setTag(msgItem);
        }else
        {
            msgItem = (MsgItem) convertView.getTag();
        }
        msgItem.tvMsg.setText(msglst.get(position));
        return convertView;
    }

    @Override
    public int getCount() {
        return msglst.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return msglst.get(position);
    }

    public void setMsglst(List<String> msglst) {
        this.msglst = msglst;
        notifyDataSetChanged();
    }

    class MsgItem
    {
       public TextView tvMsg;
    }
}
