package com.answertest.adapter;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.answertest.AnalogyExaminationActivity;
import com.answertest.R;
import com.answertest.bean.AnSwerInfo;
import com.answertest.bean.CollectQuesstionInfo;
import com.answertest.bean.ErrorQuestionInfo;
import com.answertest.bean.SaveQuestionInfo;
import com.answertest.utils.ConstantUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExaminationSubmitAdapter extends PagerAdapter {
	final String CORRECT_ANSWER ="答对了，正确答案：";
	final String ERROR_ANSWER1 ="答错了，正确答案：";
	final String ERROR_ANSWER2 ="您的选择：";
	AnalogyExaminationActivity mContext;
	// 传递过来的页面view的集合
	List<View> viewItems;
	// 每个item的页面view
	View convertView;
	// 传递过来的所有数据
	List<AnSwerInfo> dataItems;
//	List<CollectQuesstionInfo> collectList;

	String imgServerUrl="";

	private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	private Map<Integer, Boolean> mapClick = new HashMap<Integer, Boolean>();
	private Map<Integer, String> mapMultiSelect = new HashMap<Integer, String>();



	boolean isClick=false;

	boolean isNext = false;

	StringBuffer answer=new StringBuffer();
	StringBuffer answerLast=new StringBuffer();
	StringBuffer answer1=new StringBuffer();

	int errortopicNum=0;
	String isCorrect= ConstantUtil.isCorrect;//1对，0错
	String resultA="";
	String resultB="";
	String resultC="";
	String resultD="";
	String resultE="";
	int ia=2;
	int ib=2;
	int ic=2;
	int id=2;
	int ie=2;

	public ExaminationSubmitAdapter(AnalogyExaminationActivity context, List<View> viewItems, List<AnSwerInfo> dataItems,String imgServerUrl) {
		mContext = context;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
		this.imgServerUrl = imgServerUrl;

	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container,final int position) {
		final ViewHolder holder = new ViewHolder();

		if(mContext.collectQuesstionInfos == null){
			mContext.collectQuesstionInfos = new ArrayList<>();
		}
		convertView = viewItems.get(position);
		//holder.questionType = (TextView) convertView.findViewById(R.id.activity_prepare_test_no);
		holder.question = (TextView) convertView.findViewById(R.id.activity_prepare_test_question);
		holder.nextText = (Button) convertView.findViewById(R.id.menu_bottom_nextTV);
		holder.layoutA=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_a);
		holder.layoutB=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_b);
		holder.layoutC=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_c);
		holder.layoutD=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_d);
		holder.layoutE=(LinearLayout) convertView.findViewById(R.id.activity_prepare_test_layout_e);
		holder.ivA=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a);
		holder.ivB=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b);
		holder.ivC=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c);
		holder.ivD=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d);
		holder.ivE=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e);
		holder.tvA=(TextView) convertView.findViewById(R.id.vote_submit_select_text_a);
		holder.tvB=(TextView) convertView.findViewById(R.id.vote_submit_select_text_b);
		holder.tvC=(TextView) convertView.findViewById(R.id.vote_submit_select_text_c);
		holder.tvD=(TextView) convertView.findViewById(R.id.vote_submit_select_text_d);
		holder.tvE=(TextView) convertView.findViewById(R.id.vote_submit_select_text_e);
		holder.ivA_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_a_);
		holder.ivB_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_b_);
		holder.ivC_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_c_);
		holder.ivD_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_d_);
		//holder.ivE_=(ImageView) convertView.findViewById(R.id.vote_submit_select_image_e_);
		holder.activity_tv_answer_layout = (TextView) convertView.findViewById(R.id.activity_tv_answer_layout);//答案结果
		holder.activity_prepare_test_wrongLayout  = (LinearLayout) convertView.findViewById(R.id.activity_prepare_test_wrongLayout);//错题详解
		holder.activity_prepare_test_explaindetail = (TextView) convertView.findViewById(R.id.activity_prepare_test_explaindetail);
		holder.image_add_to_collect = (ImageView) convertView.findViewById(R.id.image_add_to_collect);
		if(dataItems.get(position).getOptionA().equals("")){
			holder.layoutA.setVisibility(View.GONE);
		}if(dataItems.get(position).getOptionB().equals("")){
			holder.layoutB.setVisibility(View.GONE);
		}if(dataItems.get(position).getOptionC().equals("")){
			holder.layoutC.setVisibility(View.GONE);
		}if(dataItems.get(position).getOptionD().equals("")){
			holder.layoutD.setVisibility(View.GONE);}
