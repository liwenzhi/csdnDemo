package com.example.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

            String inputStr = "简单加密";
            getResult(inputStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String KEY_MD = "MD";
    public static String getResult(String inputStr)
    {
       Log.e("TAG","=======加密前的数据:"+inputStr);
        BigInteger bigInteger=null;
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD);
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {e.printStackTrace();}
        Log.e("TAG", "MD加密后:" + bigInteger.toString());
        return bigInteger.toString();
    }


}



