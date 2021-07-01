package com.github.dusanzahoransky.stockanalyst.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext


@Configuration
class MongoDbConfig: AbstractMongoClientConfiguration() {

    @Bean
    override fun mappingMongoConverter(databaseFactory: MongoDatabaseFactory, customConversions: MongoCustomConversions, mappingContext: MongoMappingContext): MappingMongoConverter {
        val converter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext)
        converter.setMapKeyDotReplacement("-DOT-")
        return converter
    }

    override fun getDatabaseName(): String {
        return "Stocks"
    }

    override fun getMappingBasePackage(): String {
        return "com.github.dusanzahoransky.stockanalyst.model.mongo"
    }
}