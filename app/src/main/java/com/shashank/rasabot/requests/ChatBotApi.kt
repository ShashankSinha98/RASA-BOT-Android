package com.shashank.rasabot.requests

import com.shashank.rasabot.models.BotMessage
import com.shashank.rasabot.models.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatBotApi {

    @POST("webhook")
    fun messageBot(@Body userMessage:Message): Call<ArrayList<BotMessage>>
}