package com.dobrowol.traininglog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dobrowol.traininglog.adding_training.adding_exercise.Exercise;
import com.dobrowol.traininglog.adding_training.adding_exercise.ExerciseDescription;
import com.dobrowol.traininglog.adding_training.adding_exercise.ExerciseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Exercise item);
    }

    private List<Exercise> exerciseList;
    private List<ExerciseDescription> exerciseDescriptionList;

    MyRecyclerViewAdapter() {
        exerciseList = new ArrayList<>();
        exerciseDescriptionList = new ArrayList<>();
    }

    void setExerciseList(List<Exercise> exerciseList){
        if(this.exerciseList != null) {
            this.exerciseList.clear();
        }
        this.exerciseList = exerciseList;
    }

    void setExerciseDescriptionList(List<ExerciseDescription> exerciseList){
        this.exerciseDescriptionList = exerciseList;
    }

    List<Exercise> getExerciseList(){
        return exerciseList;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        Exercise textAtPosition = exerciseList.get(i);
        customViewHolder.fillView(textAtPosition);
    }

    @Override
    public int getItemCount() {
        return (null != exerciseList ? exerciseList.size() : 0);
    }
    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionText;

        CustomViewHolder(View view) {
            super(view);
            this.descriptionText = view.findViewById(R.id.description);
        }

        void fillView(Exercise textAtPosition) {
            ExerciseDescription ed = null;
            if(exerciseDescriptionList != null) {
                for (int i = 0; i < exerciseDescriptionList.size(); i++) {
                    if (exerciseDescriptionList.get(i).eid.equalsIgnoreCase(textAtPosition.exerciseDescriptionId)) {
                        ed = exerciseDescriptionList.get(i);
                    }
                }
            }
            if(ed != null) {
                descriptionText.setText(String.format(Locale.ENGLISH, "%dx( %d x %dm) + %s",
                        textAtPosition.numberOfSetsInSeries, textAtPosition.numberOfRepetitionsInSet, textAtPosition.distance,
                        ed.description
                ));
            }
        }
    }

    private OnItemClickListener onItemClickListener;
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
