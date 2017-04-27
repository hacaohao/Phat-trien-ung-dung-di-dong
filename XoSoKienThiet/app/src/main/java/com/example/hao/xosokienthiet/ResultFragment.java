package com.example.hao.xosokienthiet;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hao.xosokienthiet.model.DataStore;
import com.example.hao.xosokienthiet.model.Prize;
import com.example.hao.xosokienthiet.utils.StringHelper;

import java.util.List;

public class ResultFragment extends Fragment {
    private static final String ARG_AREA = "area";
    private static final String ARG_DATE = "date";

    private RecyclerView mPrizeRecyclerView;
    private PrizeAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_template, container, false);
        mPrizeRecyclerView = (RecyclerView) view.findViewById(R.id.template_recycler_view);
        mPrizeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        String area = getArguments().getString(ARG_AREA);
        String date = getArguments().getString(ARG_DATE);

        DataStore dataStore = DataStore.getInstance();
        List<Prize> prizes = dataStore.getAllPrizesInDate(area, date);

        if(mAdapter == null){
            mAdapter = new ResultFragment.PrizeAdapter(prizes);
            mPrizeRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    public static ResultFragment newFragment(String area, String date){
        Bundle args = new Bundle();
        args.putString(ARG_AREA, area);
        args.putString(ARG_DATE, date);

        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private class PrizeHolder extends RecyclerView.ViewHolder {
        private TextView mPositionTextView;
        private TextView mNumberTextView;

        public PrizeHolder(View itemView) {
            super(itemView);
            mPositionTextView = (TextView) itemView.findViewById(R.id.text_view_result_position);
            mNumberTextView = (TextView) itemView.findViewById(R.id.text_view_result_number);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void bindView(Prize prize, boolean isLast){
            List<String> numbers = prize.getNumbers();
            mNumberTextView.setText(StringHelper.constructNumber(numbers));
            mPositionTextView.setText(StringHelper.constructPosition(numbers, prize.getPosition()));
            mPositionTextView.getLayoutParams().height = mNumberTextView.getMaxHeight();

            if(isLast){
                mPositionTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                mNumberTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        }

        public void bindHeader(){
            mPositionTextView.setText(R.string.position);
            mNumberTextView.setText(R.string.number);

            mPositionTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mNumberTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mPositionTextView.setPadding(0, 15, 0, 15);
            mNumberTextView.setPadding(0, 15, 0, 15);
        }
    }

    private class PrizeAdapter extends RecyclerView.Adapter<PrizeHolder> {
        List<Prize> mPrizes;

        public PrizeAdapter(List<Prize> prizes) {
            mPrizes = prizes;
        }

        @Override
        public PrizeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_list_item_result, parent, false);
            return new ResultFragment.PrizeHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(PrizeHolder holder, int position) {
            if(position != 0){
                holder.bindView(mPrizes.get(position - 1), isLastItem(position));
            }else{
                holder.bindHeader();
            }
        }

        private boolean isLastItem(int index){
            return (index - 1) == (mPrizes.size() - 1);
        }

        @Override
        public int getItemCount() {
            return mPrizes.size() + 1;
        }
    }
}
