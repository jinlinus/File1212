package com.jinlinus.filetest11;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.io.File;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // Fragment 관련 설정
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private fragment_ex01 fragment_ex01 = new fragment_ex01();
    private fragment_ex02 fragment_ex02 = new fragment_ex02();
    private fragment_ex03 fragment_ex03 = new fragment_ex03();
    private commentActivity commentActivity = new commentActivity();
    // --> 4th Fragment에 띄울 화면인 comment_layout.xml에 대한 클래스 파일

    // 하단의 내비게이션 뷰에 항목을 추가할 때마다 위와 같이 코드를 추가로 작성한다.
    // 각각의 .java 파일에서는 View 객체를 생성하기 위해 inflater를 사용한다.
    // res/layout 경로에서 구성한 xml 파일을 화면에 띄워주도록 한다.

    //private static final int REQUEST_CODE_PREFERENCE = 1;
    // request code를 위한 정수형 변수 REQUEST_CODE_PREFERENCE를 선언한다.
    // 자료형(int) 앞에 final을 붙여서 값이 변경되지 않도록 설정한다.

    private Animation fao, fac;
    // Animation 효과의 활성화/비활성화 상태에 대한 변수를 선언한다.
    private Boolean isfao = false;
    // FloatingActionButton을 누르기 전에는 비활성화되어 있는 상태이다.
    // 버튼을 누르지 않은 상태일 때 애니메이션 효과를 주기 위해 논리형(boolean)으로 변수를 선언한
    // 다음 초기값을 false로 설정한다.
    private FloatingActionButton FA, FM1, FM2;
    // 3개의 FloatingActionButton에 대한 변수를 선언한다.

    final int GL_CODE = 200;
    // 내장 갤러리에 접근하기 위한 권한 요청 코드에 대한 변수를 선언한다.

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater action_inflate = getMenuInflater();
        action_inflate.inflate(R.menu.action_menu, menu);
        // super.onCreateOptionsMenu(menu);
        // menu.add("Setting");
        // actionbar 메뉴에 기존에 구성해놓은 항목들을 삭제한 후 Setting(환경 설정) 실행을 위한 메뉴를 생성하였다.)
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.SR:
                // Intent i10 = new Intent(getApplicationContext(), sliding_activity.class);
                // startActivity(i10);
                return true;


            case R.id.set:
                Intent i5 = new Intent(this, preference_setting.class);
                startActivityForResult(i5, REQUEST_CODE_PREFERENCE);
            return true;
            // startActivity()와 startActivityForResult()의 차이
            // startActivity() --> 사용자가 새로 실행하고자 하는 Activity를 실행한다. 결과값을 다른 Activity에 전달하지는 않는다.
            // startActivityForResult() --> Activity를 실행한다는 점에서는 startActivity()와 유사하다.
            // 하지만 결과값을 다른 Activity에 전달하여 사용자가 원하는 기능을 수행하도록 한다는 점에서 차이가 있다.
            // ** 명시적 인텐트(explicit intent) = 다른 Activity의 이름을 명확하게 지정하여 사용한다.
            // / 암시적 인텐트(implicit intent) = 약손된 Action을 지정하여
            // 안드로이드에서 기본적으로 제공하는 응용 프로그램을 실행하도록 하는 것이다. ex) 전화, 웹 브라우저 접속, 갤러리, 영상.....
            // 상단에 작성한 코드를 보면 Intent 클래스 변수 i5를 선언한 다음 preference_setting.java 라는 Activity class 파일을 넘긴다.

        }
        return false;
    }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // activity_main.xml 파일을 불러와서 사용한다.


        Toolbar toolbar = findViewById(R.id.tool_tb);
        setSupportActionBar(toolbar);
        new SlidingRootNavBuilder(this).withMenuLayout(R.layout.sliding_layout).withToolbarMenuToggle(toolbar).inject();
        // Toolbar 추가 --> sub_activity.xml에서 추가한 toolbar의 id값을 가져온 다음 설정한 속성값들을 받아온다.
        // SlidingRootNav를 사용하여 좌측에서 우측으로 화면을 쓸어 넘길 경우 sliding_layout.xml에서 구성한 메뉴 화면이 나타나게 된다.
        // SlidingRootNav --> 좌측 화면에 구성할 메뉴 버튼에 대한 변수를 선언하였다.
        // 위젯(뷰)의 id값을 가져와서 해당 뷰 실행 시 발생하는 이벤트 등을 나타내고자 할때
        // 기본적으로 A = (Button) findViewById(R.id.a); 의 형식을 띄게 된다.
        // 그러나 실제로 코드를 작성할 경우 casting 'findViewById(...)' to 'Button' is redundant 라는 경고 문구가 나타난다.
        // 이는 findViewById 함수를 사용하여 형변환, 즉 casting을 하고자 할 때 더이상 'Button'과 같이 뷰의 이름을 사용할 필요가 없어졌다는 의미이다.
        // 따라서 이와 같이 경고 문구가 뜨면서 글씨 색깔이 회색으로 바뀌면 당황하지 말고 회색으로 변경된 부분을 지우고 작업을 수행하면 된다.
        // 앱 실행 시 정상적으로 작업이 이루어진다는 것을 알 수 있다.
        // Toolbar 추가 --> sub_activity.xml에서 추가한 toolbar의 id값을 가져온 다음 설정한 속성값들을 받아온다.
        // SlidingRootNav를 사용하여 좌측에서 우측으로 화면을 쓸어 넘길 경우 sliding_layout.xml에서 구성한 메뉴 화면이 나타나게 된다.

        Button home = findViewById(R.id.btn_home);
        Button dash = findViewById(R.id.btn_dash_board);
        Button pdf = findViewById(R.id.btn_pdf);
        Button mat = findViewById(R.id.btn_material);
        Button exit = findViewById(R.id.btn_exit);
        // SlidingRootNav에 구현할 버튼들의 id값을 가져온 다음 각 위젯(뷰)에서 설정한 속성들을 받아온다.
        home.setOnClickListener(this);
        dash.setOnClickListener(this);
        pdf.setOnClickListener(this);
        mat.setOnClickListener(this);
        exit.setOnClickListener(this);

        fao = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.float_open);
        fac = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.float_close);
        // ** 폴더명을 anim, animation 으로 지정해야만 에러가 발생하지 않는다.
        // Expected resource of type anim error

        FA = findViewById(R.id.FloatAction);
        FM1 = findViewById(R.id.FloatMini1);
        FM2 = findViewById(R.id.FloatMini2);
        // R클래스에서 View의 id값을 가져온 다음
        // 각각의 FloatingActionButton에서 설정한 속성들을 가져온다.

        FA.setOnClickListener(this);
        FM1.setOnClickListener(this);
        FM2.setOnClickListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.material);
        // BottomNavigationView 변수를 선언하며 R클래스에서 View의 id인 material을 가져온 다음 activity_main.xml에서 설정한 BottomNavigationView의 속성들을 가져온다.
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.FL1, fragment_ex01).commitAllowingStateLoss();
        // Fragment 생성 후 activity에 추가하는 것만으로는 정상적인 서비스 동작을 기대할 수 없다.
        // Activity or Fragment 간 상호작용을 구현하는 것이 필요한데 이러한 역할을 하는것이
        // 바로 Fragment Manager이다.
        // Transaction --> 특정 대상에 대해 추가/변경/제거 등의 작업들이 발생하는 것을 통들어 이르는 말이다.
        // Fragment Manager는 사용자가 Activity를 추가/변경/제거/입력 이벤트 등의 작업을 수행할 수 있도록 한다.
        // Transaction의 상태를 Fragment Backstack을 통해 저장할 수 있도록 한다.
        // Fragment Transaction --> "Fragment의 추가/삭제/교체가 가능하다" 라는 사전적 의미가 있다.
        // Android 내의 Fragment Transaction의 경우 Backstack 관리, Fragment 전환 애니메이션 설정 등 많은 일을 할 수 있다.

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // Fragment 사용을 위해선 상단 부분에서 작성한
                // private FragmentManager fragmentManager = getSupportFragmentManager(); 라는 구문을 통해
                // FragmentManager를 선언한다. 그런 다음 beginTransaction() 메서드를 호출해서
                // Fragment Transaction 작업을 시작할 수 있도록 한다.
                // Fragment에 대한 추가/삭제/교체 등의 작업, 애니메이션, Fragment Backstack 등을 추가한 다음에는
                // commit() 메서드를 호출하여 Transaction 작업을 마무리 한다.
                // 단 해당 프로젝트에서는 commint()를 호출하지 않아도 정상적으로 실행이 된다. (fragment를 xml 파일에 별도로 선언하지 않고 자바 파일에서만 선언한 상태이다.)
                // 하지만 보통의 경우에는 앱 실행 후 화면 전환을 시도할 때 강제 종료가 발생한다.
                switch(item.getItemId())
                {// menu 폴더의 bottom_navigation.xml 파일에서 설정한 item 항목들의 id값을 비교하여
                 // 해당 항목의 id가 Page1, Page2, Page3일 때 각각의 경우에 맞는 화면 전환이 이루어지도록 한다.
                    case R.id.Page1:
                        {// id가 Page1일 경우
                            transaction.replace(R.id.FL1, fragment_ex01).commitAllowingStateLoss();
                            // 화면을 fragment_ex01.xml 화면으로 변경한다.
                            // transaction.replace.commitAllowStateLoss() --> commitAllowLoss()
                            // Fragment에서 다른 Fragment로의 이동이 일어날 때 예외 상황이 발생하는 경우가 있는데
                            // 이는 onCreate() 메서드에서 SavedInstanceState를 호출한 다음 commit()를 실행할 수 없을 경우에 발생한다.
                            // 이를 Activity State Loss 현상이라 한다.
                            // callback에서 transaction의 commit가 이루어지지 않는다.(Asynchronous의 callback)
                            // commit()을 실행할 수 없는 경우 commitAllowStateLoss()를 사용한다.
                            break;
                        }

                    case R.id.Page2:
                        {// id가 Page2일 경우
                            transaction.replace(R.id.FL1, fragment_ex02).commitAllowingStateLoss();
                            // 화면을 fragment_ex02.xml 화면으로 변경한다.
                            break;
                        }

                    case R.id.Page3:
                        {// id가 Page3일 경우
                            transaction.replace(R.id.FL1, fragment_ex03).commitAllowingStateLoss();
                            // 화면을 fragment_ex03.xml 화면으로 변경한다.
                            break;
                        }

                    case R.id.Page4:
                        {
                            //id가 Page4일 경우
                            transaction.replace(R.id.FL1, commentActivity).commitAllowingStateLoss();
                        }

                }

                return true;
            }
        });
        //  ** 하단의 코드들 -> 초기 프로그램 작성 도중 기능 변경 or 삭제의 필요성이 느껴지는 경우들은 전부 주석처리하였다.
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        // AndroidManifest.xml에서 권한 설정 후 PermissionChecker의 checkSelfPermission 메서드를 사용하여 권한 획득 여부를 확인한다.
        // 권한이 획득되었을 경우 바로 해당 기능을 사용할 수 있다. 획득되지 않았을 경우에는
        // ActivityCompat의 requestPermissions 메서드를 사용하여 권한을 요청한다.
        // app 실행 시 권한 요청 대화창이 처음 나타나게 되며 권한 요청을 수락할 경우 특정 권한을 사용할 수 있다.
        // 이 대화창은 표준 dialog box이며 커스터마이징이 불가능하다.
        // 권한 요청을 거부할 경우 ActivityCompat의 shouldShowRequestPermissionRationale 메서드 반환 값이 true가 된다.
        // 다시 대화창을 띄우지 않도록 설정하는 CheckBox가 추가되며 이 항목을 체크한 뒤에도 권한 요청을 거부할 경우
        // 어플 실행 시 더 이상 대화창이 나타나지 않는다. (어플 설정에서 권한을 직접 허가해줘야 한다.)
        //Button btnMkdir, btnRmdir, btnFileList, newWindow;
        //final EditText edtFileList;
        // EditText = text 입력 및 수정이 가능한 view
        // final EditText edtFileList --> text 변경이 불가능하도록 설정한다.
       // btnMkdir = findViewById(R.id.btnMkdir);
        // btnRmdir = findViewById(R.id.btnRmdir);
        // btnFileList = findViewById(R.id.btnFileList);
        // newWindow = findViewById(R.id.newWindow);
        // edtFileList = findViewById(R.id.edtFileList);
        // findViewById(R.id.View_id) = R(클래스)에서 View_id 호출 후 이 View를 사용하는 xml파일에서 설정한 속성들을 가져온다.

        //final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //final File myDir = new File(strSDpath + "/SP-A");
        // External Storage(외부 저장소)의 최상위 주소 구현 --> getExternalStorageDirectory().getAbsolutePath();
        // System Folder(Directory) 반환 --> getRootDirectory()
        // Data Folder 반환 --> getDataDirectory
        // SD card의 절대 경로를 반환한다.
        // /sdcard 와  /storage/emulated/0 의 경로에서 폴더 생성 및 삭제가 정상적으로 수행되는 것을 확인하였다.
        /*btnMkdir.setOnClickListener(new View.OnClickListener() {
            // mkdir 버튼 클릭 시 발생하는 리스너 이벤트를 정의한다.
            @Override
            public void onClick(View view) {
                myDir.mkdir();
                // 지정된 경로에서 디렉터리를 생성한다.
            }
        });
        */
        /*btnRmdir.setOnClickListener(new View.OnClickListener() {
            // rmdir 버튼 클릭 시 발생하는 리스너 이벤트를 정의한다.
            @Override
            public void onClick(View view) {
                myDir.delete();
                // 지정된 경로에 있는 디렉터리를 삭제한다.
            }
        });
         */
        /* btnFileList.setOnClickListener(new View.OnClickListener() {
            // system folder / file 목록 버튼 클릭 시 발생하는 리스너 이벤트를 정의한다.
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                // System Folder (Directory) 경로를 반환한다.
                File[] sysFiles = (new File(sysDir).listFiles());
                // ** listFiles() --> 디렉터리 경로에 있는 파일 목록(디렉터리도 포함한다.)을 배열로 반환한다.
                // ** listFiles(FilenameFilter filter)
                // --> 디렉터리 경로에 있는 파일 이름을 기준으로 정렬한 다음 결과값을 배열로 반환한다.
                // ** listFiles(FileFilter filter)
                // --> 디렉터리 경로에 있는 파일 이름으로만 정렬한 다음 결과값을 배열로 반환한다.
                // ** list() --> 하위 경로의 파일의 이름만 배열로 반환한다.
                String strFrame;
                // 출력할 폴더 및 파일 목록에 대한 문자열 변수 strFrame 을 선언한다.
                for(int i=0; i<sysFiles.length; i++)
                {// i=0부터 i가 sysFiles(폴더 및 파일 목록의 배열)의 index(배열의 길이)보다 작을때 까지
                    // i를 1씩 증가시키면서 아래의 문장을 반복하여 실행한다.
                    if(sysFiles[i].isDirectory() == true)
                        strFrame = "<Folder>" + sysFiles[i].toString();
                    // sysFiles(폴더 및 파일 목록의 배열)의 해당 경로에 디렉터리가 있는지 확인하여 폴더가 있을경우
                    // "<Folder>" 를 붙인 다음 sysFiles --> 문자열로 변환한다.
                    else
                        strFrame = "<File>" + sysFiles[i].toString();
                    edtFileList.setText(edtFileList.getText() + strFrame + "\n");
                    // 폴더가 아닐 경우 "<File>"을 붙인 다음 sysFiles --> 문자열로 변환한다.
                    // EditText 안에 있는 text를 받아온 다음 strFrame(폴더 및 파일 목록)을 뒤에 출력한다.
                    // 이 때 "\n"은 줄바꿈을 의미하며 폴더 및 파일의 목록들을 한 줄씩 나열하여 출력하게 된다.

                }

            }
        }); */

        /* newWindow.setOnClickListener(new View.OnClickListener() {
            // Next 버튼 클릭 시 발생하는 리스너 이벤트를 정의한다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Sub.class);
                // Intent 클래스의 인스턴스(변수)를 생성한다.
                // intent --> Android 4대 구성요소인 액티비티, 서비스, 브로드캐스트 리시버, 콘텐트 프로바이더가 상호 간 데이터를 주고 받기 위한 메시지 객체를 의미한다.
                // getApplicationContext() = 메인 클래스의 Context를 반환한다. --> MainActivity.this로 작성해도 무방하다. --> 1번째 Parameter(매개변수)
                // Context = 어플에 대해 시스템이 관리하는 정보에 접근하는 역할과
                // 안드로이드 시스템 서비스에서 제공하는 API(Application Programming Interface) 호출하는 역할을 수행한다.
                // Sub.class = intent에 포함될 Sub(Activity)를 넘긴다. (넘길 Activity 이름 뒤에 .class 추가)
                startActivity(intent);
                // startActivity() = Activity 화면 출력을 위한 메서드
            }
        }); */

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        int ID = view.getId();
        // 비교할 id 값에 대한 정수형 변수 ID를 선언한다.
        switch(ID)
        {// id값을 받아온 다음 각각의 id값과 비교하여 해당 id값과 일치할 경우
            // 아래와 같은 문장을 실행한다.
            case R.id.FloatAction:
                anim();
                // View.OnClickListener를 implements 시키게 되면
                // oncreate() 내부에 아래와 같이 setOnClickListener(this)를 추가해야 한다.
                // FA.setOnClickListener(this);
                // FM1.setOnClickListener(this);
                // FM2.setOnClickListener(this);
                // onClick() 메서드를 onCreate() 메서드 밖에 재정의(override)한다.
                break;

            case R.id.FloatMini2:
                // id값이 FloatMini2일 경우 공유 기능을 실행한다.
                anim();
                // anim() 메서드를 호출하여 실행한다.
                Intent e1 = new Intent(android.content.Intent.ACTION_SEND);
                // Intent 클래스 변수 e1을 선언한다. 이메일을 전송하는 기능을 넣을 예정이므로
                // 전송에 대한 액션을 호출한다.
                e1.setType("text/email");
                // intent의 유형을 설정한다.
                // intent의 유형을 설정하지 않을 경우 다음과 같은 문구가 화면에 출력된다.
                // --> No apps can perform this action = 이 액션을 수행할 앱이 없다는 의미이다.
                e1.putExtra(Intent.EXTRA_EMAIL, new String[]{"jinlinus07@gmail.com"});
                // putExtra() --> 현재 Activity에서 다른 Activity로
                // 특정 값을 전달하고자 할 때 사용하는 함수이다.
                // putExtra(String name =
                // Intent.EXTRA_EMAIL, String[] value = new String[]{"jinlinus07@gmail.com"});
                // 2번째 매개변수(parameter) =>
                // 이메일 수신자에 대한 문자열 배열 변수를 새로 생성한다.
                // {}안에는 받을 사람의 이메일 주소를 작성하였다.
                e1.putExtra(Intent.EXTRA_SUBJECT, "Title: Feedback");
                // putExtra(String name, String value) => 이메일의 제목의 설정한다.
                startActivity(Intent.createChooser(e1, "Send Email to Developer"));
                // FloatMini2 = Send FloatingActionButton 클릭 시 선택기가 화면에 나타난다.
                // 해당 화면에서 사용자는 기기에서 기본적으로 제공되어지는 공유 기능을 사용하거나
                // 설치되어 있는 어플리케이션의 공유 기능을 이용하여 다른 작업을 진행할 수 있다.
                // 위에서 진행한 사항들을 확인하기 위해 Gmail 앱을 클릭할 경우 앞서서 설정한 대로
                // 정상적으로 이메일 주소와 제목이 출력되는 것을 알 수 있다.
                break;

            case R.id.FloatMini1:
                anim();
                // anim() 메서드를 호출하여 실행한다.
                ComponentName cn = new ComponentName("com.samsung.android.app.notes",
                        "com.samsung.android.app.notes.memolist.MemoListBaseActivity");
                // Samsung Notes 어플의 Package Name 과 Activity Name을 인수로 받는다.
                Intent e2 = new Intent(Intent.ACTION_MAIN);
                e2.addCategory(Intent.CATEGORY_LAUNCHER);
                e2.setComponent(cn);
                startActivity(e2);
                // 해당 FloatingActionButton 클릭 시 삼성 노트 어플을 실행한다.
                break;

            case R.id.btn_home:
                // Home 버튼 클릭 시 메인 화면(앱 실행 시 처음 보여지는 화면)으로 이동하게 된다.
                Intent i21 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i21);
                break;

            case R.id.btn_dash_board:
                // Dashboard 버튼 클릭 시 내장 갤러리를 호출하여 사진 목록을 보여준다.
                Intent e3 = new Intent(Intent.ACTION_VIEW);
                // ACTION_PICK으로 설정할 경우 갤러리에 접근할 수는 있지만 이미지 선택 시 화면이 종료된다.
                e3.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(e3, GL_CODE);
                // final int GL_CODE = 200; --> 갤러리에 접근하기 위한 미리 선언한 requestCode를 사용하여 권한을 요청한다.
                break;

            case R.id.btn_pdf:
                // PDF Viewer를 실행하여 .pdf 파일을 볼 수 있도록 한다.
                // Adobe Reader를 사용하였다.
                ComponentName cn2 = new ComponentName("com.adobe.reader", "com.adobe.reader.AdobeReader");
                // Adobe Reader 어플의 패키지명과 액티비티명을 인수로 받는 ComponentName을 새로 생성한다.
                Intent e5 = new Intent(Intent.ACTION_MAIN);
                e5.addCategory(Intent.CATEGORY_LAUNCHER);
                e5.setComponent(cn2);
                startActivity(e5);
                break;

            case R.id.btn_material:
                // Material Design 라이브러리의 공식 홈페이지를 호출한다.
                Uri uri2 = Uri.parse("https://material.io");
                Intent i25 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(i25);
                break;

            case R.id.btn_exit:
                moveTaskToBack(true);
                finishAndRemoveTask();
                android.os.Process.killProcess(android.os.Process.myPid());
                // 어플을 종료하되 Task List에 어플이 남도록 한다. (어플을 완전히 종료하는 방안은 아니다.)
                // finishAndRemoveTask() --> finish() 대신에 이 함수를 호출할 경우 어플을 완전히 종료시키게 된다.
                break;
    }
    }

    public void anim()
    {
        if(isfao)
        {   // --> isfao = 버튼이 올라갈 경우에 대한 Boolean형 변수 isfao를 앞에서 선언하였다.
            // --> isfao의 논리값을 false로 설정하였다. (private Boolean isfao = false;)
            // --> FloatingActionButton이 내려갈 경우
            FM2.startAnimation(fac);
            // res/anim/ 경로에 float_close.xml 파일을 생성하여
            // 버튼이 내려갈 경우에 대한 애니메이션 효과를 설정한다.
            // startAnimation() --> 앞에서 설정한 애니메이션 효과를 실행한다.
            FM1.startAnimation(fac);
            FM2.setClickable(false);
            // 버튼이 내려갈 경우에는 버튼을 비활성화 하도록 설정한다.
            FM1.setClickable(false);
            isfao = false;
        }
        else
            {// FloatingActionButton이 올라갈 경우 (private Boolean isfao = true)
                FM2.startAnimation(fao);
                // 마찬가지로 float_open.xml 파일을 생성하여
                // 버튼이 올라갈 경우에 대한 애니메이션 효과를 설정한다.
                FM1.startAnimation(fao);
                FM2.setClickable(true);
                // 버튼이 올라갈 경우에는 버튼을 활성화 하도록 한다.
                FM1.setClickable(true);
                isfao = true;
            }
    }
}
