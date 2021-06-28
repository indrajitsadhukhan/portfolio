// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetTransmitMotorVibrationActivity_ViewBinding implements Unbinder {
  private SetTransmitMotorVibrationActivity target;

  private View view7f0901e5;

  @UiThread
  public SetTransmitMotorVibrationActivity_ViewBinding(SetTransmitMotorVibrationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetTransmitMotorVibrationActivity_ViewBinding(
      final SetTransmitMotorVibrationActivity target, View source) {
    this.target = target;

    View view;
    target.edittransmitmotor = Utils.findRequiredViewAsType(source, R.id.edittransmitmotor, "field 'edittransmitmotor'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submittransmitmotor, "field 'submittransmitmotor' and method 'onViewClicked'");
    target.submittransmitmotor = Utils.castView(view, R.id.submittransmitmotor, "field 'submittransmitmotor'", Button.class);
    view7f0901e5 = view;
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
    SetTransmitMotorVibrationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edittransmitmotor = null;
    target.submittransmitmotor = null;

    view7f0901e5.setOnClickListener(null);
    view7f0901e5 = null;
  }
}
