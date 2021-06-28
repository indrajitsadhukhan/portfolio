// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetHeartRateActivity_ViewBinding implements Unbinder {
  private SetHeartRateActivity target;

  private View view7f0901f8;

  @UiThread
  public SetHeartRateActivity_ViewBinding(SetHeartRateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetHeartRateActivity_ViewBinding(final SetHeartRateActivity target, View source) {
    this.target = target;

    View view;
    target.starttimepicker = Utils.findRequiredViewAsType(source, R.id.starttimepicker, "field 'starttimepicker'", TimePicker.class);
    target.endtimepicker = Utils.findRequiredViewAsType(source, R.id.endtimepicker, "field 'endtimepicker'", TimePicker.class);
    target.radioGroup_autoHeart = Utils.findRequiredViewAsType(source, R.id.radioGroup_autoHeart, "field 'radioGroup_autoHeart'", RadioGroup.class);
    target.timeinterval = Utils.findRequiredViewAsType(source, R.id.edittimeinterval, "field 'timeinterval'", EditText.class);
    target.suncheck = Utils.findRequiredViewAsType(source, R.id.suncheck, "field 'suncheck'", CheckBox.class);
    target.moncheck = Utils.findRequiredViewAsType(source, R.id.moncheck, "field 'moncheck'", CheckBox.class);
    target.tuecheck = Utils.findRequiredViewAsType(source, R.id.tuecheck, "field 'tuecheck'", CheckBox.class);
    target.wedcheck = Utils.findRequiredViewAsType(source, R.id.wedcheck, "field 'wedcheck'", CheckBox.class);
    target.thurscheck = Utils.findRequiredViewAsType(source, R.id.thurscheck, "field 'thurscheck'", CheckBox.class);
    target.fricheck = Utils.findRequiredViewAsType(source, R.id.fricheck, "field 'fricheck'", CheckBox.class);
    target.satcheck = Utils.findRequiredViewAsType(source, R.id.satcheck, "field 'satcheck'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.submitheartrate, "field 'submitheartrate' and method 'onViewClicked'");
    target.submitheartrate = Utils.castView(view, R.id.submitheartrate, "field 'submitheartrate'", Button.class);
    view7f0901f8 = view;
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
    SetHeartRateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.starttimepicker = null;
    target.endtimepicker = null;
    target.radioGroup_autoHeart = null;
    target.timeinterval = null;
    target.suncheck = null;
    target.moncheck = null;
    target.tuecheck = null;
    target.wedcheck = null;
    target.thurscheck = null;
    target.fricheck = null;
    target.satcheck = null;
    target.submitheartrate = null;

    view7f0901f8.setOnClickListener(null);
    view7f0901f8 = null;
  }
}
