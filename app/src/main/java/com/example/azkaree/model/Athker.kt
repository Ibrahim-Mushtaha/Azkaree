package com.example.azkaree.model

class Athker(var id :Int,var content:String,var counter:Int,var status:Int,var type:Int){

    companion object{
        val COL_ID="id"
        val COL_CONTENT="content"
        val COL_COUNTER="counter"
        val COL_STATUS="status"
        val COL_TYPE="type"

        val TABLE_NAME="athker"
        val CREATE_TABLE=
            " CREATE TABLE ${TABLE_NAME} " +
                    "( ${COL_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ${COL_CONTENT} TEXT NOT NULL," +
                    "${COL_COUNTER} INTEGER NOT NULL," +
                    "${COL_STATUS} INTEGER," +
                    " ${COL_TYPE} INTEGER NOT NULL);"
    }
}