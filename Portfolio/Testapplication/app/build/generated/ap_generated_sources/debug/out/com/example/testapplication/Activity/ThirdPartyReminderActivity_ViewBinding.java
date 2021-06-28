// Generated code from Butter Knife. Do not modify!
package com.example.testapplication.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.testapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ThirdPartyReminderActivity_ViewBinding implements Unbinder {
  private ThirdPartyReminderActivity target;

  private View view7f090216;

  @UiThread
  public ThirdPartyReminderActivity_ViewBinding(ThirdPartyReminderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ThirdPartyReminderActivity_ViewBinding(final ThirdPartyReminderActivity target,
      View source) {
    this.target = target;

    View view;
    target.recyclerreminder = Utils.findRequiredViewAsType(source, R.id.recyclerreminder, "field 'recyclerreminder'", RecyclerView.class);
    target.remindercontent = Utils.findRequiredViewAsType(source, R.id.remindercontent, "field 'remindercontent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submitreminder, "field 'submitreminder' and method 'onViewClicked'");
    target.submitreminder = Utils.castView(view, R.id.submitreminder, "field 'submitreminder'", Button.class);
    view7f090216 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.array = res.getStringArray(R.array.nofityarray);
  }

  @Override
  @CallSuper
  public void unbind() {
    ThirdPartyReminderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerreminder = null;
    target.remindercontent = null;
    target.submitreminder = null;

    view7f090216.setOnClickListener(null);
    view7f090216 = null;
  }
}
