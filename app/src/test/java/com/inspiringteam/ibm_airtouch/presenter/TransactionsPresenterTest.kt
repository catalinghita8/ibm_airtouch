package com.inspiringteam.ibm_airtouch.presenter

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmRepositorySource
import com.inspiringteam.ibm_airtouch.ui.transactions.TransactionsContract
import com.inspiringteam.ibm_airtouch.ui.transactions.TransactionsPresenter
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import uk.co.transferx.app.util.schedulers.ImmediateSchedulerProvider


class TransactionsPresenterTest {
    // SUT
    lateinit var presenter: TransactionsPresenter

    @Mock
    lateinit var repository: IbmRepositorySource

    @Mock
    lateinit var view: TransactionsContract.View

    // Mock Data
    private val rawListProducts =
        listOf<String>("Product1", "Product2", "Product3")

    private val rawListTransactions =
        listOf<Transaction>(Transaction("Product1", "55", "PD"))

    // Mock Data
    private val rawListRates =
        listOf<ExchangeRate>(ExchangeRate("", "CAD", "1.67"), ExchangeRate("EUR", "CAD", "1.67"))

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = TransactionsPresenter(repository, ImmediateSchedulerProvider())
    }

    @Test
    fun testAttachView_successResponse() {
        prepareSuccessfulViewAttach()

        // Verify that correct API calls are made at this stage
        Mockito.verify(repository).getProducts()

        Mockito.verify(repository).getRates()

        Mockito.verify(view).showProducts(Mockito.anyListOf(String::class.java))
    }

    @Test
    fun testRequestTransactions_successResponse() {
        prepareSuccessfulViewAttach()


        Mockito.`when`(repository.getRelatedTransactions("Product1")).thenReturn(rawListTransactions)

        presenter.getRelatedTransactions("Product1")

        // Verify that correct API calls are made at this stage
        Mockito.verify(repository).getRelatedTransactions("Product1")


        Mockito.verify(view).showRelatedTransactions(rawListTransactions)
    }

    @Test
    fun testAttachView_failureResponse() {
        Mockito.`when`(repository.getProducts()).thenReturn(Single.error(Throwable()))

        // Prepare successful mock response from API
        Mockito.`when`(repository.getRates()).thenReturn(Single.error(Throwable()))

        // Attach UI
        presenter.subscribe(view)

        // Make sure the view acts accordingly
        Mockito.verify(view, times(2)).showError()
    }

    private fun prepareSuccessfulViewAttach() {
        // Prepare successful mock response from API
        Mockito.`when`(repository.getProducts()).thenReturn(Single.just(rawListProducts))

        // Prepare successful mock response from API
        Mockito.`when`(repository.getRates()).thenReturn(Single.just(rawListRates))

        // Attach UI
        presenter.subscribe(view)
    }
}