package com.pme.mpe.ui.block;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.pme.mpe.R;
import com.pme.mpe.activities.BlockCategoryActivity.NewBlockCategoryActivity;
import com.pme.mpe.activities.CategoryActivity.NewCategoryActivity;
import com.pme.mpe.model.relations.CategoryWithCatBlocksAndTasksRelation;
import com.pme.mpe.model.tasks.CategoryBlock;
import com.pme.mpe.storage.repository.TasksPackageRepository;
import com.pme.mpe.ui.category.CategoryAdapter;
import com.pme.mpe.ui.category.CategoryViewModel;

import java.util.List;

public class BlockFragment extends Fragment {

    private BlockViewModel blockViewModel;
    private List<CategoryBlock> blocks;
    private AppCompatTextView blockAdd;
    private TasksPackageRepository tasksPackageRepository;
    private CategoryWithCatBlocksAndTasksRelation categoryWithCatBlocksAndTasksRelation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        blockViewModel = new ViewModelProvider(this).get(BlockViewModel.class);
        View root = inflater.inflate(R.layout.fragment_block, container, false);
        GridView gridView = (GridView) root.findViewById(R.id.block_grid_layout);
        BlockGridAdapter blockGridAdapter = new BlockGridAdapter(getContext(), blocks, tasksPackageRepository,categoryWithCatBlocksAndTasksRelation);
        gridView.setAdapter(blockGridAdapter);

        blockViewModel.getBlocks().observe(this.requireActivity(),blockGridAdapter::setBlocks);

        blockAdd = root.findViewById(R.id.block_grid_text);
        blockAdd.setText(R.string.addBlock);
        LinearLayoutCompat addBlockActivity = root.findViewById(R.id.block_grid_add);
        addBlockActivity.setOnClickListener(this.addButtonClickListener);

        return root;
    }

    private final View.OnClickListener addButtonClickListener = v -> {

        if( v.getId() == R.id.block_grid_add)
        {
            Intent newBlockIntent = new Intent(getContext(), NewBlockCategoryActivity.class);
            startActivity(newBlockIntent);
        }
    };



}