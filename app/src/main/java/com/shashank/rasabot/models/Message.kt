package com.shashank.rasabot.models

import com.shashank.rasabot.util.Constants

data class Message(
    var message: String?= null,
    var id: Int = Constants.USER,
    var imageUrl: String?=null
)
