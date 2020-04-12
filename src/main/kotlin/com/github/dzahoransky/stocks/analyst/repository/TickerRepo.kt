@Suppress("SpellCheckingInspection")
class TickerRepo {

    fun invested(): List<String> {
        return listOf(
            *investedInAud().toTypedArray(),
            *investedInUsd().toTypedArray(),
            *investedInGbp().toTypedArray(),
            *investedInEur().toTypedArray()
        )
    }

    fun eur(): List<String> {
        return listOf(
            *investedInEur().toTypedArray(),
            *watchListEur().toTypedArray()
        )
    }

    fun aud(): List<String> {
        return listOf(
            *investedInAud().toTypedArray(),
            *watchListAud().toTypedArray()
        )
    }

    fun audIndices(): List<String> {
        return listOf(
            *investedIndicesInAud().toTypedArray(),
            *watchListIndicesAud().toTypedArray()
        )
    }

    fun usd(): List<String> {
        return listOf(
            *investedInUsd().toTypedArray(),
            *watchListUsd().toTypedArray()
        )
    }

    fun gbp(): List<String> {
        return listOf(
            *investedInGbp().toTypedArray(),
            *watchListGbp().toTypedArray()
        )
    }

    fun gbpIndices(): List<String> {
        return listOf(
            *investedIndicesInGbp().toTypedArray(),
            *watchListIndicesGbp().toTypedArray()
        )
    }

    fun chf(): List<String> {
        return listOf(
            *investedInChf().toTypedArray(),
            *watchListChf().toTypedArray()
        )
    }

    fun investedInEur(): List<String> {
        return listOf(

        )
    }

    fun investedInGbp(): List<String> {
        return listOf(
            "RYA:FTSE"
        )
    }

    fun investedIndicesInGbp(): List<String> {
        return listOf(
            "VUSA:FTSE",
            "VFEM:FTSE"
        )
    }

    fun watchListIndicesGbp(): List<String> {
        return listOf(
            "VUSA:FTSE",
            "VFEM:FTSE",
            "VUKE:FTSE",
            "VMID:FTSE",
            "VHYL:FTSE",
            "VWRL:FTSE",
            "VEVE:FTSE",
            "VDEV:FTSE",
            "VAPX:FTSE",
            "VJPN:FTSE"
        )
    }

    fun investedInUsd(): List<String> {
        return listOf(
            "GOOGL:NASDAQ",
            "GOOG:NASDAQ",
            "INTC:NASDAQ",
            "MAR:NASDAQ",
            "GRPN:NASDAQ",
            "AAl:NASDAQ",
            "UAL:NASDAQ",
            "LTM:NYSE",
            "BKNG:NASDAQ",
            "DAL:NASDAQ",
            "MSFT:NASDAQ",
            "EB:NASDAQ",
            "CMCSA:NASDAQ",
            "CTSH:NASDAQ",
            "ALXN:NASDAQ",
            "EBAY:NASDAQ",
            "TMUS:NASDAQ",
            "FB:NASDAQ",
            "GILD:NASDAQ",
            "QCOM:NASDAQ",
            "AMAT:NASDAQ",
            "CHTR:NASDAQ",
            "AVGO:NASDAQ"
        )
    }

    fun watchListUsd(): List<String> {
        return listOf(
            "AMD:NASDAQ",
            "BABA:NASDAQ",
            "CAJ:NASDAQ",
            "ZHN:NYSE",
            "CLDR:NASDAQ",
            "NET:NASDAQ",
            "DAL:NASDAQ",
            "DOCU:NYSE",
            "HUBS:NASDAQ",
            "MA:NASDAQ",
            "NFLX:NASDAQ",
            "OKTA:NASDAQ",
            "PYPL:NASDAQ",
            "REGI:NASDAQ",
            "WORK:NASDAQ",
            "SNE:NASDAQ",
            "SQ:NASDAQ",
            "ZEN:NASDAQ",
            "ZM:NASDAQ",
            "PD:NASDAQ",
            "BIDU:NASDAQ",
            "ROK:NASDAQ",
            "EMR:NYSE",
            "HON:NYSE"
        )
    }

    fun watchListEur(): List<String> {
        return listOf(
            "SIE:DAX",
            "LHA:DAX",
            "KU2:DAX",
            "SU:PA"
        )
    }

    fun watchListChf(): List<String> {
        return listOf(
            "ABBN:SIX"
        )
    }
    fun investedInChf(): List<String> {
        return listOf(
        )
    }

    fun watchListGbp(): List<String> {
        return listOf(
            "IAG:FTSE"
        )
    }

    fun watchListAud(): List<String> {
        return listOf(
            "ANZ:ASX",
            "APT:ASX",
            "CBA:ASX",
            "ING:ASX",
            "NAB:ASX",
            "QAN:ASX",
            "SEK:ASX",
            "SYD:ASX",
            "VAH:ASX",
            "WTC:ASX",
            "XRO:ASX"
        )
    }

    fun watchListIndicesAud(): List<String> {
        return listOf(
            "A200:ASX",
            "VAS:ASX",
            "VHY:ASX",
            "ETHI:ASX",
            "IOO:ASX",
            "QLTY:ASX",
            "RBTZ:ASX",
            "UMAX:ASX",
            "VESG:ASX",
            "VEU:ASX",
            "VGE:ASX",
            "VGS:ASX"
        )
    }

    fun investedIndicesInAud(): List<String> {
        return listOf(
            "VTS:ASX",
            "VEU:ASX",
            "VAP:ASX",
            "VAP:ASX",
            "VGE:ASX",
            "NDQ:ASX",
            "IVV:ASX",
            "ASIA:ASX"
        )
    }

    fun investedInAud(): List<String> {
        return listOf(
        )
    }

    fun nasdaq100(): List<String> {
        return listOf(
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
    }
}
