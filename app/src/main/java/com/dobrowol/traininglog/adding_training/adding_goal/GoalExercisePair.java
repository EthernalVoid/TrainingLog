package com.dobrowol.traininglog.adding_training.adding_goal;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.dobrowol.traininglog.adding_training.adding_exercise.Exercise;

import java.util.List;

public class GoalExercisePair {
    @Embedded
    public Goal goal;
    @Embedded
    public Exercise exercise;

}
