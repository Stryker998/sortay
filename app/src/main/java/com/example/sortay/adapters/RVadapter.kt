package com.example.sortay.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sortay.R
import com.example.sortay.data.CryptocurrencyData
import com.example.sortay.data.NFTData
import com.example.sortay.data.RealEstateData
import com.example.sortay.data.StocksData
import com.example.sortay.databinding.FurtherStatsBinding

class RVadapter: RecyclerView.Adapter<RVadapter.RVViewHolder>() {

    internal var stocksList: List<StocksData>? = null
    internal var nftList: List<NFTData>? = null
    internal var realEstateList: List<RealEstateData>? = null
    internal var cryptocurrencyList: List<CryptocurrencyData>? = null

    inner class RVViewHolder(private val binding: FurtherStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(
            stockData: StocksData? = null, nftData: NFTData? = null, realEstateData: RealEstateData? = null, cryptocurrencyData: CryptocurrencyData? = null, position: Int? = null
        ) {
            if (position == 0) {
                binding.itemDivider.visibility = View.GONE
            }
            stocksList?.let {
                val data = stockData!!
                binding.apply {
                    if (position == 0) {
                        nameStatsText.text = "Name"
                        percentageStatsText.text = "%(1Y)"
                        priceStatsText.text = "Price"
                    }
                    else {
                        nameStatsText.text = data.name
                        percentageStatsText.setTextColor(Color.WHITE)
                        if (data.percentage < 0) {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                            percentageStatsText.text = "${data.percentage}%"
                        }
                        else {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)
                            percentageStatsText.text = "+${data.percentage}%"
                        }
                        priceStatsText.text = "₹${data.price}"
                    }
                }
            }
            nftList?.let {
                val data = nftData!!
                binding.apply {
                    if (position == 0) {
                        nameStatsText.text = "Name"
                        percentageStatsText.text = "%(1Y)"
                        priceStatsText.text = "Floor price"
                    } else {
                        nameStatsText.text = data.name
                        percentageStatsText.setTextColor(Color.WHITE)
                        if (data.percentage < 0) {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                            percentageStatsText.text = "${data.percentage}%"
                        }
                        else {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)
                            percentageStatsText.text = "+${data.percentage}%"
                        }
                        priceStatsText.text = "${data.price}ETH"
                    }
                }
            }
            realEstateList?.let {
                val data = realEstateData!!
                binding.apply {
                    if (position == 0) {
                        nameStatsText.text = "Name"
                        percentageStatsText.text = "%(1Y)"
                        priceStatsText.text = """Price
                            |(/sqfeet)""".trimMargin()
                    } else {
                        percentageStatsText.setTextColor(Color.WHITE)
                        nameStatsText.text = data.name
                        if (data.percentage < 0) {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                            percentageStatsText.text = "${data.currentPrice}${data.unit}(${data.percentage}%)"
                        }
                        else {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)
                            percentageStatsText.text = "${data.currentPrice}${data.unit}(+${data.percentage}%)"
                        }
                        priceStatsText.text = "₹${data.price}"
                    }
                }
            }
            cryptocurrencyList?.let {
                val data = cryptocurrencyData!!
                binding.apply {
                    if (position == 0) {
                        nameStatsText.text = "Name"
                        percentageStatsText.text = "%(1Y)"
                        priceStatsText.text = "Price"
                    } else {
                        percentageStatsText.setTextColor(Color.WHITE)
                        nameStatsText.text = data.name
                        if (data.percentage < 0) {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                            percentageStatsText.text = "${data.percentage}%"
                        }
                        else {
                            percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)
                            percentageStatsText.text = "+${data.percentage}%"
                        }
                        priceStatsText.text = "$${data.price}"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return RVViewHolder(
            FurtherStatsBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }


    override fun getItemCount(): Int {
        stocksList?.let {
            return it.size
        }
        nftList?.let {
            return it.size
        }
        realEstateList?.let {
            return it.size
        }
        cryptocurrencyList?.let {
            return it.size
        }
        throw Exception("Not declared")
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        stocksList?.let {
            holder.bind(stockData = it[position], position = position)
        }
        nftList?.let {
            holder.bind(nftData = it[position], position = position)
        }
        realEstateList?.let {
            holder.bind(realEstateData = it[position], position = position)
        }
        cryptocurrencyList?.let {
            holder.bind(cryptocurrencyData = it[position], position = position)
        }
    }
}