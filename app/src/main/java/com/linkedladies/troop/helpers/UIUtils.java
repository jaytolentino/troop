package com.linkedladies.troop.helpers;

import android.content.Context;
import android.widget.Toast;

public class UIUtils {

    private Context context;

    public UIUtils(Context context) {
        this.context = context;
    }

    public void showToast(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
