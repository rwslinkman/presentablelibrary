package nl.rwslinkman.presentable.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import nl.rwslinkman.presentable.Presenter;

/**
 * @author Rick Slinkman
 */

public class TestStringPresenter implements Presenter<String, TestStringPresenter.ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, String item) {
        //
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
