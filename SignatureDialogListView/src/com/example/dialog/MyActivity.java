package com.example.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ListView的单选对话框.
 */
public class MyActivity extends Activity implements View.OnClickListener {


    Button btn_getsignature;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_getsignature = (Button) findViewById(R.id.btn_getsignature);
        imageView = (ImageView) findViewById(R.id.iv_signature);
        btn_getsignature.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_getsignature) {
            getSignatureDialog();//弹出获取签名图片的对话框
        }
    }


    // 使用对话框内的控件来关闭对话框
    private void getSignatureDialog() {

        // View
        final View view = View.inflate(this, R.layout.dialog_signature, null);
        ListView lv_signature = (ListView) view.findViewById(R.id.lv_signature);
        Button btn_yes = (Button) view.findViewById(R.id.btn_yes);
        Button btn_no = (Button) view.findViewById(R.id.btn_no);
        final View listviewLayout = View.inflate(this, R.layout.dialog_signature, null);

        //List
        List<SignatureBean> signatureList = new ArrayList<SignatureBean>();
        final String[] info = {"张三", "李四", "王五", "赵六"};
        final int[] pictureID = {R.drawable.ic_launcher, R.drawable.sig, R.drawable.signature, R.drawable.sort1};
        for (int i = 0; i < info.length; i++) {
            signatureList.add(new SignatureBean(info[i], pictureID[i]));
        }

        //adapter
        final SignatureAdapter adapter = new SignatureAdapter(this, signatureList);
        lv_signature.setAdapter(adapter);


        // 创建对话框对象
        final AlertDialog dialog = new AlertDialog.Builder(this).
                // 设置标题
//                        setTitle("通过按钮关闭对话框").
                        // 添加输入的文本框
                        setView(view).
                // 产生
                        create();

        // 设置对话框不可以关闭，一般情况下对话框是失去焦点后自动消失的
        // 但是加 了.setCancelable(false)，对话框就不会消失，除非手动退出
        dialog.setCancelable(false);
        // 显示
        dialog.show();

        //设置View内的点击事件

        btn_yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(MyActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < info.length; i++) {
                    if (adapter.states.get(i) != null && (adapter.states.get(i))) {
                        imageView.setImageResource(pictureID[i]);
                    }
                }
                dialog.dismiss();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击后关闭对话框,两种方法都可以
                //  dialog.cancel();
                dialog.dismiss();
//                Toast.makeText(MyActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
