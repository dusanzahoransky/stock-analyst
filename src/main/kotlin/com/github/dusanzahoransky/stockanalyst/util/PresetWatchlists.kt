package com.github.dusanzahoransky.stockanalyst.util

import com.github.dusanzahoransky.stockanalyst.model.mongo.Watchlist

class PresetWatchlists {
    fun test(): Watchlist {
        val watchlist = Watchlist("TEST")
        watchlist.tickers = mutableSetOf(
                "MSFT:NASDAQ"
        )
        return watchlist
    }

    fun testEtfs(): Watchlist {
        val watchlist = Watchlist("TEST_ETF")
        watchlist.tickers = mutableSetOf(
                "VGE:ASX",
                "VFEM:FTSE"
        )
        return watchlist
    }

    fun toCheck(): Watchlist {
        val watchlist = Watchlist("TO_CHECK")
        watchlist.tickers = mutableSetOf(
                "ALXN:NASDAQ",
                "GOOGL:NASDAQ",
                "MSFT:NASDAQ",
                "BKNG:NASDAQ",
                "REGI:NASDAQ",
                "CLDR:NYSE",
                "TCOM:NASDAQ",
                "GRPN:NASDAQ",
                "NOK:NYSE",
                "INTC:NASDAQ",
                "AVGO:NASDAQ"
        )
        return watchlist
    }

    //ETFs
    fun etfsAll(): Watchlist {
        val watchlist = Watchlist("ETF_ALL")
        watchlist.tickers = mutableSetOf(
                *auEtfsAll().tickers.toTypedArray(),
                *euEtfsAll().tickers.toTypedArray(),
                *gbEtfsAll().tickers.toTypedArray()
        )
        return watchlist
    }

    fun auEtfsAll(): Watchlist {
        val watchlist = Watchlist("AU_ETF_ALL")
        watchlist.tickers = mutableSetOf(
                *auEtfsInvestedIn().tickers.toTypedArray(),
                *auEtfsWatchlist().tickers.toTypedArray()
        )
        return watchlist
    }

    fun auEtfsAu(): Watchlist {
        val watchlist = Watchlist("AU_ETF_AU")
        watchlist.tickers = mutableSetOf(
                "ATEC:ASX",
                "VLC:ASX",
                "A200:ASX",
                "VAS:ASX",
                "VAP:ASX",
                "VHY:ASX",
                "VSO:ASX"
        )
        return watchlist
    }

    fun auEtfsUs(): Watchlist {
        val watchlist = Watchlist("AU_ETF_US")
        watchlist.tickers = mutableSetOf(
                "VTS:ASX",
                "IVV:ASX",
                "NDQ:ASX",
                "UMAX:ASX"
        )
        return watchlist
    }

    fun auEtfsAsia(): Watchlist {
        val watchlist = Watchlist("AU_ETF_ASIA")
        watchlist.tickers = mutableSetOf(
                "ASIA:ASX",
/*            "AAXJ:NASDAQ"),*/
                "VGE:ASX",
                "VAE:ASX",
                "IIND:ASX"
        )
        return watchlist
    }

    fun auEtfsBond(): Watchlist {
        val watchlist = Watchlist("AU_ETF_BOND")
        watchlist.tickers = mutableSetOf(
                "VAF:ASX",
                "VGB:ASX",
                "VACF:ASX",
                "VBND:ASX",
                "VIF:ASX",
                "VCF:ASX",
                "VEFI:ASX"
        )
        return watchlist
    }

    fun auEtfsInvestedIn(): Watchlist {
        val watchlist = Watchlist("AU_ETF_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "VTS:ASX",
                "VEU:ASX",
                "VAP:ASX",
                "VHY:ASX",
                "NDQ:ASX",
                "IVV:ASX",
                "VGE:ASX",
                "ASIA:ASX",
                "VAS:ASX",
                "VSO:ASX"
        )
        return watchlist
    }

