package com.example.lonia.domain.exceptions

import androidx.annotation.StringRes

class NetworkException (@StringRes val errorMessage: Int): Exception() {


}