package com.jinlinus.filetest11;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;


public class commentActivity extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v4 = inflater.inflate(R.layout.comment_layout, container, false);
        // comment_layout.xml 파일을 호출한다.

        Button material_information = v4.findViewById(R.id.material_information);

        material_information.setOnClickListener(this);


        return v4;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.material_information:
            // information 버튼 클릭 시 실행할 내용을 작성한다.
                // Snackbar.make(getView(), "File Management Process Writting...", Snackbar.LENGTH_INDEFINITE).show();
                // duration = LENGTH_INDEFINITE --> 사용자가 임의로 설정한 Action이 실행되기 전까지는 사라지지 않도록 설정한다.
                Uri uri3 = Uri.parse("https://it-dev-jinlinus.tistory.com");
                Intent i32 = new Intent(Intent.ACTION_VIEW, uri3);
                startActivity(i32);
                // information 버튼 클릭 시 미리 지정한 uri 주소로 이동한다.
                break;

        }
    }
}
