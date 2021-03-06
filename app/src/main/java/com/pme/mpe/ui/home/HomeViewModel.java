package com.pme.mpe.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pme.mpe.model.tasks.Category;
import com.pme.mpe.model.tasks.CategoryBlock;
import com.pme.mpe.model.tasks.Task;
import com.pme.mpe.storage.repository.TasksPackageRepository;

import java.time.LocalDate;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final TasksPackageRepository tasksPackageRepository;

    public HomeViewModel (Application application)
    {
        super(application);
        this.tasksPackageRepository = TasksPackageRepository.getRepository(application);
    }

    public List<CategoryBlock> getCategoryBlocks(LocalDate date) {
        return this.tasksPackageRepository.getCategoryBlocksByGivenDay(date);
    }
    public List<Task> getTasks(){
        return this.tasksPackageRepository.getTasks();
    }

}