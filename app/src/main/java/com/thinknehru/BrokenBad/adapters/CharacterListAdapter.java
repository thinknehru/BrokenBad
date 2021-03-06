package com.thinknehru.BrokenBad.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thinknehru.BrokenBad.R;
import com.thinknehru.BrokenBad.models.Character;
import com.thinknehru.BrokenBad.ui.CharacterDetailActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {
    private List<Character> mCharacters;
    private Context mContext;

    public CharacterListAdapter(List<Character> mCharacters, Context mContext) {
        this.mCharacters = mCharacters;
        this.mContext = mContext;
    }

    @Override
    public CharacterListAdapter.CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
        CharacterViewHolder viewHolder = new CharacterViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CharacterListAdapter.CharacterViewHolder holder, int position){
        holder.bindCharacter(mCharacters.get(position));
    }

    @Override
    public int getItemCount(){
        return mCharacters.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.characterImageView)
        ImageView mCharacterImageView;
        @BindView(R.id.characterNameTextView) TextView mNameTextView;
        @BindView(R.id.nicknameTextView)
        TextView mNicknameTextView;
        @BindView(R.id.statusTextView) TextView mStatusTextView;

        private Context mContext;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CharacterDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("characters", Parcels.wrap(mCharacters));
            mContext.startActivity(intent);
        }

        public void bindCharacter(Character character){
            Picasso.get().load(character.getImg()).into(mCharacterImageView);
            mNameTextView.setText(character.getName());
            mNicknameTextView.setText(character.getNickname());
            mStatusTextView.setText(character.getStatus());

        }


    }
}
