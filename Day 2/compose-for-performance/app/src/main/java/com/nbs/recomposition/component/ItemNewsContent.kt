package com.nbs.recomposition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.recomposition.NewsData
import com.nbs.recomposition.R

@Composable
fun ItemNewsContent(
    data: NewsData? = null
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8))
        ) {
            ImageView(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                imageResourceId = data?.thumbnail ?: 0,
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
            , contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                text = data?.title.orEmpty(),
                fontSize = 12.sp,
                maxLines = 1,

                color = Color.Black,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = data?.datePublish  ?: "",
            fontSize = 10.sp,
            color = Color.LightGray
        )

    }
}

@Preview
@Composable
fun ItemNewsContentPreview() {
    val data = NewsData(
        xid = "1adsadsdfdsfdxczxs",
        title = "Diskon 30% Semua Menu Spesial",
        datePublish = "03 Jan 2025 19:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250
    )
    ItemNewsContent(data = data)
}

val newsList = listOf(
    NewsData(
        xid = "1adsadsdfdsfdxczxs",
        title = "Diskon 30% Semua Menu Spesial",
        datePublish = "03 Jan 2025 19:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250
    ),
    NewsData(
        xid = "2adsadsaklksdsad",
        title = "Beli 1 Gratis 1 Ayam Crispy",
        datePublish = "04 Jan 2025 11:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "3adsasaddsasdsad",
        title = "Paket Berdua Hemat Mulai 50rb",
        datePublish = "05 Jan 2025 18:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "4adsadsasdghertzsad",
        title = "Gratis Minuman untuk Setiap Pembelian Bento",
        datePublish = "06 Jan 2025 12:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "5adsadsjhgasdsad",
        title = "Diskon 25% Menu Breakfast",
        datePublish = "07 Jan 2025 17:30",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250
    ),
    NewsData(
        xid = "6adsadsnbnasdsad",
        title = "Harga Spesial Nasi Goreng Kampung",
        datePublish = "08 Jan 2025 19:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "7adsadsasdasdasdsad",
        title = "Paket Hemat untuk Pelajar",
        datePublish = "09 Jan 2025 08:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "8adsadsasddsadsad",
        title = "Promo Pizza Jumbo 20%",
        datePublish = "10 Jan 2025 10:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250
    ),
    NewsData(
        xid = "9adsadsaadasdsdsad",
        title = "Diskon 40% Menu Keluarga",
        datePublish = "11 Jan 2025 20:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "10adsadsasdsadasdsad",
        title = "Roti Bakar Cashback 10%",
        datePublish = "12 Jan 2025 13:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "11adsadsassadasddsad",
        title = "Minuman Segar Mulai 5rb",
        datePublish = "13 Jan 2025 18:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "12adsadsashghdfgdsad",
        title = "Diskon 50% Menu Sushi Terpilih",
        datePublish = "14 Jan 2025 15:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "13adsasdfsdsasdsad",
        title = "Paket Ayam Geprek Level 10 Diskon 15%",
        datePublish = "15 Jan 2025 14:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250
    ),
    NewsData(
        xid = "14adsadsaxcxcsdsad",
        title = "Menu Korea Buy 2 Get 1",
        datePublish = "16 Jan 2025 17:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "15adsgfbadsasdsad",
        title = "Pasta Weekend Promo 20%",
        datePublish = "17 Jan 2025 19:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "16adsadasdadsasdsad",
        title = "Discount 10% for All Snacks",
        datePublish = "18 Jan 2025 09:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "17adsadsasdvbbsad",
        title = "Special Menu Seafood Mulai 25rb",
        datePublish = "19 Jan 2025 14:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250
    ),
    NewsData(
        xid = "18adsadsaryrtysdsad",
        title = "Diskon Ayam Bakar 20%",
        datePublish = "20 Jan 2025 18:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    ),
    NewsData(
        xid = "19adsadssdsdasdsad",
        title = "Dimsum All You Can Eat",
        datePublish = "21 Jan 2025 12:00",
        slug = "dine-in",
        thumbnail = R.drawable._50_400x250__2_
    ),
    NewsData(
        xid = "20adsadsadgfgfgsdsad",
        title = "Es Kopi Susu Promo 50%",
        datePublish = "22 Jan 2025 20:00",
        slug = "take-away",
        thumbnail = R.drawable._50_400x250__1_
    )
)

