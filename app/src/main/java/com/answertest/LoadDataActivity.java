package com.answertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.answertest.bean.SQLInfo;
import com.answertest.constant.FileConstant;
import com.answertest.bean.InformationAndComputerInfo;
import com.answertest.bean.MultimediaInfo;
import com.answertest.bean.NetDataList;
import com.answertest.bean.OfficeAutoInfo;
import com.answertest.bean.WindowInfo;
import com.answertest.utils.SessionUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LoadDataActivity extends Activity {
    private List<String> infoList  = new ArrayList<>();
    private List<String> multimediaList = new ArrayList<>();
    private List<String> netList = new ArrayList<>();
    private List<String> officeautoList = new ArrayList<>();
    private List<String> sqlList = new ArrayList<>();
    private List<String> windowList = new ArrayList<>();
    String answerName[] = new String[50];//每章30题
    String answerId[] = new String [50];
    String answerOptionA[] = new String[50];
    String answerOptionB[] = new String[50];
    String answerOptionC[] =new String[50];
    String answerOptionD[] = new String[50];
    String answerAnalysis[] = new String[50];
    String answerCorrect[] = new String[50];
    private InformationAndComputerInfo indata;
    private MultimediaInfo multimediaInfo;
    private  NetDataList netDataList;
    private OfficeAutoInfo autoData;
    private SQLInfo sqlInfo;
    private WindowInfo windowInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        judge();

    }
    private void judge(){
       boolean state =  SessionUtils.getInstance(getApplicationContext()).getLoginState();
        if(state){
            Intent intent = new Intent(LoadDataActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            initData();
            initInfo();
            initMultimedia();
            initNetList();
            initOfficeAuto();
            initSQL();
            initWindowList();
            saveState();
            Intent intent = new Intent(LoadDataActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
    private void initData(){
        infoList = convertCodeAndGetText(FileConstant.INFORMATION_FILE);
        multimediaList = convertCodeAndGetText(FileConstant.MULTIMEDIA_FILE);
        netList = convertCodeAndGetText(FileConstant.NET_FILE);
        officeautoList = convertCodeAndGetText(FileConstant.OFFICE_AUTO_FILE);
        sqlList = convertCodeAndGetText(FileConstant.SQL_FILE);
        windowList = convertCodeAndGetText(FileConstant.WINDOW_FILE);
    }
    private void initInfo(){
        initList(infoList);
        indata = new InformationAndComputerInfo();
        indata.setAnswerId(answerId);
        indata.setAnswerName(answerName);
        indata.setAnswerCorrect(answerCorrect);
        indata.setAnswerAnalysis(answerAnalysis);
        indata.setAnswerOptionA(answerOptionA);
        indata.setAnswerOptionB(answerOptionB);
        indata.setAnswerOptionC(answerOptionC);
        indata.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).saveInformationAndComputerData(indata);
    }
    private void initMultimedia(){

        initList(multimediaList);
        multimediaInfo = new MultimediaInfo();
        multimediaInfo.setAnswerId(answerId);
        multimediaInfo.setAnswerName(answerName);
        multimediaInfo.setAnswerCorrect(answerCorrect);
        multimediaInfo.setAnswerAnalysis(answerAnalysis);
        multimediaInfo.setAnswerOptionA(answerOptionA);
        multimediaInfo.setAnswerOptionB(answerOptionB);
        multimediaInfo.setAnswerOptionC(answerOptionC);
        multimediaInfo.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).saveMultimediaData(multimediaInfo);
    }
    private void initNetList(){
        initList(netList);
        netDataList =new NetDataList();
        netDataList.setAnswerId(answerId);
        netDataList.setAnswerName(answerName);
        netDataList.setAnswerCorrect(answerCorrect);
        netDataList.setAnswerAnalysis(answerAnalysis);
        netDataList.setAnswerOptionA(answerOptionA);
        netDataList.setAnswerOptionB(answerOptionB);
        netDataList.setAnswerOptionC(answerOptionC);
        netDataList.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).saveNetData(netDataList);
    }
    private void initOfficeAuto(){
        initList(officeautoList);
        autoData = new OfficeAutoInfo();
        autoData.setAnswerId(answerId);
        autoData.setAnswerName(answerName);
        autoData.setAnswerCorrect(answerCorrect);
        autoData.setAnswerAnalysis(answerAnalysis);
        autoData.setAnswerOptionA(answerOptionA);
        autoData.setAnswerOptionB(answerOptionB);
        autoData.setAnswerOptionC(answerOptionC);
        autoData.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).saveOfficeAutoData(autoData);
    }
    private void initSQL(){
    initList(sqlList);
        sqlInfo = new SQLInfo();
        sqlInfo.setAnswerId(answerId);
        sqlInfo.setAnswerName(answerName);
        sqlInfo.setAnswerCorrect(answerCorrect);
        sqlInfo.setAnswerAnalysis(answerAnalysis);
        sqlInfo.setAnswerOptionA(answerOptionA);
        sqlInfo.setAnswerOptionB(answerOptionB);
        sqlInfo.setAnswerOptionC(answerOptionC);
        sqlInfo.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).savaSQLData(sqlInfo);
    }
    private void initWindowList(){
    initList(windowList);
        windowInfo = new WindowInfo();
        windowInfo.setAnswerId(answerId);
        windowInfo.setAnswerName(answerName);
        windowInfo.setAnswerCorrect(answerCorrect);
        windowInfo.setAnswerAnalysis(answerAnalysis);
        windowInfo.setAnswerOptionA(answerOptionA);
        windowInfo.setAnswerOptionB(answerOptionB);
        windowInfo.setAnswerOptionC(answerOptionC);
        windowInfo.setAnswerOptionD(answerOptionD);
        SessionUtils.getInstance(getApplicationContext()).saveWindowData(windowInfo);

    }
    private void initList(List<String> datas){
        int title = 0;
        int correct =0;
        int analysis = 0;
        int optionA = 0;
        int optionB = 0;
        int optionC = 0;
        int optionD = 0;
        for(int i = 0 ; i<datas.size() ; i++){

            if(i %7 == 0 && title<50 ){
                answerId[title] =String.valueOf(title+1);
                answerName[title] = datas.get(i).trim();
                Log.d("info", "answerName: "+answerName[title]);
                title ++;
            }

            if(i % 7 == 1 && correct<50 ){
                answerCorrect[correct] = datas.get(i).trim();
                Log.d("info", "answerCorrect: "+answerCorrect[correct]);
                correct ++;
            }

            if(i %7 == 2  && analysis<50){
                answerAnalysis[analysis] = datas.get(i).trim();
                Log.d("info", "answerAnalysis: "+ answerAnalysis[analysis] );
                analysis ++;
            }

            if(i % 7 == 3 && optionA<50){
                answerOptionA[optionA] = datas.get(i).trim();
                Log.d("info", "answerOptionA: "+answerOptionA[optionA] );
                optionA ++;
            }

            if(i % 7 == 4 && optionB <50){
                answerOptionB[optionB] = datas.get(i).trim();
                Log.d("info", "answerOptionB: "+ answerOptionB[optionB] );
                optionB ++;
            }

            if(i % 7 == 5 && optionC<50){
                answerOptionC[optionC] = datas.get(i).trim();
                Log.d("info", "answerOptionC: "+ answerOptionC[optionC] );
                optionC ++;
            }

            if(i % 7 == 6 && optionD<50){
                answerOptionD[optionD] = datas.get(i).trim();
                Log.d("info", "answerOptionD: "+ answerOptionD[optionD] );
                optionD ++;
            }
        }
    }
    public List<String> convertCodeAndGetText(String str_filepath) {// 转码
        //File file = new File(str_filepath);
        List<String> contents  = new ArrayList<>();
        BufferedReader reader;
        String text = "";
        try {
            //FileInputStream fis = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(getAssets().open(str_filepath));
            in.mark(4);
            byte[] first3bytes = new byte[3];
            in.read(first3bytes);//找到文档的前三个字节并自动判断文档类型。
            in.reset();
            if (first3bytes[0] == (byte) 0xEF && first3bytes[1] == (byte) 0xBB
                    && first3bytes[2] == (byte) 0xBF) {// utf-8

                reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

            } else if (first3bytes[0] == (byte) 0xFF
                    && first3bytes[1] == (byte) 0xFE) {

                reader = new BufferedReader(
                        new InputStreamReader(in, "unicode"));
            } else if (first3bytes[0] == (byte) 0xFE
                    && first3bytes[1] == (byte) 0xFF) {

                reader = new BufferedReader(new InputStreamReader(in,
                        "utf-16be"));
            } else if (first3bytes[0] == (byte) 0xFF
                    && first3bytes[1] == (byte) 0xFF) {

                reader = new BufferedReader(new InputStreamReader(in,
                        "utf-16le"));
            } else {

                reader = new BufferedReader(new InputStreamReader(in, "GBK"));
            }
            String str = reader.readLine();
            while (str != null) {
                text = text + str + "/n";
                contents.add(str);
                Log.d("read", "convertCodeAndGetText: "+str);
                str = reader.readLine();
            }
            in.close();
            reader.close();
            //fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
    private void saveState(){
        if(indata!=null && multimediaInfo !=null
            &&netDataList != null && autoData !=null
            && sqlInfo != null && windowInfo != null){
        SessionUtils.getInstance(getApplicationContext()).saveLoginState();
    }}
}
