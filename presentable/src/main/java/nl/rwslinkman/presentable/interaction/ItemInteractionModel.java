package nl.rwslinkman.presentable.interaction;

import android.view.View;

/**
 * @author Rick Slinkman
 */

public class ItemInteractionModel<T> implements View.OnClickListener, View.OnLongClickListener
{
    private T object;
    private PresentableItemClickListener<T> itemClickListener;
    private PresentableItemPressedListener<T> itemLongClickListener;

    public ItemInteractionModel(T item)
    {
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

    public void putClickListener(PresentableItemClickListener<T> clickListener) {
        this.itemClickListener = clickListener;
    }

    public void putPressedListener(PresentableItemPressedListener<T> pressedListener) {
        this.itemLongClickListener = pressedListener;
    }

    public void putInteractionListener(PresentableItemInteractionListener<T> interactionListener)
    {
        this.itemClickListener = interactionListener;
        this.itemLongClickListener = interactionListener;
    }
}