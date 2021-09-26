package com.shashank.rasabot.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shashank.rasabot.models.BotMessage
import com.shashank.rasabot.models.Message
import com.shashank.rasabot.models.UserMessage
import com.shashank.rasabot.repositories.ChatBotRepository
import com.shashank.rasabot.util.Constants

class ChatBotViewModel: ViewModel() {

    private val TAG = "ChatBotViewModel"

    private val mChatBotRepository: ChatBotRepository = ChatBotRepository

    fun getBotMessages(): LiveData<List<BotMessage>> = mChatBotRepository.getBotMessages()

    fun getConversation(): LiveData<MutableList<Message>> = mChatBotRepository.getConversation()

    fun addUserMessageInConversation(message: String) {
        mChatBotRepository.addUserMessageInConversation(
            UserMessage(
                message = message,
                id = Constants.USER
            )
        )
    }

    fun queryBot(message: String) {
        mChatBotRepository.queryBot(message)
    }

}