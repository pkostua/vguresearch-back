package org.springframework.social.vkontakte.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.web.client.HttpClientErrorException;

/**
 * override because of incorrect set userProviderId
 * see original org.springframework.social.vkontakte.connect.VKontakteAdapter#setConnectionValues
 **/
public class VKontakteAdapter implements ApiAdapter<VKontakte> {
    @Override
    public boolean test(VKontakte vkontakte) {
        try {
            vkontakte.usersOperations().getProfile();
            return true;
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(VKontakte vkontakte, ConnectionValues values) {
        VKontakteProfile profile = vkontakte.usersOperations().getProfile();
        values.setProviderUserId(profile.getScreenName());
        values.setDisplayName(profile.getFirstName() + " " + profile.getLastName());
        values.setProfileUrl(profile.getProfileURL());
        values.setImageUrl(profile.getPhoto());
    }

    @Override
    public UserProfile fetchUserProfile(VKontakte vkontakte) {
        VKontakteProfile profile = vkontakte.usersOperations().getProfile();
        return new UserProfileBuilder()
                .setUsername(profile.getScreenName())
                .setFirstName(profile.getFirstName())
                .setLastName(profile.getLastName())
                .setName(profile.getFirstName() + " " + profile.getLastName())
                .build();
    }

    @Override
    public void updateStatus(VKontakte vkontakte, String message) {
        // TODO: change to status api
        vkontakte.wallOperations().post(message);
    }
}