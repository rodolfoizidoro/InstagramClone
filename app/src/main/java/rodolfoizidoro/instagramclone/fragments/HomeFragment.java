package rodolfoizidoro.instagramclone.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import rodolfoizidoro.instagramclone.R;
import rodolfoizidoro.instagramclone.adapter.HomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private ArrayList<ParseObject> postagens;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> query;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        postagens = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.list_postagem_home);
        adapter = new HomeAdapter(getActivity(), postagens);
        listView.setAdapter(adapter);
        getPostagens();


        return view;
    }

    private void getPostagens() {

        query = ParseQuery.getQuery("Imagem");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if(objects.size() > 0){
                        postagens.clear();
                        for(ParseObject parseObject : objects){

                            postagens.add(parseObject);

                        }
                        adapter.notifyDataSetChanged();
                    }


                } else {
                }
            }
        });


    }

    public void atualizaPostagens(){
        getPostagens();
    }

}
