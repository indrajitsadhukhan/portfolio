// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Adapters;

import android.view.View;
import android.widget.CheckBox;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClockWeekAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ClockWeekAdapter.ViewHolder target;

  @UiThread
  public ClockWeekAdapter$ViewHolder_ViewBinding(ClockWeekAdapter.ViewHolder target, View source) {
    this.target = target;

    target.checkbox_clock_week = Utils.findRequiredViewAsType(source, R.id.checkbox_clock_week, "field 'checkbox_clock_week'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ClockWeekAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.checkbox_clock_week = null;
  }
}
