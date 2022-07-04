package com.example.nikeshop.feature_shop.di

import android.app.Application
import com.amirreza.ecommercenikestore.data.http.createInstanceOfApiService
import com.example.nikeshop.feature_shop.data.repository.BannerRepositoryImpl
import com.example.nikeshop.feature_shop.data.repository.ProductRepositoryImpl
import com.amirreza.ecommercenikestore.data.source.banner_data_source.BannerRemoteDataSource
import com.amirreza.ecommercenikestore.data.source.product_data_spurce.ProductLocalDataSource
import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductRemoteDataSource
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import com.example.nikeshop.feature_shop.domain.repository.ProductRepositoryI
import com.example.nikeshop.feature_shop.presentation.home_screen.HomeScreenViewModel
import com.example.nikeshop.feature_shop.presentation.product_detail.ProductDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NikeShopApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        val module = module {
            single{ createInstanceOfApiService() }

            factory<ProductRepositoryI> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }

            factory<BannerRepositoryI> {
                BannerRepositoryImpl(
                    BannerRemoteDataSource(get())
                )
            }


        }
        val viewModelsModule = module{
            viewModel {
                HomeScreenViewModel(get(),get())
            }

            viewModel {
                ProductDetailViewModel(state = handle,productRepository = get())
            }
        }
        startKoin{
            androidContext(this@NikeShopApplication)
            modules(listOf(module,viewModelsModule))
        }
    }
}