package com.shashank.rasabot.adapters.viewholders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shashank.rasabot.R
import com.shashank.rasabot.models.Message
import com.shashank.rasabot.util.Constants

class ChatBotRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "ChatBotRecyclerAdapter"

    private var mConversation: MutableList<Message> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view: View?= null

        when(viewType) {

            Constants.USER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_message_layout, parent, false)
                return UserMessageViewHolder(view)
            }

            Constants.BOT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.bot_message_layout, parent, false)
                return BotMessageViewHolder(view)
            }

            Constants.LOADING -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.loading_bot_layout, parent, false)
                return LoadingBotViewHolder(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.user_message_layout, parent, false)
                return UserMessageViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemViewType: Int = getItemViewType(position)

        if(itemViewType == Constants.USER) {

            val userMessageViewHolder = holder as UserMessageViewHolder
            userMessageViewHolder.userMessageTV.text = mConversation[position].message?: Constants.MESSAGE_TEXT_NULL

        } else if(itemViewType == Constants.BOT) {

            val botMessageViewHolder = holder as BotMessageViewHolder

            when {
                mConversation[position].message!=null -> {
                    botMessageViewHolder.botTextMessage.visibility = View.VISIBLE
                    botMessageViewHolder.botImageMessage.visibility = View.GONE

                    botMessageViewHolder.botTextMessage.text = mConversation[position].message
                }

                mConversation[position].imageUrl!=null -> {
                    botMessageViewHolder.botTextMessage.visibility = View.GONE
                    botMessageViewHolder.botImageMessage.visibility = View.VISIBLE

                    val requestOptions: RequestOptions = RequestOptions()
                        .placeholder(R.drawable.loading_img)

                    Glide.with(holder.itemView.context)
                        .setDefaultRequestOptions(requestOptions)
                        .load(mConversation[position].imageUrl)
                        .into(botMessageViewHolder.botImageMessage)
                }

                else -> {
                    botMessageViewHolder.botTextMessage.visibility = View.VISIBLE
                    botMessageViewHolder.botImageMessage.visibility = View.GONE

                    botMessageViewHolder.botTextMessage.text = Constants.MESSAGE_TEXT_NULL
                }
            }


        }

    }

    override fun getItemCount(): Int = mConversation.size


    override fun getItemViewType(position: Int): Int {
        return mConversation[position].id
    }

    fun setConversation(conversation: MutableList<Message>) {
        Log.d(TAG,"new conversation set: $conversation")
        mConversation = conversation
        notifyDataSetChanged()
    }

    fun showBotLoading() {

        if(isBotLoading()) return

        mConversation.add(
            Message(
                message = null,
                id = Constants.LOADING
        ))
        Log.d(TAG,"Showing loading")
        notifyDataSetChanged()
    }

    private fun isBotLoading(): Boolean  {
        Log.d(TAG,"isBotLoading: $mConversation")
        if(mConversation.isEmpty()) return false

        return mConversation[mConversation.size-1].id==Constants.LOADING
    }

    fun hideBotLoading() {
        if(!isBotLoading()) {
            Log.d(TAG,"Bot is not loading")
            return
        }

        mConversation.removeAt(mConversation.size-1)
        notifyDataSetChanged()
    }

}