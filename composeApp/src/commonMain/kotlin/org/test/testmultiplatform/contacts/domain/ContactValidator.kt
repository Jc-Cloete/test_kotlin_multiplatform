package org.test.testmultiplatform.contacts.domain

object ContactValidator{
    fun validateContact(contact: Contact): ValidationResult {
        var result = ValidationResult()

        if(contact.firstName.isBlank()){
            result = result.copy(firstNameError = "First name is required")
        }
        if(contact.lastName.isBlank()){
            result = result.copy(lastNameError = "Last name is required")
        }
        if(contact.email.isBlank()){
            result = result.copy(emailError = "Email is required")
        }

        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        if(!emailRegex.matches(contact.email)){
            result = result.copy(emailError = "Email is not valid")
        }

        if(contact.phoneNumber.isBlank()){
            result = result.copy(phoneNumberError = "Phone number is required")
        }

        return result
    }

    data class ValidationResult(
        val firstNameError: String? = null,
        val lastNameError: String? = null,
        val emailError: String? = null,
        val phoneNumberError: String? = null,
    )
}