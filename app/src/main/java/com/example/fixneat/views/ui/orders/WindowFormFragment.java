package com.example.fixneat.views.ui.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.fixneat.Model.Door;
import com.example.fixneat.Model.Job;
import com.example.fixneat.Model.Order;
import com.example.fixneat.Model.Window;
import com.example.fixneat.R;
import com.example.fixneat.Utils.SignalUser;
import com.example.fixneat.databinding.FragmentWindowFormBinding;

import java.util.ArrayList;


public class WindowFormFragment extends Fragment {


    public static final String WINDOW = "WINDOW";
    private Order order;
    private ArrayAdapter<String> adapterColorDD;
    private ArrayAdapter<String> adapterProfileDD;
    private ArrayAdapter<String> adapterGlassDD;
    private ArrayAdapter<String> adapterWindowTypeDD;
    private String color;
    private String profile;
    private String windowType;
    private String glassType;


    private FragmentWindowFormBinding binding;
    public WindowFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWindowFormBinding.inflate(inflater, container, false);
        order = (Order)getArguments().getParcelable(WINDOW);

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
                createWindow();
        });


        binding.cancelBTN.setOnClickListener(v -> {
            getFragmentManager().popBackStackImmediate();
        });

    }

    private void createWindow() {

        String doorType = this.windowType != null ? this.windowType : "";
        String glassType = this.glassType != null ? this.glassType : "";
        String profileType = this.profile != null ? this.profile : "";
        String color = this.color != null ? this.color : "";
        String description = binding.itemDescriptionET.getText().toString().isEmpty() ? "" : binding.itemDescriptionET.getText().toString();
        double length = binding.lengthET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.lengthET.getText().toString());
        double width = binding.widthET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.widthET.getText().toString());
        double height = binding.heightET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.heightET.getText().toString());
        double price = binding.priceET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.priceET.getText().toString());
        double cost = binding.costET.getText().toString().isEmpty() ? 0 : Double.parseDouble(binding.costET.getText().toString());
        String note = binding.noteET.getText().toString().isEmpty() ? "" : binding.noteET.getText().toString();
        boolean includeMosquitoNet = binding.mosquitoNetCB.isChecked();

        Job window = new Window()
                .setWindowType(windowType)
                .setGlassType(glassType)
                .setProfileType(profileType)
                .setColor(color)
                .setIncludeMosquitoNet(includeMosquitoNet)
                .setDescription(description)
                .setLength(length)
                .setHeight(height)
                .setWidth(width)
                .setPrice(price)
                .setCost(cost)
                .setNote(note);


        ArrayList<Job> jobs;
        if (order.getJobs() == null)
            jobs = new ArrayList<>();
        else
            jobs= order.getJobs();

        jobs.add(window);
        order.setJobs(jobs);

        Bundle bundle = new Bundle();
        MainFormFragment fragment = new MainFormFragment();

        bundle.putParcelable(MainFormFragment.EDITED_ORDER, order);
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment)
                .commit();

    }

    private boolean inputValid() {
        //TODO
        return true;
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

        adapterGlassDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getGlassTypes());
        binding.glassTypeDropDown.setAdapter(adapterGlassDD);
        binding.glassTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                glassType = adapterView.getItemAtPosition(i).toString();
            }
        });

        adapterWindowTypeDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getWindowTypes());
        binding.windowTypeDropDown.setAdapter(adapterWindowTypeDD);
        binding.windowTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                windowType = adapterView.getItemAtPosition(i).toString();
            }
        });

        adapterProfileDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getProfileTypes());
        binding.profileTypeDropDown.setAdapter(adapterColorDD);
        binding.profileTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                profile = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

}