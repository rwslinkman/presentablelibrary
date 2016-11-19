package nl.rwslinkman.presentable.interaction;

/**
 * @author Rick Slinkman
 */

public interface PresentableItemInteractionListener<T>
        extends PresentableItemClickListener<T>, PresentableItemPressedListener<T>
{
    // Interface methods are included via inheritance
}