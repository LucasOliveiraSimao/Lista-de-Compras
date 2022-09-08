package com.lucassimao.listadecompras.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.lucassimao.listadecompras.data.model.PurchaseModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PurchaseDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: PurchaseDAO
    private lateinit var db: PurchaseDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PurchaseDatabase::class.java
        ).build()

        dao = db.purchaseDAO

    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun shouldInsertPurchaseInDatabase() = runBlocking {
        val purchase = purchaseItem(1)

        dao.insertPurchase(purchase)

        val response = dao.getAllPurchase().first()
        val expected = response.contains(purchase)

        Truth.assertThat(expected).isTrue()
    }

    @Test
    fun shouldGetAllPurchaseInDatabase() = runBlocking {
        val purchase = purchaseItem(1)
        val purchase2 = PurchaseModel(2, "test", 1, "test")
        val purchase3 = PurchaseModel(3, "test", 1, "test")

        dao.insertPurchase(purchase)
        dao.insertPurchase(purchase2)
        dao.insertPurchase(purchase3)

        val response = dao.getAllPurchase().first().size
        val expected = 3

        Truth.assertThat(expected).isEqualTo(response)
    }

    @Test
    fun shouldReturnEmptyList_whenGetAllPurchaseIsEmpty() = runBlocking {

        val response = dao.getAllPurchase().first().size
        val expected = 0

        Truth.assertThat(expected).isEqualTo(response)
    }

    @Test
    fun shouldReturnListSortedInAscendingOrder() = runBlocking {
        val purchase = purchaseItem(1)
        val purchase2 = purchaseItem(2)
        val purchase3 = purchaseItem(3)

        dao.insertPurchase(purchase)
        dao.insertPurchase(purchase2)
        dao.insertPurchase(purchase3)

        val response = dao.getAllPurchase().first().sortedWith { first, second ->
            first.item_id.compareTo(second.item_id)
        }

        val expected = listOf(purchase, purchase2, purchase3).sortedWith { first, second ->
            first.item_id.compareTo(second.item_id)
        }

        Truth.assertThat(expected).isEqualTo(response)
    }

    @Test
    fun shouldUpdatePurchaseInDatabase() = runBlocking {
        val purchase = purchaseItem(1)
        val update = PurchaseModel(1, "test 2", 2, "test 2")

        dao.insertPurchase(purchase)
        dao.updatePurchase(update)


        val response = dao.getAllPurchase().first()
        val expected = response.contains(update)

        Truth.assertThat(expected).isTrue()
    }

    @Test
    fun shouldDeletePurchaseInDatabase() = runBlocking {
        val purchase = purchaseItem(1)

        dao.insertPurchase(purchase)
        dao.deletePurchase(purchase)


        val response = dao.getAllPurchase().first()
        val expected = response.contains(purchase)

        Truth.assertThat(expected).isFalse()
    }

    private fun purchaseItem(id: Int) = PurchaseModel(id, "test", 1, "test")
}