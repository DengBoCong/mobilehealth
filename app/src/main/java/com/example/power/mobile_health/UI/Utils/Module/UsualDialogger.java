package com.example.power.mobile_health.UI.Utils.Module;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.power.mobile_health.R;

/**
 * Created by Power on 2019/1/11.
 */

public class UsualDialogger extends Dialog {
    private final String TITLE;
    private final String MESSAGE;
    private final String CONFIRMTEXT;
    private final String CANCELTEXT;
    private final onConfirmClickListener ONCONFIRMCLICKLISTENER;
    private final onCancelClickListener ONCANCELCLICKLISTENER;

    public interface onConfirmClickListener{
        void onClick(View view);
    }

    public interface onCancelClickListener {
        void onClick(View view);
    }

    private UsualDialogger(@NonNull Context context, String title, String message, String confirmText, String cancelText,
                           onConfirmClickListener onConfirmClickListener, onCancelClickListener onCancelClickListener) {
        super(context, R.style.UsualDialog);
        this.TITLE = title;
        this.MESSAGE = message;
        this.CONFIRMTEXT = confirmText;
        this.CANCELTEXT = cancelText;
        this.ONCONFIRMCLICKLISTENER = onConfirmClickListener;
        this.ONCANCELCLICKLISTENER = onCancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usual_dialog);
        setCanceledOnTouchOutside(true);
        initView();
    }

    public static Builder Builder(Context context){
        return new Builder(context);
    }

    private void initView(){
        Button confirmButton = (Button)findViewById(R.id.dialog_btn_confirm);
        Button cancelButton = (Button)findViewById(R.id.dialog_btn_cancel);
        TextView titleView = (TextView)findViewById(R.id.dialog_tv_title);
        TextView messageView = (TextView)findViewById(R.id.dialog_tv_content);
        messageView.setMovementMethod(ScrollingMovementMethod.getInstance());//设置滑动

        if(!TITLE.isEmpty()){
            titleView.setText(TITLE);
        }
        if(!MESSAGE.isEmpty()){
            messageView.setText(MESSAGE);
        }
        if(!CONFIRMTEXT.isEmpty()){
            confirmButton.setText(CONFIRMTEXT);
        }
        if(!CANCELTEXT.isEmpty()){
            cancelButton.setText(CANCELTEXT);
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ONCONFIRMCLICKLISTENER == null) {
                    throw new NullPointerException("系统组件化出错！");
                } else {
                    ONCONFIRMCLICKLISTENER.onClick(view);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(ONCANCELCLICKLISTENER == null){
                    throw new NullPointerException("系统组件化出错！");
                }else{
                    ONCANCELCLICKLISTENER.onClick(view);
                }
            }
        });
    }

    public UsualDialogger shown(){
        show();
        return this;
    }

    public static class Builder{
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private onConfirmClickListener mOnConfirmClickListener;
        private onCancelClickListener mOnCcancelClickListener;
        private Context mContext;

        private Builder(Context context){
            this.mContext = context;
        }

        public Builder setTitle(String title){
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message){
            this.mMessage = message;
            return this;
        }

        public Builder setOnConfirmClickListener(String confirmText, onConfirmClickListener confirmClickListener){
            this.mConfirmText = confirmText;
            this.mOnConfirmClickListener = confirmClickListener;
            return this;
        }

        public Builder setOnCancelClickListener(String cancelText, onCancelClickListener cancelClickListener){
            this.mCancelText = cancelText;
            this.mOnCcancelClickListener = cancelClickListener;
            return this;
        }

        public UsualDialogger build(){
            return new UsualDialogger(mContext, mTitle, mMessage, mConfirmText, mCancelText,
                    mOnConfirmClickListener, mOnCcancelClickListener);
        }
    }
}
