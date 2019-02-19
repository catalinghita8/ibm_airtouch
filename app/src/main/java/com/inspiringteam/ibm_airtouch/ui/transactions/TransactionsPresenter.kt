package com.inspiringteam.ibm_airtouch.ui.transactions

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmRepositorySource
import com.inspiringteam.ibm_airtouch.di.scopes.ActivityScoped
import com.inspiringteam.ibm_airtouch.mvp.BasePresenter
import com.inspiringteam.ibm_airtouch.utils.Constants
import io.reactivex.disposables.CompositeDisposable
import uk.co.transferx.app.util.schedulers.BaseSchedulerProvider
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@ActivityScoped
class TransactionsPresenter @Inject constructor(
    val repository: IbmRepositorySource,
    val schedulerProvider: BaseSchedulerProvider
) :
    BasePresenter<TransactionsContract.View>(),
    TransactionsContract.Presenter {
    lateinit var listOfProducts: List<String>
    lateinit var listOfRates: ArrayList<ExchangeRate>
    var disposable: CompositeDisposable? = null


    override
    fun subscribe(view: TransactionsContract.View) {
        super.subscribe(view)

        if (disposable == null) disposable = CompositeDisposable()

        getProducts()
    }

    override fun unsubscribe() {
        super.unsubscribe()
        disposable?.let {
            disposable!!.dispose()
            disposable = null
        }
    }

    override
    fun getProducts() {
        // Get rates beforehand to assure faster retrieval

        disposable?.add(
            repository.getRates()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    // onNext
                    { list ->
                        // Caching purposes
                        listOfRates = ArrayList(list)
                    },
                    // onError
                    { view?.showError() }
                ))

        disposable?.add(
            repository.getProducts()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    // onNext
                    { list ->
                        // Caching purposes
                        listOfProducts = list
                        view?.showProducts(removeDuplicates(ArrayList(list)))
                    },
                    // onError
                    { view?.showError() }
                ))
    }

    override
    fun getRelatedTransactions(productName: String) {
        view?.showRelatedTransactions(repository.getRelatedTransactions(productName))
    }

    override
    fun getRelatedTransactionsSum(list: List<Transaction>) {
        var value = BigDecimal.valueOf(0)

        for (t in list) {
            // First add transactions with currency in default value Constants.DEFAULT_CURRENCY
            if (t.currency.equals(Constants.DEFAULT_CURRENCY)) {
                value = value.add(BigDecimal.valueOf(t.amount.toDouble()))
                value = value.setScale(2, RoundingMode.HALF_EVEN)
            } else {
                // First try to search if exchange rate is known
                for (rate in listOfRates) {
                    if (t.currency.equals(rate.from) && rate.to.equals(Constants.DEFAULT_CURRENCY)) {
                        // We have a direct exchange rate
                        val rawValue = BigDecimal.valueOf(t.amount.toDouble())
                            .multiply(BigDecimal.valueOf(rate.rate.toDouble()))
                        value = value.setScale(2, RoundingMode.HALF_EVEN)
                    } else {
                        // We need to get the exchange rate
                        val newRate = getConversionRate(t.currency, Constants.DEFAULT_CURRENCY, listOfRates)

                        // Add to sum
                        val newValue = BigDecimal.valueOf(t.amount.toDouble())
                            .multiply(BigDecimal.valueOf(newRate.rate.toDouble()))
                        val roundedValue = newValue.setScale(2, RoundingMode.HALF_EVEN)
                        value = value.add(roundedValue)
                    }
                }
            }
        }

        // Show the sum
        view?.showRelatedTransactionsSum(value.toString())
    }

    private fun getConversionRate(from: String, to: String, list: List<ExchangeRate>): ExchangeRate {
        // We know it's missing thus let's search for missing chains
        var tempTo = from
        var tempFrom = from
        var currentRateValue = BigDecimal.valueOf(1)

        while (tempTo != to) {
            // Search for next kin
            for (currentRate in list) {
                if (tempFrom == currentRate.from) {
                    if (currentRate.to.equals(to)) {
                        // Found last chain piece then exit
                        currentRateValue = currentRateValue.multiply(BigDecimal.valueOf(currentRate.rate.toDouble()))
                        tempTo = to
                        break
                    }

                    // if not, keep on going
                    tempFrom = currentRate.to
                    currentRateValue = currentRateValue.multiply(BigDecimal.valueOf(currentRate.rate.toDouble()))
                }
            }
        }

        return ExchangeRate(from, to, currentRateValue.toString())
    }

    private fun removeDuplicates(list: ArrayList<String>): List<String> {
        val set = HashSet(list)
        list.clear()
        list.addAll(set)
        return list
    }
}