package com.example.testtask.domain.exceptions

import androidx.annotation.StringRes

class NetworkException (@StringRes val errorMessage: Int): Exception() {
}