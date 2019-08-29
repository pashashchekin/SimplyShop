package com.somnium.simplyshop.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.somnium.simplyshop.App
import com.somnium.simplyshop.R
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.LoginModel
import com.somnium.simplyshop.entities.ResponseModel
import com.somnium.simplyshop.entities.UserCreate
import com.somnium.simplyshop.enums.ServerStatus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response


class RegistrationActivity : AppCompatActivity() {

    private lateinit var singInBtn : TextView
    private lateinit var username : EditText
    private lateinit var email : EditText
    private lateinit var password : TextInputEditText
    private lateinit var registrationBtn : Button
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initUI()
    }

    private fun initUI() {
        username = findViewById(R.id.input_name_reg)
        email = findViewById(R.id.input_email_reg)
        password = findViewById(R.id.input_password_reg)
        registrationBtn = findViewById(R.id.registration_btn)
        singInBtn = findViewById(R.id.signin_actvity_btn)

        singInBtn.setOnClickListener { onSignInClick() }
        registrationBtn.setOnClickListener { proceedToRegistration() }
    }

    private fun validateFields(): Boolean {
        if(username.text.isBlank()){
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.text!!.isBlank()){
            Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.text!!.isBlank()){
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun proceedToRegistration() {
        if (validateFields()){
            registrationProceed()
        }
    }

    private fun registrationProceed() {
        disposables.add(App.getSimplyShopApi().registration(LoginModel(username.text.toString(),password.text.toString()))
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = ::onLoginSuccess,
                        onError = ::onLoginError
                )
        )
    }

    private fun onLoginSuccess(response: Response<ResponseModel<UserCreate>>) {
        if (response.isSuccessful && response.body()?.status == ServerStatus.SUCCESS){
            Toast.makeText(this, "Вы успешно зарегистрированы", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onLoginError(throwable: Throwable) {


    }

    private fun onSignInClick() {
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
    }
}
