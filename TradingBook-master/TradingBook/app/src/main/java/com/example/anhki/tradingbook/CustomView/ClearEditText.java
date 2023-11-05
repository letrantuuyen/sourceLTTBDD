package com.example.anhki.tradingbook.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.anhki.tradingbook.R;


@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {

    Drawable clear, unclear;
    Boolean visible = false;
    Drawable drawable;

    public ClearEditText(Context context) {
        super(context);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        khoitao();
    }

    private void khoitao(){
        clear = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        unclear = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent);
        caidat();
    }

    private void  caidat(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? clear:unclear;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (MotionEvent.ACTION_DOWN == event.getAction() && event.getX() >= (getWidth() - drawable.getBounds().width())){
            setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0){
            visible = false;
            caidat();
        } else {
            visible = true;
            caidat();
        }
    }
}
