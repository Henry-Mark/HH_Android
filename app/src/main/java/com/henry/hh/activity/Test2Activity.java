package com.henry.hh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;

public class Test2Activity extends MyBaseActivity implements View.OnClickListener {
    //存放Fragment
    private FrameLayout mFragment;
    //聊天室、生活圈、探索园、我的家 TextView控件
    private TextView mTvChatting, mTvLivingCicle, mTvExploration, mTvMyhome;
    //聊天室、生活圈、探索园、我的家 ImageView控件
    private ImageView mIvChatting, mIvLivingCicle, mIvExploration, mIvMyhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mFragment = getViewById(R.id.main_fragment);
        mIvChatting = getViewById(R.id.img_footer_chatting);
        mIvLivingCicle = getViewById(R.id.img_footer_livingcicle);
        mIvExploration = getViewById(R.id.img_footer_explorationpark);
        mIvMyhome = getViewById(R.id.img_footer_myhome);
        mTvChatting = getViewById(R.id.title_footer_chatting);
        mTvLivingCicle = getViewById(R.id.title_footer_livingcicle);
        mTvExploration = getViewById(R.id.title_footer_explorationpark);
        mTvMyhome = getViewById(R.id.title_footer_myhome);

    }











//
//
//
//
//    package cn.com.liandisys.mrlmt.phone.activity;
//
//    import org.jivesoftware.smackx.muc.MultiUserChat;
//
//    import android.os.AsyncTask;
//    import android.os.Bundle;
//    import android.util.Log;
//    import android.view.View;
//    import android.widget.ImageView;
//    import android.widget.LinearLayout;
//    import android.widget.RelativeLayout;
//    import android.widget.TextView;
//    import cn.com.liandisys.mrlmt.common.broadcast.MainActivityBroadcastReceiver.MessageType;
//    import cn.com.liandisys.mrlmt.common.fragment.base.BusinessBaseFragment;
//    import cn.com.liandisys.mrlmt.fw.util.ToastUtil;
//    import cn.com.liandisys.mrlmt.fw.webrtc.WebRTCClient.UserInfo;
//    import cn.com.liandisys.mrlmt.phone.R;
//    import cn.com.liandisys.mrlmt.phone.exception.PhoneWebRTCExceptionHandler;
//    import cn.com.liandisys.mrlmt.phone.fragment.ConferenceFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.ConferencingFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.FileDetailFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.FileEditFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.FileFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.LiveFragment;
//    import cn.com.liandisys.mrlmt.phone.fragment.MoreFragment;
//
//    /**
//     * 主页Activity
//     */
//    public class MainActivity
//            extends cn.com.liandisys.mrlmt.common.activity.MainActivity {
//
//        /**
//         * Tab索引
//         */
//        public static class TabIndex extends
//                cn.com.liandisys.mrlmt.common.activity.MainActivity.TabIndex {
//
//            /**
//             * 直播界面
//             */
//            public static final int LIVE = 0;
//
//            /**
//             * 文件界面
//             */
//            public static final int FILE = 2;
//
//            /**
//             * 更多界面
//             */
//            public static final int MORE = 3;
//
//            /**
//             * 文件详情界面
//             */
//            public static final int FILE_DETAIL = 5;
//
//            /**
//             * 文件编辑界面
//             */
//            public static final int FILE_EDIT = 6;
//
//        }
//
//        /**
//         * 直播布局
//         */
//        private RelativeLayout lytLive;
//
//        /**
//         * 视频会议布局
//         */
//        private RelativeLayout lytConference;
//
//        /**
//         * 文件布局
//         */
//        private RelativeLayout lytFile;
//
//        /**
//         * 更多布局
//         */
//        private LinearLayout lytMore;
//
//        /**
//         * 直播图标
//         */
//        private ImageView ivLive;
//
//        /**
//         * 视频会议图标
//         */
//        private ImageView ivConference;
//
//        /**
//         * 文件图标
//         */
//        private ImageView ivFile;
//
//        /**
//         * 更多图标
//         */
//        private ImageView ivMore;
//
//        /**
//         * 直播工作图标
//         */
//        private ImageView ivLiveWorking;
//
//        /**
//         * 视频会议工作图标
//         */
//        private ImageView ivConferenceWorking;
//
//        /**
//         * 文件工作图标
//         */
//        private ImageView ivFileWorking;
//
//        // /**
//        // * 场景名标签
//        // */
//        // private TextView tvScenarioName;
//
//        // /**
//        // * 其他按钮
//        // */
//        // private ImageButton ibOther;
//
//        /***
//         * 直播 字体
//         */
//        private TextView live_tv;
//
//        /***
//         * 会议 字体
//         */
//        private TextView conference_tv;
//
//        /***
//         * 文件字体
//         */
//        private TextView file_tv;
//
//        /***
//         * 更多 字体
//         */
//        private TextView more_tv;
//
//        /*
//         * (non-Javadoc)
//         * @see cn.com.liandisys.mrlmt.common.activity.base.BusinessBaseWebRTCActivity#onCreate(android.os.Bundle)
//         */
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//
//            super.onCreate(savedInstanceState);
//
//            Log.d("TAG", "Phone-Main--onCreate()");
//
//            // WebRTC异常处理者
//            this.exceptionHandler = new PhoneWebRTCExceptionHandler(this);
//
//            setContentView(R.layout.activity_main);
//
//            // 初始化
//            init();
//
//            switchTab(TabIndex.LIVE);
//        }
//
//        /**
//         * 初如化
//         */
//        private void init() {
//
//            // 表示初次刚进主页面
//            tabIndex = -1;
//            tabFileIndex = TabIndex.FILE;
//
//            // 直播布局
//            lytLive = (RelativeLayout) findViewById(R.id.main_lyt_live);
//            lytLive.setOnClickListener(this);
//            // 视频会议布局
//            lytConference = (RelativeLayout) findViewById(R.id.main_lyt_conference);
//            lytConference.setOnClickListener(this);
//            // 文件布局
//            lytFile = (RelativeLayout) findViewById(R.id.main_lyt_file);
//            lytFile.setOnClickListener(this);
//            // 更多布局
//            lytMore = (LinearLayout) findViewById(R.id.main_lyt_more);
//            lytMore.setOnClickListener(this);
//            // 直播图标
//            ivLive = (ImageView) lytLive.findViewById(R.id.main_iv_live);
//            // 视频会议图标
//            ivConference =
//                    (ImageView) lytConference.findViewById(R.id.main_iv_conference);
//            // 文件图标
//            ivFile = (ImageView) lytFile.findViewById(R.id.main_iv_file);
//            // 更多图标
//            ivMore = (ImageView) lytMore.findViewById(R.id.main_iv_more);
//            // 直播工作图标
//            ivLiveWorking =
//                    (ImageView) lytLive.findViewById(R.id.main_iv_live_working);
//            // 视频会议工作图标
//            ivConferenceWorking = (ImageView) lytConference
//                    .findViewById(R.id.main_iv_conference_working);
//            // 文件工作图标
//            ivFileWorking =
//                    (ImageView) lytFile.findViewById(R.id.main_iv_file_working);
//            // 其他按钮
//            // ibOther = (ImageButton) findViewById(R.id.main_ib_other);
//            // ibOther.setOnClickListener(this);
//
//            // 切换更换live字体颜色
//            live_tv = (TextView) findViewById(R.id.live_tv);
//            // 切换更换conference字体颜色
//            conference_tv = (TextView) findViewById(R.id.conference_tv);
//            // 切换更换file字体颜色
//            file_tv = (TextView) findViewById(R.id.file_tv);
//            // 切换更换more字体颜色
//            more_tv = (TextView) findViewById(R.id.more_tv);
//        }
//
//        /**
//         * 刷新页面语言
//         */
//        @Override
//        protected void refreshLanguage() {
//
//            // live_tv 的Text文言
//            live_tv.setText(R.string.main_tv_live);
//            // conference_tv 的Text文言
//            conference_tv.setText(R.string.main_tv_conference);
//            // file_tv 的Text文言
//            file_tv.setText(R.string.main_tv_flie);
//            // more_tv 的Text文言
//            more_tv.setText(R.string.main_tv_more);
//
//        }
//
//        /*
//         * (non-Javadoc)
//         * @see android.view.View.OnClickListener#onClick(android.view.View)
//         */
//        @Override
//        public void onClick(View v) {
//
//            if (isLanguageChanged()) {
//
//                refreshLanguage();
//            }
//
//            switch (v.getId()) {
//                case R.id.main_lyt_live: // 直播
//
//                    // 设置选中的tab页
//                    switchTab(TabIndex.LIVE);
//                    break;
//                case R.id.main_lyt_conference: // 视频会议
//                    int index = getBusinessApplication().getGlobalData()
//                            .isConferenceWorking()
//                            ? TabIndex.CONFERENCING
//                            : TabIndex.CONFERENCE;
//
//                    // 设置选中的tab页
//                    switchTab(index);
//                    break;
//                case R.id.main_lyt_file: // 文件
//
//                    // 设置选中的tab页
//                    switchTab(tabFileIndex);
//
//                    break;
//                case R.id.main_lyt_more: // 更多
//
//                    // // 设置选中的tab页
//                    switchTab(TabIndex.MORE);
//
//                    break;
//                // case R.id.main_ib_other:
//                //
//                // // 设置选中的tab页
//                // switchTab(TabIndex.MORE);
//                // break;
//
//                default:
//                    break;
//            }
//        }
//
//        /*
//         * (non-Javadoc)
//         * @see
//         * cn.com.liandisys.mrlmt.common.activity.base.BusinessBaseWebRTCActivity#onJoinRoom(org.jivesoftware.smackx.muc
//         * .MultiUserChat, java.lang.String, org.jivesoftware.smack.RosterEntry)
//         */
//        @Override
//        public void onJoinRoom(MultiUserChat muc, UserInfo userInfo) {
//
//            super.onJoinRoom(muc, userInfo);
//
//            Log.d("join", "MainActivity--onJoinRoom()");
//
//            BusinessBaseFragment fragment = getFragment(TabIndex.CONFERENCING);
//            if (fragment == null) {
//                fragment = new ConferencingFragment();
//            }
//
//            showFragment(tabIndex,
//                    TabIndex.CONFERENCING,
//                    fragment,
//                    R.id.main_lyt_fragment);
//
//            tabIndex = TabIndex.CONFERENCING;
//
//            getGlobalData().setConferenceWorking(true);
//
//            runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//
//                    // 切换每个Tab的工作图标
//                    switchTabWorkingIcon();
//
//                    // 设置Tab的背景
//                    switchTabBackground(TabIndex.CONFERENCING);
//                }
//            });
//        }
//
//        /**
//         * 切换Tab
//         *
//         * @param index Tab索引
//         * @param bundle fragment参数
//         */
//        @Override
//        protected void switchTab(int index, Bundle bundle) {
//
//            Log.d("TAG", "switchTab:" + index);
//            // 切换每个Tab的工作图标
//            switchTabWorkingIcon();
//
//            // 设置Tab的背景
//            switchTabBackground(index);
//
//            if (index == TabIndex.FILE_DETAIL)
//                tabFileIndex = TabIndex.FILE_DETAIL;
//            else if (index == TabIndex.FILE)
//                tabFileIndex = TabIndex.FILE;
//            else if (index == TabIndex.FILE_EDIT)
//                tabFileIndex = TabIndex.FILE_EDIT;
//
//            BusinessBaseFragment fragment = getFragment(index);
//            if (fragment == null) {
//                switch (index) {
//                    case TabIndex.LIVE: // 直播界面
//                        fragment = new LiveFragment();
//                        break;
//                    case TabIndex.CONFERENCE: // 视频会议主界面
//                        fragment = new ConferenceFragment();
//                        break;
//                    case TabIndex.CONFERENCING: // 视频会议进行界面
//                        fragment = new ConferencingFragment();
//                        break;
//                    case TabIndex.FILE: // 文件界面
//                        // tabFileIndex = TabIndex.FILE;
//                        fragment = new FileFragment();
//                        break;
//                    case TabIndex.FILE_DETAIL: // 文件详情界面
//                        // tabFileIndex = TabIndex.FILE_DETAIL;
//                        fragment = new FileDetailFragment();
//                        break;
//                    case TabIndex.FILE_EDIT: // 文件编辑界面
//                        // tabFileIndex = TabIndex.FILE_EDIT;
//                        fragment = new FileEditFragment();
//                        break;
//                    case TabIndex.MORE: // 更多界面
//                        fragment = new MoreFragment();
//                        break;
//                }
//            }
//
//            // 设置参数
//            setFragmentArgument(fragment, bundle);
//
//            // // 记住上一次的Tab索引
//            // getGlobalData().setLastIndex(tabIndex);
//            ConferencingFragment conferencingFragment =
//                    (ConferencingFragment) getFragment(TabIndex.CONFERENCING);
//
//            if (index == TabIndex.CONFERENCING
//                    && !getGlobalData().isConferenceWorking()) {
//                joinRoomTask = new JoinRoomTask(this);
//                joinRoomTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            } else {
//                // 视频会议画面退出，进入视频会议入口画面
//                if (index == TabIndex.CONFERENCE && conferencingFragment != null) {
//                    exitConferencingFragment(R.id.main_lyt_fragment, fragment);
//                    conferencingFragment = null;
//                } else {
//                    showFragment(tabIndex, index, fragment, R.id.main_lyt_fragment);
//                }
//
//                tabIndex = index;
//            }
//        }
//
//        /**
//         * 切换每个Tab的工作图标
//         */
//        @Override
//        protected void switchTabWorkingIcon() {
//
//            // 直播工作图标
//            if (!getGlobalData().isLiveWorking()) {
//
//                ivLiveWorking.setVisibility(View.GONE);
//
//            } else {
//
//                ivLiveWorking.setVisibility(View.VISIBLE);
//
//            }
//
//            // 视频会议工作图标
//            if (!getGlobalData().isConferenceWorking()) {
//
//                ivConferenceWorking.setVisibility(View.GONE);
//
//            } else {
//
//                ivConferenceWorking.setVisibility(View.VISIBLE);
//
//            }
//
//            // 文件工作图标
//            if (!getGlobalData().isFileWorking()) {
//
//                ivFileWorking.setVisibility(View.GONE);
//
//            } else {
//
//                ivFileWorking.setVisibility(View.VISIBLE);
//
//            }
//        }
//
//        /**
//         * 设置Tab按钮的背景
//         *
//         * @param index Tab索引
//         */
//        @Override
//        protected void switchTabBackground(int index) {
//
//            // 直播图标
//            ivLive.setImageResource(R.drawable.live_available);
//            // 视频会议图标
//            ivConference.setImageResource(R.drawable.conference_available);
//            // 文件图标
//            ivFile.setImageResource(R.drawable.document_available);
//            // 更多图标
//            ivMore.setImageResource(R.drawable.more_available);
//
//            live_tv.setTextColor(
//                    MainActivity.this.getResources().getColor(R.color.c_B4B4B5));
//
//            conference_tv.setTextColor(
//                    MainActivity.this.getResources().getColor(R.color.c_B4B4B5));
//
//            file_tv.setTextColor(
//                    MainActivity.this.getResources().getColor(R.color.c_B4B4B5));
//
//            more_tv.setTextColor(
//                    MainActivity.this.getResources().getColor(R.color.c_B4B4B5));
//
//            switch (index) {
//                case TabIndex.LIVE: // 直播界面
//                    ivLive.setImageResource(R.drawable.live_activate);
//                    live_tv.setTextColor(MainActivity.this.getResources()
//                            .getColor(R.color.c_00FF77));
//                    break;
//                case TabIndex.CONFERENCE: // 视频会议主界面
//                case TabIndex.CONFERENCING: // 视频会议进行界面
//                    ivConference.setImageResource(R.drawable.conference_activate);
//                    conference_tv.setTextColor(MainActivity.this.getResources()
//                            .getColor(R.color.c_00FF77));
//                    break;
//                case TabIndex.FILE: // 文件界面
//                case TabIndex.FILE_DETAIL: // 文件详情界面
//                case TabIndex.FILE_EDIT: // 文件编辑界面
//
//                    ivFile.setImageResource(R.drawable.document_press);
//                    file_tv.setTextColor(MainActivity.this.getResources()
//                            .getColor(R.color.c_00FF77));
//                    break;
//                case TabIndex.MORE: // 更多界面
//                    ivMore.setImageResource(R.drawable.more_activate);
//                    more_tv.setTextColor(MainActivity.this.getResources()
//                            .getColor(R.color.c_00FF77));
//                    break;
//            }
//        }
//
//        /**
//         * 状态栏消息提示
//         *
//         * @param resId 显示的文言资源ID
//         * @param messageType 消息类型
//         */
//        protected void showStatusbarsMsg(int resId, MessageType messageType,
//                                         Object... formatArgs) {
//
//            ToastUtil.showLongToast(this, resId, formatArgs);
//        }
//
//        /**
//         * Dialog消息提示
//         *
//         * @param resId 显示的文言资源ID
//         * @param messageType 消息类型
//         */
//        protected void showDialogMsg(int resId, MessageType messageType,
//                                     Object... formatArgs) {
//
//            ToastUtil.showLongToast(this, resId, formatArgs);
//        }
//
//        /*
//         * (non-Javadoc)
//         * @see cn.com.liandisys.mrlmt.common.activity.MainActivity#onDestroy()
//         */
//        @Override
//        protected void onDestroy() {
//
//            super.onDestroy();
//            // 退出时工作状态置为False
//            // 设置直播Tab的工作状态
//            getGlobalData().setLiveWorking(false);
//            // 设置视频会议Tab的工作状态
//            getGlobalData().setConferenceWorking(false);
//            // 设置文档管理Tab的工作状态
//            getGlobalData().setFileWorking(false);
//
//            // 设置Tab的背景
//            switchTabWorkingIcon();
//
//        }
//    }

}
