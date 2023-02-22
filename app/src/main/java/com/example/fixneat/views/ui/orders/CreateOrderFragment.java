package com.example.fixneat.views.ui.orders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fixneat.Model.Address;
import com.example.fixneat.Model.Client;
import com.example.fixneat.Model.DateTime;
import com.example.fixneat.Model.Order;
import com.example.fixneat.R;
import com.example.fixneat.databinding.FragmentCreateOrderBinding;

import java.util.Calendar;


public class CreateOrderFragment extends Fragment {

    private FragmentCreateOrderBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        initButtons();

        return binding.getRoot();
    }

    private void initButtons() {

        binding.dateBTN.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        binding.timeBTN.setOnClickListener(v -> {
            showTimePickerDialog();
        });

        binding.saveBTN.setOnClickListener(v -> {
            createOrder();
        });

        binding.cancelBTN.setOnClickListener(v -> {
            getFragmentManager().popBackStackImmediate();
        });
    }

    private void createOrder() {

        // TODO: validate all fields.
        Address address = new Address()
                .setCity(binding.cityET.getText().toString())
                .setStreet(binding.streetET.getText().toString())
                .setHomeNum(Integer.parseInt(binding.homeNoET.getText().toString()))
                .setAptNum(Integer.parseInt(binding.aptNoET.getText().toString()));

        Client client = new Client()
                .setClientID()
                .setAddress(address)
                .setFirstName(binding.firstNameET.getText().toString())
                .setLastName(binding.lastNameET.getText().toString())
                .setPhoneNumber(binding.phoneNumET.getText().toString())
                .setEmailAddress(binding.emailET.getText().toString());

        DateTime dateTime = new DateTime()
                .setDate(binding.dateBTN.getText().toString())
                .setTime(binding.timeBTN.getText().toString());

        Order newOrder = new Order()
                .setCurrentStatus(Order.STATUS_CREATED)
                .setMeasureVisitD_T(dateTime)
                .setClient(client)
                .setNote(binding.noteET.getText().toString());


        HomeFragment fragment = new HomeFragment();

        Bundle bundle = new Bundle();

        bundle.putParcelable(HomeFragment.NEW_ORDER, newOrder);
        fragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_menu, fragment).commit();
    }

    private boolean inputValid() {
        //TODO
        return true;
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

    //    // validating email id
//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

}