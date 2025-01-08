package com.tatay.crudsqlitemaster;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Siswa> siswaList;
    private AdapterListener listener;

    public SiswaAdapter(List<Siswa> list, AdapterListener listener) {
        this.siswaList = list;
        this.listener=listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_siswa, parent, false);
        return new ItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemView = (ItemViewHolder) holder;
        Siswa s = siswaList.get(position);
        itemView.vNis.setText(String.valueOf(s.getNis()));
        itemView.vNama.setText(s.getNamaSiwa());
        itemView.vJk.setText(s.getJenisKelamin());
        itemView.vKK.setText(s.getKonsentrasiKeahlian());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(s);
            }
        });

        itemView.moreMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMoreMenu(s, itemView.moreMenu);
            }
        });

    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }


    public void setDataList(List<Siswa> dataList) {
        this.siswaList = dataList;
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void onClick(Siswa siswa);
        void onMoreMenu(Siswa siswa, ImageView widget);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView vNis, vNama, vJk, vKK;
        ImageView moreMenu;

        @SuppressLint("WrongViewCast")
        public ItemViewHolder(View itemView) {
            super(itemView);
            vNis = itemView.findViewById(R.id.textNis);
            vNama = itemView.findViewById(R.id.textNama);
            vJk = itemView.findViewById(R.id.textJk);
            vKK = itemView.findViewById(R.id.textKk);
            moreMenu = itemView.findViewById(R.id.more_menu);
        }
    }
}