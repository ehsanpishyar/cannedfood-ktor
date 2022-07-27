package com.example.tables

import org.jetbrains.exposed.sql.Table

object SellerCategoryTable: Table() {

    val id = integer("id")
    val title = varchar(name = "title", length = 50)

    override val primaryKey = PrimaryKey(id, name = "PK_SC_ID")
}