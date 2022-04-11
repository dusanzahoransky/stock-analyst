package com.github.dusanzahoransky.stockanalyst.repository

import com.mongodb.ConnectionString
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import scala.sys.*


@Configuration
class MongoDbConfig: AbstractMongoClientConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    lateinit var connectionString: String

    @Bean
    override fun mappingMongoConverter(databaseFactory: MongoDatabaseFactory, customConversions: MongoCustomConversions, mappingContext: MongoMappingContext): MappingMongoConverter {
        val converter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext)
        converter.setMapKeyDotReplacement("-DOT-")
        return converter
    }

    @Bean
    override fun mongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(ConnectionString(connectionString))
    }

    override fun getDatabaseName(): String {
        return "Stocks"
    }

    override fun getMappingBasePackage(): String {
        return "com.github.dusanzahoransky.stockanalyst.model.mongo"
    }
}