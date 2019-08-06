package com.widget.mlistview;

import android.content.Context;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.toolutils.R;
import com.widget.MResource;

/**
 * User: guoxiaomeng
 * Date: 2019-08-06
 * Time: 08:53
 */
public class RListView extends ListView implements AbsListView.OnScrollListener {

    private Context context;
    private View view;
    private int headerHeight;
    final int NONE = 0;// 正常状态；
    final int PULL = 1;// 提示下拉状态；
    final int RELESE = 2;// 提示释放状态；
    final int REFLASHING = 3;// 刷新状态；

    public void setReflashListener(ReflashListener reflashListener) {
        this.reflashListener = reflashListener;
    }

    private ReflashListener reflashListener;

    public RListView(Context tmpcontext) {
        super(tmpcontext);
        context = tmpcontext;
        init();
    }

    public RListView(Context tmpcontext, AttributeSet attrs, int defStyle) {
        super(tmpcontext, attrs, defStyle);
        context = tmpcontext;
        init();
    }

    public RListView(Context tmpcontext, AttributeSet attrs) {
        super(tmpcontext, attrs);
        context = tmpcontext;
        init();
    }

    //初如化
    private void init() {
        try {
            LayoutInflater inflate = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.rlistview_head_layout, null);
            measureview(view);
            headerHeight = view.getMeasuredHeight();
            Log.i("tag", "headerHeight=" + headerHeight);
            topPadding(-headerHeight);  //负的高度，把它能没啦
            this.addHeaderView(view);
            this.setOnScrollListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void topPadding(int topPadding) {
        view.setPadding(view.getPaddingLeft(), topPadding, view.getPaddingRight(), view.getPaddingBottom());
        view.invalidate();
    }

    //计算头部局占的高度
    private void measureview(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int width = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int height;
        int tempHeight = layoutParams.height;
        if (tempHeight > 0) {
            //高度不是空，需要填充这个布局
            //EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
            height = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            //UNSPECIFIED(未指定),父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小；
            height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);
    }


    //滚动状态
    private int mScrollState = 0;
    //第一个item
    private int mFirstVisibleItem;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mScrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
    }

    private boolean isRemark = false;
    private int startY = 0;
    private int state = 0;//当前状态

    //判断点触位置
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:  //下拉
                if (mFirstVisibleItem == 0) { //在界面最顶端
                    isRemark = true;     //标记，当前是在listview的最顶部摁下的
                    startY = (int) ev.getY();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                onMove(ev);
                break;
            case MotionEvent.ACTION_UP:  //抬起
                if (state == RELESE) {  //释放
                    state = REFLASHING; //正在刷新
                    // 加载最新数据；
                    reflashViewByState();
                    reflashListener.onReflash();
                } else if (state == PULL) {  //下拉过程中，没到一定距离的时候
                    state = NONE;  // 正常状态；
                    isRemark = false;   //在顶部的标记
                    reflashViewByState();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /*
     * 改变当前页面状态
     * */
    private void reflashViewByState() {
        //TextView tip = (TextView) view.findViewById(MResource.getIdByName(context.getApplicationContext(), "id", "tv_txt"));

        RotateAnimation animation = new RotateAnimation(0, 180
                , RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        RotateAnimation animation2 = new RotateAnimation(180, 0
                , RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation2.setDuration(500);
        animation2.setFillAfter(true);
        switch (state) {
            case NONE:
                //arrow.clearAnimation();
                topPadding(-headerHeight);
                break;
            case PULL:     //下拉
                //arrow.setVisibility(View.VISIBLE);
                //bar.setVisibility(View.GONE);
                //tip.setText("下拉可以刷新");
                //arrow.clearAnimation();
                //arrow.setAnimation(animation2);
                break;
            case RELESE:    //释放
                //arrow.setVisibility(View.VISIBLE);
                //bar.setVisibility(View.GONE);
                //tip.setText("松开可以刷新");
                //arrow.clearAnimation();
                //arrow.setAnimation(animation);
                break;
            case REFLASHING:  //刷新
                topPadding(50);
                // arrow.setVisibility(View.GONE);
                //bar.setVisibility(View.VISIBLE);
                //tip.setText("正在刷新");
                //arrow.clearAnimation();
                break;

            default:
                break;
        }
    }

    private void onMove(MotionEvent ev) {
        if (!isRemark) {  //不在顶的时候移动
            return;
        }
        //移动的过程中状态是不断改变的
        int tempY = (int) ev.getY();
        int sapce = tempY - startY;  //移动的距离
        int topPadding = sapce - headerHeight; //移动过程中不断设置topPadding
        switch (state) {
            case NONE:   // 正常状态；
                if (sapce > 0) {
                    state = PULL; // 提示下拉状态
                    reflashViewByState();
                }
                break;
            case PULL:   // 提示下拉状态；
                topPadding(topPadding);  //不断设置上边距
                if (sapce > headerHeight + 30
                        && mScrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    state = RELESE;  // 提示释放状态；
                    reflashViewByState();
                }

                break;
            case RELESE:    // 提示释放状态；
                topPadding(topPadding);
                if (sapce < headerHeight + 30) {
                    state = PULL;
                    reflashViewByState();
                } else if (sapce <= 0) {
                    state = NONE;
                    isRemark = false;
                    reflashViewByState();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取完数据
     */
    public void reflashComplete() {
        state = NONE;
        isRemark = false;  //不在顶部
        reflashViewByState();
      /*  TextView lastupdatetime = (TextView) view.findViewById(R.id.lastUpdate_time);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        lastupdatetime.setText(time);*/

    }
}
