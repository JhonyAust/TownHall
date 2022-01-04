package com.example.townhall.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.townhall.databinding.ItemCustomListLayoutBinding
import com.example.townhall.view.activities.AddUpdateDishActivity
import com.example.townhall.view.fragments.AllDishesFragment


// TODO: Create a custom list adapter to use it while showing the list item in the RecyclerView.
// START
class CustomListItemAdapter(
    private val activity: Activity,
    private val fragment: Fragment?,
    private val listItems: List<String>,
    private val selection: String
) :
    RecyclerView.Adapter<CustomListItemAdapter.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCustomListLayoutBinding =
            ItemCustomListLayoutBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listItems[position]

        holder.tvText.text = item
        // TODO: Define the ItemView click event and send the result to the base class.
        // START
        holder.itemView.setOnClickListener {

            if (activity is AddUpdateDishActivity) {
                activity.selectedListItem(item, selection)
            }
            if (fragment is AllDishesFragment){
                fragment.filterSelection(item)
            }

        }
        // END
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return listItems.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: ItemCustomListLayoutBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val tvText = view.tvText
    }
}
// END