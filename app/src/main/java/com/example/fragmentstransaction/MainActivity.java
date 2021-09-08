package com.example.fragmentstransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnAddA, mBtnRemoveA,mBtnReplaceAWithBWithBackStack, mBtnReplaceAWithBWithoutBackStack, mBtnAddB,mBtnRemoveB,mBtnReplaceBWithA;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager=getSupportFragmentManager();
    }

    private void initViews() {
        mBtnAddA=findViewById(R.id.btnAddA);
        mBtnAddB=findViewById(R.id.btnAddB);
        mBtnRemoveA=findViewById(R.id.btnRemoveA);
        mBtnRemoveB=findViewById(R.id.btnRemoveB);
        mBtnReplaceAWithBWithBackStack=findViewById(R.id.btnReplaceAWithBackStack);
        mBtnReplaceAWithBWithoutBackStack=findViewById(R.id.btnReplaceAWithBWithoutBackstack);
        mBtnReplaceBWithA=findViewById(R.id.btnReplaceBWithA);

        mBtnAddA.setOnClickListener(this);
        mBtnAddB.setOnClickListener(this);
        mBtnRemoveA.setOnClickListener(this);
        mBtnRemoveB.setOnClickListener(this);
        mBtnReplaceAWithBWithBackStack.setOnClickListener(this);
        mBtnReplaceAWithBWithoutBackStack.setOnClickListener(this);
        mBtnReplaceBWithA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnAddA:
                addA();
                break;

            case R.id.btnAddB:
                addB();
                break;

            case R.id.btnRemoveA:
                removeA();
                break;

            case R.id.btnRemoveB:
                removeB();
                break;

            case R.id.btnReplaceAWithBackStack:
                replaceAWithBWithBackStack();
                break;

            case R.id.btnReplaceAWithBWithoutBackstack:
                replaceAWithBWithoutBackStack();
                break;
            case R.id.btnReplaceBWithA:
                replaceBWithA();
                break;
        }
    }

    private void replaceBWithA() {
        FragmentA fragmentA=new FragmentA();
        fragmentManager.beginTransaction().replace(R.id.flContainer,fragmentA,"FragmentA").commit();
    }

    private void replaceAWithBWithoutBackStack() {
        FragmentB fragmentB=new FragmentB();
        fragmentManager.beginTransaction().replace(R.id.flContainer,fragmentB,"FragmentB").commit();
    }

    private void replaceAWithBWithBackStack() {
        FragmentB fragmentB=new FragmentB();
        fragmentManager.beginTransaction().replace(R.id.flContainer,fragmentB,"FragmentB").addToBackStack("fragB").commit();
    }

    private void removeB() {
        FragmentB fragmentB= (FragmentB) fragmentManager.findFragmentByTag("FragmentB");
        fragmentManager.beginTransaction().remove(fragmentB).commit();

    }

    private void removeA() {

        FragmentA fragmentA= (FragmentA) fragmentManager.findFragmentByTag("FragmentA");
        if (fragmentA!=null) {
            fragmentManager.beginTransaction().remove(fragmentA).commit();
        }
    }

    private void addB() {
        FragmentB fragmentB=new FragmentB();
        fragmentManager.beginTransaction().add(R.id.flContainer,fragmentB,"FragmentB").commit();
    }

    private void addA() {
        FragmentA fragmentA=new FragmentA();
        fragmentManager.beginTransaction().add(R.id.flContainer,fragmentA,"FragmentA").commit();
    }
}