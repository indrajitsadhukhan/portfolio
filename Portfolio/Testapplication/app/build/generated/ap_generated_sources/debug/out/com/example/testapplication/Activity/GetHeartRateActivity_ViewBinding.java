// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GetHeartRateActivity_ViewBinding implements Unbinder {
  private GetHeartRateActivity target;

  private View view7f0901e8;

  @UiThread
  public GetHeartRateActivity_ViewBinding(GetHeartRateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GetHeartRateActivity_ViewBinding(final GetHeartRateActivity target, View source) {
    this.target = target;

    View view;
    target.starttime = Utils.findRequiredViewAsType(source, R.id.starttime, "field 'starttime'", TextView.class);
    target.endtime = Utils.findRequiredViewAsType(source, R.id.endtime, "field 'endtime'", TextView.class);
    target.timeinterval = Utils.findRequiredViewAsType(source, R.id.timeinterval, "field 'timeinterval'", TextView.class);
    target.week = Utils.findRequiredViewAsType(source, R.id.week, "field 'week'", TextView.class);
    view = Utils.findRequiredView(source, R.id.editheartrate, "field 'editheartrate' and method 'onViewClicked'");
    target.editheartrate = Utils.castView(view, R.id.editheartrate, "field 'editheartrate'", Button.class);
    view7f0901e8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    GetHeartRateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.starttime = null;
    target.endtime = null;
    target.timeinterval = null;
    target.week = null;
    target.editheartrate = null;

    view7f0901e8.setOnClickListener(null);
    view7f0901e8 = null;
  }
}
