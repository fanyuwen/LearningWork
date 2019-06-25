package com.learning.designpattern.factory;

import com.learning.designpattern.factory.bairenjie.BaiRenJieFactory;
import com.learning.designpattern.factory.heshuzhuang.HeShuZhuangFactory;
import com.learning.designpattern.factory.songwei.SongWeiFactory;

import java.util.Arrays;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:42
 * 工厂模式 main
 */
public class Main {

    private static TrainingCampFactory[] trainingCampFactories;

    static {
        trainingCampFactories = new TrainingCampFactory[0];
        trainingCampFactories = Arrays.copyOf(trainingCampFactories, trainingCampFactories.length + 1);
        trainingCampFactories[trainingCampFactories.length - 1] = new BaiRenJieFactory();
        trainingCampFactories = Arrays.copyOf(trainingCampFactories, trainingCampFactories.length + 1);
        trainingCampFactories[trainingCampFactories.length - 1] = new HeShuZhuangFactory();
        trainingCampFactories = Arrays.copyOf(trainingCampFactories, trainingCampFactories.length + 1);
        trainingCampFactories[trainingCampFactories.length - 1] = new SongWeiFactory();
    }

    public static void main(String[] args) {
        System.out.println("训练营新一届的颁奖典礼开始");
        for (TrainingCampFactory trainingCampFactory : trainingCampFactories) {
            OutstandingStudent outstandingStudent = trainingCampFactory.create();
            System.out.println("优秀学员: " + outstandingStudent.name());
            System.out.println(outstandingStudent.achievement());
            System.out.println("有请" + outstandingStudent.name() + "发表获奖感言");
            System.out.println(outstandingStudent.acceptanceSpeech());
        }
    }

}
