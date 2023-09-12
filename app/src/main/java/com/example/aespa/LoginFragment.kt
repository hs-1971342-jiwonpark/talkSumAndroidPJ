    package com.example.aespa

    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.activity.result.ActivityResultLauncher
    import androidx.activity.result.contract.ActivityResultContracts
    import androidx.fragment.app.Fragment
    import androidx.fragment.app.activityViewModels
    import com.example.aespa.databinding.LoginFragmentBinding
    import com.kakao.sdk.user.UserApiClient

    class LoginFragment : Fragment() {
        val viewModel: ButtonViewModel by activityViewModels()
        private lateinit var binding: LoginFragmentBinding // 바인딩 객체 선언

        // registerForActivityResult를 선언
        private lateinit var launcher: ActivityResultLauncher<Intent>

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            binding = LoginFragmentBinding.inflate(inflater, container, false) // 뷰 바인딩 초기화

            // registerForActivityResult()를 여기에서 호출
            launcher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val receivedData = data?.getBooleanExtra("loginState",false) // 전달된 데이터 받기
                    viewModel.loginable.value = receivedData
                }
            }
            binding.imageButton4.setOnClickListener {
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    launcher.launch(intent)
                }
            }
            return binding.root // 뷰 계층 구조 반환
        }
    }
