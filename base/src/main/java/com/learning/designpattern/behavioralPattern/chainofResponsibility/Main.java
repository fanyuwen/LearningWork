package com.learning.designpattern.behavioralPattern.chainofResponsibility;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.handler.AbstractRequestHandler;
import com.learning.designpattern.behavioralPattern.chainofResponsibility.handler.BaiRenJieHandler;
import com.learning.designpattern.behavioralPattern.chainofResponsibility.handler.HeShuZhuangHandler;
import com.learning.designpattern.behavioralPattern.chainofResponsibility.handler.LiZiHanHander;

/**
 * @author fanyuwen
 * @date 2019/7/3 21:14
 */
public class Main {


    static AbstractRequestHandler init() {
        AbstractRequestHandler handler = new LiZiHanHander("李梓涵");
        handler = new HeShuZhuangHandler("何书壮", handler);
        return new BaiRenJieHandler("柏人杰", handler);
    }

    public static void main(String[] args) {
        AbstractRequestHandler handler = init();
        Request request = new Request("请问,你知道Java之父是谁嘛?");
        handler.resolve(request);
    }


}