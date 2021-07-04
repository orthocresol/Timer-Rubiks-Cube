package org.timerrubikscube;

import android.view.View;
import android.widget.Button;

import org.timerrubikscube.nonactivityclass.Item;

import java.util.ArrayList;
import java.util.Collections;

public class JavaClassForKotlinConversion {
    Button b = null;
    public void array(){
        float result = 10.3434f;
        String toShow = String.format("%.2f", result);
        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }
}
