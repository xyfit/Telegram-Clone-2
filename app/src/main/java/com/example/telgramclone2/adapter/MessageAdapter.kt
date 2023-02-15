package com.example.telgramclone2.adapter
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.telgramclone2.App
import com.example.telgramclone2.databinding.MyMessageItemLyBinding
import com.example.telgramclone2.databinding.OtherMessageItemLyBinding
import com.example.telgramclone2.models.Message

class MessageAdapter: RecyclerView.Adapter<MessageViewHolder>() {

    companion object{
        private const val VIEW_TYPE_MY_MESSAGE = 1
        private const val VIEW_TYPE_OTHER_MESSAGE = 2
    }
    private val messagesList: ArrayList<Message> = ArrayList()

    fun addMessage(message: Message){
        messagesList.add(message)
        notifyDataSetChanged()
    }
    inner class MyMessageViewHolder (b: MyMessageItemLyBinding) : MessageViewHolder(b.root) {
        private var messageText: TextView = b.txtMyMessage
        private var timeText: TextView = b.txtMyMessageTime

        override fun messageBind(message: Message) {
            messageText.text = message.message
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }
    inner class OtherMessageViewHolder (b: OtherMessageItemLyBinding) : MessageViewHolder(b.root) {
        private var messageText: TextView = b.txtOtherMessage
        private var userText: TextView = b.txtOtherUser
        private var timeText: TextView = b.txtOtherMessageTime

        override fun messageBind(message: Message) {
            messageText.text = message.message
            userText.text = message.user
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
            MyMessageViewHolder(MyMessageItemLyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            OtherMessageViewHolder(OtherMessageItemLyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val itemData = messagesList[position]
        holder.messageBind(itemData)
    }

    override fun getItemCount(): Int  = messagesList.size

    override fun getItemViewType(position: Int): Int {
        val message = messagesList[position]

        return if(App.user1 == message.user) {
            VIEW_TYPE_MY_MESSAGE
        }
        else {
            VIEW_TYPE_OTHER_MESSAGE
        }
    }

}
open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun messageBind(message:Message) {}
}