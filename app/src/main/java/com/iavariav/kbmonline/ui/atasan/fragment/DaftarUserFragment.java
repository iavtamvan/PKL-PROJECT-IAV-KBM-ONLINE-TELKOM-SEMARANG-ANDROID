package com.iavariav.kbmonline.ui.atasan.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.iavariav.kbmonline.R;
import com.iavariav.kbmonline.ui.atasan.presenter.DaftarUserPresenter;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarUserFragment extends Fragment {

    private DaftarUserPresenter daftarUserPresenter;
    private EditText edtNamaUser;
    private EditText edtNik;
    private EditText edtPassword;
    private Button btnDaftarkan;

    private String keyEncrypt;

    public DaftarUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daftar_user, container, false);
        initView(view);
        randomAlphabet();
        daftarUserPresenter = new DaftarUserPresenter();
        btnDaftarkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtNamaUser.getText().toString().isEmpty() || edtNik.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Lengkapi data terlebih dahulu.", Toast.LENGTH_SHORT).show();
                } else {
                    daftarUserPresenter.daftarkanUser(getActivity(), edtNamaUser.getText().toString().trim(), edtNik.getText().toString().trim(),
                            edtPassword.getText().toString().trim(), "NULL", keyEncrypt);
                    edtNamaUser.setText("");
                    edtNik.setText("");
                    edtPassword.setText("");
                }
            }
        });
        return view;
    }

    private void randomAlphabet(){
        Random random = new Random();
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
// convert the string to a char array
        char[] alphabetArr = alphabet.toCharArray();
        //set the max number for the int to be the length of the string.
        int randomInt = random.nextInt(alphabet.length());
        System.out.println(randomInt + " :: " + alphabetArr[randomInt]);
        keyEncrypt = "enc"+ alphabetArr[randomInt] + alphabetArr[randomInt] + alphabetArr[randomInt] + "kbmtelkom";

    }

    private void initView(View view) {
        edtNamaUser = view.findViewById(R.id.edt_nama_user);
        edtNik = view.findViewById(R.id.edt_nik);
        edtPassword = view.findViewById(R.id.edt_password);
        btnDaftarkan = view.findViewById(R.id.btn_daftarkan);
    }
}
