package com.shashank.rasabot.repositories

import androidx.lifecycle.LiveData
import com.shashank.rasabot.models.BotMessage
import com.shashank.rasabot.models.Message
import com.shashank.rasabot.models.UserMessage
import com.shashank.rasabot.requests.ChatBotApiClient
import com.shashank.rasabot.util.Constants

object ChatBotRepository {

    private const val TAG = "ChatBotRepository"

    private val mChatBotApiClient: ChatBotApiClient = ChatBotApiClient

    fun getBotMessages(): LiveData<List<BotMessage>> = mChatBotApiClient.getBotMessages()

    fun getConversation(): LiveData<MutableList<Message>> = mChatBotApiClient.getConversation()

    fun addUserMessageInConversation(userMessage: UserMessage) {
        mChatBotApiClient.addUserMessageInConversation(userMessage)
    }


    fun queryBot(message: String) {
        mChatBotApiClient.queryBot(Constants.USER, message)
    }
}