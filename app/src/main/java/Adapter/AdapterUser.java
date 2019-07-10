package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.googleservicefoody.R;

import java.util.List;

import Model.UserModel;

public class AdapterUser extends ArrayAdapter<UserModel> {

    Context context;
    int resource;
    List<UserModel> objects;

    public AdapterUser(Context context, int resource, List<UserModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(this.resource, null);
        TextView txtTen = row.findViewById(R.id.txtTen);
        TextView txtTuoi = row.findViewById(R.id.txtTuoi);
        CheckBox chkGioitinh = row.findViewById(R.id.chkGioitinh);
        UserModel userModel = this.objects.get(position);
        chkGioitinh.setChecked(userModel.isSex());
        txtTen.setText(userModel.getTen());
        txtTuoi.setText(userModel.getTuoi().toString());
        return row;
    }

}
