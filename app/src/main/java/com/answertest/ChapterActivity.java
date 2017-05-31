package com.answertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.answertest.adapter.ChapterAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends Activity implements View.OnClickListener {

    private RecyclerView recyclerView_chapter;
    private List<String> mChapters;
    Intent intent;
    private ImageView left;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chapter);
        initData();
        initView();
        setData();
    }

    private void initData() {
        mChapters = new ArrayList<>();
        String chapter1 = "第1章-信息与计算机";
        String chapter2 = "第2章-Windows操作系统";
        String chapter3 = "第3章-办公自动化软件应用";
        String chapter4 = "第4章-多媒体应用技术基础";
        String chapter5 = "第5章-计算机网络基础";
        String chapter6 = "第6章-数据库技术及应用基础";
        mChapters.add(chapter1);
        mChapters.add(chapter2);
        mChapters.add(chapter3);
        mChapters.add(chapter4);
        mChapters.add(chapter5);
        mChapters.add(chapter6);
    }

    private void initView() {
        recyclerView_chapter = (RecyclerView) findViewById(R.id.recyclerView_chapter);
        recyclerView_chapter.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        left = (ImageView) findViewById(R.id.left);
        title = (TextView) findViewById(R.id.title);
        title.setText("历年真题");
        left.setOnClickListener(this);
    }
    private void setData() {
        ChapterAdapter adapter = new ChapterAdapter(mChapters, getApplicationContext());
        adapter.setOnItemClickLitener(new ChapterAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (mChapters.get(position)) {
                    case "第1章-信息与计算机":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 1);
                        startActivity(intent);
                        break;
                    case "第2章-Windows操作系统":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 2);
                        startActivity(intent);
                        break;
                    case "第3章-办公自动化软件应用":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 3);
                        startActivity(intent);
                        break;
                    case "第4章-多媒体应用技术基础":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 4);
                        startActivity(intent);
                        break;
                    case "第5章-计算机网络基础":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 5);
                        startActivity(intent);
                        break;
                    case "第6章-数据库技术及应用基础":
                        intent = new Intent(ChapterActivity.this, AnalogyExaminationActivity.class);
                        intent.putExtra("checkId", 6);
                        startActivity(intent);
                        break;
                }

            }
        });
        recyclerView_chapter.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left:
                finish();
                break;
        }

    }
}
