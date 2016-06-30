package com.example.jack.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jack.activity.R;

/**
 * Created by Jack on 2015/9/11.
 */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String title;           //标题文字
        private String message;       //内容文字
        private String positiveButtonText;        //确定按钮的文字
        private String negativeButtonText;        //取消按钮的文字

        private DialogInterface.OnClickListener positiveButtonClickListener;    //确定按钮的点击事件
        private DialogInterface.OnClickListener negativeButtonClickListener;    //取消按钮的点击事件

        private View contentView;       //表示dialog的布局

        public Builder(Context context) {
            this.context = context;
        }

        //Set the Dialog message from String
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        //Set the Dialog message from resource
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        //Set the Dialog title from String
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        //Set the Dialog title from resource
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        //Set the positive button resource and it's listener
        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener positiveButtonListener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = positiveButtonListener;
            return this;
        }

        //Set the positive button text and it's listener
        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener positiveButtonListener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = positiveButtonListener;
            return this;
        }

        //Set the negative button resource and it's listener
        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener negativeButtonClickListener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        //Set the negative button text and it's listener
        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener negativeButtonClickListener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        //创建dialog
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 给dialog设置一个样式
            final CustomDialog dialog = new CustomDialog(context, R.style.custom_dialog_style);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.dialog_custom_dialog, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // 设置dialog的提示信息
            ((TextView) layout.findViewById(R.id.dialog_tv_tip)).setText(title);

            // 设置确定按钮
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.dialog_btn_sure)).setText(positiveButtonText);
                if (positiveButtonClickListener != null)
                    ((Button) layout.findViewById(R.id.dialog_btn_sure)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
            } else {
                // 如果没有确定按钮，设置取消按钮为隐藏
                layout.findViewById(R.id.dialog_btn_sure).setVisibility(View.GONE);
            }

            // 设置取消按钮
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.dialog_btn_cancel)).setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.dialog_btn_cancel)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                // 如果没有取消按钮，设置取消按钮为隐藏
                layout.findViewById(R.id.dialog_btn_cancel).setVisibility(View.GONE);
            }

            // 设置内容部分的信息
            if (message != null) {
                ((TextView) layout.findViewById(R.id.dialog_tv_content)).setText(message);
            } else if (contentView != null) {
                // 如果没有信息
                // 把contentView加到dialog上
                ((RelativeLayout) layout.findViewById(R.id.dialog_rv_content)).removeAllViews();
                ((RelativeLayout) layout.findViewById(R.id.dialog_rv_content)).addView(contentView,
                        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }

    }
}
