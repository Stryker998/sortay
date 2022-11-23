package com.example.sortay

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.sortay.adapters.RVadapter
import com.example.sortay.data.CryptocurrencyData
import com.example.sortay.data.NFTData
import com.example.sortay.data.RealEstateData
import com.example.sortay.data.StocksData
import com.example.sortay.databinding.ActivityFurtherStatsBinding
import com.example.sortay.databinding.ActivityStatsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class FurtherStatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFurtherStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFurtherStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val realEstateList = listOf(
            RealEstateData("", 0, 0.0, "", 0.0),
            RealEstateData("Golf course Road", 16000, 3.1, "Cr", 7.0),
            RealEstateData("Windchant", 10000, 2.52, "Cr", 9.0),
            RealEstateData("Hiranandani Appts", 12000, 4.53, "Cr", 10.0),
            RealEstateData("Ambience mall shop no 2", 21000, 4.56, "Cr", 9.3),
            RealEstateData("Pacific mall shop no 23", 20000, 2.27, "Cr", 8.0),
            RealEstateData("DLF mall shop no 23", 19000, 2.56, "Cr", -2.0),
            RealEstateData("Gokul Dham society", 9000, 2.45, "Cr", 8.0),
            RealEstateData("American Dream Mall", 25000, 15.0, "Cr", 12.0),
            RealEstateData("Dubai Marina mall", 23000, 9.0, "Cr", 11.0),
            RealEstateData("DDA Towers", 10000, 90.0, "L", -2.0),
        )
        val stockList = listOf(
            StocksData("", 0.0, 0.0),
            StocksData("Reliance", 2557.5, 8.76),
            StocksData("Tata", 423.95, -13.24),
            StocksData("Apollo hospital", 4610.0, -14.59),
            StocksData("Asian paints", 3096.35, -1.92),
            StocksData("SBI", 607.0, 23.11),
            StocksData("Bajaj Finance", 6775.0, -5.61),
            StocksData("Jindal Steel", 525.0, 37.6),
            StocksData("Mahindra", 1234.0, 38.15),
            StocksData("ICICI", 926.0, 21.88),
            StocksData("Britannia", 4160.8, 14.83),
        )
        val cryptoList = listOf(
            CryptocurrencyData("", 0.0, 0.0),
            CryptocurrencyData("Bitcoin(BTC)", 16585.0, -68.83),
            CryptocurrencyData("Ethereum(ETH)", 1166.0, -70.49),
            CryptocurrencyData("Tether(USDT)", 1.01, 9.70),
            CryptocurrencyData("Binance(BNB)", 1.0, -45.18),
            CryptocurrencyData("XRP(XRP)", 0.38, -61.15),
            CryptocurrencyData("Cardano(ADA)", 0.32, -80.13),
            CryptocurrencyData("Solana(SOL)", 13.78, 14.33),
            CryptocurrencyData("Polkadot(DOT)", 5.41, -84.0),
            CryptocurrencyData("Litecoin(LTC)", 78.61, -60.67),
            CryptocurrencyData("Dogecoin(DOGE)", 0.08, -60.86),
        )
        val nftList = listOf(
            NFTData("", 0.0, 0.0),
            NFTData("Valhalla", 0.66, 14.0),
            NFTData("BAYC", 59.25, 12.0),
            NFTData("Heaven Computer", 0.05, 25.0),
            NFTData("MAYC", 11.49, 8.0),
            NFTData("MutantCats", 0.131, 31.0),
            NFTData("Shamanzs Genesis", 0.01, 8.0),
            NFTData("CryptoPunks", 61.99, 21.0),
            NFTData("Parallel Alpha", 0.27, 12.0),
            NFTData("Rug radio", 0.021, 14.0),
            NFTData("Consortium key", 3.84, 17.0),
        )
        val intentData = intent.extras?.getString("list")
        val adapter = RVadapter().apply {
            when(intentData) {
                "realEstateList" -> this.realEstateList = realEstateList
                "stockList" -> this.stocksList = stockList
                "cryptoList" -> this.cryptocurrencyList = cryptoList
                "nftList" -> this.nftList = nftList
                else -> throw Exception("Wrong Intent")
            }
        }
        bind(adapter)
    }

    private fun bind(adapter: RVadapter) {
        binding.apply {
            pieChartFurtherStats.setUsePercentValues(true)
            pieChartFurtherStats.description.isEnabled = false
            pieChartFurtherStats.setExtraOffsets(5f, 10f, 5f, 5f)
            pieChartFurtherStats.dragDecelerationFrictionCoef = 0.95f
            pieChartFurtherStats.isDrawHoleEnabled = false
            pieChartFurtherStats.setTransparentCircleAlpha(0)
            pieChartFurtherStats.setDrawCenterText(true)
            pieChartFurtherStats.legend.textColor = resources.getColor(R.color.legends, theme)
            pieChartFurtherStats.rotationAngle = 0f
            pieChartFurtherStats.isRotationEnabled = true
            pieChartFurtherStats.isHighlightPerTapEnabled = true
            pieChartFurtherStats.animateY(1400, Easing.EaseInOutQuad)
            pieChartFurtherStats.setEntryLabelColor(Color.WHITE)
            pieChartFurtherStats.setEntryLabelTextSize(12f)
            val entries: ArrayList<PieEntry> = ArrayList()
            entries.add(PieEntry(15.6f, "Real Estate"))
            entries.add(PieEntry(19.3f, "NFT"))
            entries.add(PieEntry(30f, "Stocks"))
            entries.add(PieEntry(43f, "Cryptocurrency"))
            val dataSet = PieDataSet(entries, "")
            dataSet.setDrawIcons(false)
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f
            val colors: ArrayList<Int> = ArrayList()
            colors.add(resources.getColor(R.color.realEstate, theme))
            colors.add(resources.getColor(R.color.nft, theme))
            colors.add(resources.getColor(R.color.stocks, theme))
            colors.add(resources.getColor(R.color.cryptocurrency, theme))
            dataSet.colors = colors
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(15f)
            data.setValueTypeface(Typeface.DEFAULT_BOLD)
            data.setValueTextColor(Color.WHITE)
            pieChartFurtherStats.data = data
            pieChartFurtherStats.highlightValues(null)
            pieChartFurtherStats.invalidate()

            statsRv.adapter = adapter
            statsRv.layoutManager =
                GridLayoutManager(this@FurtherStatsActivity, 1, GridLayoutManager.VERTICAL, false)
        }
    }
}