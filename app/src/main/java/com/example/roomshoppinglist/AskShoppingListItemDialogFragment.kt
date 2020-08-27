package com.example.roomshoppinglist

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_ask_new_shopping_list_item.view.*

class AskShoppingListItemDialogFragment : DialogFragment(){
    private lateinit var mListener: AddDialogListener
    interface AddDialogListener {
        fun onDialogPositiveClick(item: ShoppingListItem)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as AddDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement AddDialogListener"))
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val customView = LayoutInflater.from(context).inflate(R.layout.dialog_ask_new_shopping_list_item, null)
            val builder = AlertDialog.Builder(it)
            builder.setView(customView)

            builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_ok) { dialog, id ->
                    // create a ShoppingList item
                    val name = customView.nameEditText.text.toString()
                    val count = customView.countEditText.text.toString().toInt()
                    val price = customView.priceEditText.text.toString().toDouble()
                    val item = ShoppingListItem(0, name, count, price)
                    mListener.onDialogPositiveClick(item)
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.dialog_cancel) { dialog, id ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }




        }
