package com.sankyawhtwe.codetest.data.exceptions

class ApiException(message: String, val code:Int): Exception(message) {
}