    fun auEtfsWatchlist(): Watchlist {
        val watchlist = Watchlist("AU_ETF_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                *auEtfsAu().tickers.toTypedArray(),
                *auEtfsUs().tickers.toTypedArray(),
                *auEtfsAsia().tickers.toTypedArray(),
                *auEtfsBond().tickers.toTypedArray(),
                "ETHI:ASX",
                "QLTY:ASX",
                "RBTZ:ASX",
                "VESG:ASX",
                "VEU:ASX",
                "VGS:ASX",
                "IOO:ASX",
                "MNRS:ASX",
                "BBOZ:ASX",
                "RBTZ:ASX",
                "VBLD:ASX",
                "VAE:ASX",
                "VESG:ASX"
        )
        return watchlist
    }

    fun gbEtfsAll(): Watchlist {
        val watchlist = Watchlist("GB_ETF_ALL")
        watchlist.tickers = mutableSetOf(
                *gbEtfsInvestedIn().tickers.toTypedArray(),
                *gbEtfsWatchlist().tickers.toTypedArray(),
                *gbEtfsBonds().tickers.toTypedArray()
        )
        return watchlist
    }

    fun gbEtfsBonds(): Watchlist {
        val watchlist = Watchlist("GB_ETF_BOND")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }

    fun gbEtfsInvestedIn(): Watchlist {
        val watchlist = Watchlist("GB_ETF_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "VUSA:FTSE",
                "CNDX:FTSE",
                "VDEV:FTSE",
                "VUKE:FTSE",
                "VFEM:FTSE"
        )
        return watchlist
    }

    fun gbEtfsWatchlist(): Watchlist {
        val watchlist = Watchlist("GB_ETF_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "CPJ1:FTSE",
                /*"VAPX:FTSE"),*/
                "WSML:FTSE",     //iShares MSCI World Small Cap UCITS ETF USD (Acc)
                "VMID:FTSE",     //Vanguard FTSE 250 UCITS ETF
                "UKDV:FTSE",     //SPDR S&amp;P UK Dividend Aristocrats UCITS ETF
                /*"INRG:FTSE"),*/
                "PHGP:FTSE",    //WisdomTree Physical Gold
                "IUKP:FTSE",    //iShares UK Property UCITS ETF GB (Dist)

                /*     "NASD:FTSE"),*/
/*            "CNX1:FTSE"),*/
                /*"VHYL:FTSE"),*/                /*"VWRL:FTSE"),*/
                "VEVE:FTSE",     //Vanguard FTSE Developed World UCITS ETF USD Distributing

                "VJPN:FTSE",     //Vanguard FTSE Japan UCITS ETF USD Distributing
                /*"UKDV:FTSE"),*/ //SPDR S&P UK Dividend Aristocrats UCITS ETF
                "USDV:FTSE",     //SPDR S&P US Dividend Aristocrats UCITS ETF Dis

                "SGVL:FTSE",
                "VVAL:FTSE",
                "IWVL:FTSE"
        )
        return watchlist
    }

    fun euEtfsAll(): Watchlist {
        val watchlist = Watchlist("EU_ETF_ALL")
        watchlist.tickers = mutableSetOf(
                *euEtfsInvestedIn().tickers.toTypedArray(),
                *euEtfsWatchlist().tickers.toTypedArray(),
                *euEtfsBondEtfs().tickers.toTypedArray()
        )
        return watchlist
    }

    fun euEtfsBondEtfs(): Watchlist {
        val watchlist = Watchlist("EU_ETF_BOND")
        watchlist.tickers = mutableSetOf(
                "VGTY:DAX", //USD Treasury Bond
                "VGOV:DAX", //U.K. Gilt
                "VGEM:DAX", //Emerging Markets Government Bond
                "VGEB:DAX"  //Eurozone Government Bond
        )
        return watchlist
    }

    fun euEtfsInvestedIn(): Watchlist {
        val watchlist = Watchlist("EU_ETF_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "VWCE:DAX",
                "IUS3:DAX",
                "SXR8:DAX",
                "QDVE:DAX"
        )
        return watchlist
    }

    fun euEtfsWatchlist(): Watchlist {
        val watchlist = Watchlist("EU_ETF_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "QDVE:DAX",
                "SXR8:DAX",
                "SXR1:DAX",
                "EXS1:DAX",      //iShares Core DAX UCITS ETF (DE)
                "ZPRA:DAX",      //SPDR S&P Pan Asia Dividend Aristocrats UCITS ETF
                "SPYW:DAX",      //SPDR S&P Euro Dividend Aristocrats UCITS ETF
                "ZPRV:DAX",
                "LYMS:DAX"
//            "LYXINR:DAX")
        )
        return watchlist
    }

