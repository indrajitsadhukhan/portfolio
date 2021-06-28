// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Adapters;

import android.view.View;
import android.widget.RadioButton;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotifyAdapter$ViewHolder_ViewBinding implements Unbinder {
  private NotifyAdapter.ViewHolder target;

  @UiThread
  public NotifyAdapter$ViewHolder_ViewBinding(NotifyAdapter.ViewHolder target, View source) {
    this.target = target;

    target.radioreminder = Utils.findRequiredViewAsType(source, R.id.radioreminder, "field 'radioreminder'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotifyAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioreminder = null;
  }
}
