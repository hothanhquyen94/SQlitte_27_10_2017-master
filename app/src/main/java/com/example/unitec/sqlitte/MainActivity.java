package com.example.unitec.sqlitte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unitec.sqlitte.Adapter.CustomAdapter;
import com.example.unitec.sqlitte.DAO.NhanVienDAO;
import com.example.unitec.sqlitte.DTO.NhanVienDTO;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button btnThem;
    EditText editThem;
    NhanVienDAO nhanVienDAO;
    ListView listView;
    CustomAdapter adapter;
    ArrayList<NhanVienDTO> arrayListNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThem = (Button) findViewById(R.id.themNhanVien);
        editThem = (EditText) findViewById(R.id.tenNhanVien);
        listView = (ListView) findViewById(R.id.listNhanVien);
        arrayListNhanVien = new ArrayList<NhanVienDTO>();
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienDAO.open();

        arrayListNhanVien = (ArrayList<NhanVienDTO>) nhanVienDAO.LayDanhSachNhanVien();


        adapter = new CustomAdapter(MainActivity.this,R.layout.customlist,arrayListNhanVien);
        listView.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                NhanVienDTO nhanVienDTO = new NhanVienDTO();
                String ten = editThem.getText().toString();
                nhanVienDTO.setTenNhanvien(editThem.getText().toString() );
                arrayListNhanVien.add(nhanVienDTO);
                boolean kt = nhanVienDAO.themNhanVien(nhanVienDTO);
                if(kt){
                    Toast.makeText(MainActivity.this,"OK! SUCCESS ",Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this,"ERRRRROORRRR",Toast.LENGTH_LONG).show();
                }
            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.xoa:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                NhanVienDTO nhanVienDTO =arrayListNhanVien.get(menuInfo.position);
                nhanVienDAO.XoaNhanVien(nhanVienDTO);
                adapter.remove(nhanVienDTO);
                adapter.notifyDataSetChanged();
                break;
            case R.id.sua:

        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contextmenu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
