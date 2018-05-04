package com.monkey.learn.android.storage.file;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.monkey.learn.android.storage.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileStorageActivity extends AppCompatActivity {
    /*
    1） File file = new File ("hello.txt");
    FileInputStream in=new FileInputStream(file);

    2） File file = new File ("hello.txt");
    FileInputStream in=new FileInputStream(file);
    InputStreamReader inReader=new InputStreamReader(in);
    BufferedReader bufReader=new BufferedReader(inReader);

    3） File file = new File ("hello.txt");
    FileReader fileReader=new FileReader(file);
    BufferedReader bufReader=new BufferedReader(fileReader);
     */

    private EditText mEditText;
    private Button mButton_write;
    private Button mButton_read;

    private Button mButton_write_external;
    private Button mButton_read_external;

    private Button mButton_read_raw;
    private Button mButton_read_assets;
    private Button mButton_read_xml;

    private File mFile;

    private FileInputStream mFileInputStream;
    private InputStreamReader mInputStreamReader;

    private FileOutputStream mFileOutputStream;
    private OutputStreamWriter mOutputStreamWriter;

    private BufferedReader mBufferedReader;
    private BufferedWriter mBufferedWriter;

    private InputStream mInputStream;
    private OutputStream mOutputStream;

    /*
    FileInputStream, FileOutputStream,结合openFileOutput，openFileInput方法
     */

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        mEditText = findViewById(R.id.edit_text_file_storage);

        //不需要先定义File，只是用于测试
        //mFile=new File("FileDemo.txt");

        mButton_write = findViewById(R.id.button_write_file_storage);
        mButton_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //写入文件到本地
                try {
                    //文件存储在data/data/Package_Name中
                    mFileOutputStream = openFileOutput("FileDemo.txt", Context.MODE_PRIVATE);
                    mOutputStreamWriter = new OutputStreamWriter(mFileOutputStream);
                    mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                    mBufferedWriter.write(mEditText.getText().toString());
                    mBufferedWriter.flush();
                    Toast.makeText(FileStorageActivity.this, "文件写入完毕", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (mBufferedWriter != null) {
                            mBufferedWriter.close();
                        }
                        //顶层流关闭底层流自动关闭，所以不需要关闭OutputStreamReader
//                        if(mOutputStreamReader!=null){
//                            mOutputStreamReader.close();
//                        }
                        if (mFileOutputStream != null) {
                            mFileOutputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        mButton_read = findViewById(R.id.button_read_file_storage);
        mButton_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder mStringBuilder = new StringBuilder();
                try {
                    mFileInputStream = openFileInput("FileDemo.txt");
                    mInputStreamReader = new InputStreamReader(mFileInputStream);
                    mBufferedReader = new BufferedReader(mInputStreamReader);
                    String string = mBufferedReader.readLine();

                    while (string != null) {
                        mStringBuilder.append(string).append("\n");
                        string = mBufferedReader.readLine();
                    }
                    mEditText.setText(mStringBuilder.toString());
                    Toast.makeText(FileStorageActivity.this, "文件读出完毕", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (mBufferedReader != null) {
                            mBufferedReader.close();
                        }
                        if (mFileInputStream != null) {
                            mFileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        mButton_write_external = findViewById(R.id.button_write_to_external_file_storage);
        mButton_write_external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    File dir = Environment.getExternalStorageDirectory();   //获得sd卡内dir
                    Log.i("ExternalDir", dir.toString());
                    mFile = new File(dir, "ExternalFileDemo.txt");

                    try {
                        mFileOutputStream = new FileOutputStream(mFile);
                        mOutputStreamWriter = new OutputStreamWriter(mFileOutputStream);
                        mBufferedWriter = new BufferedWriter(mOutputStreamWriter);
                        mBufferedWriter.write(mEditText.getText().toString());
                        mBufferedWriter.flush();
                        Toast.makeText(FileStorageActivity.this, "文件写入完毕", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {

                        try {
                            if (mBufferedWriter != null) {
                                mBufferedWriter.close();
                            }
                            if (mFileOutputStream != null) {
                                mFileOutputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                } else {
                    Toast.makeText(FileStorageActivity.this, "外部存储（SD卡）不可用", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mButton_read_external = findViewById(R.id.button_read_from_external_file_storage);
        mButton_read_external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    File dir = Environment.getExternalStorageDirectory();
                    mFile = new File(dir, "ExternalFileDemo.txt");
                    StringBuilder mStringBuilder = new StringBuilder();
                    try {
                        mFileInputStream = new FileInputStream(mFile);
                        mInputStreamReader = new InputStreamReader(mFileInputStream);
                        mBufferedReader = new BufferedReader(mInputStreamReader);
                        String string = mBufferedReader.readLine();

                        while (string != null) {
                            mStringBuilder.append(string).append("\n");
                            string = mBufferedReader.readLine();
                        }
                        mEditText.setText(mStringBuilder.toString());
                        Toast.makeText(FileStorageActivity.this, "文件读出完毕", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {

                        try {
                            if (mBufferedReader != null) {
                                mBufferedReader.close();
                            }
                            if (mFileInputStream != null) {
                                mFileInputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    Toast.makeText(FileStorageActivity.this, "外部存储(SD卡）不可用", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mButton_read_raw = findViewById(R.id.button_read_raw_file_storage);
        mButton_read_raw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder mStringBuilder = new StringBuilder();
                try {
                    mInputStream = getResources().openRawResource(R.raw.raw_text);
                    mInputStreamReader = new InputStreamReader(mInputStream);
                    mBufferedReader = new BufferedReader(mInputStreamReader);
                    String string = mBufferedReader.readLine();

                    while (string != null) {
                        mStringBuilder.append(string).append("\n");
                        string = mBufferedReader.readLine();
                    }
                    mEditText.setText(mStringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mButton_read_assets = findViewById(R.id.button_read_assets_file_storage);
        mButton_read_assets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder mStringBuilder = new StringBuilder();
                try {
                    mInputStream = getAssets().open("assets_text");
                    mInputStreamReader = new InputStreamReader(mInputStream);
                    mBufferedReader = new BufferedReader(mInputStreamReader);
                    String string = mBufferedReader.readLine();

                    while (string != null) {
                        mStringBuilder.append(string).append("\n");
                        string = mBufferedReader.readLine();
                    }
                    mEditText.setText(mStringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mButton_read_xml = findViewById(R.id.button_read_xml_file_storage);
        mButton_read_xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder mStringBuilder = new StringBuilder();
                XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.xml_test);
                try {
                    int eventType = xmlResourceParser.getEventType();

                    while (eventType != XmlResourceParser.END_DOCUMENT) {

                        switch (xmlResourceParser.getEventType()) {
                            case XmlResourceParser.START_TAG:
                                String tag_name = xmlResourceParser.getName();
                                mStringBuilder.append("<" + tag_name + "\n");


                                if (tag_name.equals("begin")) {
                                    int count = xmlResourceParser.getAttributeCount();
                                    for (int i = 0; i < count; i++) {
                                        mStringBuilder.append(xmlResourceParser.getAttributeName(i)).append("=");
                                        mStringBuilder.append(xmlResourceParser.getAttributeValue(i));
                                    }
                                }

                                if (tag_name.equals("walls")) {
                                    int count = xmlResourceParser.getAttributeCount();
                                    for (int i = 0; i < count; i++) {
                                        mStringBuilder.append(xmlResourceParser.getAttributeName(i)).append("=");
                                        mStringBuilder.append(xmlResourceParser.getAttributeValue(i));
                                    }
                                }

                                if (tag_name.equals("wall")) {
                                    int count = xmlResourceParser.getAttributeCount();
                                    for (int i = 0; i < count; i++) {
                                        mStringBuilder.append(xmlResourceParser.getAttributeName(i)).append("=");
                                        mStringBuilder.append(xmlResourceParser.getAttributeValue(i));
                                    }
                                }
                                break;
                            case XmlResourceParser.END_TAG:
                                mStringBuilder.append("/>\n");
                                break;
                            case XmlResourceParser.TEXT:
                                mStringBuilder.append(xmlResourceParser.getText());
                                break;
                        }
                        xmlResourceParser.next();
                        eventType=xmlResourceParser.getEventType();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("SeeLog", mStringBuilder.toString());
            }
        });
    }
}
