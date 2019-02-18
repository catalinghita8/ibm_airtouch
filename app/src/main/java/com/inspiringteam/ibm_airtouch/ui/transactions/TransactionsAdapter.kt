package com.inspiringteam.ibm_airtouch.ui.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inspiringteam.ibm_airtouch.R
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionsAdapter(private val transactions: List<Transaction>) :
        RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(transaction: Transaction) {
            itemView.tvName.text = transaction.productName
            itemView.tvAmount.text = transaction.amount
            itemView.tvCurrency.text = transaction.currency
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TransactionsAdapter.ViewHolder {
        // create a new view
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)

        return ViewHolder(layout)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindItems(transactions[position])
    }


    override fun getItemCount() = transactions.size
}