package com.answertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.answertest.data.ExcelData;
import com.answertest.data.InformationInputData;
import com.answertest.data.InputData;
import com.answertest.data.PPTData;
import com.answertest.data.WordData;

public class AnalogyQandAActivity extends Activity {

    private ImageView left;
    private TextView title;
    private TextView tv_question;
    private TextView tv_answer;
    private String mtitle = "";
    private LinearLayout line_qanda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_analogy_qand_a);
        initData();
        initView();
        setData();
        initEvent();
    }

    private void initData() {
        mtitle = getIntent().getStringExtra("title");
    }

    private void initView() {
        left = (ImageView) findViewById(R.id.left);
        title = (TextView) findViewById(R.id.title);
        tv_question = (TextView) findViewById(R.id.tv_question);
        tv_answer = (TextView) findViewById(R.id.tv_answer);
        line_qanda = (LinearLayout) findViewById(R.id.line_qanda);

    }

    private void setData() {

            if (mtitle != null && !mtitle.equals("")) {
                title.setText(mtitle);
                switch (mtitle) {
                    case "操作题":
                        tv_question.setText(InformationInputData.question);
                        tv_answer.setText(InformationInputData.answer);
                        break;
                    case "录入题":
                        tv_question.setText(InputData.question);
                        tv_answer.setVisibility(View.GONE);
                        break;
                    case "Excel电子表格":
                        tv_question.setText(ExcelData.question);
                        tv_answer.setText(ExcelData.answer);
                        break;
                    case "ppt演示文稿题目":
                        tv_question.setText(PPTData.question);
                        tv_answer.setText(PPTData.answer);
                        break;
                    case "Word文字编辑":
                        tv_question.setText(WordData.question);
                        tv_answer.setText(WordData.answer);
                        break;
                }
            }
          }

    private void initEvent() {
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
