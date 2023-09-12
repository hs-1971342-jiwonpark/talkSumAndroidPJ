package com.example.aespa

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.aespa.databinding.LoginEditFragmentBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginEditFragment : Fragment() {
    val viewModel: ButtonViewModel by activityViewModels()


    private fun showProgressDialog(): Dialog {
        val progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.progressbar)
        progressDialog.setCancelable(false)
        progressDialog.show()
        return progressDialog
    }

    private fun performLogin(): Completable {
        return Completable.create { emitter ->
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireActivity())) {
                UserApiClient.instance.loginWithKakaoTalk(requireActivity()) { token, error ->
                    if (error != null) {
                        emitter.onError(Throwable("로그인 실패"))
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(requireActivity(), callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공! ${token.accessToken}")
                        val resultIntent = Intent()
                        resultIntent.putExtra("loginState", true) // 전달할 데이터 설정
                        activity?.setResult(Activity.RESULT_OK, resultIntent)
                        requireActivity().finish()
                        emitter.onComplete()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireActivity(), callback = { token, error ->
                    if (error != null || token == null) {
                        emitter.onError(Throwable("로그인 실패"))
                    } else {
                        emitter.onComplete()
                    }
                })
            }
        }
    }

    private fun performLogout(): Completable {
        return Completable.create { emitter ->
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    emitter.onError(Throwable("로그아웃 실패"))
                } else {
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                    viewModel.loginable.value = false
                    Toast.makeText(requireContext(),"로그아웃 성공!",Toast.LENGTH_SHORT)
                    Log.d("로그아웃","로그아웃")
                    val resultIntent = Intent()
                    resultIntent.putExtra("loginState", false) // 전달할 데이터 설정
                    activity?.setResult(Activity.RESULT_OK, resultIntent)
                    requireActivity().finish()
                    emitter.onComplete()
                }
            }
        }
    }





    private lateinit var binding: LoginEditFragmentBinding // 바인딩 객체 선언
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Toast.makeText(requireContext(),"로그인 성공!",Toast.LENGTH_SHORT)
            Log.i(TAG, "카카오계정으로 로그인에 성공하셨습니다. 토큰이 존재 ${token.accessToken}")
            val resultIntent = Intent()
            resultIntent.putExtra("loginState", true) // 전달할 데이터 설정
            activity?.setResult(Activity.RESULT_OK, resultIntent)
            requireActivity().finish()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginEditFragmentBinding.inflate(inflater, container, false) // 뷰 바인딩 초기화


        binding.imageView6.setOnClickListener {
            val progressDialog = showProgressDialog()

            performLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { progressDialog.dismiss() }
                .subscribe({
                    // 로그인 성공 시 처리
                    Toast.makeText(requireContext(), "로그인 성공!", Toast.LENGTH_SHORT).show()
                    // ... (이전 코드와 동일한 성공 처리)
                }, { error ->
                    // 로그인 실패 시 처리
                    Toast.makeText(requireContext(), "로그인 실패: ${error.message}", Toast.LENGTH_SHORT)
                        .show()
                })
        }
        binding.imageView7.setOnClickListener {
            val progressDialog = showProgressDialog()


            performLogout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { progressDialog.dismiss() }
                .subscribe({
                    // 로그아웃 성공 시 처리
                    Toast.makeText(requireContext(), "로그아웃 성공!", Toast.LENGTH_SHORT).show()
                    // ... (이전 코드와 동일한 성공 처리)
                }, { error ->
                    // 로그아웃 실패 시 처리
                    Toast.makeText(
                        requireContext(),
                        "로그아웃 실패: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                })


        }
        binding.imageView8.setOnClickListener{

        }

        return binding.root // 뷰 계층 구조 반환
    }
}
