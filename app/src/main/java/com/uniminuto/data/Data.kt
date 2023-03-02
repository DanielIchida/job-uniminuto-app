package com.uniminuto.data

interface Data<T> {
    fun insert(t: T) : T
    fun update(index: Int,t: T) : T
    fun delete(index: Int) : Boolean
    fun findAll() : List<T>
    fun findById(index: Int) : T?
}