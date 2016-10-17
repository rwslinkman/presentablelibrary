Presentable library
=======

Presentable Library for RecyclerViews in Android

Author: [Rick Slinkman](<http://rwslinkman.nl>)

The library can be used to bring the `Presentable` design pattern to the `RecyclerView` in Android.<br/>
List your items easily with a library-provided `Adapter`.<br/>
The `PresentableAdapter` comes with an integrated click and long-click listener.

Using the Presentable design pattern it is easy to manage lists.

Integrating with Android Studio
-------------------------------

The Presentable library comes as a Gradle bundle via jcenter.
```
repositories {
    jcenter()
}
```

```
dependencies {
	compile 'nl.rwslinkman.presentable:presentable:1.5'
}
```

How to use
----------
To use the `PresentableAdapter`, create an instance in your `Activity` or `Fragment`.

```
LinearLayoutManager llm = new LinearLayoutManager(this);
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.example_recycler);
recyclerView.setLayoutManager(llm);

// Create adapter
PresentableAdapter adapter = new PresentableAdapter<>(new ExampleStringPresenter(), data);
adapter.setItemClickListener(this);
recyclerView.setAdapter(adapter);
```

Create a Presenter for each object you want to display as a list item.<br/>
A Presenter must contain a ViewHolder that extends RecyclerView.ViewHolder

```
class ExampleStringPresenter implements Presenter<String, ExampleStringPresenter.ViewHolder>
{
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
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
    public void onBindViewHolder(ViewHolder viewHolder, String item) {
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
```

The example app contains a simple `Presenter` that presents a person's name.<br/>
Names are represented as one `String`.

Handling clicks
----------
The PresentableAdapter comes with an internal click and long-click listener.<br/>
A listener can be set through the `PresentableAdapter` constructor.
 ```
 mAdapter = new PresentableAdapter<>(new ExampleStringPresenter(), data,
    new PresentableItemClickListener<String>()
    {
         @Override
         public void onItemClicked(String item) {
            // Handle a click
         }

         @Override
         public void onItemSelected(String item) {
            // Handle a long-click

         }
    });
 ```
It is also possible to use a setter.
```
mAdapter.setItemClickListener(new PresentableItemClickListener<String>()
{
   @Override
   public void onItemClicked(String item) {
      // Handle a click
   }

   @Override
   public void onItemSelected(String item) {
      // Handle a long-click
   }
});
```
