package recommender;

import java.io.*;

/** A class that is responsible for:
 - Reading movie and ratings data from the csv files
 - Computing movie recommendations for a given user and printing them to a file.
   Fill in code in methods of this class.
   Do not modify signatures of methods.
 */
public class MovieRecommender {
    private User[] users; // An array of users;  index is the userid, the value is a User
    private String[] movies; // An array of movie titles; the index is the movieId, the value is a title of the movie

    /**
     * Read movies and ratings from files
     * @param movieFilename name of the file with movie info
     * @param ratingsFilename name of the file with ratings info
     */
    public void loadData(String movieFilename, String ratingsFilename) {
        loadMovies(movieFilename);
        loadRatings(ratingsFilename);
    }

    /** Load information about movie ids and titles from the given file.
     * Store data in the array "movies".
     * @param movieFilename csv file that contains movie information.
     *
     */
    private void loadMovies(String movieFilename) {
        // This method has been provided to you - no need to change it
        try(FileReader fr = new FileReader(movieFilename); BufferedReader br = new BufferedReader(fr)) {
            String line;
            int numMovies = Integer.parseInt(br.readLine()); // read the number of movies
            movies = new String[numMovies + 1]; // we are not using index 0; movie ids start with 1.
            int i = 1;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                Integer movieId;
                String movieTitle;
                if (info.length != 3) {
                    // title is in quotes
                    String[] info1 = line.split("\"");
                    if (info1.length < 2) {
                        System.out.println("Format is wrong in line: " + i);
                        return;
                    }

                    String movieIdS = info1[0].substring(0, info1[0].length() - 1);
                    movieId = Integer.parseInt(movieIdS);
                    movieTitle = info1[1];
                }
                else {
                    movieId = Integer.parseInt(info[0]);
                    movieTitle = info[1]; // includes year
                }
                movies[movieId] =  movieTitle;
                i++;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Load users' movie ratings from the file
       The first line in the file is the total number of ratings
       Other lines are in the following format:
       userId,movieId,rating,timestamp
       We will ignore the timestamp for thi project.
       @param ratingsFilename name of the file that contains ratings
     */
    private void loadRatings(String ratingsFilename) {
        try (BufferedReader br = new BufferedReader(new FileReader(ratingsFilename))) {
            int numRatings = Integer.parseInt(br.readLine()); // read the number of ratings
            users = new User[numRatings + 1]; // we are not using index 0; user ids start with 1.
            int i = 1;
           // FILL IN CODE: read each line, and split it by comma using the split method in class String.
            // Read movie id, user id, and rating. Ignore the timestamp.
            // If you see a userId that has already been added to the array "users",
            // you need to call insert(movieId, rating) on that user.
            // If this is a new User, point users[userid] to a new User object, and
            // call insert(movieId, rating) on the new user.
            String str;
            while((str = br.readLine()) != null){
                String[] set = str.split(",");
                if(set.length == 4){
                    if(users[Integer.parseInt(set[0])] == null){
                        User user = new User(Integer.parseInt(set[0]));
                        user.insert(Integer.parseInt(set[1]), Double.parseDouble(set[2]));
                        users[Integer.parseInt(set[0])] = user;
                    }else{
                        users[Integer.parseInt(set[0])].insert(Integer.parseInt(set[1]), Double.parseDouble(set[2]));
                    }
                }
            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Sets the array of users for this recommender
     * Provided to you; used in the test.
     * @param users array of users
     */
    public void setUsers(User[] users) {
        this.users = users;
    }


    /**
     * The method computes the "similarity" (better to say "linear correlation")
     * between the user with the given userid and all the other users.
     * Finds the maximum "correlation coefficient" and returns the "most similar user".
     * Calls computeSimilarity method in class RatingsList.
     *
     * @param userid id of the user
     * @return the node that corresponds to the most similar user
     */
    public User findMostSimilarUser(int userid) {
        User mostSimilarUser = null;
        double maxSimilarity = -10000;
        User thisUser = users[userid];

        // Go over all the other users
        for (int id = 0; id < users.length; id++) {
            // going over other users (their ids)
            if (users[id] != null)  {
              if (id != userid) { // do not compare with yourself.
                  // FILL IN CODE
                // Call computeCorrelation from RatingsList class
                  // Update it and user if you find a "better" value
                  if(thisUser.computeSimilarity(users[id]) > maxSimilarity){
                      mostSimilarUser = users[id];
                      maxSimilarity = thisUser.computeSimilarity(users[id]);
                  }

              }
            }
        }
        // System.out.println(maxSimilarity);
        return mostSimilarUser;
    }


    /**
     * * Computes up to num movie recommendations for the user with the given user
     * id and prints these movie titles to the given file. First calls
     * findMostSimilarUser and then getFavoriteMovies(num) method on the
     * "most similar user" to get up to num recommendations. Prints movies that
     * the user with the given userId has not seen yet.
     * @param userid id of the user
     * @param num max number of recommendations
     * @param filename name of the file where to output recommended movie titles
     *                 Format of the file: one movie title per each line
     */
    public void findRecommendations(int userid, int num, String filename) {
        // FILL IN CODE
        // compute similarity between userid and all the other users
        // find the most similar user and recommend movies that the most similar
        // user rated as 5.
        // Recommend only the movies that userid has not seen (has not
        // rated).
        User mostSimilarUser = findMostSimilarUser(userid);
        // System.out.println("The user with highest Pearson correlation: " + mostSimilarUser.getId());
        // To recommend movies, get mostSimilarUser's best movies rated as 5,
        // and remove movies this user have seen already.


    }

    public void printUsers(String filename) {
        try (PrintWriter pr = new PrintWriter(filename)) {
            for (User user : users) {
                if (user != null) {
                    pr.print("(" + user.getId() + ") ");
                    // FILL IN CODE: print movies/ratings for this user
                    for(int i = 0; i < user.getMovieIds().length; i++){
                        pr.print(user.getMovieIds()[i] + ":" + user.getRating(user.getMovieIds()[i]) + "; ");
                    }
                    pr.println();
                }
            }
            pr.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}