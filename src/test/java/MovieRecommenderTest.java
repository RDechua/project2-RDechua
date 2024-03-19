import org.junit.Assert;
import org.junit.Test;
import recommender.MovieRecommender;
import recommender.User;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Test file for MovieRecommender class.
 *  Note that this class provides the minimum tests.
 *  You are responsible for thoroughly testing project1 code on your own. */
public class MovieRecommenderTest {
    public static final int[] MOVIES1 = {1, 2, 3, 4, 5, 6, 7};
    public static final double[] RATINGS1 = {5, 5, 2, 5, 4, 3, 4};
    public static final int[] MOVIES2 = {2, 4, 7};
    public static final double[] RATINGS2 = {4, 1, 2};
    public static final int[] MOVIES3 = {1, 4, 5, 7};
    public static final double[] RATINGS3 = {3, 2, 5, 1};

    @Test
    public void testUsersList() {
        MovieRecommender recommender = new MovieRecommender();
        User[] users = new User[10];
        addData(users, 1, MOVIES1, RATINGS1);
        addData(users, 2, MOVIES2, RATINGS2);
        addData(users, 3, MOVIES3, RATINGS3);
        //Path test = Paths.get(".").toAbsolutePath();
        //System.out.println(test);
        recommender.setUsers(users);
        Path actual = Paths.get("src" + File.separator + "test" + File.separator + "usersDataSimple");  // your output
        Path expected = Paths.get("src" + File.separator + "test" + File.separator + "expectedUsersDataSimple"); // instructor's
        recommender.printUsers(actual.toString());

        // Compare your output with expected output
        int count = 0;
        try {
            count = TestUtils.checkFiles(expected, actual);
            if (count <= 0)
                Assert.fail("testUsersList Failed. Expected result is not equal to the actual result.");
        } catch (IOException e) {
            Assert.fail(" File check failed: " + e.getMessage());
        }
    }

    @Test
    public void testMostSimilarUser() {
        MovieRecommender recommender = new MovieRecommender();
        User[] users = new User[4]; // using only indices 1, 2, 3.
        addData(users, 1, MOVIES1, RATINGS1);
        double[] ratings2 = {5, 4, 4};
        addData(users, 2, MOVIES2, ratings2);
        addData(users, 3, MOVIES3, RATINGS3);
        recommender.setUsers(users);

        User mostSimilarUser = recommender.findMostSimilarUser(1);
        if (mostSimilarUser == null) {
            System.out.println("Null user returned");
            Assert.fail();
        }
        else {
            int mostSimilar1 = recommender.findMostSimilarUser(1).getId();
            Assert.assertEquals("Most similar user for user 1 should be 2, but got  " + mostSimilar1, mostSimilar1, 2);
        }
    }

    /**
     * Insert a given data for a given user id into the usersList
     * @param users
     * @param userId
     * @param movies
     * @param ratings
     */
    public void addData(User[] users, int userId, int[] movies, double[] ratings) {
       // if (users == null)
         //   users = new UsersList();
        if (userId < 0 || userId >= users.length)
            throw new IllegalArgumentException();
        if (users[userId]== null)
            users[userId] = new User(userId);
        if (movies.length != ratings.length) {
            Assert.fail("Wrong parameters in addData. # of movies != # of ratings");
        }
        for (int i = 0; i < movies.length; i++) {
            users[userId].insert(movies[i], ratings[i]);
        }
    }


    @Test
    public void testRecommend() {
        MovieRecommender recommender = new MovieRecommender();
        recommender.loadData("input" + File.separator + "movies.csv","input" + File.separator + "ratings.csv");
        System.out.println("Loaded data...");
        String filenameRecommendations = "src" + File.separator + "test" + File.separator + "recommendations";
        recommender.findRecommendations(3, 5, filenameRecommendations);

        Path actual = Paths.get(filenameRecommendations);  // your output
        Path expected = Paths.get("src" + File.separator + "test" + File.separator + "expectedRecommendations"); // instructor's

        // Compare your recommendations with expected recommendations
        int count = 0;
        try {
            count = TestUtils.checkFiles(expected, actual);
            //System.out.println(count);
            if (count <= 0)
                Assert.fail("Recommendations do not match expected recommendations.");
        } catch (IOException e) {
            Assert.fail(" File check failed: " + e.getMessage());
        }
    }

}

