// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AlarmSetActivity_ViewBinding implements Unbinder {
  private AlarmSetActivity target;

  private View view7f090207;

  @UiThread
  public AlarmSetActivity_ViewBinding(AlarmSetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AlarmSetActivity_ViewBinding(final AlarmSetActivity target, View source) {
    this.target = target;

    View view;
    target.timePickerClockSet = Utils.findRequiredViewAsType(source, R.id.timePicker_clock_set, "field 'timePickerClockSet'", TimePicker.class);
    target.radioNormal = Utils.findRequiredViewAsType(source, R.id.radio_normal, "field 'radioNormal'", RadioButton.class);
    target.radioMedicine = Utils.findRequiredViewAsType(source, R.id.radio_Medicine, "field 'radioMedicine'", RadioButton.class);
    target.radioDrink = Utils.findRequiredViewAsType(source, R.id.radio_Drink, "field 'radioDrink'", RadioButton.class);
    target.radioGroupGender = Utils.findRequiredViewAsType(source, R.id.radioGroup_gender, "field 'radioGroupGender'", RadioGroup.class);
    target.RecyclerViewAlarmSet = Utils.findRequiredViewAsType(source, R.id.RecyclerView_alarm_set, "field 'RecyclerViewAlarmSet'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.bt_clock_save, "field 'btClockSave' and method 'onViewClicked'");
    target.btClockSave = Utils.castView(view, R.id.bt_clock_save, "field 'btClockSave'", Button.class);
    view7f090207 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.switchCompat = Utils.findRequiredViewAsType(source, R.id.SwitchCompat, "field 'switchCompat'", SwitchCompat.class);

    Context context = source.getContext();
    Resources res = context.getResources();
    target.weekArray = res.getStringArray(R.array.weekarray);
  }

  @Override
  @CallSuper
  public void unbind() {
    AlarmSetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.timePickerClockSet = null;
    target.radioNormal = null;
    target.radioMedicine = null;
    target.radioDrink = null;
    target.radioGroupGender = null;
    target.RecyclerViewAlarmSet = null;
    target.btClockSave = null;
    target.switchCompat = null;

    view7f090207.setOnClickListener(null);
    view7f090207 = null;
  }
}
