package com.zhuravlevmikhail.a65appshomeproject.data.androidApi.contacts

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactDetailed
import com.zhuravlevmikhail.a65appshomeproject.domain.entities.contacts.ContactGeneral

class ContactContentProvider(private val contentResolver: ContentResolver) :
    ContactsProvider {
    
    override fun getDetailedContact(contactId : Long) : ContactDetailed?{
        var contactDetailed : ContactDetailed? = null
        val contactCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.NUMBER),
            String.format("%s = ?", ContactsContract.Contacts._ID),
            arrayOf(contactId.toString()),
            null)
        try {
            contactCursor?.let {
                val nameIndex = contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val phoneIndex = contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val photoIndex = contactCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
                if (it.count > 0) {
                    it.moveToFirst()
                    val name = it.getString(nameIndex)
                    val phone = it.getString(phoneIndex)
                    val photoUri = it.getString(photoIndex)
                    var email : String? = "No email"
                    val emailCursor = contentResolver.query (
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        String.format("%s = ?", ContactsContract.Contacts.DISPLAY_NAME),
                        arrayOf(name),
                        null
                    )
                    try {
                        emailCursor?.let { emailCursor ->
                            if (emailCursor.count > 0) {
                                val emailIndex =
                                    emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
                                emailCursor.moveToFirst()
                                email = emailCursor.getString(emailIndex)
                            }
                        }
                        contactDetailed = if (photoUri != null) {
                            ContactDetailed(name, phone, email, Uri.parse(photoUri))
                        } else {
                            ContactDetailed(name, phone, email)
                        }
                    } finally {
                        emailCursor?.close()
                    }
                }
            }
        } finally {
            contactCursor?.close()
        }
        return contactDetailed
    }

    override fun getAllContacts() : ArrayList<ContactGeneral>{
        var contactsGeneral = ArrayList<ContactGeneral>()
        val contactsCursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null,
            null)

        try {
            contactsCursor?.let {
               contactsGeneral = getOutContactsFrom(it)
            }
        } finally {
            contactsCursor?.close()
        }
        return contactsGeneral
    }

    override fun getRequestedContacts(name : String) : ArrayList<ContactGeneral> {
        val contacts = ArrayList<ContactGeneral>()
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            String.format(" %s LIKE ? ", ContactsContract.Contacts.DISPLAY_NAME),
            arrayOf("%$name%"),
            String.format("%s ASC", ContactsContract.Contacts.DISPLAY_NAME)
        )
            .use { cursor -> contacts.addAll(getOutContactsFrom(cursor)) }
        return contacts
    }

    private fun getOutContactsFrom(cursor: Cursor) : ArrayList<ContactGeneral> {
        val contactsGeneral = ArrayList<ContactGeneral>()
        val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val idIndex = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(idIndex)
            val name = cursor.getString(nameIndex)
            val phone = cursor.getString(phoneIndex)
            contactsGeneral.add(
                ContactGeneral(id, name, phone)
            )
        }
        return contactsGeneral
    }
}