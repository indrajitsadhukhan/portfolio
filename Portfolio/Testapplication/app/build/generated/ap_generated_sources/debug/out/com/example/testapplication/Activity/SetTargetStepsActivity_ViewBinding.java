// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetTargetStepsActivity_ViewBinding implements Unbinder {
  private SetTargetStepsActivity target;

  private View view7f0901de;

  @UiThread
  public SetTargetStepsActivity_ViewBinding(SetTargetStepsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetTargetStepsActivity_ViewBinding(final SetTargetStepsActivity target, View source) {
    this.target = target;

    View view;
    target.targetstepstxt = Utils.findRequiredViewAsType(source, R.id.targetstepstxt, "field 'targetstepstxt'", TextView.class);
    target.edittargetsteps = Utils.findRequiredViewAsType(source, R.id.edittargetsteps, "field 'edittargetsteps'", EditText.class);
    view = Utils.findRequiredView(source, R.id.targetstepsbtn, "field 'targetstepsbtn' and method 'onViewClicked'");
    target.targetstepsbtn = Utils.castView(view, R.id.targetstepsbtn, "field 'targetstepsbtn'", Button.class);
    view7f0901de = view;
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
    SetTargetStepsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.targetstepstxt = null;
    target.edittargetsteps = null;
    target.targetstepsbtn = null;

    view7f0901de.setOnClickListener(null);
    view7f0901de = null;
  }
}
