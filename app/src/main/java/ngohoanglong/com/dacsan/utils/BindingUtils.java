package ngohoanglong.com.dacsan.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ViewAnimator;

import java.util.List;

import ngohoanglong.com.dacsan.utils.recyclerview.BaseRecyclerViewAdapter;
import ngohoanglong.com.dacsan.utils.rxview.TextChange;
import ngohoanglong.com.dacsan.utils.rxview.TextChangeAdapter;


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

    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerViewAdapter<T> adapter = (BaseRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) adapter.setItems(items);
    }
    @BindingAdapter("pageState")
    public static void setStateViewAnimator(final ViewAnimator viewAnimator, final int state) {
        viewAnimator.setDisplayedChild(state);
    }
}
