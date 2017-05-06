package com.liwenzhi.csdndemo.util.sort;


import com.liwenzhi.csdndemo.bean.XmlBean;
import com.liwenzhi.csdndemo.util.TimeUtil;

import java.util.Comparator;

/**
 * 从大到小排序时间
 */
public class TimeFromBigToSmall implements Comparator<XmlBean> {

    //排序的实际方法
    @Override
    public int compare(XmlBean lhs, XmlBean rhs) {
        //升序排列，
        //升序排列，前面加个负号就按照降序排列
        // int num1 = (int) (TimeUtil.getTimeLong("yyyy-MM-dd HH:mm:ss", lhs.getStartTime()) - TimeUtil.getTimeLong("yyyy-MM-dd HH:mm:ss", rhs.getStartTime()));
        Long num = -(TimeUtil.getTimeLong("yyyy-MM-dd HH:mm:ss", lhs.getStartTime()) - TimeUtil.getTimeLong("yyyy-MM-dd HH:mm:ss", rhs.getStartTime()));
//        Log.e("TAG", "TimeFromSmallToBigInt" + num1);
//        Log.e("TAG", "TimeFromSmallToBigLong" + num2);
//        Log.e("TAG", "TimeFromSmallToBig==" + ((long) num1 == num2));
//        Log.e("TAG", "TimeFromSmallToBig==" + ((long) num1 == num2));

        return num > 0 ? 1 : (num < 0 ? -1 : 0);
    }
}
