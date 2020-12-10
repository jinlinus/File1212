package com.jinlinus.filetest11;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

public class fragment_ex01 extends Fragment implements View.OnClickListener {

    // private final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
    // sdcard 경로에 대한 문자열 변수 strSDpath를 선언한다. 외부 저장소의 절대 경로를 가져온다.
    // 보통 storage/emulated/0 으로 나타난다.
    // private final File myDir = new File(strSDpath + "/SP-A");
    // File 클래스의 새 File 객체를 생성하는 변수 myDir을 선언한다.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.sub_activity, container, false);
        // AppCompatActivity를 상속 받는 클래스의 경우 setContentView() 함수를 사용해서 레이아웃 파일을 불러왔다.
        // 하지만 Fragment를 상속 받는 클래스의 경우 Fragment의 특성 상 content를 사용하지 않으므로 View를 사용하여 레이아웃 파일을 호출한다.

        Button btnInnerStorage = v.findViewById(R.id.btnInternalStorage);
        // Internal 버튼의 id값을 R클래스에서 가져온 다음 레이아웃 파일에서 설정한 속성값들을 받아온다.
        Button btnReview = v.findViewById(R.id.btnReview);

        btnReview.setOnClickListener(this);
        btnInnerStorage.setOnClickListener(this);
        return v;
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.btnInternalStorage:
                // View의 id값을 비교하여 id가 btnInternalStorage일 경우
                // 환경설정 내에 있는 내부 저장소 현황을 불러온다.
                Intent intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                startActivityForResult(intent, 0);
                // 호출한 Activity로부터 결과를 받을 경우에 사용한다.
                break;
                // ** switch case문으로 id값을 비교하여 각 버튼을 눌렀을 경우에 실행할 내용을 작성하였다.
                // 이 때 case문의 마지막에 break;를 작성하지 않을 경우 사용자가 작성한 실행시킬 내용이 제대로 작동하지 않는 현상이 발생한다.
                // 예) button A와 button B가 있다고 가정한다.
                // id가 a일 경우 a-1을 실행하고 id가 b일 경우 b-1을 실행하도록 작성하였다.
                // 그런데 마지막에 break;를 작성하지 않을 경우 A버튼을 누르면 b-1이 실행된다.
                // 따라서 위와 같은 현상을 방지하기 위해 break;를 작성해야만
                // A버튼 클릭 시 A에 대한 이벤트를 발생시키고 끝낸 뒤에 B버튼 클릭 시 B에 대한 이벤트를 발생시킬 수 있다.

            case R.id.btnReview:
                // id가 btnReview일 경우 플레이스토어의 링크를 호출해서 화면에 띄우게 된다.
                // 추후에 플레이스토어에 앱 출시를 한 다음에 앱의 업데이트 및 제거가 필요한 상황에 대비하기 위해
                // 다음과 같은 사항을 추가로 수정할 예정이다.
                // 현재 이동경로는 플레이스토어 메인 화면으로 설정하였지만 나중에는
                // 앱의 설치화면으로 이동하도록 설정할 계획이다.

                // ex)
                // String uri = "m.naver.com"
                // Intent intent = new Intent()Intent.ACTION_VIEW, Uri.parse(uri);
                // Uri.parse(uri) --> 사용자가 설정한 uri주소를 호출한다.
                String URL = "https://play.google.com/store";
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                startActivity(i2);
                // 호출한 Activity로부터 결과를 받을 경우에 사용한다.
                break;

        }

        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 호출한 Activity로부터 결과를 받기 위한 onActivityResult() 메서드를 재정의(override)하여 선언한다.
        super.onActivityResult(requestCode, resultCode, data);
    }
}

