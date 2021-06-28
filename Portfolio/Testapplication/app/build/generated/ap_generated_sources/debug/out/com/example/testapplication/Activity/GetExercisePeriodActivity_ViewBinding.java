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

public class GetExercisePeriodActivity_ViewBinding implements Unbinder {
  private GetExercisePeriodActivity target;

  private View view7f09021d;

  @UiThread
  public GetExercisePeriodActivity_ViewBinding(GetExercisePeriodActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GetExercisePeriodActivity_ViewBinding(final GetExercisePeriodActivity target,
      View source) {
    this.target = target;

    View view;
    target.starttimeexercise = Utils.findRequiredViewAsType(source, R.id.starttimeexercise, "field 'starttimeexercise'", TextView.class);
    target.endtimeexercise = Utils.findRequiredViewAsType(source, R.id.endtimeexercise, "field 'endtimeexercise'", TextView.class);
    target.dayexercise = Utils.findRequiredViewAsType(source, R.id.dayexercise, "field 'dayexercise'", TextView.class);
    target.leaststeps = Utils.findRequiredViewAsType(source, R.id.leaststeps, "field 'leaststeps'", TextView.class);
    target.exerciseinterval = Utils.findRequiredViewAsType(source, R.id.exerciseinterval, "field 'exerciseinterval'", TextView.class);
    view = Utils.findRequiredView(source, R.id.editExercise, "field 'editExercise' and method 'onViewClicked'");
    target.editExercise = Utils.castView(view, R.id.editExercise, "field 'editExercise'", Button.class);
    view7f09021d = view;
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
    GetExercisePeriodActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.starttimeexercise = null;
    target.endtimeexercise = null;
    target.dayexercise = null;
    target.leaststeps = null;
    target.exerciseinterval = null;
    target.editExercise = null;

    view7f09021d.setOnClickListener(null);
    view7f09021d = null;
  }
}
