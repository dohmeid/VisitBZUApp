package com.example.visitbzu.features.faq;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitbzu.R;

import java.util.List;

public class sararom_QuestionsAdapter extends RecyclerView.Adapter<sararom_QuestionsAdapter.QuestionsVH> {
    List<sararom_Question> questionList;
    private Context context;

    public sararom_QuestionsAdapter(Context context, List<sararom_Question> questionList) {
        this.questionList = questionList;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sararom_question,parent,false);
        return new QuestionsVH(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull QuestionsVH holder, int position) {
        sararom_Question question = questionList.get(position);
        holder.q.setText(question.getQuestion());
        holder.ans.setText(question.getDescription());
        holder.arrow.setImageResource(R.drawable.sararom_dropdownarrow);
        boolean isExpandable = question.isExpandable();
        holder.ans.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
        holder.arrow.setRotation(isExpandable ? 90 : 270);
        holder.background.setVisibility(isExpandable ? View.GONE : View.VISIBLE);
        holder.cardView.setBackgroundColor(isExpandable ? ContextCompat.getColor(context, R.color.sararom_lite_green): ContextCompat.getColor(context, R.color.white) );


    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionsVH extends RecyclerView.ViewHolder {
        TextView q,ans;
        LinearLayout linearLayout;
        ImageView arrow;
        View background;
        CardView cardView;
        public QuestionsVH(@NonNull View itemView) {
            super(itemView);
            q = itemView.findViewById(R.id.sararom_ques);
            ans = itemView.findViewById(R.id.sararom_ans);
            arrow = itemView.findViewById(R.id.drop_arrow);
            linearLayout = itemView.findViewById(R.id.sararom_question_linearlayout);
            background = itemView.findViewById(R.id.sararom_background);
            cardView = itemView.findViewById(R.id.sararom_cardview);

            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sararom_Question question = questionList.get(getAdapterPosition());
                    question.setExpandable(!question.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }

    }
}
