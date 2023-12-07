package com.cs407.alarm_clock;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private List<Alarm> alarms;
    private Context context;

    public AlarmAdapter(Context context, List<Alarm> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    private void removeAlarm(int position) {
        alarms.remove(position);
        notifyItemRemoved(position);
    }

    private String convertListToString(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        for (Integer value : list) {
            builder.append(value).append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1); // Remove the trailing comma
        }
        return builder.toString();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);

        // Bind data to the views in the alarm item layout
        holder.textAlarmTime.setText(alarm.getTimeAsString());
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit
                Intent editIntent = new Intent(context, alarm_selection.class);
                int hour = (alarm.isAM() || alarm.getHour() == 12) ? alarm.getHour() : alarm.getHour() + 12;
                editIntent.putExtra("alarm_id", alarm.getId());
                editIntent.putExtra("alarm_hour", hour);
                editIntent.putExtra("alarm_minute", alarm.getMinute());
                editIntent.putExtra("alarm_note", alarm.getNote());
                editIntent.putExtra("alarm_repeatable", alarm.isRepeatable());
                editIntent.putExtra("alarm_repeat_days", convertListToString(alarm.getRepeatDays()));
                context.startActivity(editIntent);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete
                AlarmDataSource alarmDataSource = new AlarmDataSource(context);
                alarmDataSource.open();
                alarmDataSource.deleteAlarm(alarm.getId());
                alarmDataSource.close();

                // Update view holder
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    removeAlarm(position);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textAlarmTime;
        Button buttonEdit;
        Button buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textAlarmTime = itemView.findViewById(R.id.textAlarmTime);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
