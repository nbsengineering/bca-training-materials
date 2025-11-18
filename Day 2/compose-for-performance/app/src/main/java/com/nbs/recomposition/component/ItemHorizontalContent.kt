package com.nbs.recomposition.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.recomposition.R

@Composable
fun ItemHorizontalContent(
    title: String,
    promoData: PromoData? = null,
    onOptionClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { },
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(8))
        ) {
            ImageView(
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                imageResourceId = promoData?.thumbnail ?: 0,
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4))
                    .background(Color.Black.copy(alpha = 0.6f))
                    .align(Alignment.BottomEnd)
            ) {
                Text(
                    text = promoData?.slug ?: "",
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 2.dp
                    ),
                    fontSize = 8.sp,
                    color = Color.LightGray
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                , contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                    text = title.ifEmpty { promoData?.title.orEmpty() },
                    fontSize = 12.sp,
                    maxLines = 1,

                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = promoData?.resto  ?: "",
                        color = Color.DarkGray,
                        fontSize = 10.sp
                    )
                    Text(
                        text = promoData?.datePublish  ?: "",
                        fontSize = 10.sp,
                        color = Color.LightGray
                    )
                }
                IconButton(
                    modifier = Modifier
                        .size(24.dp),
                    onClicked = onOptionClicked,
                    iconResource = R.drawable.outline_add_circle_outline_24,
                    colorTint = Color.Gray
                )
            }
        }

    }
}

@Composable
fun ImageView(
    @DrawableRes imageResourceId: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale? = null,
    isAdjustViewBounds: Boolean = false
) {
    if (imageResourceId == 0) {
        // Use a default image if imageResourceId is invalid
        return ImageView(
            imageResourceId = R.drawable.outline_add_shopping_cart_24,
            modifier = modifier,
            contentScale = contentScale,
            isAdjustViewBounds = isAdjustViewBounds
        )
    }

    val painter = painterResource(id = imageResourceId)
    val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
    var modifierResult = modifier
    if (isAdjustViewBounds) modifierResult = modifier.aspectRatio(imageRatio)

    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifierResult,
        contentScale = contentScale ?: ContentScale.FillBounds,
    )
}

@Composable
fun IconButton(
    @DrawableRes iconResource: Int,
    onClicked: () -> Unit,
    size: Dp = 24.dp,
    modifier: Modifier = Modifier,
    colorTint: Color = Color.Unspecified,
    isDisabled: Boolean = false
) {
    Icon(
        painter = painterResource(iconResource),
        contentDescription = null,
        modifier = if (isDisabled) {
            modifier.size(size)
        } else {
            modifier
                .size(size)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = Color.Gray, bounded = false) // New Ripple API
                ) {
                    onClicked()
                }
        },
        tint = if (isDisabled) Color.Black else colorTint
    )
}

val previewData =  PromoData(
    xid = "0asdksandhjasdasdkjk",
    title = "Beli 1 Gratis 1 Ayam Crispy",
    datePublish = "04 Jan 2025 11:00",
    slug = "take-away",
    resto = "Ayam Geprek Mantap",
    thumbnail = R.drawable._50_400x250
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HeadNewsItemHorizontalContentPreview() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        repeat(3) {
            ItemHorizontalContent(
                title = previewData.title,
                promoData = previewData,
                onOptionClicked = {},
            )

        }

    }
}

data class PromoData(
    val xid: String,
    val title: String = "",
    val datePublish: String =  "",
    val slug: String =  "",
    val resto: String =  "",
    val thumbnail: Int =  R.drawable._50_400x250,
)

