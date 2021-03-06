package com.liwenzhi.csdndemo.util.sort;


import com.liwenzhi.csdndemo.bean.XmlBean;

import java.util.Comparator;

/**
 * 从小到大排序收缩压
 */
public class MaxPressFromSmallToBig implements Comparator<XmlBean> {

    //排序的实际方法
    @Override
    public int compare(XmlBean lhs, XmlBean rhs) {
        //升序排列，前面加个负号就按照降序排列
        return lhs.getMaxPress() - rhs.getMaxPress();
    }
}
