package nl.rwslinkman.presentable.mock;

import nl.rwslinkman.presentable.interaction.PresentableItemPressedListener;

/**
 * @author Rick Slinkman
 */

public class MockPressListener implements PresentableItemPressedListener {

    private int pressCount;

    @Override
    public void onItemPressed(Object item) {
        pressCount++;
    }


    public int getPressCount() {
        return pressCount;
    }

    public boolean wasPressed()
    {
        return this.pressCount > 0;
    }
}
