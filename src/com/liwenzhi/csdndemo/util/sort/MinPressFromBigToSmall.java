package com.liwenzhi.csdndemo.util.sort;


import com.liwenzhi.csdndemo.bean.XmlBean;

import java.util.Comparator;

/**
 * 从大到小排序舒张压
 */
public class MinPressFromBigToSmall implements Comparator<XmlBean> {

    //排序的实际方法
    @Override
    public int compare(XmlBean lhs, XmlBean rhs) {
        //升序排列，前面加个负号就按照降序排列
        return -(lhs.getMinPress() - rhs.getMinPress());
    }
}
