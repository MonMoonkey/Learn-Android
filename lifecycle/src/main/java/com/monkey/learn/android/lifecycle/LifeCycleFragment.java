package com.monkey.learn.android.lifecycle;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LifeCycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LifeCycleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;


    private Button mButton;
    private TextView mTextView;
    private LinearLayout mLinearLayout;


    public LifeCycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LifeCycleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LifeCycleFragment newInstance(String param1, int param2) {
        LifeCycleFragment fragment = new LifeCycleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("LifeCycle", "Fragment.onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }

        Log.e("LifeCycle", "Fragment.onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("LifeCycle", "Fragment.onCreateView");
        View view = inflater.inflate(R.layout.fragment_life_cycle, container, false);
        mButton = view.findViewById(R.id.button_fragment);
        mButton.setVisibility(mParam2);

        mTextView=view.findViewById(R.id.text_fragment_lifecycle);
        mTextView.setText(mParam1);

//        mLinearLayout=view.findViewById(R.id.linearlayout_fragment_lifecycle);
//        mLinearLayout.setBackgroundColor(mParam2);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("LifeCycle", "Fragment.onActivityCreated");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("life", "xixixi");
                Intent intent = new Intent();
                intent.setClass(getActivity(), TestActivity.class);
                startActivityForResult(intent, 4);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("LifeCycle", "Fragment.onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("LifeCycle", "Fragment.onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("LifeCycle", "Fragment.onPause");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("LifeCycle", "Fragment.onStop");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("LifeCycle", "Fragment.onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LifeCycle", "Fragment.onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("LifeCycle", "Fragment.onDetach");

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("LifeCycle", "Fragment得到返回值: requestCode=" + String.valueOf(requestCode) + " resultCode=" + String.valueOf(resultCode));

    }
}
