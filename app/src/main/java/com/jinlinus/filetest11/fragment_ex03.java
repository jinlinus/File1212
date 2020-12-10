package com.jinlinus.filetest11;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_ex03 extends Fragment{
    public static WebView WV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v2 = inflater.inflate(R.layout.f_layout, container, false);
        // f_layout.xml 파일을 화면에 띄우기 위한 코드를 작성해야 한다.
        // Fragment에서는 view, inflater를 이용하여 레이아웃을 호출한다.
        WV = v2.findViewById(R.id.WV);
        // WebView의 id값을 R클래스에서 받아온 다음 f_layout.xml에서 설정한 속성을을 가져온다.
        WebSettings WVset = WV.getSettings();
        // WebView의 설정에 대한 변수 WebSettings를 선언하며 WebView와 연결시킨다.
        WV.setWebViewClient(new WebViewClient());
        WVset.setJavaScriptCanOpenWindowsAutomatically(true);
        // 팝업창을 띄울 필요가 있을 경우에 사용하며 이 속성이 추가되있지 않다면
        // window.open() 함수가 제대로 작동하지 않는다. --> 창이 띄워지지 않는다.
        WVset.setSupportZoom(true);
        // 웹 뷰 화면 내의 확대 및 축소 기능을 지원한다.
        WVset.setJavaScriptEnabled(true);
        // JavaScript로 구성되어 있는 기능들을 사용하기 위해 추가해야 하는 속성
        WVset.setLoadWithOverviewMode(true);
        // html 컨텐츠가 웹 뷰보다 클 경우 이를 화면 크기에 맞게 조정한다.
        // 예를 들면 우리가 접속할려는 사이트의 이미지 및 화면이 웹 뷰 크기보다 클 경우에는
        // 화면이 작게 나오거나 너무 크게 나오는 등의 문제가 생길 수 있다.
        // 따라서 자연스러운 화면을 볼려면 이러한 과정을 거쳐야 한다.
        WVset.setUseWideViewPort(true);
        // 화면 크기를 웹 뷰 화면에 정확히 맞추도록 설정한다.
        WVset.setAllowFileAccess(true);
        // 웹 뷰 화면 내에서 파일에 접근할 수 있도록 한다.
        WV.setNetworkAvailable(true);
        // 네트워크를 이용할 수 있는지 여부를 설정한다.
        WVset.setLoadsImagesAutomatically(true);
        // 어플에 등록되어 있는 image resource(이미지 자원)을 자동으로 불러오도록 한다.
        WVset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        WVset.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 웹 뷰 내에서 캐시를 사용할 것인지를 설정한다.
        // LOAD_NO_CACHE --> 즉, 캐시를 사용하지 않겠다는 뜻이다.
        WVset.setDomStorageEnabled(true);
        // Local Storage의 사용 여부를 설정한다.
        // 예를 들면 홈페이지에서 보이는 팝업창의 하단에 '1일간 보이지 않기/열지 않기' 등을 사용할 수 있도록 한다.
        WV.loadUrl("https://m.naver.com");
        // f_layout.xml 파일 = WebView 추가 --> network_security_config.xml, AndroidManifest.xml 파일 수정 필수!!
        // ** 초기 화면 실행 시 상단의 UI가 보이지 않는 현상을 해결하였다.
        // 상단의 속성들의 경우 추가 및 변경하고자 하는 기능에 따라 값을 다르게 줘야 한다.

        WV.setOnKeyListener(new View.OnKeyListener() {
            // Fragment에서 WebView를 구현할 경우 '뒤로 가기' 기능을 활성화하는 방법을 작성한다.
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                    // 만약 키이벤트의 Action값이 키이벤트의 ACTION_DOWN이 아니라면,
                    // 즉 키값이 키를 누른 상태가 아닐 경우에는 아무런 이벤트도 발생하지 않는다는 의미이다.
                    // 여기서 KeyEvent.ACTION_DOWN은 키를 눌렀을 때를 의미하며 키의 상태를 확인하고자 할 때 이 코드를 사용한다.
                    // ** KeyEvent.ACTION_UP = 키를 눌렀다가 손을 땠을 때
                    // ** KeyEvent.ACTION_MULTIPLE = 키를 여러번 눌렀을 때
                    return true;

                if(i == keyEvent.KEYCODE_BACK)
                {// Key code값이 KEYCODE_BACK일 경우(돌아가기 버튼을 클릭할 경우)
                    if(WV.canGoBack())
                    {// 뒤로 갈 수 있는 경우 --> 예를 들어서 네이버 화면 단에서 구글 접속 후 keycode를 검색했다고 가정한다면,
                        // keycode 검색 결과 화면의 입장에서는 이전 페이지가 3개 존재한다.
                        // keycode 검색 결과 화면 --> 구글 화면 --> 네이버 '구글' 검색 화면 --> 네이버 화면 등의 과정을 거친다.
                        WV.goBack();
                        // 위와 같이 이전으로 돌아갈 수 있는 페이지가 존재할 경우 이전 화면으로 넘어간다.
                    }
                    else
                        // 앞서 설명했던 과정에서 네이버 화면 단에서는 더이상 이전으로 넘어갈 페이지가 존재하지 않는다.
                        WV.destroy();
                        // 그렇기 때문에 이런 경우에는 destory()함수를 실행시켜서 어플을 종료시키게 된다.

                }
                return false;
                // 키값이 돌아가기 버튼이 아닐 때는 아무런 이벤트도 발생시키지 않는다고 앞에서 가정하였기 때문에
                // false값을 반환한다.
            }
        });

        return v2;
    }


}
