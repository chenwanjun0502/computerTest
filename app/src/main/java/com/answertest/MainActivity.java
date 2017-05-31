package com.answertest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.answertest.bean.CollectQuesstionInfo;
import com.answertest.bean.ErrorQuestionInfo;
import com.answertest.utils.SessionUtils;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener{

	private Button btn_information,btn_collect,btn_init,btn_media,btn_network,btn_big,btn_sql,btn_wrong;

	Intent intent;
	private ImageView left;
	private TextView title;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
		initListener();

	}


	private void init() {
		left=(ImageView) findViewById(R.id.left);
		title=(TextView) findViewById(R.id.title);
		btn_information=(Button) findViewById(R.id.btn_information);
		btn_init=(Button) findViewById(R.id.btn_init);
		btn_wrong = (Button) findViewById(R.id.btn_wrong);
		btn_collect = (Button) findViewById(R.id.btn_collect);
		btn_big = (Button) findViewById(R.id.btn_big);
		left.setVisibility(View.GONE);
		title.setText("计算机一级");
	}
	private void initListener() {
		btn_information.setOnClickListener(this);
		btn_init.setOnClickListener(this);
		btn_collect.setOnClickListener(this);
		btn_wrong.setOnClickListener(this);
		btn_big.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {

		case R.id.btn_information:
			intent=new Intent(MainActivity.this,ChapterActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_init:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("初始化后会删除所有答题记录");
			builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					List<CollectQuesstionInfo> collections =
							SessionUtils.getInstance(getApplicationContext()).getCollectQuestion();
					if(collections !=null  && collections.size()>0){
						collections.clear();
					}
					SessionUtils.getInstance(getApplicationContext()).saveCollect(collections);
					List<ErrorQuestionInfo> errors =
							SessionUtils.getInstance(getApplicationContext()).getErrorQuestion();
					if(errors != null && errors.size()>0){
						errors.clear();
					}
					SessionUtils.getInstance(getApplicationContext()).saveErrorQuestion(errors);
					Toast.makeText(MainActivity.this, "初始化完成", Toast.LENGTH_SHORT).show();
				}
			});
			builder.setNegativeButton("点错了", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					dialogInterface.dismiss();
				}
			});
			builder.create().show();

			break;

			case R.id.btn_collect:
				List<CollectQuesstionInfo> collectQuesstionInfos = SessionUtils.getInstance(getApplicationContext()).getCollectQuestion();
				if(collectQuesstionInfos == null || collectQuesstionInfos.size() == 0){
					Toast.makeText(this, "暂无收藏", Toast.LENGTH_SHORT).show();
				}else {
					intent=new Intent(MainActivity.this,AnalogyExaminationActivity.class);
					intent.putExtra("checkId",8);
					startActivity(intent);
				}

				break;
			case R.id.btn_wrong:
				List<ErrorQuestionInfo> errorQuestionInfos = SessionUtils.getInstance(getApplicationContext()).getErrorQuestion();
				if(errorQuestionInfos == null || errorQuestionInfos.size() ==0){
					Toast.makeText(this, "暂无错题", Toast.LENGTH_SHORT).show();
				}else {
					intent=new Intent(MainActivity.this,AnalogyExaminationActivity.class);
					intent.putExtra("checkId",7);
					startActivity(intent);
				}
				break;
			case R.id.btn_big:
				Intent intent1=new Intent(MainActivity.this,InformationInputActivity.class);
				startActivity(intent1);
				break;

		default:
			break;
		}
	}

}