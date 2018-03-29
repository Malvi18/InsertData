package com.example.webclues;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegFragment extends Fragment implements View.OnClickListener {

    EditText ed;
    Button btn,btnUpdate,btnDelete;
    Context context;
    ListView lv;
    List<User> users;
    private User nuser=null;
    public RegFragment() {
        // Required empty public constructoru
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reg, container, false);
        context=getActivity();
        lv=view.findViewById(R.id.lv);
        ed=view.findViewById(R.id.ed);
        btn=view.findViewById(R.id.btn);
        btnUpdate=view.findViewById(R.id.btnUpdate);
        btnDelete=view.findViewById(R.id.btnDelete);
        fetchData();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nuser=users.get(position);
                ed.setText(nuser.getEmail());
            }
        });
        btn.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        return view;
    }

    private void fetchData() {

        UserDao userDao=new UserDao();
        users=userDao.selectData(context);
        ArrayAdapter<User> adapter=new ArrayAdapter<User>(getActivity()
                ,android.R.layout.simple_list_item_1,users);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn:
                UserDao userDao=new UserDao();
                User user=new User();
                user.setEmail(ed.getText().toString());
                btn.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.VISIBLE);

                int count= (int) userDao.insertData(getActivity(),user);
                if(count>0){
                    Toast.makeText(context, "Insert Data", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "not", Toast.LENGTH_SHORT).show();
                }
                fetchData();
                break;

            case R.id.btnUpdate:
                UserDao userDao1=new UserDao();
                User user1=new User();
                nuser.setEmail(ed.getText().toString());
                userDao1.updateData(context,nuser);
                fetchData();
                break;

            case R.id.btnDelete:
                UserDao userDao2=new UserDao();
                int id=nuser.getId();
                userDao2.DeleteData(context,id);
                fetchData();
                break;
        }

    }
}
