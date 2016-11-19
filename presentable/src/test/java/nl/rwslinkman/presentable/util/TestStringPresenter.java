package nl.rwslinkman.presentable.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.rwslinkman.presentable.Presenter;

/**
 * @author Rick Slinkman
 */

public class TestStringPresenter implements Presenter<String, TestStringPresenter.ViewHolder>
{
    private final boolean shouldCreateView;

    public TestStringPresenter(boolean shouldCreateView)
    {
        this.shouldCreateView = shouldCreateView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {
        if(shouldCreateView) {
            // Create view
            View createdView = new TextView(null);
            return new ViewHolder(createdView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, String item)
    {
        //
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
