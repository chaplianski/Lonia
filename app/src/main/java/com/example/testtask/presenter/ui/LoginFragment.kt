package com.example.testtask.presenter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.testtask.R
import com.example.testtask.di.DaggerAppComponent
import com.example.testtask.domain.model.LoginRequest
import com.example.testtask.presenter.factories.LoginViewModelFactory
import com.example.testtask.presenter.factories.QuestionsViewModelFactory
import com.example.testtask.presenter.viewmodel.LoginViewModel
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginField = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.login_field)
        val passwordField = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.password_field)
        val loginValue = loginField.editText?.text.toString()
        val passwordValue = passwordField.editText?.text.toString()
        val loginText = view.findViewById<EditText>(R.id.et_login_login_fragment)
        val passwordText = view.findViewById<EditText>(R.id.et_password_login_fragment)





        val signInButton = view.findViewById<Button>(R.id.bt_sign_in_login_fragment)
        val tokenRequest = LoginRequest(
            email = "ilia@mail.ru",
            password = "12345"
        )



        signInButton.setOnClickListener {
            Log.d("My Log", "LoginField: $loginField, loginValue: $loginValue, loginText: $loginText")

            if (loginText.text.isBlank() && passwordText.text.isBlank()){
                passwordField.error = "Please enter login and password"
                Log.d("My Log", "Login Fragment ${passwordField.error}")
            } else {
                loginViewModel.getToken(tokenRequest)
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.action_loginFragment_to_briefCaseFragment)
                Log.d("My Log", "Login Fragment Login: $loginValue and password: $passwordValue")
            }
        }
    }


}