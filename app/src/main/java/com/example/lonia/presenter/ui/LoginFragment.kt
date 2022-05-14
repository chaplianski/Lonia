package com.example.lonia.presenter.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.lonia.R
import com.example.lonia.di.DaggerAppComponent
import com.example.lonia.domain.model.LoginRequest
import com.example.lonia.presenter.factories.LoginViewModelFactory
import com.example.lonia.presenter.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { loginViewModelFactory }

    override fun onAttach(context: Context) {
        DaggerAppComponent.builder()
            .context(context)
            .build()
            .loginFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginField = view.findViewById<TextInputLayout>(R.id.login_field)
        val passwordField = view.findViewById<TextInputLayout>(R.id.password_field)
        val loginText = view.findViewById<EditText>(R.id.et_answer_answer_fragment)
        var tokenRequest = LoginRequest("","")
        val passwordText = view.findViewById<EditText>(R.id.et_password_login_fragment)
        val loginErrorTextView = view.findViewById<TextView>(R.id.tv_login_error_message)
        var loginValue = ""
        var passwordValue = ""

        loginText.doOnTextChanged { inputText, _, _, _ ->
            if (inputText?.length!! > 0){
                loginField.error = null
            }
            loginValue = inputText.toString()
        }

        passwordText.doOnTextChanged{ inputText, _, _, _ ->
            if (inputText?.length!! > 0){
                passwordField.error = null
            }
            passwordValue = inputText.toString()
        }

        val signInButton = view.findViewById<Button>(R.id.bt_sign_in_login_fragment)


        signInButton.setOnClickListener {
            loginErrorTextView.visibility = View.GONE
            if (loginText.text.isBlank()) {
                loginField.error = "Please enter email"
                Log.d("My Log", "Login Fragment ${passwordField.error}")
            }else if (passwordText.text.isBlank()) {
                passwordField.error = "Please enter password"
                Log.d("My Log", "Login Fragment ${passwordField.error}")
            }else {
                tokenRequest = LoginRequest(
                    email = loginValue,
                    password = passwordValue
                )
                loginViewModel.getToken(tokenRequest)

                loginViewModel.loginErrorMessage.observe(this.viewLifecycleOwner) {

                    if (it == "access is allowed") {

                        val navController = Navigation.findNavController(view)
                        navController.navigate(R.id.briefCaseFragment)
                    }

                    loginErrorTextView.text = it
                    loginErrorTextView.visibility = View.VISIBLE
                }

            }
        }
    }


}