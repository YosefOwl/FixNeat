package com.example.fixneat.views.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.example.fixneat.Model.Balcony;
import com.example.fixneat.Model.Job;
import com.example.fixneat.Model.Order;
import com.example.fixneat.Model.Window;
import com.example.fixneat.R;
import com.example.fixneat.Utils.SignalUser;
import com.example.fixneat.databinding.FragmentBalconyFormBinding;

import java.util.ArrayList;

public class BalconyFormFragment extends Fragment {

    public static final String BALCONY = "BALCONY";

    private FragmentBalconyFormBinding binding;

    private ArrayAdapter<String> adapterColorDD;
    private ArrayAdapter<String> adapterProfileDD;
    private ArrayAdapter<String> adapterMaterialDD;
    private String color;
    private String profile;
    private String material;

    private Order order;

    public BalconyFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentBalconyFormBinding.inflate(inflater, container, false);
        order = (Order)getArguments().getParcelable(BALCONY);
        if (order == null) {
            SignalUser.getInstance().toast("PergolaFormFragment get PERGOLA NULL");
            getFragmentManager().popBackStackImmediate();
        }


        initDropsDownAdapter();

        initButtons();
        return binding.getRoot();
    }

    private void initDropsDownAdapter() {

        adapterColorDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                                                    OrderHelper.getColorsTypes());
        binding.colorsDropDown.setAdapter(adapterColorDD);
        binding.colorsDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                color = adapterView.getItemAtPosition(i).toString();
            }
        });

        adapterMaterialDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getBalconyMaterialTypes());
        binding.materialTypeDropDown.setAdapter(adapterMaterialDD);
        binding.materialTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                material = adapterView.getItemAtPosition(i).toString();
            }
        });

        adapterProfileDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getProfileTypes());
        binding.profileTypeDropDown.setAdapter(adapterProfileDD);
        binding.profileTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                profile = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void initButtons() {

        binding.saveBTN.setOnClickListener(v -> {
            if (inputValid())
                createBalcony();
        });

        binding.cancelBTN.setOnClickListener(v -> {
            getFragmentManager().popBackStackImmediate();
        });

    }

    private boolean inputValid() {
        //TODO
        return true;
    }

    private void createBalcony() {

        String materialType = this.material != null ? this.material : "";
        String profileType = this.profile != null ? this.profile : "";
        String color = this.color != null ? this.color : "";
        String description = binding.itemDescriptionET.getText().toString().isEmpty() ? "" : binding.itemDescriptionET.getText().toString();
        double length = binding.lengthET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.lengthET.getText().toString());
        double width = binding.widthET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.widthET.getText().toString());
        double height = binding.heightET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.heightET.getText().toString());
        double price = binding.priceET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.priceET.getText().toString());
        double cost = binding.costET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.costET.getText().toString());
        String note = binding.noteET.getText().toString().isEmpty() ? "" : binding.noteET.getText().toString();
        boolean includeRetractableRoof = binding.retractableOofCB.isChecked();

        Job balcony = new Balcony()
                .setMaterialType(materialType)
                .setProfileType(profileType)
                .setColor(color)
                .setIncludeRetractableRoof(includeRetractableRoof)
                .setDescription(description)
                .setLength(length)
                .setHeight(height)
                .setWidth(width)
                .setPrice(price)
                .setCost(cost)
                .setNote(note);

//        Job balcony = new Balcony().setMaterialType("balcmt").setIncludeRetractableRoof(true)
//                .setDescription("descrperg").setCost(541).setHeight(2.5005).setLength(1.5).setNote("dnote").setPrice(500).setWidth(0.11);

        ArrayList<Job> jobs;
        if (order.getJobs() == null)
            jobs = new ArrayList<>();
        else
            jobs= order.getJobs();

        jobs.add(balcony);
        order.setJobs(jobs);

        Bundle bundle = new Bundle();
        MainFormFragment fragment = new MainFormFragment();

        bundle.putParcelable(MainFormFragment.EDITED_ORDER, order);
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment)
                .commit();


//        getParentFragmentManager().setFragmentResult(MainFormFragment.BALCONY, bundle);
//
//        getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_menu, getParentFragment()).commit();
    }
}
