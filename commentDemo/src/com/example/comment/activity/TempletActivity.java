package com.example.comment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.comment.R;
import com.example.comment.adapter.TempletAdapter;
import com.example.comment.mvp.presenter.TempletPresenter;
import com.example.comment.widget.TempletView;
import com.example.comment.widget.NormalTitleBar;
import com.example.comment.widget.swipemenulistview.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午10:18
 * 模板管理界面
 */
public class TempletActivity extends BaseActivity<TempletView, TempletPresenter> implements TempletView, SwipeMenuListView.OnMenuItemClickListener, View.OnClickListener {

    public static final int REQUESTCODE_GET_ANALYSIS_TEMPLET = 100;
    public static final int REQUESTCODE_GET_PROPOSE_TEMPLET = 200;
    // 要选择哪个模板(诊断分析还是医生建议 , 最终操作两个不同的数据库)
    public static final String WHICH_TEMPLET = "WHICH_TEMPLET";
    // 诊断分析模板
    public static final int TEMPLET_ANALYSIS = 0;
    // 医生建议模板
    public static final int TEMPLET_PROPOSE = 1;
    // 当前选中的是哪一个模板
    private int currentTempletType = -1;

    private SwipeMenuListView mLv_templet;
    private TextView mTv_state;
    private ProgressDialog mProgressDialog;
    private TempletAdapter mAdapter;
    private NormalTitleBar mTitleBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templet);

        initView();
    }

    @Override
    protected TempletPresenter initPresenter() {
        return new TempletPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mLv_templet = (SwipeMenuListView) findViewById(R.id.lv_templet);
        mTv_state = (TextView) findViewById(R.id.tv_templet_state);

        initListView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        currentTempletType = getIntent().getIntExtra(WHICH_TEMPLET, -1);
        if (currentTempletType == -1) {
            Toast.makeText(TempletActivity.this, "本地模板数据库出错啦!", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.initData(currentTempletType);
    }

    /**
     * 初始化listview
     */
    private void initListView() {
        // 创建适配器
        mAdapter = new TempletAdapter();
        mLv_templet.setAdapter(mAdapter);

        // 设置listview条目侧滑选项
        mLv_templet.setMenuCreator(new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(TempletActivity.this);
                editItem.setWidth(dp2px(90));
                editItem.setIcon(R.drawable.ic_edit);
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                menu.addMenuItem(editItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(TempletActivity.this);
                deleteItem.setWidth(dp2px(90));
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        });

        // 设置条目侧滑选项点击监听
        mLv_templet.setOnMenuItemClickListener(this);

        mLv_templet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.getTemplet(position);
            }
        });
    }


    @Override
    public void showTempletEmpty() {
        if (mTv_state != null && mLv_templet != null) {
            mTv_state.setText("暂无模板,请按右上角加号添加模板");
            mTv_state.setVisibility(View.VISIBLE);
            mLv_templet.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showTemplets() {
        if (mTv_state != null && mLv_templet != null) {
            mLv_templet.setVisibility(View.VISIBLE);
            mTv_state.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public TempletAdapter getAdapter() {
        return mAdapter;
    }

    public void showLoading() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("数据加载中...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    public void showNetError() {

    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index) {
            case 0:
                // 编辑
                mPresenter.edit(position);
                break;
            case 1:
                // 删除
                mPresenter.delete(position);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_left:
                onBackPressed();
                break;
            case R.id.layout_right:
                // 添加模板
                mPresenter.addTemplet();
                break;
        }
    }

    /**
     * 把dp换成px
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


}