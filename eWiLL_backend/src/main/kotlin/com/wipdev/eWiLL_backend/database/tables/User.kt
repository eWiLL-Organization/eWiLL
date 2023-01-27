package com.wipdev.eWiLL_backend.database.tables

import javax.persistence.*


@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"])])
class User {

    constructor(username: String, password: String, email: String, roles: Set<Role>) {
        this.username = username
        this.password = password
        this.email = email
        this.roles = roles
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id:Long = 0

    @Column(name = "username", nullable = false)
    open var username:String = ""

    @Column(name = "password", nullable = false)
    open var password:String = ""

    @Column(name = "email", nullable = false)
    open var email:String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "role_id")])
    open var roles: Set<Role> = hashSetOf()





}