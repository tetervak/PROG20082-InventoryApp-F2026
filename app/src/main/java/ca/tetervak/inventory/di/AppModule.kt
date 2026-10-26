package ca.tetervak.inventory.di

import androidx.room.Room
import ca.tetervak.inventory.data.InventoryDatabase
import ca.tetervak.inventory.data.ItemDao
import ca.tetervak.inventory.data.ItemsRepository
import ca.tetervak.inventory.data.OfflineItemsRepository
import ca.tetervak.inventory.ui.home.HomeViewModel
import ca.tetervak.inventory.ui.item.ItemDetailsViewModel
import ca.tetervak.inventory.ui.item.ItemEditViewModel
import ca.tetervak.inventory.ui.item.ItemEntryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), InventoryDatabase::class.java, "inventory_database")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    single<ItemDao> { get<InventoryDatabase>().itemDao() }

    singleOf(::OfflineItemsRepository) bind ItemsRepository::class

    viewModelOf(::HomeViewModel)
    viewModelOf(::ItemDetailsViewModel)
    viewModelOf(::ItemEditViewModel)
    viewModelOf(::ItemEntryViewModel)
}