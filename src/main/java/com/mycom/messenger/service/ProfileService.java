package com.mycom.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mycom.messenger.database.DatabaseClass;
import com.mycom.messenger.model.Profile;

public class ProfileService {
	
	public Map<Long, Profile> profiles = DatabaseClass.getProfiles();
	
	public List<Profile> getProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(long profileId)
	{
		return profiles.get(profileId);
	}
	
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getId(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile)
	{
		if(profile.getId() <= 0)
		{
			return null;
		}
		profiles.put(profile.getId(), profile);
		return profile;
	}
	
	public void removeProfile(long profileId)
	{
		profiles.remove(profileId);
	}

}
