package com.answertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActivityResultPage extends Activity {

    @BindView(R.id.result_title)
    TextView resultTitle;
    @BindView(R.id.result_content)
    TextView resultContent;
    @BindView(R.id.btn_test)
    Button btnTest;
    private TextView result_title, result_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void init() {
        result_title = (TextView) findViewById(R.id.result_title);
        result_content = (TextView) findViewById(R.id.result_content);
    }

    private void getData() {
        Bundle b = getIntent().getExtras();
        int pageId = b.getInt("pageId");
        int pageScore = b.getInt("pageScore");
        int errorNum = b.getInt("errorNum");
        switch (pageId) {
            case 1:
                result_title.setText("恭喜您!已完成信息与计算机练习!");
                break;
            case 2:
                result_title.setText("恭喜您!已完成Windows操作系统练习!");
                break;
            case 3:
                result_title.setText("恭喜您!已完成办公自动化软件应用练习!");
                break;
            case 4:
                result_title.setText("恭喜您!已完成多媒体应用技术基础练习!");
                break;
            case 5:
                result_title.setText("恭喜您!已完成计算机网络基础练习!");
                break;
            case 6:
                result_title.setText("恭喜您!已完成数据库技术及应用基础!");
                break;
            case 7:
                result_title.setText("已完成错题集练习!");
                break;
            case 8:
                result_title.setText("已完成收藏练习题!");
                break;
            default:
                break;
        }
        if (errorNum >= 15) {
            result_content.setText("非常抱歉，错题数达到" + errorNum + "还需要多多练习，加油！");
        } else if (errorNum <= 5) {
            result_content.setText("恭喜你，只错了" + errorNum + "题,你的思路清晰，考虑周全，再接再厉哦！");
        } else {
            result_content.setText("革命尚未成功，同学仍需努力，总共错了" + errorNum + "题");
        }


    }

    @OnClick(R.id.btn_test)
    private void  test(){

    }
}
