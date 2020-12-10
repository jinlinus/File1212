package com.jinlinus.filetest11;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class fragment_ex02 extends Fragment {

    private ListView LV_FileManager;
    // ListView 변수 LV_FileManager를 선언한다.
    private List<String> Item = null;
    // ArrayList 변수 Item을 선언하며 null값으로 초기화를 진행한다.
    private List<String> IPath = null;
    // ArrayList 변수 IPath를 선언하며 null값으로 초기화를 진행한다.
    private String mRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
    // 문자열 변수 mRoot를 선언하며 External Storage(외부 저장소)의 절대 경로를 가져온다.
    private TextView mPath;
    // TextView 변수 mPath를 선언한다.
    // private ~ --> private 접근제한자를 사용하여 외부에서 접근하지 못하도록 한다.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.third_activity, container, false);
        mPath = v.findViewById(R.id.textView1);
        // R클래스에서 View의 id인 textView1을 가져온 다음 third_activity.xml 파일에서 설정한 TextView의 속성들을 가져온다.
        LV_FileManager = v.findViewById(R.id.LV_FileManager);
        // R클래스에서 View의 id인 LV_FileManager를 가져온 다음 third_activity.xml 파일에서 설정한 ListView의 속성들을 가져온다.
        getDir(mRoot);
        LV_FileManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // ListView의 item(항목)들을 클릭했을 때 발생하는 리스너 이벤트를 정의한다.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final File file = new File(IPath.get(position));
                // File 클래스의 인스턴스(변수) file 객체를 선언하며 IPath에서 position에 위치한 데이터 참조를 획득한다.
                if(file.isDirectory())
                {// 해당 경로에서 File 및 Directory가 존재하는지 여부를 확인한다.
                 // file 객체가 디렉터리(폴더)일 경우
                    if(file.canRead())
                        getDir(IPath.get(position));
                    // 만약 파일을 읽을 수 있는 상태일 경우 IPath에서 position에 위치한 데이터 참조를 획득한다.
                    else
                        {// 파일을 읽을 수 없는 상태일 경우 AlertDialog 창을 띄워서 파일을 읽을 수 없는 상태라는 것을 알려준다.
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            // AlertDialog.Builder 클래스의 인스턴스 builder를 선언한다.
                             builder.setIcon(R.drawable.img7)
                            // AlertDialog에서의 icon 이미지를 drawable 폴더에서 가져와서 설정한다.
                                    .setTitle("[" + file.getName()+"] Folder can't be read!!!")
                                     // AlertDialog에 들어갈 문구를 설정한다.
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        // OK 버튼을 눌렀을 경우에 발생하는 리스너 이벤트를 정의한다.
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                        //  ------------------------------------------------------- //
                                        }
                                    }).show();
                        }
                }
                else
                    {// file이 디렉터리가 아닌 파일일 경우 AlertDialog 창을 띄워서 파일 이름을 출력한다.
                     // 파일 이름 뒤에는 확장자가 같이 출력된다.
                        new MaterialAlertDialogBuilder(getActivity())
                                .setIcon(R.drawable.ic_folder_orange_24dp)
                                // AlertDialog에서의 icon 이미지를 drawable 폴더에서 가져와서 설정한다.
                                .setTitle(file.getName())
                                // AlertDialog에 들어갈 문구 설정
                                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // MaterialAlertDialog cancel 버튼 클릭 시 수행할 이벤트를 작성한다.

                                    }
                                })
                                .setNegativeButton("decline", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // MaterialAlertDialog decline 버튼 클릭 시 수행할 이벤트를 작성한다.
                                    }
                                })
                                // decline과 cancel 버튼 --> accpet 버튼을 제외한 이 두 버튼의 경우 별다른 기능을 설정하지 않아도 자동으로 AlertDialog 창을 닫도록 설정되어 있다.

                                .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                                    // OK 버튼을 눌렀을 경우에 발생하는 리스너 이벤트를 정의한다.
                                    // 기획 당시에는 이미지 및 문서 파일 등을 파일관리자 내에서 바로 여는 것이 목표였으나
                                    // 진행이 원활하게 되지 않은 관계로 accept 버튼 클릭 시 기기 내에 있는 내 파일 앱을 호출하여 실행하는 것으로 대체하였다.
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    // MaterialAlertDialog OK 버튼 클릭 시 수행할 이벤트를 작성한다.
                                        ComponentName cn3 = new ComponentName("com.sec.android.app.myfiles", "com.sec.android.app.myfiles.external.ui.MultiInstanceLaunchActivity");
                                        // 내 파일 어플의 패키지명과 액티비티명을 인수로 받는 ComponentName을 새로 생성한다.
                                        Intent e6 = new Intent(Intent.ACTION_MAIN);
                                        e6.addCategory(Intent.CATEGORY_LAUNCHER);
                                        e6.setComponent(cn3);
                                        startActivity(e6);

                                    }
                                }).show();
                    }
            }
        });



        return v;
    }

    private void getDir(String dirPath)
    {
        mPath.setText(" Location: " + dirPath);
        // 경로의 이름을 "Location: " + dirPath로 설정하며 dirPath의 경우 /storage/emulated/0으로 찍히게 된다.
        Item = new ArrayList<String>();
        // Item(항목)에 대한 변수를 선언하며 문자열을 ArrayList로 변환한 것이다.
        IPath = new ArrayList<String>();
        // IPath(경로)에 대한 변수를 선언하며 문자열을 ArrayList로 변환한 것이다.

        File f = new File(dirPath);
        // String dirPath(문자열 변수 dirPath)를 경로로 갖는 File 객체 f를 선언한다.
        File[] files = f.listFiles();
        // File 객체의 배열 변수 files를 선언하며 dirPath 경로 상에 있는 파일들을 배열로 반환한다.
        if(!dirPath.equals(mRoot))
        {// 만약 dirPath가 root 경로가 아닐 경우  항목에 ../ 을 추가한다.
         // IPath(경로)에 있는 파일의 상위 폴더까지의 경로를 추가한다.
            Item.add("../");
            IPath.add(f.getParent());
        }

        for(int i=0; i<files.length; i++)
        {// i는 0부터 i가 files의 배열의 길이(index)보다 작을 때 까지 i를 1씩 증가시키면서
         // 아래의 실행문을 반복하여 실행한다.
            File file = files[i];
            // File 클래스의 객체 변수 file은 files 배열의 i번째를 배열 요소로 갖는다는 의미이다.
            IPath.add(file.getAbsolutePath());
            // IPath(경로)에 파일의 절대 경로를 추가한다.

            if(file.isDirectory())
                // 만약 파일 및 디렉터리가 있는 것이 확인될 경우에는 파일의 이름 뒤에 "*"을 추가한다.
                // item.add(file.getName() + "*");
                Item.add(file.getName());
            else
                // 그렇지 않을 경우에는 파일의 이름만 추가한다.
                Item.add(file.getName());
        }

        ArrayAdapter<String> fileList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Item);
        // ArrayAdapter 변수 fileList를 선언한다.
        // android.R.layout.simple_list_item_1 --> View로 매핑될 Resource의 id를 의미한다.
        // 쉽게 말하자면 안드로이드 내부에서 사전에 생성되어 있던
        // Layout 중에서 simple_list_item_1.xml이라는 파일을 읽어 오라는 뜻이며
        // TextView 하나로 구성된 ListView item의 Resource id가 바로 simple_list_item_1 이다.

        LV_FileManager.setAdapter(fileList);
        // ListView 변수 LV_FileManager
        // ListView에 Adapter를 적용시킨다.
    }
}
