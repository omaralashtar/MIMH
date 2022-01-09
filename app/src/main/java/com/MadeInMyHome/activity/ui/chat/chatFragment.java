package com.MadeInMyHome.activity.ui.chat;

import static com.MadeInMyHome.utilities.General.getSharedPreference;
import static com.MadeInMyHome.utilities.constants.CID_KEY;
import static java.util.Collections.singletonList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_channel.ShowChannelActivity;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.databinding.FragmentChatBinding;
import com.MadeInMyHome.utilities.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.channel.ChannelClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.Channel;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.livedata.ChatDomain;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;

public class chatFragment extends Fragment {

    ShowUserProfileViewModel showUserProfileViewModel;
    String myToken;
    //String token="34spgkfrn47zkqd8dgbzsferz3h4wf7d84wt2w76rvt5zazf7e4bwatbmpsux9qv";
    private FragmentChatBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        showUserProfileViewModel = ViewModelProviders.of(this).get(ShowUserProfileViewModel.class);
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myToken = getSharedPreference(getActivity(), "token");
        ChatClient client = new ChatClient.Builder(getString(R.string.apikey), getActivity().getApplicationContext())
                .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
                .build();
        new ChatDomain.Builder(client, getActivity().getApplicationContext()).build();

        showUserProfileViewModel.getUserProfile(getActivity(), myToken)
                .observe(getActivity(), new Observer<com.MadeInMyHome.model.User>() {
                    @Override
                    public void onChanged(com.MadeInMyHome.model.User myUser) {
                        // Step 2 - Authenticate and connect the user
                        User user = new User();
                        user.setId(myUser.getId());
                        user.setRole("admin");
                        user.setName(myUser.getF_name() + " " + myUser.getL_name());
                        user.setImage(constants.BASE_HOST + constants.IMAGE_USER + myUser.getImage());

                        String token = client.devToken(user.getId());

                        client.connectUser(
                                user,
                                token
                        ).enqueue(result ->{
                            if (result.isSuccess()) {
                                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();                        } else {
                            Toast.makeText(getActivity(), result.error().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        } );



                        // Step 3 - Set the channel list filter and order
                        // This can be read as requiring only channels whose "type" is "messaging" AND
                        // whose "members" include our "user.id"
                        FilterObject filter = Filters.and(
                                Filters.eq("type", "messaging"),
                                Filters.in("members", singletonList(user.getId()))
                        );

                        ChannelListViewModelFactory factory = new ChannelListViewModelFactory(
                                filter,
                                ChannelListViewModel.DEFAULT_SORT
                        );

                        ChannelListViewModel channelsViewModel =
                                new ViewModelProvider(getActivity(), factory).get(ChannelListViewModel.class);

                        // Step 4 - Connect the ChannelListViewModel to the ChannelListView, loose
                        //          coupling makes it easy to customize
                        ChannelListViewModelBinding.bind(channelsViewModel, binding.channelListView, getActivity());
                        binding.channelListView.setChannelItemClickListener(
                                channel -> startActivity(ShowChannelActivity.newIntent(getActivity(), channel))
                        );
                        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                client.createChannel("messaging", Arrays.asList(user.getId(), "62")).enqueue(result -> {
                                    if (result.isSuccess()) {
                                        Intent i = new Intent(getActivity(), ShowChannelActivity.class);
                                        i.putExtra(CID_KEY, result.data().getCid());
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(getActivity(), result.error().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });

        return root;
    }
}