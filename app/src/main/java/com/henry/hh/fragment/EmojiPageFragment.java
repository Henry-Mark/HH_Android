package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.henry.hh.R;
import com.henry.hh.adapter.EmojiAdapter;
import com.henry.hh.adapter.FacePagerAdapter;
import com.henry.hh.entity.Emojicon;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.hh.utils.DisplayRules;
import com.henry.library.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Emoji表情分类的显示
 * A simple {@link Fragment} subclass.
 */
public class EmojiPageFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    //每个页面共计28个图标
    private static final int ITEM_PAGE_COUNT = 28;

    private ViewPager mPagerFace;
    private LinearLayout pagePointLayout;
    private RadioButton[] pointViews;
    private List<Emojicon> datas;
    private List<RecyclerView> allPageViews;
    private OnOperationListener listener;
    private LinearLayoutManager mLayoutManager;

    public EmojiPageFragment() {
        // Required empty public constructor
    }


    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_emoji_page);
        mPagerFace = getViewById(R.id.frag_pager_face);
        pagePointLayout = getViewById(R.id.frag_point);
        mPagerFace.setOnPageChangeListener(this);
        datas = DisplayRules.getAllByType();

        int total = datas.size();
        int pages = total / ITEM_PAGE_COUNT
                + (total % ITEM_PAGE_COUNT == 0 ? 0 : 1);

        pointViews = new RadioButton[pages];
        allPageViews = new ArrayList<>();

        for (int x = 0; x < pages; x++) {
            int start = x * ITEM_PAGE_COUNT;
            int end = (start + ITEM_PAGE_COUNT) > total ? total
                    : (start + ITEM_PAGE_COUNT);
            final List<Emojicon> itemDatas = datas.subList(start, end);

            EmojiAdapter adapter = new EmojiAdapter(getActivity());
            RecyclerView view = new RecyclerView(getActivity());
            mLayoutManager = new GridLayoutManager(getActivity(), 7);
            view.setBackgroundResource(android.R.color.transparent);
            view.setVerticalScrollBarEnabled(false);
            view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));

            view.setLayoutManager(mLayoutManager);
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            view.setHasFixedSize(true);

            view.setAdapter(adapter);
            adapter.refresh(itemDatas);
            adapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(View view, List data, int position) {
                    if (listener != null) {
                        Emojicon emoji = itemDatas.get(position);
                        if (DisplayRules.isDeleteEmojicon(emoji)) {
                            listener.selectedBackSpace(emoji);
                        } else {
                            listener.selectedEmoji(emoji);
                        }
                    }
                }
            });

            allPageViews.add(x, view);

            RadioButton tip = new RadioButton(getActivity());
            tip.setBackgroundResource(R.drawable.selector_bg_tip);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    8, 8);
            layoutParams.leftMargin = 10;
            pagePointLayout.addView(tip, layoutParams);
            if (x == 0) {
                tip.setChecked(true);
            }
            pointViews[x] = tip;
        }

        PagerAdapter facePagerAdapter = new FacePagerAdapter(allPageViews);
        mPagerFace.setAdapter(facePagerAdapter);

    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pointViews[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
