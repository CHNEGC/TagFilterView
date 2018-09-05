package com.cn.tag.flter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * CHENGC
 */
public class ChooseLabelView extends ViewGroup {

    /**
     * 子View之间的行间距
     */
    private int verticalSpacing;
    /**
     * 子View之间的列间距
     */
    private int horizontalSpacing;

    /**
     * 是否显示标题
     */
    private boolean isShowTitle = false;
    /**
     * 是否支持多选
     */
    private boolean isMoreSelect = true;
    /**
     * 是否支持返选 - 只有单选模式有效
     */
    private boolean isBackSelect = false;
    /**
     * 是否显示不限/全部
     */
    private boolean isShowUnlimited = false;
    /**
     * 是否默认选择不限
     */
    private boolean isSelectUnlimited = true;
    /**
     * 是否自适应
     */
    private boolean isAdaptive = false;
    /**
     * 不限/全部
     */
    private String unlimitedText = "不限";
    /**
     * 标题字体大小
     */
    private int titleTextSize = 20;
    /**
     * 标题
     */
    private String titleText = "标题";
    /**
     * 标题默认字体验证
     */
    private int titleTextColor = Color.parseColor("#333333");

    /**
     * 标签字体
     */
    private int tagTextSize = 12;
    /**
     * 标签默认颜色
     */
    private int tagTextColor = Color.parseColor("#333333");
    /**
     * 标签选择颜色
     */
    private int tagTextColorSelect = Color.parseColor("#E03236");
    /**
     * 标签的宽度
     */
    private int tagWidth = 0;
    /**
     * 标签的高度
     */
    private int tagHeight = 0;
    /**
     * 标签的上间距
     */
    private int tagPaddingTop = 10;
    /**
     * 标签左间距
     */
    private int tagPaddingLeft = 10;
    /**
     * 标签右间距
     */
    private int tagPaddingRight = 10;
    /**
     * 标签底部间距
     */
    private int tagPaddingBottom = 10;
    /**
     * 标签背景
     */
    private int tagBackground;
    /**
     * 选中背景
     */
    private int tagBackgroundSelect;


    private Context mContext;
    /**
     * 标签
     */
    private List<TextView> mTags = new ArrayList<>();
    /**
     * 选中的标签
     */
    private List<TextView> mSelectTag = new ArrayList<>();
    /**
     * 选择的下标
     */
    private List<String> mSelectTagsIndex = new ArrayList<>();
    private List<String> mSelectTagsName = new ArrayList<>();

    public ChooseLabelView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public ChooseLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public ChooseLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        verticalSpacing = dip2px(10);
        horizontalSpacing = dip2px(10);

        isShowTitle = false;
        isBackSelect = false;
        isMoreSelect = true;
        isShowUnlimited = false;
        isSelectUnlimited = true;
        isAdaptive = false;

        unlimitedText = "不限";
        titleText = "标签选择";

        titleTextSize = pxToSp(20);
        titleTextColor = Color.parseColor("#333333");

