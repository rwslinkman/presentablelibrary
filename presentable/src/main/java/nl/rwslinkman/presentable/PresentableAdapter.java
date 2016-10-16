package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * class PresentableAdapter
 * Adapter that uses a custom presenter to display views.
 * Includes item click and selection handling
 * @author Rick Slinkman
 */
@SuppressWarnings("unchecked")
public class PresentableAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Presenter presenter;
    private List<T> data;
    private PresentableItemClickListener<T> clickListener;

    public PresentableAdapter(Presenter presenter, List<T> data)
    {
        this.presenter = presenter;
        this.data = data;
    }

    @SuppressWarnings("unused")
    public PresentableAdapter(Presenter presenter, List<T> data, PresentableItemClickListener<T> listener)
    {
        this(presenter, data);
        this.clickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return presenter.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        T item = this.data.get(position);
        presenter.onBindViewHolder(holder, item);
        ItemClicker clickListener = new ItemClicker(item, this.clickListener);
        holder.itemView.setOnClickListener(clickListener);
        holder.itemView.setOnLongClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @SuppressWarnings("unused")
    public void setItemClickListener(PresentableItemClickListener<T> listener)
    {
        this.clickListener = listener;
    }

    private class ItemClicker implements View.OnClickListener, View.OnLongClickListener
    {
        private PresentableItemClickListener<T> itemClickListener;
        private T object;

        ItemClicker(T item, PresentableItemClickListener<T> clickListener)
        {
            this.itemClickListener = clickListener;
            this.object = item;
        }

        @Override
        public void onClick(View view)
        {
            if(itemClickListener != null) {
                this.itemClickListener.onItemClicked(this.object);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(itemClickListener != null) {
                this.itemClickListener.onItemSelected(this.object);
            }
            return true;
        }
    }
}
