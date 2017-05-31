package com.answertest.constant;

import android.os.Environment;

/**
 * Created by CWJ on 2017/2/18.
 */

public class FileConstant {

    // SD卡目录
    public static final String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    // 喝茶猫目录
    public static final String CRM_DIR =  ROOT_DIR + "/hechamao/a.txt";

    // 图片目录
   // public static final String FILE_DIR = CRM_DIR + "/infofile/";

    //assets目录文件
    //第1章-信息与计算机
    public static final String INFORMATION_FILE = "information.txt";
    //第2章-Windows操作系统
    public static final String WINDOW_FILE ="window.txt";
    //第3章-办公自动化软件应用
    public static final String OFFICE_AUTO_FILE ="officeauto.txt";
    //第4章-多媒体应用技术基础
    public static final String MULTIMEDIA_FILE ="multimedia.txt";
    //第5章-计算机网络基础
    public static final String NET_FILE = "net.txt";
    //第6章-数据库技术及应用基础
    public static final String SQL_FILE ="sql.txt";

}
