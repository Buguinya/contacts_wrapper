package com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhuravlevmikhail.a65appshomeproject.R
import com.zhuravlevmikhail.a65appshomeproject.common.interfaces.ContactsClickListener
import com.zhuravlevmikhail.a65appshomeproject.fragments.contacts.ContactGeneral
import java.util.*
import kotlin.collections.ArrayList

class ContactsAdapter(private val itemClickListener : ContactsClickListener) : RecyclerView.Adapter<ContactHolder>() {

    var contacts  = Collections.emptyList<ContactGeneral>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val contactCell = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_contact_general, parent, false)
        return ContactHolder(
            contactCell,
            itemClickListener
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(contacts[position])
    }

    fun setContacts(newContacts : ArrayList<ContactGeneral>) {
        contacts = newContacts
        notifyDataSetChanged()
    }
}