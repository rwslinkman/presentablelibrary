package nl.rwslinkman.presentable.interaction;

import org.junit.Test;

import nl.rwslinkman.presentable.mock.MockClickListener;
import nl.rwslinkman.presentable.mock.MockInteractionListener;
import nl.rwslinkman.presentable.mock.MockPressListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Rick Slinkman
 */
@SuppressWarnings("unchecked")
public class ItemInteractionModelTest
{
    @Test
    public void test_shouldCallClickListener_whenOnClick_withClickListener()
    {
        MockClickListener listener = new MockClickListener();
        ItemInteractionModel<String> model = new ItemInteractionModel<>("");
        model.putClickListener(listener);

        model.onClick(null);

        assertTrue(listener.wasClicked());
        assertEquals(1, listener.getClickCount());
    }

    @Test
    public void test_shouldCallPressListener_whenOnLongClick_withPressListener()
    {
        MockPressListener listener = new MockPressListener();
        ItemInteractionModel<String> model = new ItemInteractionModel<>("");
        model.putPressedListener(listener);

        model.onLongClick(null);

        assertTrue(listener.wasPressed());
        assertEquals(1, listener.getPressCount());
    }

    @Test
    public void test_shouldCallClickListener_whenOnClick_withInteractionListener()
    {
        MockInteractionListener listener = new MockInteractionListener();
        ItemInteractionModel<String> model = new ItemInteractionModel<>("");
        model.putInteractionListener(listener);

        model.onClick(null);

        assertTrue(listener.wasClicked());
        assertEquals(1, listener.getClickCount());
        assertFalse(listener.wasPressed());
        assertEquals(0, listener.getPressCount());
    }

    @Test
    public void test_shouldCallClickListener_whenOnLongClick_withInteractionListener()
    {
        MockInteractionListener listener = new MockInteractionListener();
        ItemInteractionModel<String> model = new ItemInteractionModel<>("");
        model.putInteractionListener(listener);

        model.onLongClick(null);

        assertFalse(listener.wasClicked());
        assertEquals(0, listener.getClickCount());
        assertTrue(listener.wasPressed());
        assertEquals(1, listener.getPressCount());
    }
}