package nl.rwslinkman.presentable.mock;

import nl.rwslinkman.presentable.interaction.PresentableItemClickListener;

/**
 * @author Rick Slinkman
 */

public class MockClickListener implements PresentableItemClickListener
{
    private int clickCount;

    @Override
    public void onItemClicked(Object item) {
        clickCount++;
    }

    public boolean wasClicked()
    {
        return clickCount > 0;
    }

    public int getClickCount()
    {
        return this.clickCount;
    }
}
