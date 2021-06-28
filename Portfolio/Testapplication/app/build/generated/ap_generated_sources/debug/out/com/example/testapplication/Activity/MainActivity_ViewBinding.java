// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f090003;

  private View view7f090232;

  private View view7f09016d;

  private View view7f09012b;

  private View view7f09008d;

  private View view7f09016c;

  private View view7f0901dd;

  private View view7f0901dc;

  private View view7f0901e2;

  private View view7f0901e3;

  private View view7f0901ec;

  private View view7f0901e7;

  private View view7f0901eb;

  private View view7f0901ea;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.BT_CONNECT, "field 'btConnect' and method 'onViewClicked'");
    target.btConnect = Utils.castView(view, R.id.BT_CONNECT, "field 'btConnect'", Button.class);
    view7f090003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.batteryLevel = Utils.findRequiredViewAsType(source, R.id.batterylevel, "field 'batteryLevel'", TextView.class);
    target.deviceTime = Utils.findRequiredViewAsType(source, R.id.devicetime, "field 'deviceTime'", TextView.class);
    target.devicenametxt = Utils.findRequiredViewAsType(source, R.id.devicenametxt, "field 'devicenametxt'", TextView.class);
    target.recordtemp = Utils.findRequiredViewAsType(source, R.id.recordtemp, "field 'recordtemp'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.mainstartbtn, "field 'mainstartbtn' and method 'onViewClicked'");
    target.mainstartbtn = Utils.castView(view, R.id.mainstartbtn, "field 'mainstartbtn'", Button.class);
    view7f090232 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.caloriestable = Utils.findRequiredViewAsType(source, R.id.caloriestable, "field 'caloriestable'", TextView.class);
    target.stepstable = Utils.findRequiredViewAsType(source, R.id.stepstable, "field 'stepstable'", TextView.class);
    target.heartratetable = Utils.findRequiredViewAsType(source, R.id.heartratetable, "field 'heartratetable'", TextView.class);
    target.activetable = Utils.findRequiredViewAsType(source, R.id.activetable, "field 'activetable'", TextView.class);
    target.timetable = Utils.findRequiredViewAsType(source, R.id.timetable, "field 'timetable'", TextView.class);
    target.distancetable = Utils.findRequiredViewAsType(source, R.id.distancetable, "field 'distancetable'", TextView.class);
    target.temptable = Utils.findRequiredViewAsType(source, R.id.temptable, "field 'temptable'", TextView.class);
    view = Utils.findRequiredView(source, R.id.setdevicetime, "method 'onViewClicked'");
    view7f09016d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.myprofile, "method 'onViewClicked'");
    view7f09012b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.deviceinfo, "method 'onViewClicked'");
    view7f09008d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.setdevicename, "method 'onViewClicked'");
    view7f09016c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.targetsteps, "method 'onViewClicked'");
    view7f0901dd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.restorefactory, "method 'onViewClicked'");
    view7f0901dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.mcusoftreset, "method 'onViewClicked'");
    view7f0901e2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.transmitmotor, "method 'onViewClicked'");
    view7f0901e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.reminder, "method 'onViewClicked'");
    view7f0901ec = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.alarm, "method 'onViewClicked'");
    view7f0901e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.heartrate, "method 'onViewClicked'");
    view7f0901eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.exerciseperiod, "method 'onViewClicked'");
    view7f0901ea = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btConnect = null;
    target.batteryLevel = null;
    target.deviceTime = null;
    target.devicenametxt = null;
    target.recordtemp = null;
    target.mainstartbtn = null;
    target.caloriestable = null;
    target.stepstable = null;
    target.heartratetable = null;
    target.activetable = null;
    target.timetable = null;
    target.distancetable = null;
    target.temptable = null;

    view7f090003.setOnClickListener(null);
    view7f090003 = null;
    view7f090232.setOnClickListener(null);
    view7f090232 = null;
    view7f09016d.setOnClickListener(null);
    view7f09016d = null;
    view7f09012b.setOnClickListener(null);
    view7f09012b = null;
    view7f09008d.setOnClickListener(null);
    view7f09008d = null;
    view7f09016c.setOnClickListener(null);
    view7f09016c = null;
    view7f0901dd.setOnClickListener(null);
    view7f0901dd = null;
    view7f0901dc.setOnClickListener(null);
    view7f0901dc = null;
    view7f0901e2.setOnClickListener(null);
    view7f0901e2 = null;
    view7f0901e3.setOnClickListener(null);
    view7f0901e3 = null;
    view7f0901ec.setOnClickListener(null);
    view7f0901ec = null;
    view7f0901e7.setOnClickListener(null);
    view7f0901e7 = null;
    view7f0901eb.setOnClickListener(null);
    view7f0901eb = null;
    view7f0901ea.setOnClickListener(null);
    view7f0901ea = null;
  }
}
