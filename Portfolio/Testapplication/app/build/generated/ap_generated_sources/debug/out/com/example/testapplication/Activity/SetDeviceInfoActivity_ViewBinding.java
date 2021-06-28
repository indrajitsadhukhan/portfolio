// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SwitchCompat;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetDeviceInfoActivity_ViewBinding implements Unbinder {
  private SetDeviceInfoActivity target;

  private View view7f090193;

  @UiThread
  public SetDeviceInfoActivity_ViewBinding(SetDeviceInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetDeviceInfoActivity_ViewBinding(final SetDeviceInfoActivity target, View source) {
    this.target = target;

    View view;
    target.edittimeformat = Utils.findRequiredViewAsType(source, R.id.edittimeformat, "field 'edittimeformat'", RadioGroup.class);
    target.editscreenbrightness = Utils.findRequiredViewAsType(source, R.id.editscreenbrightness, "field 'editscreenbrightness'", EditText.class);
    target.editdialinterface = Utils.findRequiredViewAsType(source, R.id.editdialinterface, "field 'editdialinterface'", EditText.class);
    target.editbaseheartrate = Utils.findRequiredViewAsType(source, R.id.editbaseheartrate, "field 'editbaseheartrate'", EditText.class);
    target.socialdistance = Utils.findRequiredViewAsType(source, R.id.socialdistance, "field 'socialdistance'", SwitchCompat.class);
    target.chineeseEnglish = Utils.findRequiredViewAsType(source, R.id.chineeseEnglish, "field 'chineeseEnglish'", SwitchCompat.class);
    target.farenheitcent = Utils.findRequiredViewAsType(source, R.id.farenheitcent, "field 'farenheitcent'", SwitchCompat.class);
    target.brightScreen = Utils.findRequiredViewAsType(source, R.id.brightScreen, "field 'brightScreen'", SwitchCompat.class);
    target.nightmode = Utils.findRequiredViewAsType(source, R.id.nightmode, "field 'nightmode'", SwitchCompat.class);
    target.horizontalscreen = Utils.findRequiredViewAsType(source, R.id.horizontalscreen, "field 'horizontalscreen'", SwitchCompat.class);
    view = Utils.findRequiredView(source, R.id.submitdeviceinfo, "method 'onViewClicked'");
    view7f090193 = view;
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
    SetDeviceInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edittimeformat = null;
    target.editscreenbrightness = null;
    target.editdialinterface = null;
    target.editbaseheartrate = null;
    target.socialdistance = null;
    target.chineeseEnglish = null;
    target.farenheitcent = null;
    target.brightScreen = null;
    target.nightmode = null;
    target.horizontalscreen = null;

    view7f090193.setOnClickListener(null);
    view7f090193 = null;
  }
}
