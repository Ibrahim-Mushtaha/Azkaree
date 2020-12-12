package com.ibrahim7.azkaree.model

data class  TypeAthker(var title:String, var image : Int){
    companion object{
        val COL_ID="id"
        val COL_TITLE="title"
        val COL_TYPE="type"

        val TABLE_NAME="typeAthker"

        val CREATE_TABLE="CREATE TABLE $TABLE_NAME(" +
                "$$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_TITLE TEXT NOT NULL," +
                "$COL_TYPE INTEGER NOT NULL);"
    }
}