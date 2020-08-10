package com.github.dusanzahoransky.stockanalyst.controller


import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist
import com.github.dusanzahoransky.stockanalyst.service.AdminService
import com.github.dusanzahoransky.stockanalyst.service.WatchlistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin")
class AdminController @Autowired constructor(
        val adminService: AdminService
) {
    @PutMapping("di")
    @ResponseBody
    fun runDi() {
        return adminService.runDi()
    }

}