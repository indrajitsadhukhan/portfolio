// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetExercisePeriodActivity_ViewBinding implements Unbinder {
  private SetExercisePeriodActivity target;

  private View view7f090228;

  @UiThread
  public SetExercisePeriodActivity_ViewBinding(SetExercisePeriodActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetExercisePeriodActivity_ViewBinding(final SetExercisePeriodActivity target,
      View source) {
    this.target = target;

    View view;
    target.startexercise = Utils.findRequiredViewAsType(source, R.id.startexercise, "field 'startexercise'", TimePicker.class);
    target.endexercise = Utils.findRequiredViewAsType(source, R.id.endexercise, "field 'endexercise'", TimePicker.class);
    target.timeinterval = Utils.findRequiredViewAsType(source, R.id.timeintervalex, "field 'timeinterval'", EditText.class);
    target.leaststepsex = Utils.findRequiredViewAsType(source, R.id.leaststepsex, "field 'leaststepsex'", EditText.class);
    target.suncheck = Utils.findRequiredViewAsType(source, R.id.sunday, "field 'suncheck'", CheckBox.class);
    target.moncheck = Utils.findRequiredViewAsType(source, R.id.monday, "field 'moncheck'", CheckBox.class);
    target.tuecheck = Utils.findRequiredViewAsType(source, R.id.tuesday, "field 'tuecheck'", CheckBox.class);
    target.wedcheck = Utils.findRequiredViewAsType(source, R.id.wednesday, "field 'wedcheck'", CheckBox.class);
    target.thurscheck = Utils.findRequiredViewAsType(source, R.id.thursday, "field 'thurscheck'", CheckBox.class);
    target.fricheck = Utils.findRequiredViewAsType(source, R.id.friday, "field 'fricheck'", CheckBox.class);
    target.satcheck = Utils.findRequiredViewAsType(source, R.id.saturday, "field 'satcheck'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.submitex, "field 'submitex' and method 'onViewClicked'");
    target.submitex = Utils.castView(view, R.id.submitex, "field 'submitex'", Button.class);
    view7f090228 = view;
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
    SetExercisePeriodActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.startexercise = null;
    target.endexercise = null;
    target.timeinterval = null;
    target.leaststepsex = null;
    target.suncheck = null;
    target.moncheck = null;
    target.tuecheck = null;
    target.wedcheck = null;
    target.thurscheck = null;
    target.fricheck = null;
    target.satcheck = null;
    target.submitex = null;

    view7f090228.setOnClickListener(null);
    view7f090228 = null;
  }
}
