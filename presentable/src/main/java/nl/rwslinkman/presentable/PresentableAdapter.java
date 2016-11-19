package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nl.rwslinkman.presentable.interaction.ItemInteractionModel;
import nl.rwslinkman.presentable.interaction.PresentableItemClickListener;
import nl.rwslinkman.presentable.interaction.PresentableItemInteractionListener;
import nl.rwslinkman.presentable.interaction.PresentableItemPressedListener;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get item and raise event to Presenter
        T item = this.data.get(position);
        presenter.onBindViewHolder(holder, item);

        // After creating item, put listeners
        ItemInteractionModel<T> itemInteractionModel = new ItemInteractionModel(item);
        if (this.clickListener != null) {
            itemInteractionModel.putClickListener(this.clickListener);
            holder.itemView.setOnClickListener(itemInteractionModel);
        }
        if (this.pressedListener != null) {
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

    @SuppressWarnings("unused")
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

    /**
     * Convenience method
     * @param listener PresentableItemInteractionListener<T>
     */
    public void setItemInteractionListener(PresentableItemInteractionListener<T> listener)
    {
        this.setItemClickListener(listener);
        this.setItemPressedListener(listener);
    }
}
