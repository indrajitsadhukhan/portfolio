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

public class SetDeviceNameActivity_ViewBinding implements Unbinder {
  private SetDeviceNameActivity target;

  private View view7f090194;

  @UiThread
  public SetDeviceNameActivity_ViewBinding(SetDeviceNameActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetDeviceNameActivity_ViewBinding(final SetDeviceNameActivity target, View source) {
    this.target = target;

    View view;
    target.editdevicename = Utils.findRequiredViewAsType(source, R.id.editdevicename, "field 'editdevicename'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submitdevicename, "field 'submitdevicename' and method 'onViewClicked'");
    target.submitdevicename = Utils.castView(view, R.id.submitdevicename, "field 'submitdevicename'", Button.class);
    view7f090194 = view;
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
    SetDeviceNameActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editdevicename = null;
    target.submitdevicename = null;

    view7f090194.setOnClickListener(null);
    view7f090194 = null;
  }
}
