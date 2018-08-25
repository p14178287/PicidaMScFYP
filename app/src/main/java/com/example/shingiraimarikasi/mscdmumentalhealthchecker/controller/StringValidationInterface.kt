package com.example.shingiraimarikasi.mscdmumentalhealthchecker.controller

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

interface StringValidationInterface {

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(validator: (String) -> Boolean, message: String) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }



    fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()

//put together can then use this line in your LoginActivity code
//    loginform_username.validate({ s -> s.isValidEmail() },
//            "Valid email address required")

    fun <E>joinString (collection: Collection<E>, separator: String = ", "): String {
        val result = StringBuilder(separator)
        for ((index, element) in collection.withIndex()) {
            if (index > 0) result.append(separator)
            result.append(element)
        }
        result.append(separator)
        return result.toString()

    }

}

//class Implementator: KotUtilValidateInput{
//
//    //invoking a default
//    val list = listOf<Int>(1, 2, 3)
//    override fun <E> joinString(collection: Collection<E>, separator: String)
//            = super.joinString(list, "")
//
//}
//
//fun main(args: Array<String>) {
//    val imp = Implementator()
//    println(imp.list)
//}
//


