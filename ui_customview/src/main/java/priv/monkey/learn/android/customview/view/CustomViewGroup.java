package priv.monkey.learn.android.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Monday
 * @date Create on 2018/8/17
 */
public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0;
        int height = 0;

        // 父View给的测量模式，如果父View不做特殊处理，则等于自己LayoutParams的mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 父View给的可用大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        // 同上
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 遍历子View，让子View计算大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            LayoutParams layoutParams = child.getLayoutParams();
            int widthSpec;
            int heightSpec;
            switch (layoutParams.width) {
                case LayoutParams.MATCH_PARENT:
                    widthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
                    break;
                case LayoutParams.WRAP_CONTENT:
                    widthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);
                    break;
                default:
                    widthSpec = MeasureSpec.makeMeasureSpec(layoutParams.width, MeasureSpec.EXACTLY);
            }

            switch (layoutParams.height) {
                case LayoutParams.MATCH_PARENT:
                    heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
                    break;
                case LayoutParams.WRAP_CONTENT:
                    heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);
                    break;
                default:
                    heightSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, MeasureSpec.EXACTLY);
            }

            child.measure(widthSpec, heightSpec);
            height += child.getMeasuredHeight();
            width = Math.max(width, child.getMeasuredWidth());

            switch (widthMode) {
                case MeasureSpec.EXACTLY:
                    width = widthSize;
                    break;
                case MeasureSpec.AT_MOST:
                    width = Math.min(width,widthSize);
                    break;
                default:
            }

            switch (heightMode) {
                case MeasureSpec.EXACTLY:
                    height = heightSize;
                    break;
                case MeasureSpec.AT_MOST:
                    height = Math.min(height, heightSize);
                    break;
                default:
            }

            setMeasuredDimension(width, height);


        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
