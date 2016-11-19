package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import nl.rwslinkman.presentable.util.TestStringPresenter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Rick Slinkman
 */
public class PresentableAdapterTest
{

    @Test
    public void test_shouldReturnViewHolder()
    {
        PresentableAdapter<String> adapter = buildTestAdapter(true);
        ViewGroup inputGroup = new RelativeLayout(null);

        RecyclerView.ViewHolder output = adapter.onCreateViewHolder(inputGroup, 0);
        assertNotNull(output);
        assertNotNull(output.itemView);
    }

    @Test(expected = Exception.class)
    public void test_shouldThrowExceptionWhenNoViewReturned()
    {
        PresentableAdapter<String> adapter = buildTestAdapter(false);
        ViewGroup inputGroup = new RelativeLayout(null);

        RecyclerView.ViewHolder output = adapter.onCreateViewHolder(inputGroup, 0);
    }

    @Test
    public void test_shouldReturnItemCount()
    {
        PresentableAdapter<String> adapter = buildTestAdapter(true);
        assertEquals(2, adapter.getItemCount());
    }

    private PresentableAdapter<String> buildTestAdapter(boolean shouldCreateView)
    {
        List<String> data = Arrays.asList("Hello", "World");
        return buildTestAdapter(shouldCreateView, data);
    }

    private PresentableAdapter<String> buildTestAdapter(boolean shouldCreateView, List<String> data)
    {
        TestStringPresenter presenter = new TestStringPresenter(shouldCreateView);
        return new PresentableAdapter<>(presenter, data);
    }
}