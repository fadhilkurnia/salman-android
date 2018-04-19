package com.salmanitb.alumnisalman.helper;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by Fadhil Imam Kurnia on 19/04/2018.
 */

public class ISwipeRefreshLayout extends SwipeRefreshLayout {
    public ISwipeRefreshLayout(Context context) {
        super(context);
    }

    public ISwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (isRefreshing()){
            return true;
        }
        return false;
    }
}
