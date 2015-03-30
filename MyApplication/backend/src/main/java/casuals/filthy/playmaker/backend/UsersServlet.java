/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package casuals.filthy.playmaker.backend;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

import data.UserData;
import data.metadata.MetaDataObject;
import data.metadata.UserIdMgr;
import data.metadata.UserLookup;

import static casuals.filthy.playmaker.backend.OfyService.ofy;

public class UsersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UsersServlet.class.getName());
    private static Gson gson = new Gson();

    static {
        ObjectifyService.register(UserData.class);
        ObjectifyService.register(UserLookup.class);
        ObjectifyService.register(UserIdMgr.class);
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userEmail = req.getParameter("user_email");
        String userIdString = req.getParameter("user_id");
        long userId;

        // lookup the user if the id was not specified
        if (userIdString == null) {
            UserLookup lookup = ofy().load().type(UserLookup.class).id(UserLookup.META_ID).now();
            if (lookup == null) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }

            userId = lookup.getUserId(userEmail);
        } else {
            userId = Long.parseLong(userIdString);
        }

        // get the User data
        UserData user = ofy().load().type(UserData.class).id(userId).now();
        if (user == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // convert to json
        String userJson = gson.toJson(user);

        // respond with information
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(userJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
     public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Add support for changing name and adding groups
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Create a new user by force
        String name = req.getParameter("user_name");
        String email = req.getParameter("user_email");

        if (name == null || email == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserIdMgr idMgr = ofy().load().type(UserIdMgr.class).id(UserIdMgr.META_ID).now();
        long id;
        if (idMgr == null) {
            // create a the mgr
            idMgr = new UserIdMgr();
        }
        UserLookup lookup = ofy().load().type(UserLookup.class).id(UserLookup.META_ID).now();
        if (lookup == null) {
            // create a the mgr
            lookup = new UserLookup();
        }
        if (lookup.getUserId(email) != -1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User already exists with this email");
            return;
        }

        // get next id
        id = idMgr.getNextId();
        lookup.addUser(email, id);
        ofy().save().entities(lookup, idMgr).now();

        // create user
        UserData user = new UserData(email, name, id);
        ofy().save().entity(user);

        // respond
        String userJson = gson.toJson(user);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(userJson);
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        // TODO this should probably be implemented at some point
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
