package com.inspiringteam.ibm_airtouch.data

import com.inspiringteam.ibm_airtouch.data.source.contracts.IbmDataSource
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmAPI
import com.inspiringteam.ibm_airtouch.data.source.remote.IbmRemoteDataSource
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import uk.co.transferx.app.util.schedulers.ImmediateSchedulerProvider

class IbmRemoteDataSourceTest {
    // SUT
    lateinit var source: IbmDataSource

    @Mock
    lateinit var ibmApi: IbmAPI

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        source = IbmRemoteDataSource(ibmApi)
    }

    @Test
    fun testRequestProducts() {
        source.getProducts()

        // Verify that correct API calls are made at this stage
        Mockito.verify(ibmApi).getTransactions()
    }

    @Test
    fun testRequestRates() {
        source.getRates()

        // Verify that correct API calls are made at this stage
        Mockito.verify(ibmApi).getRates()
    }
}