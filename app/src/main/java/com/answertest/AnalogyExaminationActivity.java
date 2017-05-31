package com.answertest;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.answertest.adapter.ExaminationSubmitAdapter;
import com.answertest.bean.AnSwerInfo;
import com.answertest.bean.CollectQuesstionInfo;
import com.answertest.bean.ErrorQuestionInfo;
import com.answertest.bean.SaveQuestionInfo;
import com.answertest.bean.InformationAndComputerInfo;
import com.answertest.bean.MultimediaInfo;
import com.answertest.bean.NetDataList;
import com.answertest.bean.OfficeAutoInfo;
import com.answertest.bean.SQLInfo;
import com.answertest.bean.WindowInfo;
import com.answertest.utils.ConstantUtil;
import com.answertest.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 答题
 *
 */
public class AnalogyExaminationActivity extends Activity {

	private ImageView leftIv;
	private TextView titleTv;
	private TextView right;

	VoteSubmitViewPager viewPager;
	//	ViewPager viewPager;
	ExaminationSubmitAdapter pagerAdapter;
	List<View> viewItems = new ArrayList<View>();
	List<AnSwerInfo> dataItems = new ArrayList();
	private List<ErrorQuestionInfo> errorQuestionInfoList;
	private ProgressDialog progressDialog;

	private String pageCode;
	private int pageScore;
	private int errortopicNums;// 错题数
	private int errortopicNums1;// 错题数
	private String isPerfectData = "1";// 是否完善资料0完成 1未完成
	private String type = "0";// 0模拟 1竞赛
	private String errorMsg="";

	Dialog builderSubmit;

	//	public List<Map<String, SaveQuestionInfo>> list = new ArrayList<Map<String, SaveQuestionInfo>>();
	//	public Map<String, SaveQuestionInfo> map2 = new HashMap<String, SaveQuestionInfo>();
	public List<SaveQuestionInfo> questionInfos = new ArrayList<SaveQuestionInfo>();
	public List<CollectQuesstionInfo> collectQuesstionInfos ;
	Timer timer;
	TimerTask timerTask;
	int minute = 5;
	int second = 0;

	int pageId=0;

	boolean isPause = false;
	int isFirst;

	String dateStr = "";
	String imgServerUrl = "";

	private boolean isUpload= false;

