package co.ke.meritsystems.adcb_service_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.ke.meritsystems.adcb_service_app.models.Subcounty;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.dataViewHolder> {


    private List<Subcounty> dataList;
    private Context context;

    private static int currentPosition = 0;

    public DataAdapter(List<Subcounty> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_subcounties, parent, false);
        return new dataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final dataViewHolder holder, final int position) {
        Subcounty data = dataList.get(position);
        holder.textViewId.setText(Integer.toString(data.getId()));
        holder.textViewName.setText(data.getSub_county_name());
        holder.textViewRequests.setText(Integer.toString(data.getSub_county_requests()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + dataList.get(position).getSub_county_name(), Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
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
            textViewId = itemView.findViewById(R.id.txtID);
            textViewName = itemView.findViewById(R.id.txtName);
            textViewRequests = itemView.findViewById(R.id.txtRequests);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}