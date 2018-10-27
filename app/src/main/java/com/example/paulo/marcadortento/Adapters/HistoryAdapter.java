package com.example.paulo.marcadortento.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paulo.marcadortento.Interface.AdapterPositionOnClickListener;
import com.example.paulo.marcadortento.Models.History;
import com.example.paulo.marcadortento.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
    private Context mContext;
    private AdapterPositionOnClickListener adapterPositionOnClickListener;
    public List<History> mList;

    public HistoryAdapter(Context context, List<History> list){
        this.mContext = context;
        this.mList = list;
    }

    //Inserir uma função de contato entre a activity e o Adapter
    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click){
        adapterPositionOnClickListener = click;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_history, parent, false);
        viewHolder ViewHolder = new viewHolder(holder);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        History history = mList.get(position);

        String resultado = String.valueOf(history.ptsTeam1) +
                " x " +
                String.valueOf(history.ptsTeam2);

        String titulo = history.ptsTeam1 > history.ptsTeam2 ?
                "Nós ganhamos" : "Eles ganharam";

        holder.lbl_title.setText(titulo);
        holder.lbl_result.setText(resultado);
        holder.lbl_date.setText(history.date);
        holder.lbl_time.setText(history.time);
    }

    public void deleteItem(int positon){
        mList.remove(positon);
        notifyItemRemoved(positon);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView lbl_title;
        public TextView lbl_result;
        public TextView lbl_date;
        public TextView lbl_time;
        private ImageButton mBtnDel;

        public viewHolder(View itemView) {
            super(itemView);

            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_result = itemView.findViewById(R.id.lbl_result);
            lbl_date = itemView.findViewById(R.id.lbl_date);
            lbl_time = itemView.findViewById(R.id.lbl_time);
            mBtnDel = itemView.findViewById(R.id.btn_del);

            //Aplica a função do click na view toda
            //itemView.setOnClickListener(this);

            // Aplica a função de click somente no botão excluir
            mBtnDel.setOnClickListener(this);
        }


        //Passar os parametros que vai ser executado na HistoryActivity
        @Override
        public void onClick(View view) {

            //Verificar se a ponte de contato entre o activity e o adapter foi passado
            if(adapterPositionOnClickListener != null){

                //passar os parametros que vai ser exibido lá na history activity
                adapterPositionOnClickListener.setAdapterPositionOnClickListener(view, getPosition());
            }
        }
    }
}

