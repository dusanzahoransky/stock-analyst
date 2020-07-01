package com.github.dusanzahoransky.stockanalyst.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.convert.DbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

@Configuration
class MongoDbConfig {

    @Bean
    fun mongoConverter(mongoFactory: MongoDbFactory?, mongoMappingContext: MongoMappingContext?): MappingMongoConverter? {
        val dbRefResolver: DbRefResolver = DefaultDbRefResolver(mongoFactory!!)
        val mongoConverter = MappingMongoConverter(dbRefResolver, mongoMappingContext!!)
        mongoConverter.setMapKeyDotReplacement("-DOT-")
        return mongoConverter
    }
}