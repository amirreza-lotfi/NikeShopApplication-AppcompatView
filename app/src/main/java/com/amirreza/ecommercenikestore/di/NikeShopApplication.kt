package com.amirreza.ecommercenikestore.di

import android.app.Application
import com.amirreza.ecommercenikestore.data.http.createInstanceOfApiService
import com.example.nikeshop.feature_shop.data.repository.BannerRepositoryImpl
import com.example.nikeshop.feature_shop.data.repository.ProductRepositoryImpl
import com.amirreza.ecommercenikestore.data.source.banner_data_source.BannerRemoteDataSource
import com.amirreza.ecommercenikestore.data.source.product_data_spurce.ProductLocalDataSource
import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductRemoteDataSource
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import com.amirreza.ecommercenikestore.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.domain.useCases.ProductUseCases
import com.amirreza.ecommercenikestore.domain.useCases.banner_usecase.GetBannerUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.AddProductToFavoritesUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.DeleteProductFromFavoritesUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.GetFavoriteProductsUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.GetProductsUC
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
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

            single{
                ProductUseCases(
                    GetProductsUC(get()),
                    GetFavoriteProductsUC(get()),
                    AddProductToFavoritesUC(get()),
                    DeleteProductFromFavoritesUC(get()),
                )
            }
            single {
                BannerUseCases(
                    GetBannerUC(get())
                )
            }


        }
//        val viewModelsModule = module{
//            viewModel {
//                HomeScreenViewModel(get(),get())
//            }
//
//            viewModel {
//                ProductDetailViewModel(state = handle,productRepository = get())
//            }
//        }
        startKoin{
            androidContext(this@NikeShopApplication)
            modules(listOf(module))
        }
    }
}