val promoList = listOf(
    PromoData(
        xid = "1adsadsdfdsfdxczxs",
        title = "Diskon 30% Semua Menu Spesial",
        datePublish = "03 Jan 2025 19:00",
        slug = "dine-in",
        resto = "Ramen Ichiro",
        thumbnail = R.drawable._50_400x250
    ),
    PromoData(
        xid = "2adsadsaklksdsad",
        title = "Beli 1 Gratis 1 Ayam Crispy",
        datePublish = "04 Jan 2025 11:00",
        slug = "take-away",
        resto = "Ayam Geprek Mantap",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "3adsasaddsasdsad",
        title = "Paket Berdua Hemat Mulai 50rb",
        datePublish = "05 Jan 2025 18:00",
        slug = "dine-in",
        resto = "Dapoer Sunda Asri",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "4adsadsasdghertzsad",
        title = "Gratis Minuman untuk Setiap Pembelian Bento",
        datePublish = "06 Jan 2025 12:00",
        slug = "take-away",
        resto = "Bento Koi",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "5adsadsjhgasdsad",
        title = "Diskon 25% Menu Breakfast",
        datePublish = "07 Jan 2025 17:30",
        slug = "dine-in",
        resto = "Sunny Toast",
        thumbnail = R.drawable._50_400x250
    ),
    PromoData(
        xid = "6adsadsnbnasdsad",
        title = "Harga Spesial Nasi Goreng Kampung",
        datePublish = "08 Jan 2025 19:00",
        slug = "take-away",
        resto = "Nasi Goreng 99",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "7adsadsasdasdasdsad",
        title = "Paket Hemat untuk Pelajar",
        datePublish = "09 Jan 2025 08:00",
        slug = "dine-in",
        resto = "Bakso Jagoan",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "8adsadsasddsadsad",
        title = "Promo Pizza Jumbo 20%",
        datePublish = "10 Jan 2025 10:00",
        slug = "take-away",
        resto = "Pizza Corner",
        thumbnail = R.drawable._50_400x250
    ),
    PromoData(
        xid = "9adsadsaadasdsdsad",
        title = "Diskon 40% Menu Keluarga",
        datePublish = "11 Jan 2025 20:00",
        slug = "dine-in",
        resto = "Sate Nusantara",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "10adsadsasdsadasdsad",
        title = "Roti Bakar Cashback 10%",
        datePublish = "12 Jan 2025 13:00",
        slug = "dine-in",
        resto = "Roti Bakar Legend",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "11adsadsassadasddsad",
        title = "Minuman Segar Mulai 5rb",
        datePublish = "13 Jan 2025 18:00",
        slug = "take-away",
        resto = "Kopi Senja",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "12adsadsashghdfgdsad",
        title = "Diskon 50% Menu Sushi Terpilih",
        datePublish = "14 Jan 2025 15:00",
        slug = "dine-in",
        resto = "Sushi Haru",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "13adsasdfsdsasdsad",
        title = "Paket Ayam Geprek Level 10 Diskon 15%",
        datePublish = "15 Jan 2025 14:00",
        slug = "take-away",
        resto = "Ayam Babylon",
        thumbnail = R.drawable._50_400x250
    ),
    PromoData(
        xid = "14adsadsaxcxcsdsad",
        title = "Menu Korea Buy 2 Get 1",
        datePublish = "16 Jan 2025 17:00",
        slug = "dine-in",
        resto = "K-Food Station",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "15adsgfbadsasdsad",
        title = "Pasta Weekend Promo 20%",
        datePublish = "17 Jan 2025 19:00",
        slug = "dine-in",
        resto = "Pasta Delizioso",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "16adsadasdadsasdsad",
        title = "Discount 10% for All Snacks",
        datePublish = "18 Jan 2025 09:00",
        slug = "take-away",
        resto = "Snack Mania",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "17adsadsasdvbbsad",
        title = "Special Menu Seafood Mulai 25rb",
        datePublish = "19 Jan 2025 14:00",
        slug = "dine-in",
        resto = "Seafood Kiloan",
        thumbnail = R.drawable._50_400x250
    ),
    PromoData(
        xid = "18adsadsaryrtysdsad",
        title = "Diskon Ayam Bakar 20%",
        datePublish = "20 Jan 2025 18:00",
        slug = "take-away",
        resto = "Ayam Bakar Jogja",
        thumbnail = R.drawable._50_400x250__1_
    ),
    PromoData(
        xid = "19adsadssdsdasdsad",
        title = "Dimsum All You Can Eat",
        datePublish = "21 Jan 2025 12:00",
        slug = "dine-in",
        resto = "Dimsum Empire",
        thumbnail = R.drawable._50_400x250__2_
    ),
    PromoData(
        xid = "20adsadsadgfgfgsdsad",
        title = "Es Kopi Susu Promo 50%",
        datePublish = "22 Jan 2025 20:00",
        slug = "take-away",
        resto = "Kopi Mantul",
        thumbnail = R.drawable._50_400x250__1_
    )
)