    fun etfTrading212InvestedIn(): Watchlist {
        val watchlist = Watchlist("ETF_TRADING_212_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                *euEtfsInvestedIn().tickers.toTypedArray(),
                *gbEtfsInvestedIn().tickers.toTypedArray()
        )
        return watchlist
    }

    //Stocks
    fun invested(): Watchlist {
        val watchlist = Watchlist("ALL_INVESTED")
        watchlist.tickers = mutableSetOf(
                *auInvestedIn().tickers.toTypedArray(),
                *usInvestedIn().tickers.toTypedArray(),
                *gbInvestedIn().tickers.toTypedArray(),
                *euInvestedIn().tickers.toTypedArray()
        )
        return watchlist
    }

    fun us(): Watchlist {
        val watchlist = Watchlist("US_ALL")
        watchlist.tickers = mutableSetOf(
                *usInvestedIn().tickers.toTypedArray(),
                *usWatchList().tickers.toTypedArray(),
                *dividends().tickers.toTypedArray()
        )
        return watchlist
    }

    fun usInvestedIn(): Watchlist {
        val watchlist = Watchlist("US_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "ALXN:NASDAQ",
                "TMUS:NASDAQ",
                "GILD:NASDAQ",
                "AMAT:NASDAQ",
                "REGI:NASDAQ",
                "GRPN:NASDAQ",
                "T:NYSE",
                "SO:NYSE",
                "TSM:NYSE",
                "LMT:NYSE",
                "KO:NYSE",
                "KO:NYSE",
                "TSM:NYSE",
                *investedInTech().tickers.toTypedArray()
        )
        return watchlist
    }

    fun usWatchList(): Watchlist {
        val watchlist = Watchlist("US_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                //TODO need currency conversion /*"CAJ:NASDAQ"),*/                /*"ZNH:NYSE"),*/                /* "INO:NYSE"),*/                //TODO need currency conversion /* "SNE:NASDAQ"),*/
                "ROK:NYSE",
                "HON:NYSE",
                "AAl:NASDAQ",
                "UAL:NASDAQ",
                "DAL:NYSE",
                "MA:NYSE",
                "EMR:NYSE",
                "CHTR:NASDAQ",
                "CMCSA:NASDAQ",
                "BILI:NASDAQ",
                "SHOP:NASDAQ",
                "WIX:NASDAQ",
                "DDOG:NASDAQ",
                "WDC:NASDAQ"
        )
        return watchlist
    }

    fun eu(): Watchlist {
        val watchlist = Watchlist("EU_ALL")
        watchlist.tickers = mutableSetOf(
                *euInvestedIn().tickers.toTypedArray(),
                *euWatchList().tickers.toTypedArray()
        )
        return watchlist
    }

    fun euInvestedIn(): Watchlist {
        val watchlist = Watchlist("EU_INVESTED_IN")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }

    fun euWatchList(): Watchlist {
        val watchlist = Watchlist("EU_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "KU2:DAX",
                "SIE:DAX",
                "SU:PA",
                "LHA:DAX",
                "LISN:SIX"
        )
        return watchlist
    }

    fun gb(): Watchlist {
        val watchlist = Watchlist("GB_ALL")
        watchlist.tickers = mutableSetOf(
                *gbInvestedIn().tickers.toTypedArray(),
                *gbWatchlist().tickers.toTypedArray()
        )
        return watchlist
    }

    fun gbInvestedIn(): Watchlist {
        val watchlist = Watchlist("GB_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "RYA:FTSE",
                "WIZZ:FTSE"
        )
        return watchlist
    }

    fun gbWatchlist(): Watchlist {
        val watchlist = Watchlist("GB_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "IAG:FTSE"
        )
        return watchlist
    }

    fun au(): Watchlist {
        val watchlist = Watchlist("AU_ALL")
        watchlist.tickers = mutableSetOf(
                *auInvestedIn().tickers.toTypedArray(),
                *auWatchlist().tickers.toTypedArray()
        )
        return watchlist
    }

