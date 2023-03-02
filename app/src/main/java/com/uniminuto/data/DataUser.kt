package com.uniminuto.data

import com.uniminuto.models.Roles
import com.uniminuto.models.User

class DataUser : Data<User>{

    private val users: MutableList<User> = mutableListOf()

    init {
       users.add(User(1,"gerente","123456",Roles.MANAGER))
       users.add(User(2,"admin","123456",Roles.ADMIN))
       users.add(User(3,"empacador","123456",Roles.WAREHOUSE))
       users.add(User(4,"transportista","123456",Roles.CARRIER))
       users.add(User(5,"recepcion","123456",Roles.RECEPTIONIST))
    }

    override fun insert(t: User): User {
        this.users.add(t);
        return t
    }

    override fun update(index: Int, t: User): User {
        this.users[index] = t
        return t;
    }

    override fun delete(index: Int): Boolean {
        this.users.removeAt(index)
        return true
    }

    override fun findAll(): List<User> {
        return  this.users
    }

    override fun findById(index: Int): User? {
        return this.users.find { u -> u.id == index }
    }

    fun login(name: String,password: String) : User? {
        return  this.users.firstOrNull { u -> u.name == name && u.password == password }
    }
}