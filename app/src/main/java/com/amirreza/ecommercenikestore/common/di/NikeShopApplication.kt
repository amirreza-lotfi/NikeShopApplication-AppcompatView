package com.amirreza.ecommercenikestore.common.di

import android.app.Application
import android.os.Bundle
import com.amirreza.ecommercenikestore.data.http.createInstanceOfApiService
import com.amirreza.ecommercenikestore.data.repository.CommentRepositoryImpl
import com.amirreza.ecommercenikestore.data.repository.FrescoImageLoadingService
import com.example.nikeshop.feature_shop.data.repository.BannerRepositoryImpl
import com.example.nikeshop.feature_shop.data.repository.ProductRepositoryImpl
import com.amirreza.ecommercenikestore.data.source.banner_data_source.BannerRemoteDataSource
import com.amirreza.ecommercenikestore.data.source.comment_data_source.RemoteCommentDataSource
import com.amirreza.ecommercenikestore.data.source.product_data_spurce.ProductLocalDataSource
import com.amirreza.ecommercenikestore.domain.repository.CommentRepositoryI
import com.amirreza.ecommercenikestore.domain.repository.ImageLoaderI
import com.example.nikeshop.feature_shop.data.source.product_data_spurce.ProductRemoteDataSource
import com.example.nikeshop.feature_shop.domain.repository.BannerRepositoryI
import com.amirreza.ecommercenikestore.domain.repository.ProductRepositoryI
import com.amirreza.ecommercenikestore.domain.useCases.BannerUseCases
import com.amirreza.ecommercenikestore.domain.useCases.CommentUseCase
import com.amirreza.ecommercenikestore.domain.useCases.ProductUseCases
import com.amirreza.ecommercenikestore.domain.useCases.banner_usecase.GetBannerUC
import com.amirreza.ecommercenikestore.domain.useCases.comment_usecases.GetComments
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.AddProductToFavoritesUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.DeleteProductFromFavoritesUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.GetFavoriteProductsUC
import com.amirreza.ecommercenikestore.domain.useCases.product_usecases.GetProductsUC
import com.amirreza.ecommercenikestore.presebtation.all_comments_fragment.AllCommentFragment
import com.amirreza.ecommercenikestore.presebtation.all_comments_fragment.AllCommentViewModel
import com.amirreza.ecommercenikestore.presebtation.home_fragment.HomeFragmentViewModel
import com.amirreza.ecommercenikestore.presebtation.home_fragment.product_list_util.ProductListAdapter
import com.amirreza.ecommercenikestore.presebtation.product_detail_fragment.ProductDetailViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class NikeShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)

        val module = module {
            single { createInstanceOfApiService() }


            factory<ProductRepositoryI> {
                ProductRepositoryImpl(
                    ProductRemoteDataSource(get()),
                    ProductLocalDataSource()
                )
            }

            factory<CommentRepositoryI>{
                CommentRepositoryImpl(
                    RemoteCommentDataSource(get())
                )
            }

            factory<BannerRepositoryI> {
                BannerRepositoryImpl(
                    BannerRemoteDataSource(get())
                )
            }

            factory<ImageLoaderI> {
                FrescoImageLoadingService()
            }

            factory {
                ProductListAdapter(get())
            }

            single {
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
            single {
                CommentUseCase(
                    GetComments(get())
                )
            }


        }
        val viewModelsModule = module {
            viewModel {
                HomeFragmentViewModel(get(), get())
            }
            viewModel { (bundle: Bundle)->
                ProductDetailViewModel(bundle,get())
            }
            viewModel { (int:Int)->
                AllCommentViewModel(int,get())
            }
        }
        startKoin {
            androidContext(this@NikeShopApplication)
            modules(listOf(module, viewModelsModule))
        }
    }
}