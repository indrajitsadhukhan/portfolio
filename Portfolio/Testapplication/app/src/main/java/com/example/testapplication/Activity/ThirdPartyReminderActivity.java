package com.example.testapplication.Activity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.testapplication.Adapters.NotifyAdapter;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.model.Notifier;
import java.util.Map;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class ThirdPartyReminderActivity extends BaseActivity {

    @BindView(R.id.recyclerreminder)
    RecyclerView recyclerreminder;
    @BindView(R.id.remindercontent)
    EditText remindercontent;
    @BindView(R.id.submitreminder)
    Button submitreminder;
    NotifyAdapter notifyAdapter;
    @BindArray(R.array.nofityarray)
    String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_reminder);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerreminder.setLayoutManager(gridLayoutManager);
        notifyAdapter=new NotifyAdapter(array);
        recyclerreminder.setAdapter(notifyAdapter);
    }
    @OnClick(R.id.submitreminder)
    public void onViewClicked() {
        int type=notifyAdapter.getCheck();
        String content=remindercontent.getText().toString();
        if(TextUtils.isEmpty(content))return;
        Notifier notifier=new Notifier();
        notifier.setInfo(content);
        notifier.setType(type);
        sendValue(BleSDK.setNotifyData(notifier));
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        switch (dataType) {
            case BleConst.Notify:
                showToast("Notified");
                break;
        }
    }

}