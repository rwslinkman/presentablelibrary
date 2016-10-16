package nl.rwslinkman.presentablelibrary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nl.rwslinkman.presentable.Presenter;

/**
 * @author Rick Slinkman
 */

class ExampleStringPresenter implements Presenter<String, ExampleStringPresenter.ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent)
    {
        // Inflate your custom XML layout representing a list item in the RecyclerView
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_example, parent, false);

        // Find items in layout and put them in a custom ViewHolder
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.nameView = (TextView) v.findViewById(R.id.listitem_example_name_view);

        // Return ViewHolder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, String item)
    {
        // Put the item into the TextView
        viewHolder.nameView.setText(item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // NOP
    }

    // Create your custom ViewHolder representing the Views in the list item
    class ViewHolder extends RecyclerView.ViewHolder
    {
        // Your views go here
        TextView nameView;

        ViewHolder(View v)
        {
            super(v);
        }
    }
}
