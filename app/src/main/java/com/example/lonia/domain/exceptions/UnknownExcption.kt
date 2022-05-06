package com.example.lonia.domain.exceptions

import androidx.annotation.StringRes

class UnknownExcption (@StringRes val errorMessage: Int): Exception() {
}