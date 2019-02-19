package com.inspiringteam.ibm_airtouch.data

import com.inspiringteam.ibm_airtouch.data.models.ExchangeRate
import com.inspiringteam.ibm_airtouch.data.models.Transaction
import com.inspiringteam.ibm_airtouch.data.source.IbmRepository
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmRepositorySource
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class IbmRepositoryTest {
    // SUT
    lateinit var repository: IbmRepositorySource

    @Mock
    lateinit var remoteSource: IbmDataSource


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
        repository = IbmRepository(remoteSource)
    }

    @Test
    fun testRequestProducts() {
        // Input doesn't matter
        Mockito.`when`(remoteSource.getProducts()).thenReturn(Single.just(rawListTransactions))

        repository.getProducts()

        // Verify that correct API calls are made at this stage
        Mockito.verify(remoteSource).getProducts()
    }

    @Test
    fun testRequestRates() {
        // Input doesn't matter
        Mockito.`when`(remoteSource.getRates()).thenReturn(Single.just(rawListRates))

        repository.getRates()

        // Verify that correct API calls are made at this stage
        Mockito.verify(remoteSource).getRates()
    }
}