package co.ke.meritsystems.adcb_service_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.ke.meritsystems.adcb_service_app.BusinessInspection;
import co.ke.meritsystems.adcb_service_app.R;
import co.ke.meritsystems.adcb_service_app.models.WardRequests;

public class WardRequestsAdapter extends RecyclerView.Adapter<WardRequestsAdapter.dataViewHolder> {


    private List<WardRequests> dataList;
    private Context context;

    private static int currentPosition = 0;

    public WardRequestsAdapter(List<WardRequests> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wards_requests, parent, false);
        return new dataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final dataViewHolder holder, final int position) {
        WardRequests data = dataList.get(position);
        holder.textViewRequestNO.setText(Integer.toString(data.getId()));
        holder.textBusinessName.setText(data.getBusiness_name());
        holder.textBusinessPhone.setText(data.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + dataList.get(position).getBusiness_name(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Intent intent = new Intent(v.getContext(), BusinessInspection.class);
                intent.putExtra("ID", Integer.toString(data.getInspection_id()));
                intent.putExtra("bizname", data.getBusiness_name());
                intent.putExtra("bizphone", data.getPhone());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class dataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRequestNO, textBusinessName, textBusinessPhone;
        LinearLayout linearLayout;

        dataViewHolder(View itemView) {
            super(itemView);
            textViewRequestNO = itemView.findViewById(R.id.txtRequestNo);
            textBusinessName = itemView.findViewById(R.id.txtBusinessName);
            textBusinessPhone = itemView.findViewById(R.id.txtBusinessPhone);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}