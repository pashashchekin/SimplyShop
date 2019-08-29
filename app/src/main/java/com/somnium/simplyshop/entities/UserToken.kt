package com.somnium.simplyshop.entities

import java.io.Serializable

class UserToken (
    var is_admin : String,
    var activation_code : String,
    var fullname : String,
    var auth_token : String,
    var email: String
): Serializable