package com.aviato.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aviato.R;
import com.aviato.interfaces.OnPersonChosen;
import com.aviato.model.ChooseData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosePersonAdapter extends RecyclerView.Adapter<ChoosePersonAdapter.ViewHolder>  {

    private List<ChooseData> candidates;
    private OnPersonChosen onPersonChosen;

    public ChoosePersonAdapter(List<ChooseData> data, OnPersonChosen onPersonChosen) {
        candidates = new ArrayList<>(data);
        this.onPersonChosen = onPersonChosen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_person_column_layout, parent, false);

        return new ViewHolder(v, onPersonChosen);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChooseData currentPerson = candidates.get(position);
        Context context = holder.name.getContext();
        holder.age.setText(context.getString(R.string.age, currentPerson.getAge()));
        holder.name.setText(context.getString(R.string.name, currentPerson.getName()));
        Picasso.with(holder.profile.getContext())
                .load(currentPerson.getProfileUrl())
                .centerCrop().resize(700, 800)
                .error(R.drawable.ic_person_black_24dp)
                .into(holder.profile);
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.choose_age)
        TextView age;
        @BindView(R.id.choose_name)
        TextView name;
        @BindView(R.id.choose_profile_picture)
        ImageView profile;

        private OnPersonChosen onPersonChosen;

        ViewHolder(View itemView, OnPersonChosen onPersonChosen) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.onPersonChosen = onPersonChosen;
        }

        @OnClick(R.id.chosen_layout)
        public void onPersonClicked() {
            onPersonChosen.onPersonChosen(getAdapterPosition());
        }
    }
}