        tagTextSize = pxToSp(12);
        tagTextColor = Color.parseColor("#333333");
        tagTextColorSelect = Color.parseColor("#E03236");
        tagHeight = 0;
        tagHeight = 0;
        tagBackground = R.drawable.bg_tag_defualt;
        tagBackgroundSelect = R.drawable.bg_tag_select;
        tagPaddingTop = dip2px(5);
        tagPaddingBottom = dip2px(5);
        tagPaddingLeft = dip2px(15);
        tagPaddingRight = dip2px(15);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ChooseLabelView);

        try {
            //通过属性获取配置信息

            //行列间距
            verticalSpacing = (int) array.getDimension(R.styleable.ChooseLabelView_verticalSpacing, verticalSpacing);
            horizontalSpacing = (int) array.getDimension(R.styleable.ChooseLabelView_horizontalSpacing, horizontalSpacing);

            //配置显示项
            isShowTitle = array.getBoolean(R.styleable.ChooseLabelView_isShowTitle, isShowTitle);
            isAdaptive = array.getBoolean(R.styleable.ChooseLabelView_isAdaptive, isAdaptive);
            isShowUnlimited = array.getBoolean(R.styleable.ChooseLabelView_isShowUnlimited, isShowUnlimited);
            isSelectUnlimited = array.getBoolean(R.styleable.ChooseLabelView_isSelectUnlimited, isSelectUnlimited);
            isMoreSelect = array.getBoolean(R.styleable.ChooseLabelView_isMoreSelect, isMoreSelect);
            isBackSelect = array.getBoolean(R.styleable.ChooseLabelView_isBackSelect, isBackSelect);

            //配置不限和标题
            unlimitedText = array.getString(R.styleable.ChooseLabelView_unlimitedText);
            titleText = array.getString(R.styleable.ChooseLabelView_titleText);

            //标签的宽高
            tagWidth = (int) array.getDimension(R.styleable.ChooseLabelView_tagWidth, tagWidth);
            tagHeight = (int) array.getDimension(R.styleable.ChooseLabelView_tagHeight, tagHeight);

            //标题字体大小和颜色
            titleTextSize = (int) array.getDimension(R.styleable.ChooseLabelView_titleTextSize, titleTextSize);
            titleTextColor = array.getColor(R.styleable.ChooseLabelView_titleTextColor, titleTextColor);

            //标签相关属性
            tagTextSize = (int) array.getDimension(R.styleable.ChooseLabelView_tagTextSize, tagTextSize);
            tagTextColor = array.getColor(R.styleable.ChooseLabelView_tagTextColor, tagTextColor);
            tagTextColorSelect = (int) array.getDimension(R.styleable.ChooseLabelView_tagTextColorSelect, tagTextColorSelect);
            tagBackground = array.getResourceId(R.styleable.ChooseLabelView_tagBackground, R.drawable.bg_tag_defualt);
            tagBackgroundSelect = array.getResourceId(R.styleable.ChooseLabelView_tagBackgroundSelect, R.drawable.bg_tag_select);
            tagPaddingTop = (int) array.getDimension(R.styleable.ChooseLabelView_tagPaddingTop, tagPaddingTop);
            tagPaddingRight = (int) array.getDimension(R.styleable.ChooseLabelView_tagPaddingRight, tagPaddingRight);
            tagPaddingLeft = (int) array.getDimension(R.styleable.ChooseLabelView_tagPaddingLeft, tagPaddingLeft);
            tagPaddingBottom = (int) array.getDimension(R.styleable.ChooseLabelView_tagPaddingBottom, tagPaddingBottom);

            setPadding(horizontalSpacing, 0, horizontalSpacing, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {

        final int parentLeft = getPaddingLeft();
        final int parentRight = right - left - getPaddingRight();
        final int parentTop = getPaddingTop();

        //子View左右间距
        int childViewLeft = parentLeft;
        int childViewTop = parentTop;

        //行高最大值
        int rowMaxHeight = 0;
        //获取子view
        final int childCount = getChildCount();

        //遍历子view
        for (int i = 0; i < childCount; i++) {

            final View child = getChildAt(i);

            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            if (child.getVisibility() != View.GONE) {

                if (childViewLeft + childWidth > parentRight) {
                    //换行
                    childViewLeft = parentLeft;
                    childViewTop += rowMaxHeight + verticalSpacing;
                    rowMaxHeight = childHeight;
                } else {
                    rowMaxHeight = Math.max(rowMaxHeight, childHeight);
                }
                //重新绘制子view的位置
                child.layout(childViewLeft, childViewTop, childViewLeft + childWidth, childViewTop + childHeight);
                childViewLeft += childWidth;
                if (i < childCount - 1) {
                    childViewLeft += +horizontalSpacing;
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量子View视图
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int row = 0;//行数
        int rowWidth = 0;//行宽
        int rowMaxHeiht = 0;//行高

        //获取子view的总数
        int count = getChildCount();
        //遍历子view
        for (int i = 0; i < count; i++) {

            final View child = getChildAt(i);

            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            //判断子view是否是不占位隐藏，如果是占位隐藏，还有计算
            if (child.getVisibility() != View.GONE) {

                rowWidth += childWidth;

                //判断行的宽度是否已经大于组件的宽度，如果大于则就换行
                if (rowWidth > widthSize) {
                    //下一行
                    rowWidth = childWidth;
                    //高度
                    height += rowMaxHeiht + verticalSpacing;
                    //下一行的高度
                    rowMaxHeiht = childHeight;
                    //记录行数
                    row++;
                } else {
                    //单行的高度
                    rowMaxHeiht = Math.max(rowMaxHeiht, childHeight);
                }
                if (i < count - 1) {
                    rowWidth += horizontalSpacing;
                }
            }
        }

        //最后一行的高度
        height += rowMaxHeiht;
        //最后一行的内间距
        height += getPaddingBottom() + getPaddingTop();

        //如果标签只有一行，则就设置宽度包裹标签
        if (row == 0) {
            width += rowWidth;
            width += getPaddingLeft() + getPaddingRight();
        } else {
            //如果标签超一行，则设置宽度填充父布局
            width = widthSize;
        }
        //设置组件大小
        setMeasuredDimension(widthModel == MeasureSpec.EXACTLY ? widthSize : width, heightModel == MeasureSpec.EXACTLY ? heightSize : height);
    }


    /**
     * @param tags 标签数据
     */
    public void onCreateTag(String[] tags) {
        onCreateTag(tags, -1, null, null, null);
    }

    /**
     * @param tags  标签数据
     * @param index 下标
     */
    public void onCreateTag(String[] tags, int index) {
        onCreateTag(tags, index, null, null, null);
    }

    /**
     * @param tags  标签数据
     * @param index 文案
     */
    public void onCreateTag(String[] tags, String index) {
        onCreateTag(tags, -1, null, index, null);
    }

    /**
     * @param tags          标签数据
     * @param mSelectIndexs 默认选中集合
     * @param t             无用参数
     */
    public void onCreateTag(String[] tags, List<Integer> mSelectIndexs, int t) {
        onCreateTag(tags, -1, mSelectIndexs, null, null);
    }

    /**
     * @param tags        标签数据
     * @param mSelectTags 默认选择集合
     */
    public void onCreateTag(String[] tags, List<String> mSelectTags) {
        onCreateTag(tags, -1, null, null, mSelectTags);
    }

    /**
     * @param tags
     * @param mSelectIndex
     * @param mSelectIndexs
     * @param mSelectedTag
     * @param mSelectTags
     */
    public void onCreateTag(String[] tags, int mSelectIndex, List<Integer> mSelectIndexs, String mSelectedTag, List<String> mSelectTags) {

        //计算tag的宽高
        if (!isAdaptive) {
            tagWidth = ((getScreenWidth() - getPaddingRight() - getPaddingLeft() - (horizontalSpacing * 3)) / 4);
            tagHeight = dip2px(32);
        } else {
            tagWidth = 0;
            tagHeight = 0;
        }

        //创建标题
        if (isShowTitle) {
            TextView titleTv = new TextView(mContext);
            titleTv.setText(TextUtils.isEmpty(titleText) ? "标题" : titleText);
            titleTv.setTextColor(titleTextColor);
            titleTv.setTextSize(titleTextSize);

            LayoutParams paramsTitle = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            titleTv.setLayoutParams(paramsTitle);

            addView(titleTv);
        }
        //是否有不限
        if (isShowUnlimited) {
            TextView textView = onCreateTagView(TextUtils.isEmpty(unlimitedText) ? "不限" : unlimitedText);
            //设置了有不限。但是有设置默认选中不限以外的其他项，就不选中不限项了
            if (isSelectUnlimited && mSelectIndex == -1 && (null == mSelectIndexs || mSelectIndexs.size() <= 0) && TextUtils.isEmpty(mSelectedTag) && (null == mSelectTags || mSelectTags.size() <= 0)) {
                textView.setBackgroundResource(tagBackgroundSelect);
                mSelectTag.add(textView);
                mSelectTagsIndex.add("0");
            } else {
                mSelectTag.clear();
                mSelectTagsIndex.clear();
                textView.setBackgroundResource(tagBackground);
            }
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTagClick(view, 0);
                }
            });
        }

        //创建标签
        for (int i = 0; i < tags.length; i++) {
            TextView textView = onCreateTagView(tags[i]);
            //如果有不限，下标要往后加1
            int index = i;
            if (isShowUnlimited) {
                index += 1;
            }

            if (isMoreSelect) {//多选
                if (null != mSelectIndexs && mSelectIndexs.size() > 0) {
                    if (mSelectIndexs.contains(index)) {
                        textView.setBackgroundResource(tagBackgroundSelect);
                        mSelectTag.add(textView);
                        mSelectTagsName.add(textView.getText().toString());
                        mSelectTagsIndex.add(index + "");
                    } else {
                        textView.setBackgroundResource(tagBackground);
                    }
                }
                if (null != mSelectTags && mSelectTags.size() > 0) {
                    if (mSelectTags.contains(textView.getText().toString())) {
                        textView.setBackgroundResource(tagBackgroundSelect);
                        mSelectTag.add(textView);
                        mSelectTagsName.add(textView.getText().toString());
                        mSelectTagsIndex.add(index + "");
                    } else {
                        textView.setBackgroundResource(tagBackground);
                    }
                }
            } else {//单选
                if (mSelectIndex == index || (!TextUtils.isEmpty(mSelectedTag) && mSelectedTag.equals(textView.getText().toString()))) {
                    textView.setBackgroundResource(tagBackgroundSelect);
                    mSelectTag.add(textView);
                    mSelectTagsName.add(textView.getText().toString());
                    mSelectTagsIndex.add(index + "");
                } else {
                    textView.setBackgroundResource(tagBackground);
                }
            }

            final int finalIndex = index;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTagClick(view, finalIndex);
                }
            });
        }
    }

    /**
     * 创建标签
     *
     * @param text 文案
     * @return
     */
    private TextView onCreateTagView(String text) {
        TextView tag = new TextView(mContext);
        tag.setText(text);
        tag.setGravity(Gravity.CENTER);
        tag.setTextColor(tagTextColor);
        tag.setTextSize(TypedValue.COMPLEX_UNIT_SP, tagTextSize);
        tag.setBackgroundResource(tagBackground);
        LayoutParams paramsTag;
        if (tagHeight > 0 && tagWidth > 0) {
            paramsTag = new LayoutParams(tagWidth, tagHeight);
        } else {
            tag.setPadding(tagPaddingLeft, tagPaddingTop, tagPaddingRight, tagPaddingBottom);
            paramsTag = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        tag.setLayoutParams(paramsTag);

        addView(tag);
        mTags.add(tag);
        return tag;
    }


    private int dip2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext
                .getResources().getDisplayMetrics());
    }

    private int pxToSp(int px) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return (int) (displayMetrics.density * 160 / displayMetrics.densityDpi) * px;
    }

    /**
     * 获取屏幕的宽
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * tag点击事件处理
     *
     * @param view
     */
    private void onTagClick(View view, int position) {
        TextView tagTextView = ((TextView) view);
        if (position == 0 && isShowUnlimited) {//不限
            for (TextView textView : mSelectTag) {
                textView.setBackgroundResource(tagBackground);
            }
            mSelectTag.clear();
            mSelectTagsIndex.clear();
            mSelectTagsName.clear();

            tagTextView.setBackgroundResource(tagBackgroundSelect);
            mSelectTagsIndex.add(position + "");
            mSelectTagsName.add(tagTextView.getText().toString());
            mSelectTag.add(tagTextView);

            if (isMoreSelect) {//多选
                if (null != moreSelectClickListener) {
                    moreSelectClickListener.onClicked(position, tagTextView.getText().toString(), mSelectTagsIndex, mSelectTagsName);
                }
            } else {//单选
                if (null != singleSelectClickListener) {
                    singleSelectClickListener.onClicked(position, tagTextView.getText().toString());
                }
            }
        } else {
            if (isMoreSelect) {//支持多选
                if (mSelectTagsIndex.contains("0") && isShowUnlimited) {
                    for (TextView textView : mSelectTag) {
                        textView.setBackgroundResource(tagBackground);
                    }
                    mSelectTag.clear();
                    mSelectTagsName.clear();
                    mSelectTagsIndex.clear();
                }
                if (!mSelectTag.contains(tagTextView)) {
                    tagTextView.setBackgroundResource(tagBackgroundSelect);
                    mSelectTag.add(tagTextView);
                    mSelectTagsIndex.add(position + "");
                    mSelectTagsName.add(tagTextView.getText().toString());
                } else {
                    mSelectTag.remove(tagTextView);
                    mSelectTagsName.remove(tagTextView.getText().toString());
                    mSelectTagsIndex.remove(position + "");
                    tagTextView.setBackgroundResource(tagBackground);
                }

                if (null != moreSelectClickListener) {
                    if (null != mSelectTagsIndex && mSelectTagsIndex.size() > 0) {
                        moreSelectClickListener.onClicked(position, tagTextView.getText().toString(), mSelectTagsIndex, mSelectTagsName);
                    } else {
                        moreSelectClickListener.onClicked(-1, "", mSelectTagsIndex, mSelectTagsName);
                    }
                }
            } else if (isBackSelect) {//单选可反选
                if (mSelectTagsIndex.contains(position + "")) {
                    for (TextView textView : mSelectTag) {
                        textView.setBackgroundResource(tagBackground);
                    }

                    tagTextView.setBackgroundResource(tagBackground);
                    mSelectTag.clear();
                    mSelectTagsName.clear();
                    mSelectTagsIndex.clear();

                    if (null != singleSelectClickListener) {
                        singleSelectClickListener.onClicked(-1, "");
                    }
                } else {
                    for (TextView textView : mSelectTag) {
                        textView.setBackgroundResource(tagBackground);
                    }
                    mSelectTag.clear();
                    mSelectTagsName.clear();
                    mSelectTagsIndex.clear();

                    tagTextView.setBackgroundResource(tagBackgroundSelect);
                    mSelectTag.add(tagTextView);
                    mSelectTagsIndex.add(position + "");
                    mSelectTagsName.add(tagTextView.getText().toString());

                    if (null != singleSelectClickListener) {
                        singleSelectClickListener.onClicked(position, tagTextView.getText().toString());
                    }
                }

            } else {//单选

                if (!mSelectTag.contains(tagTextView)) {
                    for (TextView textView : mSelectTag) {
                        textView.setBackgroundResource(tagBackground);
                    }
                    mSelectTag.clear();
                    mSelectTagsName.clear();
                    mSelectTagsIndex.clear();

                    tagTextView.setBackgroundResource(tagBackgroundSelect);
                    mSelectTag.add(tagTextView);
                    mSelectTagsIndex.add(position + "");
                    mSelectTagsName.add(tagTextView.getText().toString());
                }
                if (null != singleSelectClickListener) {
                    singleSelectClickListener.onClicked(position, tagTextView.getText().toString());
                }
            }
        }
    }

    /**
     * 重置
     */
    public void onReset() {
        if (null != mSelectTag && mSelectTag.size() > 0) {
            for (TextView textView : mSelectTag) {
                textView.setBackgroundResource(tagBackground);
            }
            mSelectTag.clear();
        }
        if (null != mSelectTagsName && mSelectTagsName.size() > 0) {
            mSelectTagsName.clear();
        }
        if (null != mSelectTagsIndex && mSelectTagsIndex.size() > 0) {
            mSelectTagsIndex.clear();
        }

        //恢复默认项
        if (null != mTags && mTags.size() > 0) {
            for (TextView textView : mTags) {
                if (isShowUnlimited && isSelectUnlimited) {
                    if (!TextUtils.isEmpty(unlimitedText) && unlimitedText.equals(textView.getText().toString())) {
                        mSelectTag.add(textView);
                        mSelectTagsName.add(textView.getText().toString());
                        mSelectTagsIndex.add("0");
                        break;
                    }
                }
            }
        }
    }

    private OnSingleSelectClickListener singleSelectClickListener;
    private OnMoreSelectClickListener moreSelectClickListener;

    public interface OnSingleSelectClickListener {
        void onClicked(int position, String tag);
    }

    public interface OnMoreSelectClickListener {
        void onClicked(int position, String tag, List<String> mSelectIndex, List<String> mSelectTag);
    }

    public void setSingleSelectClickListener(OnSingleSelectClickListener singleSelectClickListener) {
        this.singleSelectClickListener = singleSelectClickListener;
    }

    public void setMoreSelectClickListener(OnMoreSelectClickListener moreSelectClickListener) {
        this.moreSelectClickListener = moreSelectClickListener;
    }
}
