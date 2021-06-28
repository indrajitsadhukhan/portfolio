// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClockAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ClockAdapter.ViewHolder target;

  @UiThread
  public ClockAdapter$ViewHolder_ViewBinding(ClockAdapter.ViewHolder target, View source) {
    this.target = target;

    target.clocktimetxt = Utils.findRequiredViewAsType(source, R.id.clocktimetxt, "field 'clocktimetxt'", TextView.class);
    target.clocktimeweek = Utils.findRequiredViewAsType(source, R.id.clocktimeweek, "field 'clocktimeweek'", TextView.class);
    target.delclock = Utils.findRequiredViewAsType(source, R.id.delclock, "field 'delclock'", ImageView.class);
    target.switchclock = Utils.findRequiredViewAsType(source, R.id.switchclock, "field 'switchclock'", SwitchCompat.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ClockAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clocktimetxt = null;
    target.clocktimeweek = null;
    target.delclock = null;
    target.switchclock = null;
  }
}
