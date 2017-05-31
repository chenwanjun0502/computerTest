package com.answertest.utils;

import android.content.Context;

import com.answertest.bean.CollectQuesstionInfo;
import com.answertest.bean.ErrorQuestionInfo;
import com.answertest.bean.InformationAndComputerInfo;
import com.answertest.bean.MultimediaInfo;
import com.answertest.bean.NetDataList;
import com.answertest.bean.OfficeAutoInfo;
import com.answertest.bean.SQLInfo;
import com.answertest.bean.WindowInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by CWJ on 2016/12/19.
 */

public class SessionUtils {
    private static SessionUtils instance;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private static final String xmlFileName="hechamall";
    public static SessionUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SessionUtils();
        }
        if (instance.sharedPreferencesUtils == null) {
            instance.sharedPreferencesUtils = SharedPreferencesUtils.getInstance(context);
        }
        return instance;
    }

    public InformationAndComputerInfo getInformationAndComputerData() {
        String userInfoStr = instance.sharedPreferencesUtils.getStringSP(xmlFileName, "infoComputer");
        Gson gson = new Gson();
        return gson.fromJson(userInfoStr, InformationAndComputerInfo.class);
    }

    public void saveInformationAndComputerData(InformationAndComputerInfo infor) {
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName, "infoComputer", gson.toJson(infor));
    }
    public MultimediaInfo getMultimediaData(){
        String mutimediaData = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"multimedia");
        Gson gson = new Gson();
        return gson.fromJson(mutimediaData,MultimediaInfo.class);
    }
    public void saveMultimediaData(MultimediaInfo multimediaInfo){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"multimedia",gson.toJson(multimediaInfo));
    }

    public NetDataList getNetDataList(){
        String netData = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"netData");
        Gson gson = new Gson();
       return gson.fromJson(netData,NetDataList.class);
    }
    public void saveNetData(NetDataList netDataList){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"netData",gson.toJson(netDataList));
    }
    public OfficeAutoInfo getOfficeAutoData(){
        String officeAutoData  = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"officeAuto");
        Gson gson = new Gson();
        return gson.fromJson(officeAutoData,OfficeAutoInfo.class);
    }
    public void saveOfficeAutoData(OfficeAutoInfo officeAutoInfo){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"officeAuto",gson.toJson(officeAutoInfo));
    }
    public SQLInfo getSQLdata(){
        String  sqlData = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"sqlInfo");
        Gson gson = new Gson();
        return gson.fromJson(sqlData,SQLInfo.class);
    }
    public void savaSQLData(SQLInfo sqlInfo){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"sqlInfo",gson.toJson(sqlInfo));
    }
    public WindowInfo getWindowData(){
        String windowData  = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"window");
        Gson gson = new Gson();
        return gson.fromJson(windowData,WindowInfo.class);
    }

    public void saveWindowData(WindowInfo windowInfo){
        Gson gson = new Gson();
      instance.sharedPreferencesUtils.setSP(xmlFileName,"window",gson.toJson(windowInfo));
    }
    public void saveErrorQuestion(List<ErrorQuestionInfo> errorQuestionInfos){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"errorQuestion",gson.toJson(errorQuestionInfos));
    }
    public List<ErrorQuestionInfo>  getErrorQuestion(){
        String errorQuestionts = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"errorQuestion");
        Gson gson = new Gson();
        Type listTypeA = new TypeToken<List<ErrorQuestionInfo>>() {}.getType();
        List<ErrorQuestionInfo> errorQuestionInfos = gson.fromJson(errorQuestionts,listTypeA);
        return errorQuestionInfos;
    }
    public void saveCollect(List<CollectQuesstionInfo> collectQuesstionInfos){
        Gson gson = new Gson();
        instance.sharedPreferencesUtils.setSP(xmlFileName,"collector",gson.toJson(collectQuesstionInfos));
    }
    public List<CollectQuesstionInfo>  getCollectQuestion(){
        String errorQuestionts = instance.sharedPreferencesUtils.getStringSP(xmlFileName,"collector");
        Gson gson = new Gson();
        Type listTypeA = new TypeToken<List<CollectQuesstionInfo>>() {}.getType();
        List<CollectQuesstionInfo> collectQuesstionInfos = gson.fromJson(errorQuestionts,listTypeA);
        return collectQuesstionInfos;
    }

    public void saveLoginState(){
        instance.sharedPreferencesUtils.setBooleanSP(xmlFileName,"open",true);

    }
    public boolean getLoginState(){
        boolean state = instance.sharedPreferencesUtils.getBooleanSP(xmlFileName,"open");
        return state;
    }

    public void saveKeyValue(String key, String value){
        instance.sharedPreferencesUtils.setSP(xmlFileName, key,value);
    }

    public String getValue(String key){
        return instance.sharedPreferencesUtils.getStringSP(xmlFileName, key);
    }


}
