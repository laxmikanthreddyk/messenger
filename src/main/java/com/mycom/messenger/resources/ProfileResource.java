package com.mycom.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycom.messenger.model.Profile;
import com.mycom.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles()
	{
		return profileService.getProfiles();
	}
	
	@GET
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") long profileId)
	{
		return profileService.getProfile(profileId);
	}
	
	@POST
	@Path("/add")
	public Profile addProfile(Profile profile)
	{
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/update/{profileId}")
	public Profile updateProfile(Profile profile)
	{
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/delete/{profileId}")
	public void removeProfile(long profileId)
	{
		profileService.removeProfile(profileId);
	}

}
