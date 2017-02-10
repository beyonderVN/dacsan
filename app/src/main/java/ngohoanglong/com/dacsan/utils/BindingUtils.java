package ngohoanglong.com.dacsan.utils;

import android.databinding.BindingAdapter;
import android.widget.EditText;

import ngohoanglong.com.dacsan.utils.view.TextChange;
import ngohoanglong.com.dacsan.utils.view.TextChangeAdapter;


/**
 * Created by nongdenchet on 11/22/16.
 */

public class BindingUtils {

    @BindingAdapter("textChange")
    public static void textChange(final EditText editText, final TextChange textChange) {
        editText.addTextChangedListener(new TextChangeAdapter() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textChange.onChange(charSequence.toString());
            }
        });
    }
}
