package com.github.dusanzahoransky.stockanalyst.repository

import com.github.dusanzahoransky.stockanalyst.model.StockTicker
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist
import com.github.dusanzahoransky.stockanalyst.model.enums.Watchlist.*
import org.springframework.stereotype.Repository

@Suppress("SpellCheckingInspection")
@Repository
class WatchlistRepo {

    fun getWatchlist(watchlist: Watchlist): Set<StockTicker>{
        when(watchlist){
            TEST -> return test()
            INVESTED -> return invested()
            EUR -> return eur()
            AUD -> return aud()
            USD -> return usd()
            USD_TECH -> return usdTech()
            GBP -> return gbp()
            CHF -> return chf()
            GBP_INDICES -> return gbpIndices()
            AUD_INDICES -> return audIndices()
            INVESTED_IN_EUR -> return investedInEur()
            INVESTED_IN_GBP -> return investedInGbp()
            INVESTED_INDICES_IN_GBP -> return investedIndicesInGbp()
            WATCH_LIST_INDICES_GBP -> return watchListIndicesGbp()
            INVESTED_IN_USD -> return investedInUsd()
            WATCH_LIST_USD -> return watchListUsd()
            WATCH_LIST_EUR -> return watchListEur()
            WATCH_LIST_CHF -> return watchListChf()
            INVESTED_IN_CHF -> return investedInChf()
            WATCH_LIST_GBP -> return watchListGbp()
            WATCH_LIST_AUD -> return watchListAud()
            WATCH_LIST_INDICES_AUD -> return watchListIndicesAud()
            INVESTED_INDICES_IN_AUD -> return investedIndicesInAud()
            INVESTED_IN_AUD -> return investedInAud()
            NASDAQ_100 -> return nasdaq100()
        }
    }

    fun invested(): Set<StockTicker> {
        return setOf(
            *investedInAud().toTypedArray(),
            *investedInUsd().toTypedArray(),
            *investedInGbp().toTypedArray(),
            *investedInEur().toTypedArray()
        )
    }

    fun eur(): Set<StockTicker> {
        return setOf(
            *investedInEur().toTypedArray(),
            *watchListEur().toTypedArray()
        )
    }

    fun aud(): Set<StockTicker> {
        return setOf(
            *investedInAud().toTypedArray(),
            *watchListAud().toTypedArray()
        )
    }

    fun audIndices(): Set<StockTicker> {
        return setOf(
            *investedIndicesInAud().toTypedArray(),
            *watchListIndicesAud().toTypedArray()
        )
    }

    fun test(): Set<StockTicker> {
        return setOf(
//            StockTicker.fromString("CBA:ASX")
            StockTicker.fromString("GOOGL:NASDAQ")
//            StockTicker.fromString("LTM:NASDAQ"),
//            StockTicker.fromString("GRPN:NASDAQ"),
//            StockTicker.fromString("UAL:NASDAQ")
        )
    }

    fun usdTech(): Set<StockTicker> {
        return setOf(
            *investedInUsdTech().toTypedArray(),
            *watchListUsdTech().toTypedArray()
        )
    }

    fun usd(): Set<StockTicker> {
        return setOf(
            *investedInUsd().toTypedArray(),
            *watchListUsd().toTypedArray()
        )
    }

    fun gbp(): Set<StockTicker> {
        return setOf(
            *investedInGbp().toTypedArray(),
            *watchListGbp().toTypedArray()
        )
    }

    fun gbpIndices(): Set<StockTicker> {
        return setOf(
            *investedIndicesInGbp().toTypedArray(),
            *watchListIndicesGbp().toTypedArray()
        )
    }

    fun chf(): Set<StockTicker> {
        return setOf(
            *investedInChf().toTypedArray(),
            *watchListChf().toTypedArray()
        )
    }

