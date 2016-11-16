package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    private PresentableItemPressedListener<T> pressedListener;

    public PresentableAdapter(Presenter presenter, List<T> data)
    {
        this.presenter = presenter;
		if(data == null) {
			data = new ArrayList<>();
		}
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return presenter.onCreateViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        // Get item and raise event to Presenter
        T item = this.data.get(position);
        presenter.onBindViewHolder(holder, item);

        // After creating item, put listeners
        ItemInteractionModel itemInteractionModel = new ItemInteractionModel(item);
        if(this.clickListener != null) {
            itemInteractionModel.putClickListener(this.clickListener);
            holder.itemView.setOnClickListener(itemInteractionModel);
        }
        if(this.pressedListener != null) {
            itemInteractionModel.putPressedListener(this.pressedListener);
            holder.itemView.setOnLongClickListener(itemInteractionModel);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return this.data;
    }

    public T getItem(int position)
    {
        return this.data.get(position);
    }

    @SuppressWarnings("unused")
    public void setItemClickListener(PresentableItemClickListener<T> listener)
    {
        this.clickListener = listener;
    }

    public void setItemPressedListener(PresentableItemPressedListener<T> listener)
    {
        this.pressedListener = listener;
    }

    private class ItemInteractionModel implements View.OnClickListener, View.OnLongClickListener
    {
        private T object;
        private PresentableItemClickListener<T> itemClickListener;
        private PresentableItemPressedListener<T> itemLongClickListener;

        ItemInteractionModel(T item)
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
            if(itemLongClickListener != null) {
                this.itemLongClickListener.onItemPressed(this.object);
            }
            return true;
        }

        void putClickListener(PresentableItemClickListener<T> clickListener) {
            this.itemClickListener = clickListener;
        }

        void putPressedListener(PresentableItemPressedListener<T> pressedListener) {
            this.itemLongClickListener = pressedListener;
        }
    }
}
