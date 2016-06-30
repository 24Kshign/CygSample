package com.example.jack.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jack.activity.AutomaticLoginActivity;
import com.example.jack.activity.ChangeButtonActivity;
import com.example.jack.activity.AutoTextView;
import com.example.jack.activity.DrawerLayoutActivity;
import com.example.jack.activity.GuideActivity;
import com.example.jack.activity.PressActivity;
import com.example.jack.activity.ProvinceTwoMenuActivity;
import com.example.jack.activity.R;
import com.example.jack.activity.SearchActivity;
import com.example.jack.activity.SlidindMenuActivity;
import com.example.jack.activity.ThreeMenuActivity;
import com.example.jack.activity.WeChatShareActivity;
import com.example.jack.activity.WebViewActivity;

/**
 * Created by Jack on 2015/9/4.
 */
public class SchoolFragment extends Fragment implements View.OnClickListener {

    private View mSchoolView;
    private Button btnChangeButton;
    private Button btnWebview;
    private Button btnSliding;
    private Button btnDrawerLayout;
    private Button btnGuide;
    private Button btnAutomaticLogin;
    private Button btnTwoMenu;
    private Button btnThreeMenu;
    private Button btnPress;
    private Button btnMsg;
    private Button btnWeChat;
    private Button btnSearch;
    private Button btnCustonSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSchoolView = inflater.inflate(R.layout.fragment_school, null);
        initeView();
        return mSchoolView;
    }

    private void initeView() {
        btnChangeButton = (Button) mSchoolView.findViewById(R.id.fs_btn_change_button);
        btnWebview = (Button) mSchoolView.findViewById(R.id.fs_btn_webview);
        btnSliding = (Button) mSchoolView.findViewById(R.id.fs_btn_sliding);
        btnDrawerLayout = (Button) mSchoolView.findViewById(R.id.fs_btn_drawerlayout);
        btnGuide = (Button) mSchoolView.findViewById(R.id.fs_btn_guide);
        btnAutomaticLogin = (Button) mSchoolView.findViewById(R.id.fs_btn_automatic_login);
        btnTwoMenu = (Button) mSchoolView.findViewById(R.id.fs_btn_two_menu);
        btnThreeMenu = (Button) mSchoolView.findViewById(R.id.fs_btn_three_menu);
        btnPress = (Button) mSchoolView.findViewById(R.id.fs_btn_press);
        btnMsg = (Button) mSchoolView.findViewById(R.id.fs_btn_msg);
        btnWeChat = (Button) mSchoolView.findViewById(R.id.fs_btn_we_chat);
        btnSearch = (Button) mSchoolView.findViewById(R.id.fs_btn_search);
        btnCustonSearch = (Button) mSchoolView.findViewById(R.id.fs_btn_custom_search);

        btnAutomaticLogin.setOnClickListener(this);
        btnGuide.setOnClickListener(this);
        btnSliding.setOnClickListener(this);
        btnWebview.setOnClickListener(this);
        btnChangeButton.setOnClickListener(this);
        btnDrawerLayout.setOnClickListener(this);
        btnTwoMenu.setOnClickListener(this);
        btnThreeMenu.setOnClickListener(this);
        btnPress.setOnClickListener(this);
        btnMsg.setOnClickListener(this);
        btnWeChat.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnCustonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fs_btn_change_button:
                startActivity(new Intent(getActivity(), ChangeButtonActivity.class));
                break;
            case R.id.fs_btn_webview:
                startActivity(new Intent(getActivity(), WebViewActivity.class));
                break;
            case R.id.fs_btn_sliding:
                startActivity(new Intent(getActivity(), SlidindMenuActivity.class));
                break;
            case R.id.fs_btn_drawerlayout:
                startActivity(new Intent(getActivity(), DrawerLayoutActivity.class));
                break;
            case R.id.fs_btn_guide:
                startActivity(new Intent(getActivity(), GuideActivity.class));
                break;
            case R.id.fs_btn_automatic_login:
                startActivity(new Intent(getActivity(), AutomaticLoginActivity.class));
                break;
            case R.id.fs_btn_two_menu:
                startActivity(new Intent(getActivity(), ProvinceTwoMenuActivity.class));
                break;
            case R.id.fs_btn_three_menu:
                startActivity(new Intent(getActivity(), ThreeMenuActivity.class));
                break;
            case R.id.fs_btn_press:
                startActivity(new Intent(getActivity(), PressActivity.class));
                break;
            case R.id.fs_btn_msg:
                //调用系统拨号盘打电话
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:17764576286"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.fs_btn_we_chat:
                startActivity(new Intent(getActivity(), WeChatShareActivity.class));
                break;
            case R.id.fs_btn_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.fs_btn_custom_search:
                startActivity(new Intent(getActivity(), AutoTextView.class));
                break;
        }
    }
}
