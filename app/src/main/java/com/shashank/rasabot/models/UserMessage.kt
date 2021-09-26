package com.shashank.rasabot.models

import com.shashank.rasabot.util.Constants

data class UserMessage(
    var message: String?= null,
    var id: Int = Constants.USER
)
