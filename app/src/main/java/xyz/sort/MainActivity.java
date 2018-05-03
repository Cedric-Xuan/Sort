package xyz.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.promeg.pinyinhelper.Pinyin;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<User> list;
    private TextView tx;
    private int p=0;
    private TextView num;
    private Button searchBtn;
    private EditText searchLine;
    private String resultString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx=(TextView) findViewById(R.id.tx);
        num=(TextView) findViewById(R.id.num);
        searchBtn=(Button) findViewById(R.id.sreachBtn);
        searchLine=(EditText) findViewById(R.id.searchLine);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText=searchLine.getText().toString().trim();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).name.contains(searchText)){
                       resultString+=list.get(i).name;
                    }
                }

                Toast.makeText(getBaseContext(),resultString,Toast.LENGTH_SHORT).show();

            }
        });




        list=new ArrayList<User>();

        for(int i=0;i<10;i++) {

            list.add(new User("李四", 2));
            list.add(new User("c", 2));
            list.add(new User("王五", 2));
            list.add(new User("孙七", 2));
//            list.add(new User("周八", 2));
//            list.add(new User("b", 2));
//            list.add(new User("赵六", 2));
//            list.add(new User("A", 2));
//            list.add(new User("B", 2));
//            list.add(new User("h", 2));
//            list.add(new User("陈一", 2));
//            list.add(new User("黄二", 2));
//            list.add(new User("张三", 2));
//            list.add(new User("风啊风", 2));
//            list.add(new User("S", 2));
//            list.add(new User("x", 2));
//            list.add(new User("xyz",2));
//            list.add(new User("雪",2));
//            list.add(new User("周5胡", 2));
//            list.add(new User("吴九", 2));
//            list.add(new User("郑十", 2));
//            list.add(new User("哦哦哦", 2));
//            list.add(new User("594", 2));
//            list.add(new User("495", 2));
        }



        ComparatorUser comparatorUser=new ComparatorUser();

        Collections.sort(list,comparatorUser);


        for (int i=0;i<list.size();i++){
            tx.append(list.get(i).getName()+"\n");
        }
        num.setText(p+"");




    }


    class ComparatorUser implements Comparator {
            Collator collator = Collator.getInstance(java.util.Locale.CHINA);
            public int compare(Object arg0, Object arg1) {

                int flag;
                User user0 = (User) arg0;
                User user1 = (User) arg1;
                String name0 = (user0.getName());
                String name1 = (user1.getName());

                int age0 = user0.getAge();
                int age1 = user1.getAge();
                //先比较名字再比较年龄

                String name00 = "";
                String name11 = "";


                if(Character.isDigit(name0.charAt(0))&&!Character.isDigit(name1.charAt(0))){
                    flag=1;
                }else if(Character.isDigit(name1.charAt(0))&&!Character.isDigit(name0.charAt(0))){
                    flag=-1;
                }else{


                    for (int i = 0; i < 3; i++) {
                        if (i < name0.length()) {
                            if (Pinyin.isChinese(name0.charAt(i))) {
                                String letter = Pinyin.toPinyin(name0.charAt(i));//取单字拼音
                                name00 += letter.charAt(0);
                            } else {
                                String letter = "";
                                letter += name0.charAt(i);
                                name00 += letter.toUpperCase();
                            }
                        } else {
                            break;
                        }
                    }


                    for (int i = 0; i < 3; i++) {
                        if (i < name1.length()) {

                            if (Pinyin.isChinese(name1.charAt(i))) {
                                String letter = Pinyin.toPinyin(name1.charAt(i));//取单字拼音
                                name11 += letter.charAt(0);
                            } else {
                                String letter = "";
                                letter += name1.charAt(i);
                                name11 += letter.toUpperCase();
                            }
                        } else {
                            break;
                        }
                    }
                    Log.e("pinyin", name00 + " " + name11);


                    flag = collator.getCollationKey(name00).compareTo(collator.getCollationKey(name11));
                    p++;
                }
                    return flag;

//                int flag=collator.getCollationKey(ChineseToSpell.getThreeFirstSpell(name0)).compareTo(collator.getCollationKey(ChineseToSpell.getThreeFirstSpell(name1)));




            }

//        private String toUTF_8(String str) {
//            try {
//                return new String(str.getBytes(), "ISO-8859-1");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
    }

    class User {
        private String name;
        private int age;

        public User(String name, int age){
            this.name=name;
            this.age=age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

    }

}
