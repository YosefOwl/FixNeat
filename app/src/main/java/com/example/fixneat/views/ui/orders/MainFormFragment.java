package com.example.fixneat.views.ui.orders;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.fixneat.Model.DateTime;
import com.example.fixneat.Model.Job;
import com.example.fixneat.Model.Order;
import com.example.fixneat.R;
import com.example.fixneat.Repository.OrderRepo;
import com.example.fixneat.Utils.SignalUser;
import com.example.fixneat.databinding.FragmentMainFormBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainFormFragment extends Fragment {

    public static final String ORDER_TO_EDIT = "ORDER_TO_EDIT";
    public static final String EDITED_ORDER = "EDITED_ORDER"; //

    private ArrayAdapter<String> adapterJobAdapterDD;

    private FragmentMainFormBinding binding;
    private ArrayList<Job> jobs = new ArrayList<>();
    private String selectedJob;
    private Order order;

    public MainFormFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getArguments() != null) {

            order= (Order)getArguments().getParcelable(EDITED_ORDER);
            if (order == null) {
                order = (Order) getArguments().getParcelable(ORDER_TO_EDIT);
            }
            if (order == null) {
                SignalUser.getInstance().toast("MainFormFragment get  NULL");
                getFragmentManager().popBackStackImmediate();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMainFormBinding.inflate(inflater, container, false);
        initDropsDownAdapter();
        initButtons();

        return binding.getRoot();
    }

    private void initButtons() {

        binding.dateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        binding.timeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        binding.addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedJob == null)
                    return;

                Bundle bundle;
                switch(selectedJob) {
                    case "Balcony":
                        BalconyFormFragment balconyFormFragment = new BalconyFormFragment();
                        bundle = getBundle(BalconyFormFragment.BALCONY);
                        balconyFormFragment.setArguments(bundle);
                        replaceFragment(balconyFormFragment);
                        break;
                    case "Pergola":
                        PergolaFormFragment pergolaFormFragment = new PergolaFormFragment();
                        bundle = getBundle(PergolaFormFragment.PERGOLA);
                        pergolaFormFragment.setArguments(bundle);
                        replaceFragment(pergolaFormFragment);
                        break;
                    case "Window":
                        WindowFormFragment windowFormFragment = new WindowFormFragment();
                        bundle = getBundle(WindowFormFragment.WINDOW);
                        windowFormFragment.setArguments(bundle);
                        replaceFragment(windowFormFragment);
                        break;
                    case "Door":
                        DoorFormFragment doorFormFragment = new DoorFormFragment();
                        bundle = getBundle(DoorFormFragment.DOOR);
                        doorFormFragment.setArguments(bundle);
                        replaceFragment(doorFormFragment);
                        break;
                    default:
                        // Do nothing
                }
            }
        });

        binding.saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEditedOrder();
            }
        });

        binding.cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO show some dialog that all added data not be saved

                // if (false) No back
                if (true) { // choose yes
                    order.setNote(null);
                    order.setInstallVisitD_T(null);
                    order.setJobs(new ArrayList<>());
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });
    }

    private void initDropsDownAdapter() {
        adapterJobAdapterDD = new ArrayAdapter<String>(this.getContext(), R.layout.dropdown_list_item,
                OrderHelper.getJobsTypes());
        binding.jobTypeDropDown.setAdapter(adapterJobAdapterDD);
        binding.jobTypeDropDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedJob = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void createEditedOrder() {

        String date = binding.dateBTN.getText() != null ? String.valueOf(binding.dateBTN.getText()) : "";
        String time = binding.timeBTN.getText() != null ? String.valueOf(binding.timeBTN.getText()) : "";
        DateTime dateTime = new DateTime().setTime(time).setDate(date);

        String note = binding.noteET.getText() != null ? String.valueOf(binding.noteET.getText()): "";

        order.setCurrentStatus(Order.STATUS_MEASURED);
        order.setInstallVisitD_T(dateTime).setNote(note);



        DisplayOrderFragment fragment = new DisplayOrderFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable(DisplayOrderFragment.ORDER_F_CHILD_DOF, order);
        fragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment)
                .commit();
    }

    private Bundle getBundle(String key) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(key, order);
        return bundle;
    }

    private void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_content_menu, fragment).addToBackStack(null)
                .commit();
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                binding.dateBTN.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                binding.timeBTN.setText(time);
            }
        },
                hour, minute, false);
        timePickerDialog.show();
    }
}