    fun auWatchlist(): Watchlist {
        val watchlist = Watchlist("AU_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "CBA:ASX",
                "ING:ASX",
                "WBC:ASX",
                "NAB:ASX",
                "QAN:ASX",
                "SYD:ASX",
                "SEK:ASX",
                "WTC:ASX",
                "XRO:ASX",
                "Z1P:ASX",
                "APT:ASX",
                "OPY:ASX"
        )
        return watchlist
    }

    fun auInvestedIn(): Watchlist {
        val watchlist = Watchlist("AU_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "ANZ:ASX",
                "WBC:ASX"
        )
        return watchlist
    }

    fun chf(): Watchlist {
        val watchlist = Watchlist("CHF_ALL")
        watchlist.tickers = mutableSetOf(
                *chfWatchlist().tickers.toTypedArray()
        )
        return watchlist
    }

    fun chfWatchlist(): Watchlist {
        val watchlist = Watchlist("CHF_WATCHLIST")
        watchlist.tickers = mutableSetOf(
                "ABBN:SIX"
        )
        return watchlist
    }

    fun airlines(): Watchlist {
        val watchlist = Watchlist("AIRLINES")
        watchlist.tickers = mutableSetOf(
                "DAL:NYSE",
                "UAL:NASDAQ",
                "AAL:NASDAQ",
                "IAG:FTSE",
                "RYA:FTSE",
                "WIZZ:FTSE"
        )
        return watchlist
    }

    fun tech(): Watchlist {
        val watchlist = Watchlist("TECH")
        watchlist.tickers = mutableSetOf(
                *investedInTech().tickers.toTypedArray(),
                *watchListTech().tickers.toTypedArray()
        )
        return watchlist
    }

    fun investedInTech(): Watchlist {
        val watchlist = Watchlist("TECH_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "GOOGL:NASDAQ",
                //"GOOG:NASDAQ",
                "INTC:NASDAQ",
                "MAR:NASDAQ",
                "GRPN:NASDAQ",
                "BKNG:NASDAQ",
                "MSFT:NASDAQ",
                "EB:NYSE",
                "FB:NASDAQ",
                "QCOM:NASDAQ",
                "AVGO:NASDAQ",
                "CLDR:NYSE"
        )
        return watchlist
    }

    fun watchListTech(): Watchlist {
        val watchlist = Watchlist("TECH_INVESTED_IN")
        watchlist.tickers = mutableSetOf(
                "CTSH:NASDAQ",
                "EBAY:NASDAQ",
                "DELL:NYSE",
                "JD:NASDAQ",
                "TEAM:NASDAQ",
                "AMD:NASDAQ",
                "BABA:NYSE",
                "NET:NYSE",
                "DOCU:NASDAQ",
                "HUBS:NYSE",
                "OKTA:NASDAQ",
                "PYPL:NASDAQ",
                "WORK:NYSE",
                "SQ:NYSE",
                "ZEN:NYSE",
                "ZM:NASDAQ",
                "PD:NYSE",
                "NOK:NYSE",
                "UBER:NYSE",
                "BYND:NASDAQ",
                "NEWR:NYSE",
                "ESTC:NYSE",
                "BIDU:NASDAQ",
                "TRIP:NASDAQ",
                "TCOM:NASDAQ"
        )
        return watchlist
    }

