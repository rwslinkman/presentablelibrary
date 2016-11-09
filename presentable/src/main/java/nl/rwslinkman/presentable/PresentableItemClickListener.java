package nl.rwslinkman.presentable;

/**
 * @author Rick Slinkman
 */

public interface PresentableItemClickListener<T>
{
    void onItemClicked(T item);
    void onItemPressed(T item);
}
