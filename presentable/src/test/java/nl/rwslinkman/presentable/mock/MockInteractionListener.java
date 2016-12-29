package nl.rwslinkman.presentable.mock;

import nl.rwslinkman.presentable.interaction.PresentableItemInteractionListener;

/**
 * @author Rick Slinkman
 */

public class MockInteractionListener implements PresentableItemInteractionListener {

    private int clickCount, pressCount;

    @Override
    public void onItemClicked(Object item) {
        clickCount++;
    }

    @Override
    public void onItemPressed(Object item) {
        pressCount++;
    }

    public boolean wasPressed()
    {
        return this.pressCount > 0;
    }

    public boolean wasClicked()
    {
        return this.clickCount > 0;
    }

    public int getClickCount()
    {
        return this.clickCount;
    }

    public int getPressCount()
    {
        return this.pressCount;
    }
}
