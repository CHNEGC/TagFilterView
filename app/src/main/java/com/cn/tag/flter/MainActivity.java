package com.cn.tag.flter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ChooseLabelView mChooseLabelView1;
    private ChooseLabelView mChooseLabelView2;
    private ChooseLabelView mChooseLabelView3;
    private ChooseLabelView mChooseLabelView4;
    private ChooseLabelView mChooseLabelView5;
    private ChooseLabelView mChooseLabelView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mChooseLabelView1 = (ChooseLabelView) findViewById(R.id.chooseLabelView1);
        mChooseLabelView1 = (ChooseLabelView) findViewById(R.id.chooseLabelView1);
        mChooseLabelView2 = (ChooseLabelView) findViewById(R.id.chooseLabelView2);
        mChooseLabelView3 = (ChooseLabelView) findViewById(R.id.chooseLabelView3);
        mChooseLabelView4 = (ChooseLabelView) findViewById(R.id.chooseLabelView4);
        mChooseLabelView5 = (ChooseLabelView) findViewById(R.id.chooseLabelView5);
        mChooseLabelView6 = (ChooseLabelView) findViewById(R.id.chooseLabelView6);

        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        integers.add(3);
        integers.add(6);
        integers.add(4);

        strings.add("背景1");
        strings.add("背景2");
        strings.add("中国2");

        mChooseLabelView1.onCreateTag(new String[]{"中国ddddd", "背景fffff", "相关dddd", "sssss", "中ddd国", "背景ss", "dsds相关", "ddd天津"});
        mChooseLabelView2.onCreateTag(new String[]{"中国", "背景", "相关", "天津", "中国", "背景", "相关", "天津"}, 0);
        mChooseLabelView3.onCreateTag(new String[]{"中国", "背景", "相关", "天津", "中国", "背景", "相关", "天津"}, 1);
        mChooseLabelView4.onCreateTag(new String[]{"中国", "背景", "相关", "天津", "中国", "背景", "相关", "天津"});
        mChooseLabelView5.onCreateTag(new String[]{"中国1", "背景1", "相关1", "天津1", "中国2", "背景2", "相关2", "天津2"}, integers, 0);
        mChooseLabelView6.onCreateTag(new String[]{"中国1", "背景1", "相关1", "天津1", "中国2", "背景2", "相关2", "天津2"}, strings);

        mChooseLabelView2.setSingleSelectClickListener(new ChooseLabelView.OnSingleSelectClickListener() {
            @Override
            public void onClicked(int position, String tag) {
                Toast.makeText(MainActivity.this, position + ">>>>" + tag, Toast.LENGTH_SHORT).show();
                Log.d("ChooseLabelViewActivity", "单选：" + position + ">>>>" + tag);
            }
        });
        mChooseLabelView3.setSingleSelectClickListener(new ChooseLabelView.OnSingleSelectClickListener() {
            @Override
            public void onClicked(int position, String tag) {
                Toast.makeText(MainActivity.this, position + ">>>>" + tag, Toast.LENGTH_SHORT).show();
                Log.d("ChooseLabelViewActivity", "单选：" + position + ">>>>" + tag);
            }
        });
        mChooseLabelView4.setSingleSelectClickListener(new ChooseLabelView.OnSingleSelectClickListener() {
            @Override
            public void onClicked(int position, String tag) {
                Toast.makeText(MainActivity.this, position + ">>>>" + tag, Toast.LENGTH_SHORT).show();
                Log.d("ChooseLabelViewActivity", "单选：" + position + ">>>>" + tag);
            }
        });
        mChooseLabelView5.setMoreSelectClickListener(new ChooseLabelView.OnMoreSelectClickListener() {
            @Override
            public void onClicked(int position, String tag, List<String> mSelectIndex, List<String> mSelectTag) {
                Toast.makeText(MainActivity.this, position + ">>>>" + tag + ">>>" +
                        mSelectIndex.toString() + ">>>" + mSelectTag.toString(), Toast.LENGTH_SHORT).show();
                Log.d("ChooseLabelViewActivity", "多选：" + position + ">>>>" + tag + ">>>" +
                        mSelectIndex.toString() + ">>>" + mSelectTag.toString());
            }
        });
        mChooseLabelView6.setMoreSelectClickListener(new ChooseLabelView.OnMoreSelectClickListener() {
            @Override
            public void onClicked(int position, String tag, List<String> mSelectIndex, List<String> mSelectTag) {
                Toast.makeText(MainActivity.this, position + ">>>>" + tag + ">>>" +
                        mSelectIndex.toString() + ">>>" + mSelectTag.toString(), Toast.LENGTH_SHORT).show();
                Log.d("ChooseLabelViewActivity", "多选：" + position + ">>>>" + tag + ">>>" +
                        mSelectIndex.toString() + ">>>" + mSelectTag.toString());
            }
        });
    }
}
