package com.lucassimao.listadecompras.ui

import com.google.common.truth.Truth
import com.lucassimao.listadecompras.data.PurchaseRepository
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.utils.BaseUnitTest
import com.lucassimao.listadecompras.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PurchaseViewModelTest : BaseUnitTest() {

    private val repository: PurchaseRepository = mock()
    private val purchaseItem = mock<PurchaseModel>()
    private val purchaseList = mock<List<PurchaseModel>>()
    private val purchaseListEmpty = listOf<PurchaseModel>()

    @Test
    fun shouldInsert_inDatabase(): Unit = runBlocking {

        val viewModel = PurchaseViewModel(repository)

        viewModel.insert(
            purchaseItem,
            etPurchaseQuantity.text.toString(),
            etPurchasePrice.text.toString()
        )

        verify(repository, times(1)).insert(purchaseItem)
    }

    @Test
    fun shouldUpdate_inDatabase(): Unit = runBlocking {

        val viewModel = PurchaseViewModel(repository)

        viewModel.update(purchaseItem)

        verify(repository, times(1)).update(purchaseItem)
    }

    @Test
    fun shouldDelete_inDatabase(): Unit = runBlocking {

        val viewModel = PurchaseViewModel(repository)

        viewModel.delete(purchaseItem)

        verify(repository, times(1)).delete(purchaseItem)
    }

    @Test
    fun shouldGetAllPurchase_fromRepository(): Unit = runBlocking {

        val viewModel = mockSuccessfulCase()

        Truth.assertThat(purchaseList).isEqualTo(viewModel.getAllPurchase.getValueForTest())
    }

    @Test
    fun shouldReturnEmptyList_whenGetAllPurchaseIsEmpty(): Unit = runBlocking {

        val viewModel = mockEmptyCase()

        Truth.assertThat(purchaseListEmpty.size).isEqualTo(viewModel.getAllPurchase.getValueForTest()!!.size)
    }

    private fun mockEmptyCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(purchaseListEmpty)
            }
        )

        return PurchaseViewModel(repository)
    }

    private fun mockSuccessfulCase(): PurchaseViewModel {
        whenever(repository.getAllPurchase).thenReturn(
            flow {
                emit(purchaseList)
            }
        )

        return PurchaseViewModel(repository)
    }

}