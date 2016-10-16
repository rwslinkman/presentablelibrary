package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Rick Slinkman
 */
public interface Presenter<O, V extends RecyclerView.ViewHolder>
{
    V onCreateViewHolder(ViewGroup parent);
    void onBindViewHolder(V viewHolder, O item);
    void onUnbindViewHolder(V viewHolder);
}