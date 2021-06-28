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

public class GetProfileActivity_ViewBinding implements Unbinder {
  private GetProfileActivity target;

  private View view7f0900a8;

  @UiThread
  public GetProfileActivity_ViewBinding(GetProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GetProfileActivity_ViewBinding(final GetProfileActivity target, View source) {
    this.target = target;

    View view;
    target.age = Utils.findRequiredViewAsType(source, R.id.age, "field 'age'", TextView.class);
    target.height = Utils.findRequiredViewAsType(source, R.id.height, "field 'height'", TextView.class);
    target.weight = Utils.findRequiredViewAsType(source, R.id.weight, "field 'weight'", TextView.class);
    target.steplength = Utils.findRequiredViewAsType(source, R.id.steplength, "field 'steplength'", TextView.class);
    target.gender = Utils.findRequiredViewAsType(source, R.id.gender, "field 'gender'", TextView.class);
    view = Utils.findRequiredView(source, R.id.editPersonalDetails, "field 'editPersonalDetails' and method 'onViewClicked'");
    target.editPersonalDetails = Utils.castView(view, R.id.editPersonalDetails, "field 'editPersonalDetails'", Button.class);
    view7f0900a8 = view;
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
    GetProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.age = null;
    target.height = null;
    target.weight = null;
    target.steplength = null;
    target.gender = null;
    target.editPersonalDetails = null;

    view7f0900a8.setOnClickListener(null);
    view7f0900a8 = null;
  }
}
