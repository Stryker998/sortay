package com.example.sortay

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.example.sortay.databinding.ActivityStatsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }
    private fun nextActivity(data: String) {
        val i = Intent(this, FurtherStatsActivity::class.java)
        i.putExtra("list", data)
        val isGuest = intent.extras?.getBoolean("Guest")
        if (isGuest == true) i.putExtra("Guest", true)
        startActivity(i)
    }

    private fun bind() {
        binding.apply {
            val isGuest = intent.extras?.getBoolean("Guest")
            if (isGuest != true) {
                pieChart.setUsePercentValues(true)
                pieChart.description.isEnabled = false
                pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
                pieChart.dragDecelerationFrictionCoef = 0.95f
                pieChart.isDrawHoleEnabled = false
                pieChart.setTransparentCircleAlpha(0)
                pieChart.setDrawCenterText(true)
                pieChart.legend.textColor = resources.getColor(R.color.legends, theme)
                pieChart.rotationAngle = 0f
                pieChart.isRotationEnabled = true
                pieChart.isHighlightPerTapEnabled = true
                pieChart.animateY(1400, Easing.EaseInOutQuad)
                pieChart.setEntryLabelColor(Color.WHITE)
                pieChart.setEntryLabelTextSize(12f)
                val entries: ArrayList<PieEntry> = ArrayList()
                entries.add(PieEntry(15.6f, "Real Estate"))
                entries.add(PieEntry(19.3f, "NFT"))
                entries.add(PieEntry(27.8f, "Stocks"))
                entries.add(PieEntry(39.9f, "Cryptocurrency"))
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
                pieChart.data = data
                pieChart.highlightValues(null)
                pieChart.invalidate()
                stocksStatButton.setBackgroundColor(resources.getColor(R.color.stocks, theme))
                stocksStatButton.setTextColor(Color.WHITE)
                nftStatButton.setBackgroundColor(resources.getColor(R.color.nft, theme))
                nftStatButton.setTextColor(Color.WHITE)
                cryptocurrencyStatButton.setBackgroundColor(resources.getColor(R.color.cryptocurrency, theme))
                cryptocurrencyStatButton.setTextColor(Color.WHITE)
                realEstateStatButton.setBackgroundColor(resources.getColor(R.color.realEstate, theme))
                realEstateStatButton.setTextColor(Color.WHITE)
            } else {
                pieChart.visibility = View.GONE
                guestText.visibility = View.VISIBLE
                stocksStatButton.height = resources.getDimensionPixelOffset(R.dimen.guest_height)
                nftStatButton.height = resources.getDimensionPixelOffset(R.dimen.guest_height)
                cryptocurrencyStatButton.height = resources.getDimensionPixelOffset(R.dimen.guest_height)
                realEstateStatButton.height = resources.getDimensionPixelOffset(R.dimen.guest_height)
            }


            stocksStatButton.setOnClickListener {
                nextActivity("stockList")
            }
            nftStatButton.setOnClickListener {
                nextActivity("nftList")
            }
            cryptocurrencyStatButton.setOnClickListener {
                nextActivity("cryptoList")
            }
            realEstateStatButton.setOnClickListener {
                nextActivity("realEstateList")
            }
        }
    }
}