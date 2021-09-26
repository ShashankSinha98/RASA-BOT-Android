package com.shashank.rasabot.util

class Constants {

    companion object {
        val NGROCK_URL = "https://b026-2405-201-4029-66-6812-2e4d-93-e9e1.ngrok.io"
        val BASE_URL = "$NGROCK_URL/webhooks/rest/"
        val NETWORK_TIMEOUT = 5000L
        val MESSAGE_TEXT_NULL = "NA"

        val USER = 0
        val BOT = 1
        val LOADING = 2
    }
}