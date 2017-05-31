package com.answertest.data;

/**
 * Created by cwj on 2017/5/23.
 */

public class InformationInputData {

    public static final String question="操作题题目：\n" +
            "打开考生文件夹，通过“组织”\uF08A“文件夹与搜索选项”菜单打开“文件夹选项”框，在其“查看”页上去除“隐藏已知文件类型扩展名”的勾选。再完成下列操作：\n" +
            "\n1)\t在考生文件夹中新建一个名为MyFile.docx的Word文件，在其中任意插入一个表格。\n" +
            "\n2)\t将考生文件夹的Fruit.dat文件压缩为Preserve.zip。\n" +
            "\n3)\t用“附件”中的“画图”软件打开考生文件夹下的Sunflower.bmp文件，将其另存为PNG文件Sunflower.png。\n" +
            "\n4)\t用Windows Media Player软件打开并播放考生文件夹中的Start.wav音频文件。\n" +
            "\n5)\t通过“个性化”或者“控制面板”更改桌面背景，图片选Fjexam95\\popo.bmp，位置设置为平铺方式。\n" +
            "\n6)\t搜索Fjnewdat文件夹，查找文件名主名末尾是end，扩展名任意的文件，将其拷贝到考生文件夹下的Friend文件夹中。\n" +
            "\n7)\t将考生文件下Document文件夹中的Mfiles.dat文件属性设置为只读文件。\n" +
            "\n8)\t将考生文件夹下的Garbage文件夹中的Nothing子文件夹删除。\n";
    public static final String answer ="解析：\n" +
            "\n打开考生文件夹\n" +
            "\n1)\t新建文件\n" +
            "单击鼠标右键，弹出快捷菜单，选择【新建】|【Microsoft  Word 文档】命令，即可生成新的Word文件，此时Word文件的名字处呈现蓝色可编辑状态。编辑名称为题目指定的名称MyFile，打开MyFile.docx，点击【插入】选项卡|【表格】，最后点击【保存】。\n" +
            "\n2)\t压缩文件\n" +
            "选定文件Fruit.da，点击鼠标右键，点击【添加到压缩文件(A)…】，弹出“压缩文件名和参数”窗口，选择压缩文件格式为ZIP(Z)，压缩文件名更改为Preserve.zip，最后点击【确定】。\n" +
            "\n3)\t用画图软件转换图片格式\n" +
            "选定文件Sunflower.bmp，点击鼠标右键，选择【打开方式(H)】|【画图】，点击【另存为(A)】|【PNG图片(P)】,最后点击【保存】。\n" +
            "\n4)\t使用Windows Media Player\n" +
            "选定音频文件Start.wav，点击鼠标右键，选择【打开方式(H)】|【Windows Media Player】，关闭。\n" +
            "\n5)\t设置桌面背景\n" +
            "在电脑桌面，点击鼠标右键，点击【个性化】|【桌面背景】，点击【浏览(B)…】，弹出浏览文件夹窗口，选取Fjexam95，点击【全部清除】，勾选图片popo.bmp，设置为平铺方式。\n" +
            "\n6)\t使用通配符搜索文件\n" +
            "打开Fjnewdat文件夹，在搜索窗口输入*end.*，将搜索到的文件右击鼠标，点击【复制】，打开考生文件夹下的Friend文件夹，点击鼠标右键，点击【粘贴】。\n" +
            "\n7)\t设置文件属性\n" +
            "打开考生文件下Document文件夹，点击鼠标右键，点击【属性】，勾选【只读】。\n" +
            "\n8)\t删除文件\n" +
            "打开考生文件夹下的Garbage文件夹，选定子文件Nothing，点击鼠标右键，点击【删除】，弹出删除文件对话框，点击【是】。\n";
}
