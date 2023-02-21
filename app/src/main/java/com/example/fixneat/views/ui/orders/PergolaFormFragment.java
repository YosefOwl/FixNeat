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
import com.example.fixneat.Model.Pergola;
import com.example.fixneat.R;
import com.example.fixneat.Utils.SignalUser;
import com.example.fixneat.databinding.FragmentPergolaFormBinding;

import java.util.ArrayList;

public class PergolaFormFragment extends Fragment {
    public static final String PERGOLA = "PERGOLA";

    private FragmentPergolaFormBinding binding;

    private ArrayAdapter<String> adapterColorDD;
    private ArrayAdapter<String> adapterProfileDD;
    private ArrayAdapter<String> adapterMaterialDD;
    private String color;
    private String profile;
    private String material;

    private Order order;

    public PergolaFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPergolaFormBinding.inflate(inflater, container, false);

        order = (Order)getArguments().getParcelable(PERGOLA);
        if (order == null) {
            SignalUser.getInstance().toast("PergolaFormFragment get PERGOLA NULL");
            getFragmentManager().popBackStackImmediate();
        }


        initDropsDownAdapter();
        initButtons();
        return binding.getRoot();
    }

    private void initButtons() {

        binding.saveBTN.setOnClickListener(v -> {
            if (inputValid())
                createPergola();
        });


        binding.cancelBTN.setOnClickListener(v -> {
            getFragmentManager().popBackStackImmediate();
        });
    }

    private void createPergola() {

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
        boolean includeRainCover = binding.rainCB.isChecked();
        boolean includeShading = binding.shadowCB.isChecked();
        Job pergola = new Pergola()
                .setMaterialType(materialType)
                .setProfileType(profileType)
                .setColor(color)
                .setIncludeRainCover(includeRainCover)
                .setIncludeShading(includeShading)
                .setDescription(description)
                .setLength(length)
                .setHeight(height)
                .setWidth(width)
                .setPrice(price)
                .setCost(cost)
                .setNote(note);
//        Job pergola = new Pergola().setMaterialType("pmt").setColor("dc").setProfileType("dp").setIncludeShading(true).setIncludeRainCover(false)
//                .setDescription("descrperg").setCost(541).setHeight(2.5005).setLength(1.5).setNote("dnote").setPrice(500).setWidth(0.11);

        ArrayList<Job> jobs;
        if (order.getJobs() == null)
                jobs = new ArrayList<>();
        else
            jobs= order.getJobs();

        jobs.add(pergola);
        order.setJobs(jobs);

        Bundle bundle = new Bundle();
        MainFormFragment fragment = new MainFormFragment();

        bundle.putParcelable(MainFormFragment.EDITED_ORDER, order);
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment)
                .commit();

//        getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_menu, getParentFragment()).commit();
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
                OrderHelper.getPergolaMaterialTypes());
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

    private boolean inputValid() {
        //TODO
        return true;
    }

}