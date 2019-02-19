package com.inspiringteam.ibm_airtouch.ui.transactions


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.inspiringteam.ibm_airtouch.R
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

@ActivityScoped
class TransactionsFragment @Inject constructor() : DaggerFragment(), TransactionsContract.View {
    @Inject
    lateinit var presenter: TransactionsPresenter

    private var snackbar: Snackbar? = null

    private lateinit var viewAdapter: TransactionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_transactions, container, false)

        val transactionsRecyclerView = rootView.findViewById(R.id.transactionsRecyclerView) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        transactionsRecyclerView.layoutManager = layoutManager
        transactionsRecyclerView.adapter = TransactionsAdapter(ArrayList<Transaction>())

        transactionsRecyclerView.apply { setHasFixedSize(true) }
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onResume() {
        super.onResume()

        presenter.subscribe(this)

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                presenter.getRelatedTransactions(parentView.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }

        }
    }

    override fun onPause() {
        super.onPause()

        presenter.unsubscribe()
    }

    override fun showProducts(list: List<String>) {
        // Populate spinner with products
        spinner.adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, list)

    }

    override fun showRelatedTransactions(list: List<Transaction>) {
        // Now that we have the transactions, let's require the sum as well
        presenter.getRelatedTransactionsSum(list)

        viewAdapter = TransactionsAdapter(list)
        transactionsRecyclerView.adapter = viewAdapter
    }

    override fun showRelatedTransactionsSum(value: String) {
        tvSum.text = value
    }


    override fun showError() {
        snackbar = Snackbar.make(
            view!!,
            getString(R.string.error_message), Snackbar.LENGTH_LONG
        )
        snackbar?.view?.setBackgroundColor(Color.RED)
        snackbar?.show()
    }
}