    fun nasdaq100(): Watchlist {
        val watchlist = Watchlist("NASDAQ_100")
        watchlist.tickers = mutableSetOf(
                "MSFT:NASDAQ",
                "AAPL:NASDAQ",
                "AMZN:NASDAQ",
                "GOOG:NASDAQ",
                "GOOGL:NASDAQ",
                "FB:NASDAQ",
                "INTC:NASDAQ",
                "PEP:NASDAQ",
                "CMCSA:NASDAQ",
                "CSCO:NASDAQ",
                "ADBE:NASDAQ",
                "NFLX:NASDAQ",
                "COST:NASDAQ",
                "NVDA:NASDAQ",
                "AMGN:NASDAQ",
                "PYPL:NASDAQ",
                "GILD:NASDAQ",
                "TXN:NASDAQ",
                "CHTR:NASDAQ",
                "AVGO:NASDAQ",
                "TSLA:NASDAQ",
                "QCOM:NASDAQ",
                "SBUX:NASDAQ",
                "TMUS:NASDAQ",
                "MDLZ:NASDAQ",
                "FISV:NASDAQ",
                "INTU:NASDAQ",
                "VRTX:NASDAQ",
                "REGN:NASDAQ",
                "BIIB:NASDAQ",
                "ADP:NASDAQ",
                "BKNG:NASDAQ",
                "ISRG:NASDAQ",
                "AMD:NASDAQ",
                "WBA:NASDAQ",
                "CSX:NASDAQ",
                "ATVI:NASDAQ",
                "MU:NASDAQ",
                "AMAT:NASDAQ",
                "JD:NASDAQ",
                "ADI:NASDAQ",
                "ILMN:NASDAQ",
                "ADSK:NASDAQ",
                "EXC:NASDAQ",
                "XEL:NASDAQ",
                "MNST:NASDAQ",
                "LRCX:NASDAQ",
                "KHC:NASDAQ",
                "EA:NASDAQ",
                "CTSH:NASDAQ",
                "BIDU:NASDAQ",
                "EBAY:NASDAQ",
                "ROST:NASDAQ",
                "MELI:NASDAQ",
                "VRSK:NASDAQ",
                "ORLY:NASDAQ",
                "MAR:NASDAQ",
                "NTES:NASDAQ",
                "NXPI:NASDAQ",
                "SIRI:NASDAQ",
                "WDAY:NASDAQ",
                "PAYX:NASDAQ",
                "CSGP:NASDAQ",
                "KLAC:NASDAQ",
                "WLTW:NASDAQ",
                "PCAR:NASDAQ",
                "VRSN:NASDAQ",
                "LULU:NASDAQ",
                "XLNX:NASDAQ",
                "CTAS:NASDAQ",
                "CERN:NASDAQ",
                "ANSS:NASDAQ",
                "FAST:NASDAQ",
                "ALXN:NASDAQ",
                "SNPS:NASDAQ",
                "SGEN:NASDAQ",
                "DLTR:NASDAQ",
                "IDXX:NASDAQ",
                "CTXS:NASDAQ",
                "CPRT:NASDAQ",
                "SPLK:NASDAQ",
                "ASML:NASDAQ",
                "CDNS:NASDAQ",
                "MCHP:NASDAQ",
                "INCY:NASDAQ",
                "CHKP:NASDAQ",
                "BMRN:NASDAQ",
                "SWKS:NASDAQ",
                "CDW:NASDAQ",
                "MXIM:NASDAQ",
                "TTWO:NASDAQ",
                "TCOM:NASDAQ",
                "ALGN:NASDAQ",
                "WDC:NASDAQ",
                "NTAP:NASDAQ",
                "ULTA:NASDAQ",
                "FOXA:NASDAQ",
                "LBTYK:NASDAQ",
                "EXPE:NASDAQ",
                "FOX:NASDAQ",
                "UAL:NASDAQ",
                "AAL:NASDAQ",
                "LBTYA:NASDAQ"
        )
        return watchlist
    }

    fun dividends(): Watchlist {
        val watchlist = Watchlist("DIVIDENDS")
        watchlist.tickers = mutableSetOf(
                "SPG:NYSE",
                "LMT:NYSE",
                "WM:NYSE",
                "UNP:NYSE",
                "T:NYSE",
                "NEE:NYSE",
                "DUK:NYSE",
                "SO:NYSE",
                "D:NYSE",
                "CMCSA:NASDAQ",
                "COST:NASDAQ",
                "HD:NYSE",
                "DIS:NYSE",
                "PEP:NASDAQ"
        )
        return watchlist
    }

    fun allTrading212(): Watchlist {
        val watchlist = Watchlist("TRADING_212")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }

    fun allTrading212Us(): Watchlist {
        val watchlist = Watchlist("TRADING_212_US")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }

    fun allTrading212Eu(): Watchlist {
        val watchlist = Watchlist("TRADING_212_EU")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }

    fun allTrading212Gb(): Watchlist {
        val watchlist = Watchlist("TRADING_212_GB")
        watchlist.tickers = mutableSetOf(

        )
        return watchlist
    }
}