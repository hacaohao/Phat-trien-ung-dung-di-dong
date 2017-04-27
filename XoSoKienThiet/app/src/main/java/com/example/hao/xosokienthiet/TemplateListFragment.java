package com.example.hao.xosokienthiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

abstract public class TemplateListFragment extends Fragment {
    private RecyclerView mTemplateRecyclerView;
    private TemplateAdapter mAdapter;

    protected abstract List<String> getDatas();

    protected abstract Intent getIntent(String data);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_template, container, false);
        mTemplateRecyclerView = (RecyclerView) view.findViewById(R.id.template_recycler_view);
        mTemplateRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    private void updateUI() {
        List<String> datas = getDatas();

        if(mAdapter == null){
            mAdapter = new TemplateAdapter(datas);
            mTemplateRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TemplateHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitleTextView;

        public TemplateHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);
        }

        @Override
        public void onClick(View v) {
            Intent intent = getIntent(mTitleTextView.getText().toString());
            startActivity(intent);
        }
    }

    private class TemplateAdapter extends RecyclerView.Adapter<TemplateHolder> {
        private List<String> mDatas;

        public TemplateAdapter(List<String> datas) {
            mDatas = datas;
        }

        @Override
        public TemplateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_list_item, parent, false);
            return new TemplateHolder(view);
        }

        @Override
        public void onBindViewHolder(TemplateHolder holder, int position) {
            String TemplateName = mDatas.get(position);
            holder.mTitleTextView.setText(TemplateName);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }
}