//		}if(dataItems.get(position).getOptionE().equals("")){
//			holder.layoutE.setVisibility(View.GONE);
//		}

		//鍒ゆ柇鏄惁鏂囧瓧鍥剧墖棰樼洰
		//鏂囧瓧棰樼洰
		holder.ivA_.setVisibility(View.GONE);
		holder.ivB_.setVisibility(View.GONE);
		holder.ivC_.setVisibility(View.GONE);
		holder.ivD_.setVisibility(View.GONE);
		//holder.ivE_.setVisibility(View.GONE);
		holder.tvA.setVisibility(View.VISIBLE);
		holder.tvB.setVisibility(View.VISIBLE);
		holder.tvC.setVisibility(View.VISIBLE);
		holder.tvD.setVisibility(View.VISIBLE);
	//	holder.tvE.setVisibility(View.VISIBLE);
		holder.tvA.setText(dataItems.get(position).getOptionA());
		holder.tvB.setText(dataItems.get(position).getOptionB());
		holder.tvC.setText(dataItems.get(position).getOptionC());
		holder.tvD.setText(dataItems.get(position).getOptionD());
	//	holder.tvE.setText(dataItems.get(position).getOptionE());


		//单选题
		//判断题型
		if(dataItems.get(position).getQuestionType().equals("0")){
			//单选题
			holder.question.setText("(单选题)"+dataItems.get(position).getQuestionName()+"   "+(position+1)+"/"+dataItems.size());
			//给文字设置样式
			SpannableStringBuilder builder = new SpannableStringBuilder(holder.question.getText().toString());
			//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
			ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.parseColor("#0495E0"));
			builder.setSpan(blueSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.question.setText(builder);

			holder.layoutA.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					map.put(position, true);
					holder.ivA.setImageResource(R.mipmap.test_select_a);
					holder.tvA.setTextColor(Color.parseColor("#0495E0"));
					holder.ivB.setImageResource(R.mipmap.test_b);
					holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivC.setImageResource(R.mipmap.test_c);
					holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivD.setImageResource(R.mipmap.test_d);
					holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivE.setImageResource(R.mipmap.test_e);
					holder.tvE.setTextColor(Color.parseColor("#9a9a9a"));
					holder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
					if(dataItems.get(position).getCorrectAnswer().contains("A")){
						isCorrect=ConstantUtil.isCorrect;
					holder.activity_tv_answer_layout.setText(CORRECT_ANSWER+"A");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));

					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						holder.activity_tv_answer_layout.setText(ERROR_ANSWER1+dataItems.get(position).getCorrectAnswer()+","+
						ERROR_ANSWER2+"A");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
					}
					holder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
					holder.activity_prepare_test_explaindetail.setText(dataItems.get(position).getAnalysis());//答题详解

					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
					dataItems.get(position).setIsSelect("0");

				}
			});
			holder.layoutB.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					map.put(position, true);
					holder.ivB.setImageResource(R.mipmap.test_select_b);
					holder.tvB.setTextColor(Color.parseColor("#0495E0"));
					holder.ivA.setImageResource(R.mipmap.test_a);
					holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivC.setImageResource(R.mipmap.test_c);
					holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivD.setImageResource(R.mipmap.test_d);
					holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivE.setImageResource(R.mipmap.test_e);
					holder.tvE.setTextColor(Color.parseColor("#9a9a9a"));
					holder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
					if(dataItems.get(position).getCorrectAnswer().contains("B")){
						isCorrect=ConstantUtil.isCorrect;
						holder.activity_tv_answer_layout.setText(CORRECT_ANSWER+"B");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						holder.activity_tv_answer_layout.setText(ERROR_ANSWER1+dataItems.get(position).getCorrectAnswer()+","+
								ERROR_ANSWER2+"B");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
					}
					holder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
					holder.activity_prepare_test_explaindetail.setText(dataItems.get(position).getAnalysis());//答题详解
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
					dataItems.get(position).setIsSelect("0");
				}
			});
			holder.layoutC.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					map.put(position, true);
					holder.ivC.setImageResource(R.mipmap.test_select_c);
					holder.tvC.setTextColor(Color.parseColor("#0495E0"));
					holder.ivB.setImageResource(R.mipmap.test_b);
					holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivA.setImageResource(R.mipmap.test_a);
					holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivD.setImageResource(R.mipmap.test_d);
					holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivE.setImageResource(R.mipmap.test_e);
					holder.tvE.setTextColor(Color.parseColor("#9a9a9a"));
					holder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
					if(dataItems.get(position).getCorrectAnswer().contains("C")){
						isCorrect=ConstantUtil.isCorrect;
						holder.activity_tv_answer_layout.setText(CORRECT_ANSWER+"C");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						holder.activity_tv_answer_layout.setText(ERROR_ANSWER1+dataItems.get(position).getCorrectAnswer()+","+
								ERROR_ANSWER2+"C");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
					}
					holder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
					holder.activity_prepare_test_explaindetail.setText(dataItems.get(position).getAnalysis());//答题详解
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
					dataItems.get(position).setIsSelect("0");
				}
			});
			holder.layoutD.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					map.put(position, true);
					holder.ivD.setImageResource(R.mipmap.test_select_d);
					holder.tvD.setTextColor(Color.parseColor("#0495E0"));
					holder.ivB.setImageResource(R.mipmap.test_b);
					holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivC.setImageResource(R.mipmap.test_c);
					holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivA.setImageResource(R.mipmap.test_a);
					holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivE.setImageResource(R.mipmap.test_e);
					holder.tvE.setTextColor(Color.parseColor("#9a9a9a"));
					holder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
					if(dataItems.get(position).getCorrectAnswer().contains("D")){
						isCorrect=ConstantUtil.isCorrect;
						holder.activity_tv_answer_layout.setText(CORRECT_ANSWER+"D");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						holder.activity_tv_answer_layout.setText(ERROR_ANSWER1+dataItems.get(position).getCorrectAnswer()+","+
								ERROR_ANSWER2+"D");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
					}
					holder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
					holder.activity_prepare_test_explaindetail.setText(dataItems.get(position).getAnalysis());//答题详解
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
					dataItems.get(position).setIsSelect("0");
				}
			});
			holder.layoutE.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					map.put(position, true);
					holder.ivE.setImageResource(R.mipmap.test_select_e);
					holder.tvE.setTextColor(Color.parseColor("#0495E0"));
					holder.ivB.setImageResource(R.mipmap.test_b);
					holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivC.setImageResource(R.mipmap.test_c);
					holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivD.setImageResource(R.mipmap.test_d);
					holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
					holder.ivA.setImageResource(R.mipmap.test_a);
					holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
					holder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
					if(dataItems.get(position).getCorrectAnswer().contains("E")){
						isCorrect=ConstantUtil.isCorrect;
						holder.activity_tv_answer_layout.setText(CORRECT_ANSWER+"E");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));
					}else{
						isCorrect=ConstantUtil.isError;
						errortopicNum+=1;
						holder.activity_tv_answer_layout.setText(ERROR_ANSWER1+dataItems.get(position).getCorrectAnswer()+","+
								ERROR_ANSWER2+"E");
						holder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
					}
					holder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
					holder.activity_prepare_test_explaindetail.setText(dataItems.get(position).getAnalysis());//答题详解
					//保存数据
					SaveQuestionInfo questionInfo=new SaveQuestionInfo();
					questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
					questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
					questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
					questionInfo.setScore(dataItems.get(position).getScore());
					questionInfo.setIs_correct(isCorrect);
					mContext.questionInfos.add(questionInfo);
					dataItems.get(position).setIsSelect("0");
				}
			});
		}else if(dataItems.get(position).getQuestionType().equals("1")){

			//多选题
			holder.question.setText("(多选题)"+dataItems.get(position).getQuestionName()+"   "+(position+1)+"/"+dataItems.size());
			SpannableStringBuilder builder = new SpannableStringBuilder(holder.question.getText().toString());
			//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
			ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.parseColor("#0495E0"));
			builder.setSpan(blueSpan, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.question.setText(builder);

			holder.layoutA.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mapClick.put(position, true);
					if(ia==1){
						ia=2;
						holder.ivA.setImageResource(R.mipmap.test_a);
						holder.tvA.setTextColor(Color.parseColor("#9a9a9a"));
					}else{
						ia=1;
						holder.ivA.setImageResource(R.mipmap.test_select_a);
						holder.tvA.setTextColor(Color.parseColor("#0495E0"));
					}
					if(dataItems.get(position).getCorrectAnswer().contains("A")){
						isCorrect=ConstantUtil.isCorrect;
						if(position==viewItems.size()-1){
							answerLast.append("A");
						}else{
							answer.append("A");
						}
					}else{
						isCorrect=ConstantUtil.isError;
						mapMultiSelect.put(position, isCorrect);
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestionName());
						errorQuestionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("A");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getAnalysis());

						map.put(position, true);

						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(position).getScore());
						questionInfo.setIs_correct(isCorrect);
						mContext.questionInfos.add(questionInfo);
						dataItems.get(position).setIsSelect("0");
					}
					resultA="A";
				}
			});
			holder.layoutB.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					mapClick.put(position, true);

					if(ib==1){
						ib=2;
						holder.ivB.setImageResource(R.mipmap.test_b);
						holder.tvB.setTextColor(Color.parseColor("#9a9a9a"));
					}else{
						ib=1;
						holder.ivB.setImageResource(R.mipmap.test_select_b);
						holder.tvB.setTextColor(Color.parseColor("#0495E0"));
					}
					if(dataItems.get(position).getCorrectAnswer().contains("B")){
						isCorrect=ConstantUtil.isCorrect;
						if(position==viewItems.size()-1){
							answerLast.append("B");
						}else{
							answer.append("B");
						}
					}else{
						isCorrect=ConstantUtil.isError;
						mapMultiSelect.put(position, isCorrect);
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestionName());
						errorQuestionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("B");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getAnalysis());
						map.put(position, true);
						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(position).getScore());
						questionInfo.setIs_correct(isCorrect);
						mContext.questionInfos.add(questionInfo);
						dataItems.get(position).setIsSelect("0");
					}
					resultB="B";
				}
			});
			holder.layoutC.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mapClick.put(position, true);

					if(ic==1){
						ic=2;
						holder.ivC.setImageResource(R.mipmap.test_c);
						holder.tvC.setTextColor(Color.parseColor("#9a9a9a"));
					}else{
						ic=1;
						holder.ivC.setImageResource(R.mipmap.test_select_c);
						holder.tvC.setTextColor(Color.parseColor("#0495E0"));
					}

					if(dataItems.get(position).getCorrectAnswer().contains("C")){
						isCorrect=ConstantUtil.isCorrect;
						if(position==viewItems.size()-1){
							answerLast.append("C");
						}else{
							answer.append("C");
						}
					}else{
						isCorrect=ConstantUtil.isError;
						mapMultiSelect.put(position, isCorrect);
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestionName());
						errorQuestionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("C");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getAnalysis());
						map.put(position, true);

						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(position).getScore());
						questionInfo.setIs_correct(isCorrect);
						mContext.questionInfos.add(questionInfo);
						dataItems.get(position).setIsSelect("0");
					}
					resultC="C";
				}
			});
			holder.layoutD.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mapClick.put(position, true);
					if(id==1){
						id=2;
						holder.ivD.setImageResource(R.mipmap.test_d);
						holder.tvD.setTextColor(Color.parseColor("#9a9a9a"));
					}else{
						id=1;
						holder.ivD.setImageResource(R.mipmap.test_select_d);
						holder.tvD.setTextColor(Color.parseColor("#0495E0"));
					}
					if(dataItems.get(position).getCorrectAnswer().contains("D")){
						isCorrect=ConstantUtil.isCorrect;
						if(position==viewItems.size()-1){
							answerLast.append("D");
						}else{
							answer.append("D");
						}
					}else{
						isCorrect=ConstantUtil.isError;
						mapMultiSelect.put(position, isCorrect);
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestionName());
						errorQuestionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("D");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getAnalysis());

						map.put(position, true);

						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(position).getScore());
						questionInfo.setIs_correct(isCorrect);
						mContext.questionInfos.add(questionInfo);
						dataItems.get(position).setIsSelect("0");
					}
					resultD="D";
				}
			});
			holder.layoutE.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					mapClick.put(position, true);

					if(ie==1){
						ie=2;
						holder.ivE.setImageResource(R.mipmap.test_e);
						holder.tvE.setTextColor(Color.parseColor("#9a9a9a"));
					}else{
						ie=1;
						holder.ivE.setImageResource(R.mipmap.test_select_e);
						holder.tvE.setTextColor(Color.parseColor("#0495E0"));
					}
					if(dataItems.get(position).getCorrectAnswer().contains("E")){
						isCorrect=ConstantUtil.isCorrect;
						if(position==viewItems.size()-1){
							answerLast.append("E");
						}else{
							answer.append("E");
						}
					}else{
						isCorrect=ConstantUtil.isError;
						mapMultiSelect.put(position, isCorrect);
						errortopicNum+=1;
						//自动添加错误题目
						ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
						errorQuestionInfo.setQuestionName(dataItems.get(position).getQuestionName());
						errorQuestionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						errorQuestionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
						errorQuestionInfo.setIsRight(isCorrect);
						errorQuestionInfo.setQuestionSelect("E");
						errorQuestionInfo.setAnalysis(dataItems.get(position).getAnalysis());
						map.put(position, true);


						//保存数据
						SaveQuestionInfo questionInfo=new SaveQuestionInfo();
						questionInfo.setQuestionId(dataItems.get(position).getQuestionId());
						questionInfo.setQuestionType(dataItems.get(position).getQuestionType());
						questionInfo.setRealAnswer(dataItems.get(position).getCorrectAnswer());
						questionInfo.setScore(dataItems.get(position).getScore());
						questionInfo.setIs_correct(isCorrect);
						mContext.questionInfos.add(questionInfo);
						dataItems.get(position).setIsSelect("0");
					}
					resultE="E";
				}
			});
		}
		// 最后一页修改"下一步"按钮文字
		if (position == viewItems.size() - 1) {
			holder.nextText.setText("提交");
			//			holder.nextImage.setImageResource(R.drawable.vote_submit_finish);
		}
		holder.nextText.setOnClickListener(new LinearOnClickListener(position + 1, true,position,holder));
		holder.image_add_to_collect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				view.setSelected(!view.isSelected());
				CollectQuesstionInfo collectQuesstionInfo =new CollectQuesstionInfo();
				String titlestr = dataItems.get(position).getQuestionName().substring(2,dataItems.get(position).getQuestionName().length());
				collectQuesstionInfo.setQuestionName(titlestr);
				collectQuesstionInfo.setAnalysis(dataItems.get(position).getAnalysis());
				collectQuesstionInfo.setOptionA(dataItems.get(position).getOptionA());
				collectQuesstionInfo.setOptionB(dataItems.get(position).getOptionB());
				collectQuesstionInfo.setOptionC(dataItems.get(position).getOptionC());
				collectQuesstionInfo.setOptionD(dataItems.get(position).getOptionD());
				collectQuesstionInfo.setQuestionAnswer(dataItems.get(position).getCorrectAnswer());
				if(view.isSelected()){
					if(!mContext.collectQuesstionInfos.contains(collectQuesstionInfo)){
						mContext.collectQuesstionInfos.add(collectQuesstionInfo);
						Toast.makeText(mContext, "收藏成功", Toast.LENGTH_SHORT).show();
					}
				}else {
					for(CollectQuesstionInfo c : mContext.collectQuesstionInfos){
						if(c.getQuestionName().equals(collectQuesstionInfo.getQuestionName())){
							mContext.collectQuesstionInfos.remove(collectQuesstionInfo);
							Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
						}
					}

				}
			}

		});

		container.addView(viewItems.get(position));
		return viewItems.get(position);
	}
	/**
	 * @author  设置上一步和下一步按钮监听
	 *
	 */
	class LinearOnClickListener implements OnClickListener {

		private int mPosition;
		private int mPosition1;
		private boolean mIsNext;
		private ViewHolder viewHolder;

		public LinearOnClickListener(int position, boolean mIsNext,int position1,ViewHolder viewHolder) {
			mPosition = position;
			mPosition1 = position1;
			this.viewHolder = viewHolder;
			this.mIsNext = mIsNext;
		}

		@Override
		public void onClick(View arg0) {
			ia=2;ib=2;ic=2;id=2;ie=2;
			if (mPosition == viewItems.size()) {
				//单选
				if(dataItems.get(mPosition1).getQuestionType().equals("0")){
					if(!map.containsKey(mPosition1)){
						Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
						return;
					}
					mContext.uploadExamination(errortopicNum);
				}else if(dataItems.get(mPosition1).getQuestionType().equals("1")){
					//判断多选时的点击
					if(!map.containsKey(mPosition1)){
						if(!mapClick.containsKey(mPosition1)){
							Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
							return;
						}
					}
					map.put(mPosition1, true);
//					viewHolder.activity_tv_answer_layout.setVisibility(View.VISIBLE);
//					viewHolder.activity_prepare_test_wrongLayout.setVisibility(View.VISIBLE);
//					viewHolder.activity_prepare_test_explaindetail.setText(dataItems.get(mPosition1).getAnalysis());//答题详解
					if(mapMultiSelect.containsKey(mPosition1)){
						//提交答题
						mContext.uploadExamination(errortopicNum);
					}else{
						Toast.makeText(mContext, "注意多选!", Toast.LENGTH_SHORT).show();
						String ssStr=dataItems.get(mPosition1).getCorrectAnswer();
						ssStr=ssStr.replace("|", "");

						if(mPosition == viewItems.size()){
							if(answerLast.toString().contains("A")){
								answer1.append("A");
							}if(answerLast.toString().contains("B")){
								answer1.append("B");
							}if(answerLast.toString().contains("C")){
								answer1.append("C");
							}if(answerLast.toString().contains("D")){
								answer1.append("D");
							}if(answerLast.toString().contains("E")){
								answer1.append("E");
							}

						}else{
							if(answer.toString().contains("A")){
								answer1.append("A");
							}if(answer.toString().contains("B")){
								answer1.append("B");
							}if(answer.toString().contains("C")){
								answer1.append("C");
							}if(answer.toString().contains("D")){
								answer1.append("D");
							}if(answer.toString().contains("E")){
								answer1.append("E");
							}
						}

						if(answer1.toString().equals(ssStr)){
//							viewHolder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_right_text));
//							viewHolder.activity_tv_answer_layout.setText(CORRECT_ANSWER+answer1);
							//清除答案
							answer.delete(0, answer.length());
							answer1.delete(0, answer1.length());
							isCorrect=ConstantUtil.isCorrect;
							mapMultiSelect.put(mPosition1, ConstantUtil.isCorrect);
							//保存数据
							SaveQuestionInfo questionInfo=new SaveQuestionInfo();
							questionInfo.setQuestionId(dataItems.get(mPosition1).getQuestionId());
							questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
							questionInfo.setRealAnswer(dataItems.get(mPosition1).getCorrectAnswer());
							questionInfo.setScore(dataItems.get(mPosition1).getScore());
							questionInfo.setIs_correct(isCorrect);
							mContext.questionInfos.add(questionInfo);
							dataItems.get(mPosition1).setIsSelect("0");
							//提交答题
							mContext.uploadExamination(errortopicNum);
						}else{
//							viewHolder.activity_tv_answer_layout.setTextColor(mContext.getResources().getColor(R.color.practice_test_result_wrong_text));
//							viewHolder.activity_tv_answer_layout.setText(ERROR_ANSWER1+ssStr+","+
//									ERROR_ANSWER2+answer1);
							//清除答案
							answer.delete(0, answer.length());
							answer1.delete(0, answer1.length());
							errortopicNum+=1;
							isCorrect=ConstantUtil.isError;
							//自动添加错误题目
							ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
							errorQuestionInfo.setQuestionName(dataItems.get(mPosition1).getQuestionName());
							errorQuestionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
							errorQuestionInfo.setQuestionAnswer(dataItems.get(mPosition1).getCorrectAnswer());
							errorQuestionInfo.setIsRight(isCorrect);
							errorQuestionInfo.setQuestionSelect(answer.toString());
							errorQuestionInfo.setAnalysis(dataItems.get(mPosition1).getAnalysis());
							isCorrect=ConstantUtil.isError;
							mapMultiSelect.put(mPosition1, ConstantUtil.isError);

							//保存数据
							SaveQuestionInfo questionInfo=new SaveQuestionInfo();
							questionInfo.setQuestionId(dataItems.get(mPosition1).getQuestionId());
							questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
							questionInfo.setRealAnswer(dataItems.get(mPosition1).getCorrectAnswer());
							questionInfo.setScore(dataItems.get(mPosition1).getScore());
							questionInfo.setIs_correct(isCorrect);
							mContext.questionInfos.add(questionInfo);
							dataItems.get(mPosition1).setIsSelect("0");

						}

					}
				}else{
					if(!map.containsKey(mPosition1)){
						Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
						return;
					}
					mContext.uploadExamination(errortopicNum);
				}
			} else {
				if(mPosition ==-1){
					Toast.makeText(mContext, "已经是第一页", Toast.LENGTH_SHORT).show();
					return;
				}else{
					//单选
					if(dataItems.get(mPosition1).getQuestionType().equals("0")){
						if(mIsNext){
							if(!map.containsKey(mPosition1)){
								Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
								return;
							}
						}
						isNext = mIsNext;
						mContext.setCurrentView(mPosition);
					}else if(dataItems.get(mPosition1).getQuestionType().equals("1")){
						if(mIsNext){
							//判断多选时的点击
							if(!map.containsKey(mPosition1)){
								if(!mapClick.containsKey(mPosition1)){
									Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
									return;
								}
							}
							map.put(mPosition1, true);

							if(mapMultiSelect.containsKey(mPosition1)){
								//清除答案
								answer.delete(0, answer.length());
								//选过的，直接跳转下一题
								isNext = mIsNext;
								mContext.setCurrentView(mPosition);
							}else{
								Toast.makeText(mContext, "注意多选!", Toast.LENGTH_SHORT).show();
								String ssStr=dataItems.get(mPosition1).getCorrectAnswer();
								ssStr=ssStr.replace("|", "");
								if(answer.toString().contains("A")){
									answer1.append("A");
								}if(answer.toString().contains("B")){
									answer1.append("B");
								}if(answer.toString().contains("C")){
									answer1.append("C");
								}if(answer.toString().contains("D")){
									answer1.append("D");
								}if(answer.toString().contains("E")){
									answer1.append("E");
								}
								if(answer1.toString().equals(ssStr)){
									//清除答案
									answer.delete(0, answer.length());
									answer1.delete(0, answer1.length());
									isCorrect=ConstantUtil.isCorrect;
									mapMultiSelect.put(mPosition1, ConstantUtil.isCorrect);
									//保存数据
									SaveQuestionInfo questionInfo=new SaveQuestionInfo();
									questionInfo.setQuestionId(dataItems.get(mPosition1).getQuestionId());
									questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
									questionInfo.setRealAnswer(dataItems.get(mPosition1).getCorrectAnswer());
									questionInfo.setScore(dataItems.get(mPosition1).getScore());
									questionInfo.setIs_correct(isCorrect);
									mContext.questionInfos.add(questionInfo);
									dataItems.get(mPosition1).setIsSelect("0");
									isNext = mIsNext;
									mContext.setCurrentView(mPosition);
								}else{
									//清除答案
									answer.delete(0, answer.length());
									answer1.delete(0, answer1.length());
									errortopicNum+=1;
									isCorrect=ConstantUtil.isError;
									//自动添加错误题目
									ErrorQuestionInfo errorQuestionInfo=new ErrorQuestionInfo();
									errorQuestionInfo.setQuestionName(dataItems.get(mPosition1).getQuestionName());
									errorQuestionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
									errorQuestionInfo.setQuestionAnswer(dataItems.get(mPosition1).getCorrectAnswer());
									errorQuestionInfo.setIsRight(isCorrect);
									errorQuestionInfo.setQuestionSelect(answer.toString());
									errorQuestionInfo.setAnalysis(dataItems.get(mPosition1).getAnalysis());
									isCorrect=ConstantUtil.isError;
									mapMultiSelect.put(mPosition1, ConstantUtil.isError);

									//保存数据
									SaveQuestionInfo questionInfo=new SaveQuestionInfo();
									questionInfo.setQuestionId(dataItems.get(mPosition1).getQuestionId());
									questionInfo.setQuestionType(dataItems.get(mPosition1).getQuestionType());
									questionInfo.setRealAnswer(dataItems.get(mPosition1).getCorrectAnswer());
									questionInfo.setScore(dataItems.get(mPosition1).getScore());
									questionInfo.setIs_correct(isCorrect);
									mContext.questionInfos.add(questionInfo);
									dataItems.get(mPosition1).setIsSelect("0");
								}
							}
						}else{
							mContext.setCurrentView(mPosition);
						}

					}else{
						if(mIsNext){
							if(!map.containsKey(mPosition1)){
								Toast.makeText(mContext, "请选择选项", Toast.LENGTH_SHORT).show();
								return;
							}
						}
						isNext = mIsNext;
						mContext.setCurrentView(mPosition);
					}
				}
			}

		}
	}
	@Override
	public int getCount() {
		if (viewItems == null)
			return 0;
		return viewItems.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	public class ViewHolder {
		//TextView questionType;
		TextView question;
		TextView cur_number;
		LinearLayout previousBtn, nextBtn,errorBtn;
		Button nextText;
		TextView totalText;
		ImageView nextImage;
		LinearLayout wrongLayout;
		TextView explaindetailTv;
		LinearLayout layoutA;
		LinearLayout layoutB;
		LinearLayout layoutC;
		LinearLayout layoutD;
		LinearLayout layoutE;
		ImageView ivA;
		ImageView ivB;
		ImageView ivC;
		ImageView ivD;
		ImageView ivE;
		TextView tvA;
		TextView tvB;
		TextView tvC;
		TextView tvD;
		TextView tvE;
		ImageView ivA_;
		ImageView ivB_;
		ImageView ivC_;
		ImageView ivD_;
		ImageView ivE_;
		TextView activity_tv_answer_layout;//答案是否正确
		LinearLayout activity_prepare_test_wrongLayout;//错题详解
		TextView activity_prepare_test_explaindetail;
		ImageView image_add_to_collect;//添加到收藏
	}

}
