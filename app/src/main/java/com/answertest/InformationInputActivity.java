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

public class InformationInputActivity extends Activity {

    private ImageView left;
    private TextView title;
    private RecyclerView recyclerView_chapter;
    private List<String> mChapters;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chapter);
        initData();
        initView();
        setData();
        initEvent();
    }
    private void initData() {
        mChapters = new ArrayList<>();
        String chapter1 = "操作题";
        String chapter2 = "录入题";
        String chapter3 = "Excel电子表格";
        String chapter4 = "ppt演示文稿题目";
        String chapter5 = "Word文字编辑";
        mChapters.add(chapter1);
        mChapters.add(chapter2);
        mChapters.add(chapter3);
        mChapters.add(chapter4);
        mChapters.add(chapter5);

    }
    private void initView() {
        left = (ImageView) findViewById(R.id.left);
        title = (TextView) findViewById(R.id.title);
        recyclerView_chapter = (RecyclerView) findViewById(R.id.recyclerView_chapter);
        recyclerView_chapter.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }
    private void setData(){
        title.setText("其他题型");
        ChapterAdapter adapter = new ChapterAdapter(mChapters, getApplicationContext());
        adapter.setOnItemClickLitener(new ChapterAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (mChapters.get(position)) {
                    case "操作题":
                        intent = new Intent(InformationInputActivity.this, AnalogyQandAActivity.class);
                        intent.putExtra("title", "操作题");
                        startActivity(intent);
                        break;
                    case "录入题":
                        intent = new Intent(InformationInputActivity.this, AnalogyQandAActivity.class);
                        intent.putExtra("title", "录入题");
                        startActivity(intent);
                        break;
                    case "Excel电子表格":
                        intent = new Intent(InformationInputActivity.this, AnalogyQandAActivity.class);
                        intent.putExtra("title", "Excel电子表格");
                        startActivity(intent);
                        break;
                    case "ppt演示文稿题目":
                        intent = new Intent(InformationInputActivity.this, AnalogyQandAActivity.class);
                        intent.putExtra("title", "ppt演示文稿题目");
                        startActivity(intent);
                        break;
                    case "Word文字编辑":
                        intent = new Intent(InformationInputActivity.this, AnalogyQandAActivity.class);
                        intent.putExtra("title", "Word文字编辑");
                        startActivity(intent);
                        break;

                }

            }
        });
        recyclerView_chapter.setAdapter(adapter);
    }
    private void initEvent(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
