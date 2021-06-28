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

public class GetDeviceInfoActivity_ViewBinding implements Unbinder {
  private GetDeviceInfoActivity target;

  private View view7f0900a7;

  @UiThread
  public GetDeviceInfoActivity_ViewBinding(GetDeviceInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GetDeviceInfoActivity_ViewBinding(final GetDeviceInfoActivity target, View source) {
    this.target = target;

    View view;
    target.handlesign = Utils.findRequiredViewAsType(source, R.id.handlesign, "field 'handlesign'", TextView.class);
    target.wriston = Utils.findRequiredViewAsType(source, R.id.wriston, "field 'wriston'", TextView.class);
    target.brightness = Utils.findRequiredViewAsType(source, R.id.brightness, "field 'brightness'", TextView.class);
    target.baseheartrate = Utils.findRequiredViewAsType(source, R.id.baseheartrate, "field 'baseheartrate'", TextView.class);
    target.screenorientation = Utils.findRequiredViewAsType(source, R.id.screenorientation, "field 'screenorientation'", TextView.class);
    target.distanceunit = Utils.findRequiredViewAsType(source, R.id.distanceunit, "field 'distanceunit'", TextView.class);
    target.dialinterface = Utils.findRequiredViewAsType(source, R.id.dialinterface, "field 'dialinterface'", TextView.class);
    target.timeformat = Utils.findRequiredViewAsType(source, R.id.timeformat, "field 'timeformat'", TextView.class);
    view = Utils.findRequiredView(source, R.id.editDeviceInfo, "field 'editDeviceInfo' and method 'onViewClicked'");
    target.editDeviceInfo = Utils.castView(view, R.id.editDeviceInfo, "field 'editDeviceInfo'", Button.class);
    view7f0900a7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.macaddresstxt = Utils.findRequiredViewAsType(source, R.id.macaddresstxt, "field 'macaddresstxt'", TextView.class);
    target.softwareversion = Utils.findRequiredViewAsType(source, R.id.softwareversion, "field 'softwareversion'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GetDeviceInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.handlesign = null;
    target.wriston = null;
    target.brightness = null;
    target.baseheartrate = null;
    target.screenorientation = null;
    target.distanceunit = null;
    target.dialinterface = null;
    target.timeformat = null;
    target.editDeviceInfo = null;
    target.macaddresstxt = null;
    target.softwareversion = null;

    view7f0900a7.setOnClickListener(null);
    view7f0900a7 = null;
  }
}
