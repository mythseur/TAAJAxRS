/*
  JBoss, Home of Professional Open Source
  Copyright Red Hat, Inc., and individual contributors.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package fr.istic.taa.jaxrs;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import twitter4j.*;

@Api(description = "Twitter")
@Path("/twitter")
public class TweetEndpoint {

    private static Twitter link = TwitterFactory.getSingleton();
    private static final Logger logger = Logger.getLogger(TweetEndpoint.class);

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {

        try {
            String result_l = "";
            for(Status status_l : link.getHomeTimeline())
            {
                result_l += status_l.getText() + "\n";
            }
            return result_l;
        } catch (TwitterException e) {
            logger.error(e.getMessage(), e);
        }

        return "Erreur lors de la récupération des tweets";

    }

    @POST
    @Path("/post")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String sendTweet(String status) {

        try {
            link.updateStatus(status);
            return "Tweeted Successfully";
        } catch (TwitterException e) {
            logger.error("Erreur lors du postage de tweet : " + e.getMessage(), e);
        }
        return "An error occured";
    }
//

    @POST
    @Path("/send")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMessage(String status) {

        try {
            String[] split = status.split(":");
            link.sendDirectMessage(split[0], split[1]);
            return "Tweeted Successfully";
        } catch (TwitterException e) {
            logger.error("Erreur lors de l'envoi du message : " + e.getMessage(), e);
        }
        return "An error occured";
    }

    @GET
    @Path("/friends/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFriends(@PathParam("id") String id) {

        String result = "";
        long cursor=-1;
        PagableResponseList<User> list_l;
        try {
            do {
                list_l = link.getFriendsList(id, cursor);
                for (User user_l : list_l) {
                    result += user_l.getName() + "\n";
                }
            }while ((cursor = list_l.getNextCursor()) !=0);

        } catch (TwitterException e) {
            logger.error(e.getMessage(), e);
        }

        return result;

    }

    @GET
    @Path("/followers/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFollowers(@PathParam("id") String id) {

        String result = "";
        long cursor=-1;
        PagableResponseList<User> list_l;
        try {
            do {
                list_l = link.getFollowersList(id, cursor);
                for (User user_l : list_l) {
                    result += user_l.getName() + "\n";
                }
            }while ((cursor = list_l.getNextCursor()) !=0);

        } catch (TwitterException e) {
            logger.error(e.getMessage(), e);
        }

        return result;

    }
    

}

