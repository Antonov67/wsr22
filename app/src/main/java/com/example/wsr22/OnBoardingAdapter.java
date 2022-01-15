package com.example.wsr22;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>{
    private List<Film> films;

    public OnBoardingAdapter(List<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_main_screen_layout,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private ImageView imageOnboarding;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.item_text);
            imageOnboarding = itemView.findViewById(R.id.item_imageView);
        }
        void setOnboardingData(Film film){
            textTitle.setText(film.name);
            Picasso.get().load("http://cinema.areas.su/up/images/"+film.poster).into(imageOnboarding);
        }
    }
}