	private Handler handlerSubmit = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);

			switch (msg.what) {
				case 1:
					showSubmitDialog();
					break;
				default:
					break;
			}

		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_practice_test);
		initView();
		getPageId();
		loadData();

	}
	private void getPageId() {
		collectQuesstionInfos = SessionUtils.getInstance(getApplicationContext()).getCollectQuestion();
		Bundle intent=getIntent().getExtras();
		int checkId=intent.getInt("checkId");
		switch (checkId) {
			case 1:
				pageId=1;
				titleTv.setText("信息与计算机");
				InformationAndComputerInfo informationAndComputerData =
						SessionUtils.getInstance(getApplicationContext()).getInformationAndComputerData();
				for (int i = 0; i < informationAndComputerData.getAnswerId().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(informationAndComputerData.getAnswerId()[i]);// 试题主键
					info.setQuestionName(informationAndComputerData.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(informationAndComputerData.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(informationAndComputerData.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(informationAndComputerData.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(informationAndComputerData.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(informationAndComputerData.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(informationAndComputerData.getAnswerOptionD()[i]);// 试题选项D
					//info.setOptionE(GraduateData.answerOptionE[i]);// 试题选项E
					//info.setScore(GraduateData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 2:
				pageId=2;
				titleTv.setText("Windows操作系统");
				WindowInfo windowInfo = SessionUtils.getInstance(getApplicationContext()).getWindowData();
				for (int i = 0; i < windowInfo.getAnswerName().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(windowInfo.getAnswerId()[i]);// 试题主键
					info.setQuestionName(windowInfo.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(windowInfo.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(windowInfo.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(windowInfo.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(windowInfo.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(windowInfo.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(windowInfo.getAnswerOptionD()[i]);// 试题选项D
					//info.setOptionE(MarketData.answerOptionE[i]);// 试题选项E
					//info.setScore(MarketData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 3:
				pageId=3;
				titleTv.setText("办公自动化软件应用");
				OfficeAutoInfo officeAutoInfo = SessionUtils.getInstance(getApplicationContext()).getOfficeAutoData();
				for (int i = 0; i < officeAutoInfo.getAnswerId().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(officeAutoInfo.getAnswerId()[i]);// 试题主键
					info.setQuestionName(officeAutoInfo.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(officeAutoInfo.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(officeAutoInfo.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(officeAutoInfo.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(officeAutoInfo.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(officeAutoInfo.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(officeAutoInfo.getAnswerOptionD()[i]);// 试题选项D
					//info.setScore(SkillData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 4:
				pageId=4;
				titleTv.setText("多媒体应用技术基础");
				MultimediaInfo multimediaInfo = SessionUtils.getInstance(getApplicationContext()).getMultimediaData();
				for (int i = 0; i < multimediaInfo.getAnswerId().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(multimediaInfo.getAnswerId()[i]);// 试题主键
					info.setQuestionName(multimediaInfo.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(multimediaInfo.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(multimediaInfo.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(multimediaInfo.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(multimediaInfo.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(multimediaInfo.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(multimediaInfo.getAnswerOptionD()[i]);// 试题选项D
					//info.setOptionE(WenzhiData.answerOptionE[i]);// 试题选项E
					//info.setScore(WenzhiData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 5:
				pageId=5;
				titleTv.setText("计算机网络基础");
				NetDataList netDataList= SessionUtils.getInstance(getApplicationContext()).getNetDataList();
				for (int i = 0; i < netDataList.getAnswerId().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(netDataList.getAnswerId()[i]);// 试题主键
					info.setQuestionName(netDataList.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(netDataList.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(netDataList.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(netDataList.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(netDataList.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(netDataList.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(netDataList.getAnswerOptionD()[i]);// 试题选项D
					//info.setOptionE(WenzhiData.answerOptionE[i]);// 试题选项E
					//info.setScore(WenzhiData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 6:
				pageId=6;
				titleTv.setText("数据库技术及应用基础");
				SQLInfo sqlInfo = SessionUtils.getInstance(getApplicationContext()).getSQLdata();
				for (int i = 0; i < sqlInfo.getAnswerId().length; i++) {
					AnSwerInfo info = new AnSwerInfo();
					info.setQuestionId(sqlInfo.getAnswerId()[i]);// 试题主键
					info.setQuestionName(sqlInfo.getAnswerName()[i]);// 试题题目
					info.setQuestionType("0");// 试题类型0单选1多选
					info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
					info.setAnalysis(sqlInfo.getAnswerAnalysis()[i]);// 试题分析
					info.setCorrectAnswer(sqlInfo.getAnswerCorrect()[i]);// 正确答案
					info.setOptionA(sqlInfo.getAnswerOptionA()[i]);// 试题选项A
					info.setOptionB(sqlInfo.getAnswerOptionB()[i]);// 试题选项B
					info.setOptionC(sqlInfo.getAnswerOptionC()[i]);// 试题选项C
					info.setOptionD(sqlInfo.getAnswerOptionD()[i]);// 试题选项D
					//info.setOptionE(WenzhiData.answerOptionE[i]);// 试题选项E
					//info.setScore(WenzhiData.answerScore[i]);// 分值
					info.setOption_type("0");
					dataItems.add(info);
				}
				break;
			case 7://错题集
				pageId=7;
				titleTv.setText("错题集");
				List<ErrorQuestionInfo> errorQuestionInfos = SessionUtils.getInstance(getApplicationContext()).getErrorQuestion();
				if(errorQuestionInfos != null && errorQuestionInfos.size()>0){
					for (int i = 0; i < errorQuestionInfos.size(); i++) {
						AnSwerInfo info = new AnSwerInfo();
						info.setQuestionId(String.valueOf(i));// 试题主键
						info.setQuestionName(errorQuestionInfos.get(i).getQuestionName());// 试题题目
						info.setQuestionType("0");// 试题类型0单选1多选
						info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
						info.setAnalysis(errorQuestionInfos.get(i).getAnalysis());// 试题分析
						info.setCorrectAnswer(errorQuestionInfos.get(i).getQuestionAnswer());// 正确答案
						info.setOptionA(errorQuestionInfos.get(i).getOptionA());// 试题选项A
						info.setOptionB(errorQuestionInfos.get(i).getOptionB());// 试题选项B
						info.setOptionC(errorQuestionInfos.get(i).getOptionC());// 试题选项C
						info.setOptionD(errorQuestionInfos.get(i).getOptionD());// 试题选项D
						//info.setOptionE(WenzhiData.answerOptionE[i]);// 试题选项E
						//info.setScore(WenzhiData.answerScore[i]);// 分值
						info.setOption_type("0");
						dataItems.add(info);
					}
				}else {
					Toast.makeText(this, "您尚未有错题", Toast.LENGTH_SHORT).show();
				}

				break;
			case 8://shoucang
				pageId=8;
				titleTv.setText("收藏的试题");
				List<CollectQuesstionInfo> collectQuesstionInfos = SessionUtils.getInstance(getApplicationContext()).getCollectQuestion();
				if(collectQuesstionInfos != null && collectQuesstionInfos.size()>0){
					for (int i = 0; i < collectQuesstionInfos.size(); i++) {
						AnSwerInfo info = new AnSwerInfo();
						info.setQuestionId(String.valueOf(i));// 试题主键
						info.setQuestionName(collectQuesstionInfos.get(i).getQuestionName());// 试题题目
						info.setQuestionType("0");// 试题类型0单选1多选
						info.setQuestionFor("0");// （0模拟试题，1竞赛试题）
						info.setAnalysis(collectQuesstionInfos.get(i).getAnalysis());// 试题分析
						info.setCorrectAnswer(collectQuesstionInfos.get(i).getQuestionAnswer());// 正确答案
						info.setOptionA(collectQuesstionInfos.get(i).getOptionA());// 试题选项A
						info.setOptionB(collectQuesstionInfos.get(i).getOptionB());// 试题选项B
						info.setOptionC(collectQuesstionInfos.get(i).getOptionC());// 试题选项C
						info.setOptionD(collectQuesstionInfos.get(i).getOptionD());// 试题选项D
						//info.setOptionE(WenzhiData.answerOptionE[i]);// 试题选项E
						//info.setScore(WenzhiData.answerScore[i]);// 分值
						info.setOption_type("0");
						dataItems.add(info);
					}
				}else {
					Toast.makeText(this, "您尚未收藏试题", Toast.LENGTH_SHORT).show();
				}

				break;
			default:
				break;
		}
	}
	public void initView() {
		leftIv = (ImageView) findViewById(R.id.left);
		titleTv = (TextView) findViewById(R.id.title);
		right = (TextView) findViewById(R.id.right);

		viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
		leftIv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(collectQuesstionInfos != null && collectQuesstionInfos.size()>0){
					SessionUtils.getInstance(getApplicationContext()).saveCollect(collectQuesstionInfos);
				}
				finish();
			}
		});
	}

	private void loadData(){

		for (int i = 0; i < dataItems.size(); i++) {
			viewItems.add(getLayoutInflater().inflate(
					R.layout.vote_submit_viewpager_item, null));
		}
		pagerAdapter = new ExaminationSubmitAdapter(
				AnalogyExaminationActivity.this, viewItems,
				dataItems,imgServerUrl);
		viewPager.setAdapter(pagerAdapter);
		viewPager.getParent()
				.requestDisallowInterceptTouchEvent(false);
	}

	/**
	 * @param index
	 * 根据索引值切换页面
	 */
	public void setCurrentView(int index) {
		viewPager.setCurrentItem(index);
	}
	// 提交试卷
	public void uploadExamination(int errortopicNum) {

		String resultlist = "[";
		errortopicNums = errortopicNum;
		//从SP中取出错题集
        errorQuestionInfoList =SessionUtils.getInstance(getApplicationContext()).getErrorQuestion();
		if(errorQuestionInfoList == null){
			errorQuestionInfoList = new ArrayList<>();
		}
		errorQuestionInfoList.clear();//清空
		if(questionInfos.size()>0){
			//选择过题目
			//全部选中
			if(questionInfos.size()==dataItems.size()){
				for (int i = 0; i < questionInfos.size(); i++) {
					if (i == questionInfos.size() - 1) {
						resultlist += questionInfos.get(i).toString() + "]";
					} else {
						resultlist += questionInfos.get(i).toString() + ",";
					}
					if (questionInfos.size() == 0) {
						resultlist += "]";
					}
					//添加错题集
					if(questionInfos.get(i).getIs_correct().equals(ConstantUtil.isError)){
						ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
						errorQuestionInfo.setAnalysis(dataItems.get(i).getAnalysis());
						if(dataItems.get(i).getQuestionName().length()>0){
							String questionName = dataItems.get(i).getQuestionName().substring(2,dataItems.get(i).getQuestionName().length());
							errorQuestionInfo.setQuestionName(questionName);
						}
						errorQuestionInfo.setOptionA(dataItems.get(i).getOptionA());
						errorQuestionInfo.setOptionB(dataItems.get(i).getOptionB());
						errorQuestionInfo.setOptionC(dataItems.get(i).getOptionC());
						errorQuestionInfo.setOptionD(dataItems.get(i).getOptionD());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(i).getCorrectAnswer());
						if(!errorQuestionInfoList.contains(errorQuestionInfo)){
							errorQuestionInfoList.add(errorQuestionInfo);
						}

					}
					//分数
//					if (questionInfos.get(i).getIs_correct()
//							.equals(ConstantUtil.isCorrect)) {
//						int score = Integer.parseInt(questionInfos.get(i).getScore());
//						pageScore += score;
//					}
				}
			}else{
				//部分选中
				for (int i = 0; i < dataItems.size(); i++) {
					if(dataItems.get(i).getIsSelect()==null){

						errortopicNums1+=1;
						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(i).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(i).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(i).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(i).getScore());
						questionInfo.setIs_correct(ConstantUtil.isError);
						questionInfos.add(questionInfo);
						//添加错题集
						ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
						errorQuestionInfo.setAnalysis(dataItems.get(i).getAnalysis());
						if(dataItems.get(i).getQuestionName().length()>0){
							String questionName = dataItems.get(i).getQuestionName().substring(2,dataItems.get(i).getQuestionName().length());
							errorQuestionInfo.setQuestionName(questionName);
						}
						errorQuestionInfo.setOptionA(dataItems.get(i).getOptionA());
						errorQuestionInfo.setOptionB(dataItems.get(i).getOptionB());
						errorQuestionInfo.setOptionC(dataItems.get(i).getOptionC());
						errorQuestionInfo.setOptionD(dataItems.get(i).getOptionD());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(i).getCorrectAnswer());
						if(!errorQuestionInfoList.contains(errorQuestionInfo)){
							errorQuestionInfoList.add(errorQuestionInfo);
						}
					}
				}
				for (int i = 0; i < dataItems.size(); i++){
					if (i == dataItems.size() - 1) {
						resultlist += questionInfos.get(i).toString() + "]";
					} else {
						resultlist += questionInfos.get(i).toString() + ",";
					}
					if (dataItems.size() == 0) {
						resultlist += "]";
					}
//					if (questionInfos.get(i).getIs_correct()
//							.equals(ConstantUtil.isCorrect)) {
//						int score = Integer.parseInt(questionInfos.get(i).getScore());
//						pageScore += score;
//					}
				}
			}
		}else{
			//没有选择题目
			for (int i = 0; i < dataItems.size(); i++) {
				if(dataItems.get(i).getIsSelect()==null){
					errortopicNums1+=1;
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(i).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(i).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(i).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(i).getScore());
					questionInfo.setIs_correct(ConstantUtil.isError);
					questionInfos.add(questionInfo);
					//保存错题集
					ErrorQuestionInfo errorQuestionInfo = new ErrorQuestionInfo();
					errorQuestionInfo.setAnalysis(dataItems.get(i).getAnalysis());
					if(dataItems.get(i).getQuestionName().length()>0){
						String questionName = dataItems.get(i).getQuestionName().substring(2,dataItems.get(i).getQuestionName().length());
						errorQuestionInfo.setQuestionName(questionName);
					}
					errorQuestionInfo.setOptionA(dataItems.get(i).getOptionA());
					errorQuestionInfo.setOptionB(dataItems.get(i).getOptionB());
					errorQuestionInfo.setOptionC(dataItems.get(i).getOptionC());
					errorQuestionInfo.setOptionD(dataItems.get(i).getOptionD());
					errorQuestionInfo.setQuestionAnswer(dataItems.get(i).getCorrectAnswer());
					if(!errorQuestionInfoList.contains(errorQuestionInfo)){
						errorQuestionInfoList.add(errorQuestionInfo);
					}
				}
			}
			for (int i = 0; i < dataItems.size(); i++){
				if (i == dataItems.size() - 1) {
					resultlist += questionInfos.get(i).toString() + "]";
				} else {
					resultlist += questionInfos.get(i).toString() + ",";
				}
				if (dataItems.size() == 0) {
					resultlist += "]";
				}
//				if (questionInfos.get(i).getIs_correct()
//						.equals(ConstantUtil.isCorrect)) {
//					int score = Integer.parseInt(questionInfos.get(i).getScore());
//					pageScore += score;
//				}
			}
		}
		//System.out.println(pageScore);
		System.out.println("提交的已经选择的题目数组给后台===="+resultlist);
		SessionUtils.getInstance(getApplicationContext()).saveErrorQuestion(errorQuestionInfoList);

		Message msg = handlerSubmit.obtainMessage();
		msg.what = 1;
		handlerSubmit.sendMessage(msg);

	}
	String resultTalk="";
	// 弹出对话框通知用户提交成功
	protected void showSubmitDialog() {
		Bundle b=new Bundle();
		b.putInt("pageId", pageId);
		b.putInt("pageScore", pageScore);
		b.putInt("errorNum",errortopicNums);
		Intent intent=new Intent(AnalogyExaminationActivity.this,ActivityResultPage.class);
		intent.putExtras(b);
		startActivity(intent);
	}


}
