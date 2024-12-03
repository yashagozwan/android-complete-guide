package com.yashagozwan.cryptoapp.core.config

import com.yashagozwan.cryptoapp.data.repository.CryptoRepositoryImpl
import com.yashagozwan.cryptoapp.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MyModule {
    @Binds
    @Singleton
    abstract fun bindCryptoRepository(
        cryptoRepositoryImpl: CryptoRepositoryImpl,
    ): CryptoRepository
}