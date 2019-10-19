package nl.rwslinkman.presentable;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Rick Slinkman
 */
public interface Presenter<O, V extends RecyclerView.ViewHolder>
{
    V onCreateViewHolder(ViewGroup parent);
    void onBindViewHolder(V viewHolder, O item);
}