# TagFilterView
标签组件，支持自适应标签，展示，可选，多选，单选，不限，可选可反选


使用方法：
xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="15dp">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:isAdaptive="true"
                app:isShowTitle="true"
                app:titleText="自适应标签大小" />

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView2"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                app:isMoreSelect="false"
                app:isShowTitle="true"
                app:titleText="无不限单选" />

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView3"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                app:isMoreSelect="false"
                app:isShowTitle="true"
                app:isShowUnlimited="true"
                app:titleText="有不限单选"
                app:unlimitedText="不限" />

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView4"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                app:isBackSelect="true"
                app:isMoreSelect="false"
                app:isShowTitle="true"
                app:titleText="单选且可反选" />

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView5"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                app:isShowTitle="true"
                app:isShowUnlimited="true"
                app:titleText="多选有不限"
                app:unlimitedText="不限" />

            <com.cn.tag.flter.ChooseLabelView
                android:id="@+id/chooseLabelView6"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="20dp"
                app:isShowTitle="true"
                app:titleText="多选无不限" />
        </LinearLayout>
    </ScrollView>
</LinearLayout>

核心使用代码：
Java

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

更新于：2018-11-21

更新内容：
1.新增属性maxRowNum，可以配置显示的最大行数，默认为不限制，该属性配置不能小于等于0；
2.新增创建Tag方法onCreateTag(List<String> tags) 支持传入list创建Tags；
3.新增参数onCreateTag(List<String> tags, boolean isRefresh) 第二个参数isRefresh用于
反复调用onCreateTag的时候会重复创建tag，默认会false

bug修复：
选中状态字体颜色没有设置效果
