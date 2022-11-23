package com.example.sortay.adapters

import android.view.LayoutInflater
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

    private var stocksList: MutableList<StocksData>? = null
    private var nftList: MutableList<NFTData>? = null
    private var realEstateList: MutableList<RealEstateData>? = null
    private var cryptocurrencyList: MutableList<CryptocurrencyData>? = null

    inner class RVViewHolder(private val binding: FurtherStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(
            stockData: StocksData? = null, nftData: NFTData? = null, realEstateData: RealEstateData? = null, cryptocurrencyData: CryptocurrencyData? = null
        ) {
            stocksList?.let {
                val data = stockData!!
                binding.apply {
                    nameStatsText.text = data.name
                    if (data.percentage < 0)
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                    else
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)

                    percentageStatsText.text = data.percentage.toString()
                    priceStatsText.text = data.price.toString()
                }
            }
            nftList?.let {
                val data = nftData!!
                binding.apply {
                    nameStatsText.text = data.name
                    if (data.percentage < 0)
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                    else
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)

                    percentageStatsText.text = data.percentage.toString()
                    priceStatsText.text = data.price.toString()
                }
            }
            realEstateList?.let {
                val data = realEstateData!!
                binding.apply {
                    nameStatsText.text = data.name
                    if (data.percentage < 0)
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                    else
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)

                    percentageStatsText.text = "${data.currentPrice}(${data.percentage})"
                    priceStatsText.text = data.price.toString()
                }
            }
            cryptocurrencyList?.let {
                val data = cryptocurrencyData!!
                binding.apply {
                    nameStatsText.text = data.name
                    if (data.percentage < 0)
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_decrease, null)
                    else
                        percentageStatsText.background = ResourcesCompat.getDrawable(binding.root.context.resources, R.drawable.background_percentage_increase, null)

                    percentageStatsText.text = data.percentage.toString()
                    priceStatsText.text = data.price.toString()
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
            holder.bind(stockData = it[position])
        }
        nftList?.let {
            holder.bind(nftData = it[position])
        }
        realEstateList?.let {
            holder.bind(realEstateData = it[position])
        }
        cryptocurrencyList?.let {
            holder.bind(cryptocurrencyData = it[position])
        }
    }
}