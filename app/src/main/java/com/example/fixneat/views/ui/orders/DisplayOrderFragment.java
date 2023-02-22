package com.example.fixneat.views.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;

import com.example.fixneat.Adapters.DisplayOrderListAdapter;
import com.example.fixneat.Model.Job;
import com.example.fixneat.Model.Order;
import com.example.fixneat.R;
import com.example.fixneat.Repository.OrderRepo;
import com.example.fixneat.databinding.FragmentDisplayOrderBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayOrderFragment extends Fragment {

    public static final String ORDER_F_PARENT_DOF = "ORDER_F_PARENT_DOF";
    public static final String ORDER_F_CHILD_DOF = "ORDER_F_CHILD_DOF";

    private FragmentDisplayOrderBinding binding;
    private final List<String> groupList = Arrays.asList("Client Details", "Visit Time and Date","The Order", "Notes");
    private Map<String, List<String>> orderDataCollection;
    private ExpandableListAdapter expandableListAdapter;

    private Order order;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDisplayOrderBinding.inflate(inflater, container, false);


        if (getArguments() != null) {
            order = (Order) getArguments().getParcelable(ORDER_F_PARENT_DOF);
            if (order == null) {
                order = (Order) getArguments().getParcelable(ORDER_F_CHILD_DOF);
                if (order != null && order.getJobs() != null)
                    OrderRepo.getInstance().updateOrder(order, false);
            }
            if (order == null) {
                getFragmentManager().popBackStackImmediate();
            }
        }

        createCollection();
        initButtons();

        expandableListAdapter = new DisplayOrderListAdapter(requireContext(), groupList, orderDataCollection);
        binding.orderItemsELV.setAdapter(expandableListAdapter);

        updateUI();
        return binding.getRoot();
    }

    private void updateUI() {

        String status = order.getCurrentStatus();
        if (status.equals(Order.STATUS_MEASURED)){
            binding.ImageSt1.setImageResource(R.drawable.status_measure);
            binding.nextStateBTN.setText("Next Status");
            binding.nextStateBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    order.setCurrentStatus(Order.STATUS_PREPEND);
                    OrderRepo.getInstance().updateOrder(order, true);
                    updateUI();
                }
            });
        }
        else if (status.equals(Order.STATUS_PREPEND)){
            binding.ImageSt1.setImageResource(R.drawable.status_measure);
            binding.ImageSt2.setImageResource(R.drawable.status_prepare);

            binding.nextStateBTN.setText("Next Status");
            binding.nextStateBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    order.setCurrentStatus(Order.STATUS_INSTALLED);
                    binding.nextStateBTN.setEnabled(false);
                    OrderRepo.getInstance().updateOrder(order, true);
                    updateUI();
                }
            });
        }
        else if (status.equals(Order.STATUS_INSTALLED)){
            binding.ImageSt1.setImageResource(R.drawable.status_measure);
            binding.ImageSt2.setImageResource(R.drawable.status_prepare);
            binding.ImageSt3.setImageResource(R.drawable.status_install);
            binding.nextStateBTN.setText("Next Status");
            binding.nextStateBTN.setEnabled(false);
        }
        else {
            binding.nextStateBTN.setText("Add Jobs");
        }
    }

    private void initButtons() {

        String status = order.getCurrentStatus();
        if (status.equals(Order.STATUS_MEASURED)){
            binding.nextStateBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    order.setCurrentStatus(Order.STATUS_PREPEND);
                    OrderRepo.getInstance().updateOrder(order, true);
                    updateUI();
                }
            });
        }
        else if (status.equals(Order.STATUS_PREPEND)){
            binding.nextStateBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    order.setCurrentStatus(Order.STATUS_INSTALLED);
                    OrderRepo.getInstance().updateOrder(order, true);
                    updateUI();
                }
            });
        }
        else if (status.equals(Order.STATUS_CREATED)){
            binding.nextStateBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MainFormFragment fragment = new MainFormFragment();
                    Bundle bundle = new Bundle();

                    bundle.putParcelable(MainFormFragment.ORDER_TO_EDIT, order);
                    fragment.setArguments(bundle);

                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment_content_menu, fragment).addToBackStack(null)
                            .commit();
                }
            });
        }

        binding.backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeFragment fragment = new HomeFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_menu, fragment)
                        .commit();
            }
        });

        binding.orderItemsELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    binding.orderItemsELV.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });

        binding.orderItemsELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return true;
            }
        });
    }

    private void createCollection() {
        if (order == null)
            return;

        String client = order.getClient() == null ? "client is null" : order.getClient().toString();
        List<String> clientDetails = Arrays.asList(client);

        String fDate = order.getMeasureVisitD_T() == null ? "fDate is null" : order.getMeasureVisitD_T().toString();
        String lDate = order.getInstallVisitD_T() == null ? "lDate is null" : order.getInstallVisitD_T().toString();

        List<String> visitTimeAndDate = Arrays.asList("Measurement visit : "  + fDate,
                "Installation visit : " + lDate);
        List<String> theOrder = new ArrayList<>();

        if (order.getJobs() == null || order.getJobs().size() == 0 ){
            theOrder.add("Order is Empty, you need to fill it");
        }
        else {
            List<Job> jobs = order.getJobs();
            for (Job job: jobs) {
                theOrder.add(job.toString());
            }
        }

        String note = order.getNote() == null ? "Note is null" :order.getNote();
        List<String> notes = Arrays.asList(note);

        orderDataCollection = new HashMap<String, List<String>>();
        for(String group : groupList) {

            if (group.equals("Client Details")){
                orderDataCollection.put(group, clientDetails);
            } else if (group.equals("Visit Time and Date"))
                orderDataCollection.put(group, visitTimeAndDate);
            else if (group.equals("The Order"))
                orderDataCollection.put(group, theOrder);
            else
                orderDataCollection.put(group, notes);
        }
    }

}