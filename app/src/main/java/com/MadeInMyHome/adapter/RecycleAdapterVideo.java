package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Video;

import java.util.ArrayList;


public class RecycleAdapterVideo extends RecyclerView.Adapter<RecycleAdapterVideo.viewitem> {


    ArrayList<Video> items;
    Context context;
    WebView webView;

    String STARTHTML = "<html><body><iframe width='420' height='345' src=\"http://www.youtube.com/embed/";
    String ENDHTML = "\" allowfullscreen></iframe></body></html>";


    public RecycleAdapterVideo(Context c, ArrayList<Video> item, WebView View) {
        items = item;
        context = c;
        webView = View;
        /*webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(STARTHTML+items.get(0).getUrl()+ENDHTML, "text/html", "utf-8");
   */ }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_video, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        String url = items.get(position).getUrl();
        holder.name.setText(items.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String YouTubeVideoEmbedCode = "<html><body><iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/-fEIzQ5JD84\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

                //webView.loadData(STARTHTML+url+ENDHTML, "text/html", "utf-8");

                /*webView.loadDataWithBaseURL("", html,
                        mimeType, encoding, "");
                webView.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
