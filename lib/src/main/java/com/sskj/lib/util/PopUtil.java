package com.sskj.lib.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sskj.common.adapter.ItemClickSupport;
import com.sskj.common.adapter.slimadapter.IViewInjector;
import com.sskj.common.adapter.slimadapter.SlimAdapter;
import com.sskj.common.adapter.slimadapter.SlimInjector;
import com.sskj.lib.R;
import com.sskj.lib.widget.SpinnerPop;

import java.util.Arrays;
import java.util.List;

public class PopUtil {
    public static SpinnerPop popSpinner(Context context, ItemClickSupport.OnItemClickListener onItemClickListener, String... strs) {
        List<String> strings = Arrays.asList(strs);
        return popSpinner(context, onItemClickListener, strings);
    }

    public static SpinnerPop popSpinner(Context context, ItemClickSupport.OnItemClickListener onItemClickListener, List<String> strs) {
        try {
            SpinnerPop spinnerPop = new SpinnerPop(context);
            View contentView = spinnerPop.getContentView();
            RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            SlimAdapter slimAdapter = SlimAdapter.create().register(R.layout.lib_recy_item_spinner_text, new SlimInjector<String>() {
                @Override
                public void onInject(String data, IViewInjector injector, List payloads) {
                    injector.text(R.id.tvTitle, data);
                }
            }).attachTo(recyclerView).updateData(strs);
            ItemClickSupport.addTo(recyclerView)
                    .setOnItemClickListener(onItemClickListener);
            spinnerPop.setBackground(0);
            return spinnerPop;
        }catch (Exception e){
            return null;
        }

    }
}
