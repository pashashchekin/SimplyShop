package com.somnium.simplyshop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.somnium.simplyshop.App
import com.somnium.simplyshop.R
import com.somnium.simplyshop.api.ObserveOnMainThread
import com.somnium.simplyshop.entities.Jwtoken
import com.somnium.simplyshop.entities.LoginModel
import com.somnium.simplyshop.entities.ResponseModel
import com.somnium.simplyshop.entities.UserCreate
import com.somnium.simplyshop.enums.ServerStatus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Response

class LoginPageActivity : AppCompatActivity(){

    private  lateinit var login : EditText
    private  lateinit var password : TextInputEditText
    private  lateinit var siginBtn : Button
    private  lateinit var regBtn : TextView
    private  lateinit var forgetBtn : TextView
    private val disposables = CompositeDisposable()
    private lateinit var userToken: UserCreate
    private lateinit var jwtoken: Jwtoken


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
        Jwtoken.getToken(this)
    }

    private fun initUI() {
        login = findViewById(R.id.login_input)
        password = findViewById(R.id.password_input)
        siginBtn = findViewById(R.id.sign_in_btn)
        regBtn = findViewById(R.id.reg_actvity_btn)
        forgetBtn = findViewById(R.id.forget_activity_btn)
        regBtn.setOnClickListener { onRegistrationClick() }
        forgetBtn.setOnClickListener { onForgetPasswordClick() }
        siginBtn.setOnClickListener{ proceedToLogin() }
    }

    private fun onRegistrationClick() {
        val intent = Intent(this , RegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun onForgetPasswordClick() {
        val intent = Intent(this , ForgetPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun validateFields(): Boolean {
        if(login.text.isBlank()){
            Toast.makeText(this, "Введите имя пользователя", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.text!!.isBlank()){
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
        disposables.add(App.getSimplyShopApi().auth(LoginModel(login.text.toString().trim(),password.text.toString().trim()))
                .compose(ObserveOnMainThread())
                .subscribeBy(
                        onNext = ::onLoginSuccess,
                        onError = ::onLoginError
                )
        )
    }
    private fun onLoginSuccess(response: Response<ResponseModel<UserCreate>>) {
        if (response.isSuccessful && response.body()?.status == ServerStatus.SUCCESS) {
            userToken = response.body()?.data!!
            Log.d("qwe1", userToken.toString())
            val userToken = userToken.auth_token
            val jwtoken = Jwtoken.getToken(this)
            jwtoken.auth_token = userToken
            Log.d("token12",jwtoken.auth_token.toString())
            Jwtoken.saveToken(this@LoginPageActivity,jwtoken)

            val intent = Intent(this, HomeActivity::class.java)
            //intent.putExtra(KEY_ACCOUNT, userToken)
            startActivity(intent)
        }

    }

    private fun onLoginError(throwable: Throwable) {

    }

}
