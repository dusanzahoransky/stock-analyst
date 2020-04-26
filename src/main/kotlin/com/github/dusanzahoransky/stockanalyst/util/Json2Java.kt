package com.github.dusanzahoransky.stockanalyst.util

import com.sun.codemodel.JCodeModel
import org.jsonschema2pojo.*
import org.jsonschema2pojo.rules.RuleFactory
import java.net.URL
import java.nio.file.Files


fun main() {
    val codeModel = JCodeModel()

    val source: URL = CalcUtils::class.java.getResource("/FinancialsMockGOOGL.json")

    val config: GenerationConfig = object : DefaultGenerationConfig() {
        override fun getSourceType(): SourceType {
            return SourceType.JSON
        }
    }

    val mapper = SchemaMapper(RuleFactory(config, Jackson2Annotator(config), SchemaStore()), SchemaGenerator())
    mapper.generate(codeModel,
        "FinancialsResponse",
        "com.github.dusanzahoransky.stockanalyst.model.yahoo.financials",
        source)

    val outDir = Files.createTempDirectory("financials")
    println(outDir.toAbsolutePath())
    codeModel.build(outDir.toFile())
}