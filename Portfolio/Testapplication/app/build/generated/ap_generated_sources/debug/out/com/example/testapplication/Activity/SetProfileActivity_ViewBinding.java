// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetProfileActivity_ViewBinding implements Unbinder {
  private SetProfileActivity target;

  private View view7f09016e;

  @UiThread
  public SetProfileActivity_ViewBinding(SetProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetProfileActivity_ViewBinding(final SetProfileActivity target, View source) {
    this.target = target;

    View view;
    target.agesetprofile = Utils.findRequiredViewAsType(source, R.id.agesetprofile, "field 'agesetprofile'", EditText.class);
    target.heightsetprofile = Utils.findRequiredViewAsType(source, R.id.heightsetprofile, "field 'heightsetprofile'", EditText.class);
    target.weightsetprofile = Utils.findRequiredViewAsType(source, R.id.weightsetprofile, "field 'weightsetprofile'", EditText.class);
    target.steplengthsetprofile = Utils.findRequiredViewAsType(source, R.id.steplengthsetprofile, "field 'steplengthsetprofile'", EditText.class);
    target.gendersetprofile = Utils.findRequiredViewAsType(source, R.id.gendersetprofile, "field 'gendersetprofile'", RadioGroup.class);
    view = Utils.findRequiredView(source, R.id.setprofilesubmit, "field 'setprofilesubmit' and method 'onViewClicked'");
    target.setprofilesubmit = Utils.castView(view, R.id.setprofilesubmit, "field 'setprofilesubmit'", Button.class);
    view7f09016e = view;
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
    SetProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.agesetprofile = null;
    target.heightsetprofile = null;
    target.weightsetprofile = null;
    target.steplengthsetprofile = null;
    target.gendersetprofile = null;
    target.setprofilesubmit = null;

    view7f09016e.setOnClickListener(null);
    view7f09016e = null;
  }
}
