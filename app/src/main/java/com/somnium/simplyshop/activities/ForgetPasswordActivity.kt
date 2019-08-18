package com.somnium.simplyshop.activities

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

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var username : EditText
    private lateinit var email : EditText
    private lateinit var resetPasswordBtn : Button
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        initUI()
    }

    private fun initUI() {
        username = findViewById(R.id.input_name_forget)
        email = findViewById(R.id.input_email_forget)
        resetPasswordBtn = findViewById(R.id.reset_password_btn)
        resetPasswordBtn.setOnClickListener { onProceedResetPassword() }
    }

    private fun onProceedResetPassword() {
        if (validateFields()){
            resetPassword()
        }
    }

    private fun resetPassword() {
        disposables.add(App.getSimplyShopApi().resetPassword(username.text.toString(), email.text.toString())
                .compose(ObserveOnMainThread())
                .subscribeBy (
                        onNext = ::onResetPasswordSuccess,
                        onError = ::onResetPasswordError
                ))
    }

    private fun onResetPasswordSuccess(response: Response<ResponseModel<UserCreate>>) {
        if (response.isSuccessful && response.body()?.status == ServerStatus.SUCCESS){
            Toast.makeText(this, "Пароль был успешно сброшен", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginPageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onResetPasswordError(throwable: Throwable) {
        Toast.makeText(this, "Вы не можете сбрасывать пароль так часто", Toast.LENGTH_SHORT).show()

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
        return true
    }
}