    fun watchListEur(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("KU2:DAX"),
            StockTicker.fromString("SU:PA")
        )
    }

    fun investedInEur(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("SIE:DAX"),
            StockTicker.fromString("LHA:DAX")
        )
    }

    fun investedIndicesInGbp(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("VUSA:FTSE"),
            StockTicker.fromString("VFEM:FTSE")
        )
    }

    fun watchListIndicesGbp(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("VUSA:FTSE"),
            StockTicker.fromString("VFEM:FTSE"),
            StockTicker.fromString("VUKE:FTSE"),
            StockTicker.fromString("VMID:FTSE"),
            StockTicker.fromString("VHYL:FTSE"),
            StockTicker.fromString("VWRL:FTSE"),
            StockTicker.fromString("VEVE:FTSE"),
            StockTicker.fromString("VDEV:FTSE"),
            StockTicker.fromString("VAPX:FTSE"),
            StockTicker.fromString("VJPN:FTSE")
        )
    }

    fun investedInUsd(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("AAl:NASDAQ"),
            StockTicker.fromString("UAL:NASDAQ"),
            StockTicker.fromString("LTM:NYSE"),
            StockTicker.fromString("DAL:NASDAQ"),
            StockTicker.fromString("ALXN:NASDAQ"),
            StockTicker.fromString("TMUS:NASDAQ"),
            StockTicker.fromString("GILD:NASDAQ"),
            StockTicker.fromString("AMAT:NASDAQ"),
            StockTicker.fromString("CHTR:NASDAQ"),
            StockTicker.fromString("REGI:NASDAQ")
        )
    }
    fun investedInUsdTech(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("GOOGL:NASDAQ"),
            StockTicker.fromString("GOOG:NASDAQ"),
            StockTicker.fromString("INTC:NASDAQ"),
            StockTicker.fromString("MAR:NASDAQ"),
            StockTicker.fromString("GRPN:NASDAQ"),
            StockTicker.fromString("BKNG:NASDAQ"),
            StockTicker.fromString("MSFT:NASDAQ"),
            StockTicker.fromString("EB:NASDAQ"),
            StockTicker.fromString("CMCSA:NASDAQ"),
            StockTicker.fromString("CTSH:NASDAQ"),
            StockTicker.fromString("EBAY:NASDAQ"),
            StockTicker.fromString("FB:NASDAQ"),
            StockTicker.fromString("QCOM:NASDAQ"),
            StockTicker.fromString("AVGO:NASDAQ"),
            StockTicker.fromString("BIDU:NASDAQ"),
            StockTicker.fromString("CLDR:NASDAQ")
        )
    }
    fun watchListUsdTech(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("AMD:NASDAQ"),
            StockTicker.fromString("BABA:NASDAQ"),
            StockTicker.fromString("NET:NASDAQ"),
            StockTicker.fromString("DOCU:NYSE"),
            StockTicker.fromString("HUBS:NASDAQ"),
            StockTicker.fromString("NFLX:NASDAQ"),
            StockTicker.fromString("OKTA:NASDAQ"),
            StockTicker.fromString("PYPL:NASDAQ"),
            StockTicker.fromString("WORK:NASDAQ"),
            StockTicker.fromString("SQ:NASDAQ"),
            StockTicker.fromString("ZEN:NASDAQ"),
            StockTicker.fromString("ZM:NASDAQ"),
            StockTicker.fromString("PD:NASDAQ"),
            StockTicker.fromString("NOK:NYSE")
        )
    }
    fun watchListUsd(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("CAJ:NASDAQ"),
            StockTicker.fromString("ZNH:NYSE"),
            StockTicker.fromString("MA:NASDAQ"),
            StockTicker.fromString("SNE:NASDAQ"),
            StockTicker.fromString("ROK:NASDAQ"),
            StockTicker.fromString("EMR:NYSE"),
            StockTicker.fromString("HON:NYSE")
        )
    }

    fun watchListChf(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("ABBN:SIX")
        )
    }
    fun investedInChf(): Set<StockTicker> {
        return setOf(
        )
    }

    fun watchListGbp(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("IAG:FTSE")
        )
    }


    fun investedInGbp(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("RYA:FTSE"),
            StockTicker.fromString("WIZZ:FTSE")
        )
    }


    fun watchListAud(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("CBA:ASX"),
            StockTicker.fromString("ING:ASX"),
            StockTicker.fromString("WBC:ASX"),
            StockTicker.fromString("NAB:ASX"),
            StockTicker.fromString("QAN:ASX"),
            StockTicker.fromString("SYD:ASX"),
            StockTicker.fromString("SEK:ASX"),
            StockTicker.fromString("WTC:ASX"),
            StockTicker.fromString("XRO:ASX"),
            StockTicker.fromString("Z1P:ASX"),
            StockTicker.fromString("APT:ASX"),
            StockTicker.fromString("OPY:ASX")
        )
    }

    fun watchListIndicesAud(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("A200:ASX"),
            StockTicker.fromString("VAS:ASX"),
            StockTicker.fromString("ETHI:ASX"),
            StockTicker.fromString("QLTY:ASX"),
            StockTicker.fromString("RBTZ:ASX"),
            StockTicker.fromString("UMAX:ASX"),
            StockTicker.fromString("VESG:ASX"),
            StockTicker.fromString("VEU:ASX"),
            StockTicker.fromString("VGS:ASX"),
                StockTicker.fromString("IOO:ASX")
        )
    }

    fun investedIndicesInAud(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("VTS:ASX"),
            StockTicker.fromString("VEU:ASX"),
            StockTicker.fromString("VAP:ASX"),
            StockTicker.fromString("VHY:ASX"),
            StockTicker.fromString("NDQ:ASX"),
            StockTicker.fromString("IVV:ASX"),
            StockTicker.fromString("VGE:ASX"),
            StockTicker.fromString("ASIA:ASX")
        )
    }

    fun investedInAud(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("ANZ:ASX")
        )
    }

    fun nasdaq100(): Set<StockTicker> {
        return setOf(
            StockTicker.fromString("MSFT:NASDAQ"),
            StockTicker.fromString("AAPL:NASDAQ"),
            StockTicker.fromString("AMZN:NASDAQ"),
            StockTicker.fromString("GOOG:NASDAQ"),
            StockTicker.fromString("GOOGL:NASDAQ"),
            StockTicker.fromString("FB:NASDAQ"),
            StockTicker.fromString("INTC:NASDAQ"),
            StockTicker.fromString("PEP:NASDAQ"),
            StockTicker.fromString("CMCSA:NASDAQ"),
            StockTicker.fromString("CSCO:NASDAQ"),
            StockTicker.fromString("ADBE:NASDAQ"),
            StockTicker.fromString("NFLX:NASDAQ"),
            StockTicker.fromString("COST:NASDAQ"),
            StockTicker.fromString("NVDA:NASDAQ"),
            StockTicker.fromString("AMGN:NASDAQ"),
            StockTicker.fromString("PYPL:NASDAQ"),
            StockTicker.fromString("GILD:NASDAQ"),
            StockTicker.fromString("TXN:NASDAQ"),
            StockTicker.fromString("CHTR:NASDAQ"),
            StockTicker.fromString("AVGO:NASDAQ"),
            StockTicker.fromString("TSLA:NASDAQ"),
            StockTicker.fromString("QCOM:NASDAQ"),
            StockTicker.fromString("SBUX:NASDAQ"),
            StockTicker.fromString("TMUS:NASDAQ"),
            StockTicker.fromString("MDLZ:NASDAQ"),
            StockTicker.fromString("FISV:NASDAQ"),
            StockTicker.fromString("INTU:NASDAQ"),
            StockTicker.fromString("VRTX:NASDAQ"),
            StockTicker.fromString("REGN:NASDAQ"),
            StockTicker.fromString("BIIB:NASDAQ"),
            StockTicker.fromString("ADP:NASDAQ"),
            StockTicker.fromString("BKNG:NASDAQ"),
            StockTicker.fromString("ISRG:NASDAQ"),
            StockTicker.fromString("AMD:NASDAQ"),
            StockTicker.fromString("WBA:NASDAQ"),
            StockTicker.fromString("CSX:NASDAQ"),
            StockTicker.fromString("ATVI:NASDAQ"),
            StockTicker.fromString("MU:NASDAQ"),
            StockTicker.fromString("AMAT:NASDAQ"),
            StockTicker.fromString("JD:NASDAQ"),
            StockTicker.fromString("ADI:NASDAQ"),
            StockTicker.fromString("ILMN:NASDAQ"),
            StockTicker.fromString("ADSK:NASDAQ"),
            StockTicker.fromString("EXC:NASDAQ"),
            StockTicker.fromString("XEL:NASDAQ"),
            StockTicker.fromString("MNST:NASDAQ"),
            StockTicker.fromString("LRCX:NASDAQ"),
            StockTicker.fromString("KHC:NASDAQ"),
            StockTicker.fromString("EA:NASDAQ"),
            StockTicker.fromString("CTSH:NASDAQ"),
            StockTicker.fromString("BIDU:NASDAQ"),
            StockTicker.fromString("EBAY:NASDAQ"),
            StockTicker.fromString("ROST:NASDAQ"),
            StockTicker.fromString("MELI:NASDAQ"),
            StockTicker.fromString("VRSK:NASDAQ"),
            StockTicker.fromString("ORLY:NASDAQ"),
            StockTicker.fromString("MAR:NASDAQ"),
            StockTicker.fromString("NTES:NASDAQ"),
            StockTicker.fromString("NXPI:NASDAQ"),
            StockTicker.fromString("SIRI:NASDAQ"),
            StockTicker.fromString("WDAY:NASDAQ"),
            StockTicker.fromString("PAYX:NASDAQ"),
            StockTicker.fromString("CSGP:NASDAQ"),
            StockTicker.fromString("KLAC:NASDAQ"),
            StockTicker.fromString("WLTW:NASDAQ"),
            StockTicker.fromString("PCAR:NASDAQ"),
            StockTicker.fromString("VRSN:NASDAQ"),
            StockTicker.fromString("LULU:NASDAQ"),
            StockTicker.fromString("XLNX:NASDAQ"),
            StockTicker.fromString("CTAS:NASDAQ"),
            StockTicker.fromString("CERN:NASDAQ"),
            StockTicker.fromString("ANSS:NASDAQ"),
            StockTicker.fromString("FAST:NASDAQ"),
            StockTicker.fromString("ALXN:NASDAQ"),
            StockTicker.fromString("SNPS:NASDAQ"),
            StockTicker.fromString("SGEN:NASDAQ"),
            StockTicker.fromString("DLTR:NASDAQ"),
            StockTicker.fromString("IDXX:NASDAQ"),
            StockTicker.fromString("CTXS:NASDAQ"),
            StockTicker.fromString("CPRT:NASDAQ"),
            StockTicker.fromString("SPLK:NASDAQ"),
            StockTicker.fromString("ASML:NASDAQ"),
            StockTicker.fromString("CDNS:NASDAQ"),
            StockTicker.fromString("MCHP:NASDAQ"),
            StockTicker.fromString("INCY:NASDAQ"),
            StockTicker.fromString("CHKP:NASDAQ"),
            StockTicker.fromString("BMRN:NASDAQ"),
            StockTicker.fromString("SWKS:NASDAQ"),
            StockTicker.fromString("CDW:NASDAQ"),
            StockTicker.fromString("MXIM:NASDAQ"),
            StockTicker.fromString("TTWO:NASDAQ"),
            StockTicker.fromString("TCOM:NASDAQ"),
            StockTicker.fromString("ALGN:NASDAQ"),
            StockTicker.fromString("WDC:NASDAQ"),
            StockTicker.fromString("NTAP:NASDAQ"),
            StockTicker.fromString("ULTA:NASDAQ"),
            StockTicker.fromString("FOXA:NASDAQ"),
            StockTicker.fromString("LBTYK:NASDAQ"),
            StockTicker.fromString("EXPE:NASDAQ"),
            StockTicker.fromString("FOX:NASDAQ"),
            StockTicker.fromString("UAL:NASDAQ"),
            StockTicker.fromString("AAL:NASDAQ"),
            StockTicker.fromString("LBTYA:NASDAQ")
        )
    }
}
