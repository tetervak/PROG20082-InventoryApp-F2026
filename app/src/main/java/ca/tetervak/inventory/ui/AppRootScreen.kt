/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ca.tetervak.inventory.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.tetervak.inventory.ui.home.HomeScreen
import ca.tetervak.inventory.ui.home.HomeViewModel
import ca.tetervak.inventory.ui.item.ItemDetailsScreen
import ca.tetervak.inventory.ui.item.ItemDetailsViewModel
import ca.tetervak.inventory.ui.item.ItemEditScreen
import ca.tetervak.inventory.ui.item.ItemEditViewModel
import ca.tetervak.inventory.ui.item.ItemEntryScreen
import ca.tetervak.inventory.ui.item.ItemEntryViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun AppRootScreen(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}

/**
 * Provides Navigation graph for the application.
 */

@Serializable
object HomeRoute

@Serializable
object ItemEntryRoute

@Serializable
data class ItemDetailsRoute(val itemId: Int)

@Serializable
data class ItemEditRoute(val itemId: Int)

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        composable<HomeRoute> {
            val viewModel: HomeViewModel = koinViewModel()
            HomeScreen(
                viewModel = viewModel,
                navigateToItemEntry = { navController.navigate(ItemEntryRoute) },
                navigateToItemUpdate = {
                    navController.navigate(ItemDetailsRoute(it))
                }
            )
        }
        composable<ItemEntryRoute> {
            val viewModel: ItemEntryViewModel = koinViewModel()
            ItemEntryScreen(
                viewModel = viewModel,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable<ItemDetailsRoute> {
            val viewModel: ItemDetailsViewModel = koinViewModel()
            ItemDetailsScreen(
                viewModel = viewModel,
                navigateToEditItem = { navController.navigate(ItemEditRoute(it)) },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable<ItemEditRoute> {
            val viewModel: ItemEditViewModel = koinViewModel()
            ItemEditScreen(
                viewModel = viewModel,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}