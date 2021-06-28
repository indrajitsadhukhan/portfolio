package com.example.testapplication.Adapters;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.ResolveUtil;
import com.jstyle.blesdk1963.model.Clock;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class ClockAdapter extends RecyclerView.Adapter {
    List<Clock> clocklist = new ArrayList<>();
    String[]array;
    onClockItemClickListener onClockItemClickListener;
    private boolean isdelete;

    public ClockAdapter(onClockItemClickListener onClockItemClickListener) {
        this.onClockItemClickListener=onClockItemClickListener;
    }

    public void setData(String[]array, Clock clock){
        this.clocklist.add(clock);
        this.array=array;
        notifyDataSetChanged();
    }
    public void clear(){
        this.clocklist.clear();
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final Clock clock = clocklist.get(position);
        String hour = String.format("%02d", clock.getHour());
        String min = String.format("%02d", clock.getMinute());
        int week = clock.getWeek();
        boolean enable=clock.isEnable();
        String clockTime = hour + ":" + min;
        String weekString= ResolveUtil.getByteString((byte) week);
        String[] weekStringArray = weekString.split("-");
        String weekshow = "";
        for (int i = 0; i < 7; i++) {
            String weekEnable = weekStringArray[i];
            if (weekEnable.equals("1")) {
                weekshow += array[i];
                weekshow += ",";
            }
        }

        viewHolder.itemView.setOnClickListener(v -> onClockItemClickListener.onItemClick(clock));
        viewHolder.clocktimetxt.setText(clockTime);

        if(!TextUtils.isEmpty(weekshow))
            viewHolder.clocktimeweek.setText(weekshow.substring(0,weekshow.length()-1));


        viewHolder.switchclock.setChecked(enable);
        viewHolder.delclock.setVisibility(isdelete ? View.VISIBLE : View.GONE);
        viewHolder.delclock.setOnClickListener(v -> {
            clocklist.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeRemoved(0,clocklist.size());
            onClockItemClickListener.onDelete(clock);
        });
    }
    public void enableDelete() {
        isdelete = !isdelete;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return clocklist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clocktimetxt)
        TextView clocktimetxt;

        @BindView(R.id.clocktimeweek)
        TextView clocktimeweek;
        @BindView(R.id.delclock)
        ImageView delclock;
        @BindView(R.id.switchclock)
        SwitchCompat switchclock;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public interface onClockItemClickListener{
        public void onItemClick(Clock clock);
        public void onDelete(Clock clock);
        public void onUpdate(List<Clock> clockList);
    }
    public List<Clock> getClockList(){
        return this.clocklist;
    }
}

