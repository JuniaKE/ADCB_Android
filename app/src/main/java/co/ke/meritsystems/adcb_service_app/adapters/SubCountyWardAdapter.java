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

import co.ke.meritsystems.adcb_service_app.R;
import co.ke.meritsystems.adcb_service_app.WardRequestListing;
import co.ke.meritsystems.adcb_service_app.models.SubCountyWards;

public class SubCountyWardAdapter extends RecyclerView.Adapter<SubCountyWardAdapter.dataViewHolder> {


    private List<SubCountyWards> dataList;
    private Context context;

    private static int currentPosition = 0;

    public SubCountyWardAdapter(List<SubCountyWards> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sub_county_wards, parent, false);
        return new dataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final dataViewHolder holder, final int position) {
        SubCountyWards data = dataList.get(position);
        holder.textViewId.setText(Integer.toString(data.getId()));
        holder.textViewName.setText(data.getWard_name());
        holder.textViewRequests.setText(Integer.toString(data.getWard_request_count()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + dataList.get(position).getWard_name(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                Intent intent = new Intent(v.getContext(), WardRequestListing.class);
                intent.putExtra("ID", Integer.toString(data.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class dataViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewRequests;
        LinearLayout linearLayout;

        dataViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.txtwardID);
            textViewName = itemView.findViewById(R.id.txtwardName);
            textViewRequests = itemView.findViewById(R.id.txtwardRequests);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}