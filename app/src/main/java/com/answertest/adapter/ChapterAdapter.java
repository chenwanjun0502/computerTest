package com.answertest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.answertest.R;

import java.util.List;

/**
 * Created by cwj on 2017/5/16.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ExamHolder> {
    private List<String> mDataList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);

    }



    public ChapterAdapter(List<String> mDataList, Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mDataList = mDataList;
    }

    @Override
    public ExamHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ExamHolder holder = new ExamHolder(mLayoutInflater.inflate(R.layout.chapter_item,viewGroup,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final  ExamHolder holder, int i) {
            holder.tv_exam_title.setText(mDataList.get(i)+"");

        // 如果设置了回调，则设置点击事件
        if (onItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    onItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if(mDataList != null && mDataList.size()>0){
            return mDataList.size();
        }
       return  0;
    }

    class ExamHolder extends RecyclerView.ViewHolder{
            TextView tv_exam_title;
        public ExamHolder(View itemView) {
            super(itemView);
            tv_exam_title = (TextView) itemView.findViewById(R.id.tv_exam_title);
        }
    }
}
