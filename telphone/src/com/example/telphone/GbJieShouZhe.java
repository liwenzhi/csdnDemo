package com.example.telphone;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * 继承广播接受者
 */

public class GbJieShouZhe extends BroadcastReceiver {


    /**注意：要在清单文件中配置 和 指定接收的类型广播（这里是指定了接收广播的类型是打电话广播，在开发中记得更改为其他所需要的广播）
     *
     * 设置接受到打电话广播的权限：    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>
     *
     *  </activity>
     *  配置
     <receiver android:name="com.example.ip.GuangBoJieShouZhe">
     指定广播接收者 所需接收的广播类型
     这里指定的是接收打电话广播
     <intent-filter >
     <action android:name="android.intent.action.NEW_OUTGOING_CALL"></action>

     </intent-filter>
     </receiver>
     </application>
     * */

    /**
     * 这个方法是 当系统接收端广播时 就会被调用
     */
    public void onReceive(Context context, Intent intent) {

        Log("打电话的时候接收到广播了");

        /**添加IP路线
         *
         * 在打电话广播中，会携带拨打出去的那个 电话号码
         * */
        /**获得广播中的数据，返回一个String类型的类*/
        String number = getResultData();

        //检测是否获取到广播中的数据
        Log(number);

        /**这里是对座机号码的判断 */
        if (number.startsWith("0")) {

            /**把储存好的 ip 拿出来，得到一个储存类*/
            SharedPreferences sp = context.getSharedPreferences("ip", context.MODE_PRIVATE);

            /**如果用不添加电话号码线路的ip（17951）... 则直接为空13412345678...*/
            String ipNumber = sp.getString("ipNumber", "");

            /**把ip线路号码添加至用户拨打号码的起码 这里形成了一个拼接*/
            number = ipNumber + number;

            /**把新的号码从新放入广播中*/
            setResultData(number);

        }


    }

    //检测方法
    public static void Log(Object pObj) {
        Log.e("Ktink", (String) pObj);

    }

}
