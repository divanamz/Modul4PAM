package com.example.modul4.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TicketOrderScreen() {
    var ticketPrice by rememberSaveable {
        mutableIntStateOf(50000)
    }

    var ticketQuantity by rememberSaveable {
        mutableIntStateOf(1)
    }

    var buyerName by rememberSaveable {
        mutableStateOf("")
    }

    /*
     * status:
     * 0 = belum ada status
     * 1 = nama harus diisi
     * 2 = memproses
     * 3 = berhasil
     */
    var orderStatus by rememberSaveable {
        mutableIntStateOf(0)
    }

    var isProcessing by rememberSaveable {
        mutableStateOf(false)
    }

    //LauchedEffect
    LaunchedEffect(isProcessing) {

        if (isProcessing) {

            // Memproses pesanan selama 5 detik
            delay(5000)

            // Setelah 5 detik pesanan berhasil
            isProcessing = false
            orderStatus = 3

            // Reset form setelah berhasil pesan
            buyerName = ""
            ticketQuantity = 1
        }
    }
    //total harga
    val totalPrice = ticketPrice * ticketQuantity

    Scaffold(
        containerColor = SoftOrangeBackground
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Header
            TicketHeader()

            Spacer(modifier = Modifier.height(18.dp))

            // Form tiket
            TicketForm(
                buyerName = buyerName,
                onBuyerNameChange = {
                    buyerName = it

                    if (orderStatus == 1 && it.isNotBlank()) {
                        orderStatus = 0
                    }
                },

                ticketPrice = ticketPrice,

                ticketQuantity = ticketQuantity,
                onDecrease = {
                    if (ticketQuantity > 1) {
                        ticketQuantity--
                    }
                },
                onIncrease = {
                    if (ticketQuantity < 10) {
                        ticketQuantity++
                    }
                },

                totalPrice = totalPrice,

                isProcessing = isProcessing,

                onOrderClick = {

                    // Nama masih kosong
                    if (buyerName.trim().isEmpty()) {
                        orderStatus = 1

                    } else {
                        orderStatus = 2
                        isProcessing = true
                    }
                },

                onResetClick = {
                    ticketQuantity = 1
                },

                orderStatus = orderStatus
            )
        }
    }
}

@Composable
fun TicketHeader() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(58.dp)
                .background(
                    color = SoftOrange,
                    shape = RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.ConfirmationNumber,
                contentDescription = "Tiket",
                tint = DarkOrange,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Pemesanan Tiket",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Pesan tiketmu dengan mudah",
            fontSize = 14.sp,
            color = GrayText
        )
    }
}

@Composable
fun TicketForm(
    buyerName: String,
    onBuyerNameChange: (String) -> Unit,

    ticketPrice: Int,

    ticketQuantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,

    totalPrice: Int,

    isProcessing: Boolean,

    onOrderClick: () -> Unit,

    onResetClick: () -> Unit,

    orderStatus: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            // Form nama
            Text(
                text = "Nama",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(7.dp))

            OutlinedTextField(
                value = buyerName,

                onValueChange = onBuyerNameChange,

                modifier = Modifier.fillMaxWidth(),

                singleLine = true,

                enabled = !isProcessing,

                placeholder = {
                    Text(
                        text = "Masukkan nama Anda",
                        color = Color(0xFFB5B5B5)
                    )
                },

                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DarkOrange,
                    unfocusedBorderColor = Color(0xFFE1E1E1),
                    cursorColor = DarkOrange
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Harga tiket
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Harga Tiket",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkText
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = formatRupiah(ticketPrice),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkOrange
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Jumlah tiket
            Text(
                text = "Jumlah Tiket",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Tombol -
                IconButton(
                    onClick = onDecrease,
                    enabled = !isProcessing && ticketQuantity > 1,
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            color = SoftOrange,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {

                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Kurangi tiket",
                        tint = DarkOrange
                    )
                }

                // Jumlah
                Text(
                    text = ticketQuantity.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                // Tombol +
                IconButton(
                    onClick = onIncrease,
                    enabled = !isProcessing && ticketQuantity < 10,
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            color = SoftOrange,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah tiket",
                        tint = DarkOrange
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Total bayar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = VerySoftOrange,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 13.dp
                    ),

                horizontalArrangement = Arrangement.SpaceBetween,

                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Total Pembayaran",
                    fontSize = 14.sp,
                    color = DarkText
                )

                Text(
                    text = formatRupiah(totalPrice),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkOrange
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tombol pesan
            Button(
                onClick = onOrderClick,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                enabled = !isProcessing,

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkOrange,
                    disabledContainerColor = Color(0xFFE3B99E)
                )
            ) {

                if (isProcessing) {

                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        text = "Memproses...",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                } else {

                    Text(
                        text = "Pesan Tiket",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Tombol reset
            OutlinedButton(
                onClick = onResetClick,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                enabled = !isProcessing,

                shape = RoundedCornerShape(18.dp),

                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = DarkOrange
                ),

                border = BorderStroke(
                    width = 1.5.dp,
                    color = DarkOrange
                )
            ) {

                Text(
                    text = "Reset",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Status
            when (orderStatus) {
                //error
                1 -> {
                    StatusCard(
                        backgroundColor = ErrorBackground,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null,
                                tint = ErrorRed,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        title = "Nama harus diisi",
                        textColor = ErrorRed
                    )
                }
                //processing
                2 -> {
                    StatusCard(
                        backgroundColor = ProcessingBackground,
                        icon = {
                            CircularProgressIndicator(
                                modifier = Modifier.size(22.dp),
                                color = DarkOrange,
                                strokeWidth = 2.5.dp
                            )
                        },
                        title = "Memproses pesanan...",
                        textColor = DarkOrange
                    )
                }
                //success
                3 -> {
                    StatusCard(
                        backgroundColor = SuccessBackground,
                        icon = {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = SuccessGreen,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        title = "Tiket berhasil dipesan!",
                        textColor = SuccessGreen
                    )
                }
            }
        }
    }
}

@Composable
fun StatusCard(
    backgroundColor: Color,
    icon: @Composable () -> Unit,
    title: String,
    textColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(14.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {

        icon()

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}
fun formatRupiah(value: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(
        Locale("id", "ID")
    )
    return formatter.format(value)
        .replace(",00", "")
}

//warna
val SoftOrangeBackground = Color(0xFFFFF8F3)
val SoftOrange = Color(0xFFFFE7D5)
val VerySoftOrange = Color(0xFFFFF1E7)
val DarkOrange = Color(0xFFE88952)
val DarkText = Color(0xFF292929)
val GrayText = Color(0xFF777777)
val ErrorRed = Color(0xFFD94A4A)
val ErrorBackground = Color(0xFFFFEEEE)
val ProcessingBackground = Color(0xFFFFF3E8)
val SuccessGreen = Color(0xFF4FA66A)
val SuccessBackground = Color(0xFFEAF8EE)