package com.dobrowol.traininglog.adding_training.adding_exercise;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dobrowol.traininglog.adding_training.TrainingRoomDatabase;

import java.util.List;

import io.reactivex.Single;

public class ExerciseRepository {

    private ExerciseDAO mExerciseDao;
    private LiveData<List<Exercise>> mAllExercises;

    ExerciseRepository(Application application) {
        TrainingRoomDatabase db = TrainingRoomDatabase.getDatabase(application);
        mExerciseDao = db.exerciseDAO();
        mAllExercises = mExerciseDao.getAll();
    }

    LiveData<List<Exercise>> getAllExercises() {
        return mAllExercises;
    }

    LiveData<ExerciseDescription> getExerciseDescription(String description) {
        return mExerciseDao.findExerciseDescriptionByDescription(description);
    }

    public Single<Long> insert (Exercise exercise) {
        return Single.fromCallable(() -> mExerciseDao.insert(exercise));
    }

    public void update(Exercise exercise) {
        new updateAsyncTask(mExerciseDao).execute(exercise);
    }

    LiveData<Exercise> getExerciseById(String id) {
        return mExerciseDao.getExerciseById(id);
    }

    public void delete(Exercise deletedItem) {
        new deleteAsyncTask(mExerciseDao).execute(deletedItem);
    }

    public LiveData<Integer> getMaximumLoad() {

        return mExerciseDao.getMaximumLoad();
    }

    private static class deleteAsyncTask extends AsyncTask<Exercise, Void, Void> {

        private ExerciseDAO mAsyncTaskDao;

        deleteAsyncTask(ExerciseDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Exercise... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Exercise, Void, Void> {

        private ExerciseDAO mAsyncTaskDao;

        updateAsyncTask(ExerciseDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Exercise... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}