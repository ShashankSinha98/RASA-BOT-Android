package com.shashank.rasabot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shashank.rasabot.adapters.viewholders.ChatBotRecyclerAdapter
import com.shashank.rasabot.util.Constants
import com.shashank.rasabot.viewmodels.ChatBotViewModel

class ChatBotActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "ChatBotActivity"

    private lateinit var mMessageET: EditText
    private lateinit var mSendBtn: FloatingActionButton
    private lateinit var mChatRecyclerView: RecyclerView

    private lateinit var mChatBotViewModel: ChatBotViewModel
    private lateinit var mChatBotRecyclerAdapter: ChatBotRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        mChatBotViewModel =ViewModelProvider(this).get(ChatBotViewModel::class.java)
        initViews()
        initRecyclerView()
        subscribeObservers()

        mSendBtn.setOnClickListener(this)

    }

    private fun subscribeObservers() {

        mChatBotViewModel.getBotMessages().observe(this, Observer {  chatBotResponseList ->


            Log.d(TAG, "onChanged bot message: $chatBotResponseList")
        })

        mChatBotViewModel.getConversation().observe(this, Observer { conversation ->
            Log.d(TAG, "onChanged conversation: ${conversation.size}")

            mChatBotRecyclerAdapter.setConversation(conversation)
            mChatRecyclerView.scrollToPosition(conversation.size-1)
        })
    }

    private fun initViews() {
        mMessageET = findViewById(R.id.message_et)
        mSendBtn = findViewById(R.id.send_msg_btn)
        mChatRecyclerView = findViewById(R.id.message_list)
    }

    private fun initRecyclerView() {
        mChatBotRecyclerAdapter = ChatBotRecyclerAdapter()
        mChatRecyclerView.layoutManager = LinearLayoutManager(this)
        (mChatRecyclerView.layoutManager as LinearLayoutManager).stackFromEnd = true
        mChatRecyclerView.adapter = mChatBotRecyclerAdapter
    }

    override fun onClick(view: View?) {
        view?.let {
            when(view.id) {


                R.id.send_msg_btn -> {
                    val message = mMessageET.text.trim().toString()

                    if(!isMessageValid(message)) {
                        showToast("Message cannot be blank")
                        return
                    }

                    mChatBotViewModel.addUserMessageInConversation(message)
                    mMessageET.text.clear()
                    hideKeyboard()
                    mMessageET.clearFocus()

                    // send user message to chat bot
                    mChatBotViewModel.queryBot(message)
                }


            }
        }
    }

    private fun hideKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun isMessageValid(message: String?): Boolean {
        return !(message==null || message.trim().isEmpty())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}