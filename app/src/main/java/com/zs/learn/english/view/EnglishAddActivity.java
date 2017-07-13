package com.zs.learn.english.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chenenyu.router.annotation.Route;
import com.zs.learn.R;
import com.zs.learn.base.BaseActivity;
import com.zs.learn.english.model.EnglishWord;
import com.zs.learn.english.model.EnglishWordXml;
import com.zs.learn.retrofit.EnglishWordApi;

import org.greenrobot.eventbus.EventBus;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by shao on 2017/7/8.
 * 金山词霸查词url:http://dict-co.iciba.com/api/dictionary.php?w=go&key=ACFC93FF5988E38CCD2BE9A9277B9A74
 */
@Route("english_add")
public class EnglishAddActivity extends BaseActivity{

    EditText englishEdit;
    TextView textWord,textPhonogram,textAnnotation;
    Button buttonSearch,buttonSave;

    EnglishWord mEnglishWord;
    @Override
    protected void initView() {
        //YouDaoApplication.init(getApplicationContext(), "02120e853427b161");
        setContentView(R.layout.english_activity_addword);
        englishEdit=(EditText)findViewById(R.id.english_activity_addword_edittext_word);
        textWord=(TextView)findViewById(R.id.english_activity_addword_textview_word);
        textPhonogram=(TextView)findViewById(R.id.english_activity_addword_textview_phonogram);
        textAnnotation=(TextView)findViewById(R.id.english_activity_addword_textview_annotation);
        buttonSearch=(Button)findViewById(R.id.english_activity_addword_button_search);
        buttonSave=(Button)findViewById(R.id.english_activity_addword_button_save);
        buttonSearch.setOnClickListener(
                view->{
                    String english=englishEdit.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://dict-co.iciba.com/api/")
                            .build();
                    EnglishWordApi api= retrofit.create(EnglishWordApi.class);
                    Call<ResponseBody> call = api.getEnglishWord(english);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            EnglishWordXml xml=new EnglishWordXml();
                            try {
                                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                                InputStream is=new ByteArrayInputStream(response.body().bytes());
                                parser.parse(is,xml);
                                mEnglishWord=xml.englishWord;
                                textWord.setText(mEnglishWord.word);
                                textPhonogram.setText(mEnglishWord.phonogram);
                                textAnnotation.setText(mEnglishWord.annotation);
                                buttonSearch.setVisibility(View.GONE);
                                buttonSave.setVisibility(View.VISIBLE);
                            } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                            } catch (SAXException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
        );
        buttonSave.setOnClickListener(view->{
            if(mEnglishWord!=null){
                mEnglishWord.saveAsync().listen(success -> {
                    EventBus.getDefault().post(mEnglishWord);
                    finish();
                });
            }

        });
    }

    @Override
    protected void initData() {

    }
}
