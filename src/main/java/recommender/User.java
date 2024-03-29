package recommender;

/** The class store information about a user who watches and rated some movies.
 *  Stores a userId and a list of movie ratings of type RatingsList.
 *  FILL IN CODE in methods below.
 *  Do not modify signatures of methods.
 *  */
public class User {
    private int userId;
    private RatingsList movieRatings;

    /** A constructor for the recommender.UserNode.
     * @param id 	User id
     * */
    public User(int id) {
        userId = id;
        movieRatings = new RatingsList();
    }

    /** Return a userId stored in this node */
    public int getId() {
        return userId;
    }

    public int[] getMovieIds(){return movieRatings.getMovieIds();}


    /**
     * Add rating info for a given movie to the RatingsList
     *  for this user node;
     *
     *
     * @param movieId id of the movie
     * @param rating  rating of the movie
     */
    public void insert(int movieId, double rating) {
        movieRatings.insertByRating(movieId, rating);
    }

    /**
     * Returns an array of user's favorite movies (up to n), rated 5.
     *
     * @param n  number of movies to return
     * @return array containing movie ids this user rated the highest
     */
    public int[] getFavoriteMovies(int n) { //helper method for printUser in MovieRecommender
        int[] favList = new int[n];
        RatingNode curr = movieRatings.head();
        for(int i = 0; i < favList.length; i++){
            if(curr.getMovieRating() == 5){ //only accepts movie with a 5 rating
                favList[i] = curr.getMovieId();
            }
            curr = curr.next();
        }
        return favList;
    }

    /**
     * Computes the similarity of this user with the given "other" user using
     * Pearson correlation - simply calls computeSimilarity method
     * from RatingsList
     *
     * @param otherUser a user to compare the current user with
     * @return similarity score
     */
    public double computeSimilarity(User otherUser) {
        return movieRatings.computeCorrelation(otherUser.movieRatings);
    }
    /**
     * Changes the rating for the given movie to newRating
     * @param movieId id of the movie
     * @param newRating new rating of the movie
     */
    public void setRating(int movieId, int newRating) {
        movieRatings.setRating(movieId, newRating);
    }

    /**
     * Returns this user's rating for a given movie
     * @param movieId id of the movie
     * @return rating of the given movie
     */
    public double getRating(int movieId) {
        return movieRatings.getRating(movieId);
    }

}