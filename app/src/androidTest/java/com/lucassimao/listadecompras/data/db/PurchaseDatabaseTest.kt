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
    fun should_insert_new_purchase_in_database() = runBlocking {
        val purchase = PurchaseModel(1, "test", 1, "test")

        dao.insertPurchase(purchase)

        val expected = dao.getAllPurchase().first().contains(purchase)

        Truth.assertThat(expected).isTrue()
    }

    @Test
    fun should_get_purchase_list_in_database() = runBlocking {
        val purchase = generatesPurchaseItem(1)
        val purchase2 = generatesPurchaseItem(2)
        val purchase3 = generatesPurchaseItem(3)

        dao.insertPurchase(purchase)
        dao.insertPurchase(purchase2)
        dao.insertPurchase(purchase3)

        val actual = 3
        val expected = dao.getAllPurchase().first().size

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun should_return_an_empty_list_when_there_are_no_saved_purchases() = runBlocking {

        val actual = 0
        val expected = dao.getAllPurchase().first().size

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun should_return_a_list_sorted_in_ascending_order() = runBlocking {
        val purchase = generatesPurchaseItem(1)
        val purchase2 = generatesPurchaseItem(2)
        val purchase3 = generatesPurchaseItem(3)

        dao.insertPurchase(purchase)
        dao.insertPurchase(purchase2)
        dao.insertPurchase(purchase3)

        val expected = dao.getAllPurchase().first().sortedWith { first, second ->
            first.item_id.compareTo(second.item_id)
        }

        val actual = listOf(purchase, purchase2, purchase3).sortedWith { first, second ->
            first.item_id.compareTo(second.item_id)
        }

        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun should_update_purchase_field_in_database() = runBlocking {
        val purchase = generatesPurchaseItem(1)
        val update = PurchaseModel(1, "test 2", 2, "test 2")

        dao.insertPurchase(purchase)
        dao.updatePurchase(update)


        val expected = dao.getAllPurchase().first().contains(update)

        Truth.assertThat(expected).isTrue()
    }

    @Test
    fun should_delete_a_purchase_from_list() = runBlocking {
        val purchase = generatesPurchaseItem(1)

        dao.insertPurchase(purchase)
        dao.deletePurchase(purchase)


        val expected = dao.getAllPurchase().first().contains(purchase)

        Truth.assertThat(expected).isFalse()
    }

    @Test
    fun should_delete_all_items_from_purchase_list() = runBlocking {
        val purchase = generatesPurchaseItem(1)
        val purchase2 = generatesPurchaseItem(2)
        val purchase3 = generatesPurchaseItem(3)

        dao.insertPurchase(purchase)
        dao.insertPurchase(purchase2)
        dao.insertPurchase(purchase3)

        dao.deleteAllPurchase()

        val actual = 0
        val expected = dao.getAllPurchase().first().size

        Truth.assertThat(actual).isEqualTo(expected)
    }

    private fun generatesPurchaseItem(id: Int) = PurchaseModel(id, "test", 1, "test")
}