// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlarmListActivity_ViewBinding implements Unbinder {
  private AlarmListActivity target;

  private View view7f090206;

  private View view7f090208;

  @UiThread
  public AlarmListActivity_ViewBinding(AlarmListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlarmListActivity_ViewBinding(final AlarmListActivity target, View source) {
    this.target = target;

    View view;
    target.RecyclerViewAlarm = Utils.findRequiredViewAsType(source, R.id.RecyclerView_alarm, "field 'RecyclerViewAlarm'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.bt_add, "field 'btAdd' and method 'onViewClicked'");
    target.btAdd = Utils.castView(view, R.id.bt_add, "field 'btAdd'", Button.class);
    view7f090206 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_edit, "method 'onViewClicked'");
    view7f090208 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.weekArray = res.getStringArray(R.array.weekarray);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlarmListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.RecyclerViewAlarm = null;
    target.btAdd = null;

    view7f090206.setOnClickListener(null);
    view7f090206 = null;
    view7f090208.setOnClickListener(null);
    view7f090208 = null;
  }
}
