package com.shashank.rasabot.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shashank.rasabot.R

class BotMessageViewHolder(
    itemView:View
): RecyclerView.ViewHolder(itemView) {

    val botTextMessage:TextView = itemView.findViewById(R.id.bot_text_msg)

    val botImageMessage: ImageView = itemView.findViewById(R.id.bot_image_msg)

}