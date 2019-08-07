package com.somnium.simplyshop.mainpage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.somnium.simplyshop.App
import com.somnium.simplyshop.R
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.ResponseModel
import com.somnium.simplyshop.entities.UserCreate
import com.somnium.simplyshop.enums.ServerStatus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class LoginPageActivity : AppCompatActivity(){

    private  lateinit var login : EditText
    private  lateinit var password : EditText
    private  lateinit var siginBtn : Button
    private val disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        login = findViewById(R.id.login_input)
        password = findViewById(R.id.password_input)
        siginBtn = findViewById(R.id.sign_in_btn)
        siginBtn.setOnClickListener{proceedToLogin()}
    }

    private fun validateFields(): Boolean {
        if(login.text.isBlank()){
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.text.isBlank()){
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun proceedToLogin() {
        if(validateFields()){
            sigIn()
        }
    }

    private fun sigIn() {
        disposables.add(App.getSimplyShopApi().auth(UserCreate(login.text.toString(),password.text.toString()))
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = ::onLoginSuccess,
                        onError = ::onLoginError
                )
        )
    }
    private fun onLoginSuccess(response: Response<ResponseModel<UserCreate>>) {
        if (response.isSuccessful && response.body()?.status == ServerStatus.SUCCESS) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }

    }

    private fun onLoginError(throwable: Throwable) {


    }

}
