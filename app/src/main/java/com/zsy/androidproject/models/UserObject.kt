package com.zsy.androidproject.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class UserObject(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "1",

    @Expose
    @SerializedName("email")
    @ColumnInfo(name = "email")
    val email: String? = null,

    @Expose
    @SerializedName("username")
    @ColumnInfo(name = "username")
    val username: String? = null,

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String? = null
) {
    override fun toString(): String {
        return "User(email=$email, username=$username, image=$image)";
    }
}