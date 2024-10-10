package com.techuntried.unittesting

object Util {
    //user name condition is min 8 characters and max 15 characters
    //should not contain special characters and spaces and should not start with number
    fun checkIsUsernameValid(username: String): Boolean {
        val specialCharacters = "!@#$%^&*()_+{}:<>?,./;'[]\\|`~"
        if (username.length !in 8..15) {
            return false
        }
        if (username.contains(" ")) {
            return false
        }
        if (username[0].isDigit()) {
            return false
        }
        for (char in username) {
            if (char in specialCharacters) {
                return false
            }
        }
        return true
    }

    //conditions for valid password is min 8 characters and max 15 characters
    //should contain at least one uppercase letter, one lowercase letter, one digit,
    //and one special character
    //should not contain spaces
    fun checkPasswordValid(password: String): Boolean {
        val specialCharacters = "!@#$%^&*()_+{}:<>?,./;'[]\\|`~"
        if (password.length !in 8..15) {
            return false
        }
        if (password.contains(" ")) {
            return false
        }

        var containsSpecialChar = false
        var containsUppercase = false
        var containsLowercase = false
        var containsDigit = false
        for (char in password) {
            when {
                char in specialCharacters -> containsSpecialChar = true
                char.isUpperCase() -> containsUppercase = true
                char.isLowerCase() -> containsLowercase = true
                char.isDigit() -> containsDigit = true
            }
            if (containsUppercase&& containsLowercase && containsDigit && containsSpecialChar){
                return true
            }
        }

        return false
    }
}