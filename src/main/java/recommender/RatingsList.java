package recommender;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A class that stores movie ratings of a user in a custom singly linked list that consists of RatingNode-s.
 * Has various methods to manipulate the linked list.
 * Stores only the head of the list (no tail! no size!). The list should be sorted by
 * rating (from highest to lowest).
 * Fill in code in the methods of this class.
 *
 * Do not modify signatures of methods, do NOT add additional instance variables.
 * Not all methods are needed to compute recommendations, but all methods are required for the project.
 */
public class RatingsList implements Iterable<RatingNode> {
    private RatingNode head; // head of the linked list
    // Note: you are *not* allowed to store the tail or the size of this list

    public RatingNode head() {
        return head;
    }

    /**
     * Returns the reference to the node that contains the given movie id or
     * null if such node does not exit.
     *
     * @param movieId
     * @return
     */
    public RatingNode find(int movieId) {
        RatingNode node = head;
        // FILL IN CODE: find the node with the given movieId.

        return null; // change
    }

    /**
     * Changes the rating for a given movie to newRating. The position of the
     * node within the list should be changed accordingly, so that the list
     * remains sorted by rating (from largest to smallest).
     *
     * @param movieId id of the movie
     * @param newRating new rating of this movie
     */
    public void setRating(int movieId, double newRating) {
        // FILL IN CODE
        // Find the node with this movie id
        RatingNode node = find(movieId);
        if (node == null) {
            System.out.println("Could not change the rating: no node with this movieId");
            return;
        }
        // Delete the node
        // Reinsert: Call insertByRating for the updated node with the new rating

    }

    /**
     * Return the rating for a given movie. If the movie is not in the list,
     * returns -1.
     * @param movieId movie id
     * @return rating of a movie with this movie id
     */
    public double getRating(int movieId) {
        RatingNode node = find(movieId);
        if (node != null)
            return node.getMovieRating();
        else
            return -1;
    }

    /**
     * Create a new RatingNode with the given movieId and rating
     * and append it to the end of the list. Used in reverse method.
     * @param movieId movie id
     * @param rating rating of the movie
     */
    public void append(int movieId, double rating) {
        RatingNode curr = head;
        if (head == null) {
            head = new RatingNode(movieId, rating);
            return;
        }
        // FILL IN CODE: handle a general case, when head is not null

    }

    /**
     * Insert a new node (with a given movie id and a given rating) into the sorted linked list.
     * Assume the list is sorted by rating, from highest to lowest.
     * Insert it in the right place based on the value of the rating. The
     * list should remain sorted after this insert operation.
     * Example:
     * If the list was (10, 5) -> (14, 4)-> (65, 2) and we insert (17, 3),
     * we should get   (10, 5) -> (14, 4)-> (17, 3) -> (65, 2) after the insertion.
     * The first value at the node is the movie id (like 10) and the second is the rating.
     * Here, all nodes are shown in decreasing order of the rating.
     *
     * @param movieId id of the movie
     * @param rating rating of the movie
     */
    public void insertByRating(int movieId, double rating) {
        // insert a node into the sorted list
        RatingNode node = new RatingNode(movieId, rating);
        RatingNode curr = head;
        RatingNode prev = null;
        while (curr != null && (curr.getMovieRating() > rating)) {
            // FILL IN CODE
        }
    }

    /**
     * Computes correlation (that we interpret as similarity which is not very accurate)
     * between two lists of ratings using Pearson correlation.
     * https://en.wikipedia.org/wiki/Pearson_correlation_coefficient
     * Note: You are allowed to use a HashMap for this method.
     *
     * @param otherList another RatingList
     * @return similarity computed using Pearson correlation
     */
    public double computeCorrelation(RatingsList otherList) {
        HashMap<Integer, Double> map = new HashMap(); // maps a movie id to the rating
        RatingNode curr1 = head;
        // Go over nodes of this list using curr1 and for each node,
        // put movieId in the hash map as the key and rating as the value.
        // FILL IN CODE

        RatingNode curr2 = otherList.head();
        double similarity = 0;
        int n = 0; // number of movies that are in both this list and otherList
        // FILL IN CODE:
        // Go over nodes of the otherList using curr2, and if you see the movieId that is already
        // in the hash map (it means it is rated in both lists), update the terms required to compute Pearson correlation
        // (see the Pearson formula in the pdf).
        // In the formula, x_i is the rating of some movie from "this" list, y_i is the rating of the same movie from the otherList.
        // Note that they may not be in the same positions in the RatingsList!
        // n is the number of movie ids that occur in both lists.


        return similarity;
    }
    /**
     * Returns a sublist of this list where the rating values are in the range
     * from begRating to endRating, inclusive.
     *
     * @param begRating lower bound for ratings in the resulting list, inclusive
     * @param endRating upper bound for ratings in the resulting list, inclusive
     * @return sublist of the RatingsList that contains only nodes with
     * rating in the given interval
     */
    public RatingsList sublist(int begRating, int endRating) {
        RatingsList res = new RatingsList();
        RatingNode curr = head;
        // FILL IN CODE:

        return res;
    }

    /** Traverses the list and prints the ratings list in the following format:
     *  movieId:rating; movieId:rating; movieId:rating;  */
    public void print() {
        RatingNode curr = head;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next();
        }
        System.out.println();
    }

    /**
     * Returns a RatingsList that contains n best rated movies. These are
     * essentially first n movies from the beginning of the list. If the list is
     * shorter than size n, it will return the whole list.
     *
     * @param n the maximum number of movies to return
     * @return RatingsList with top ranked movies
     */
    public RatingsList getNBestRankedMovies(int n) {
        RatingsList result = new RatingsList();
        // FILL IN CODE

        return result;
    }

    /**
     * Return a new list that is the reverse of the original list. The returned
     * list is sorted from lowest ranked movies to the highest rated movies.
     * Use only one additional RatingsList (the one you return) and constant amount
     * of memory. You may NOT use arrays, ArrayList and other built-in Java Collections classes.
     * Read description carefully for requirements regarding implementation of this method.
     *
     * @param head head of the RatingList to reverse
     * @return reversed list
     */
    public RatingsList reverse(RatingNode head) {
        RatingsList r = new RatingsList();
        // FILL IN CODE:

        return r;
    }


    /**
     * Returns an iterator for the list
     * @return iterator
     */
    public Iterator<RatingNode> iterator() {

        return new RatingsListIterator();
    }

    // ------------------------------------------------------
    /**
     * Inner class, RatingsListIterator
     * The iterator for the ratings list. Allows iterating over the RatingNode-s of
     * the list.
     */
    private class RatingsListIterator implements Iterator<RatingNode> {
        RatingNode curr = null;

        /** Creates a new the iterator starting at the head */
        public RatingsListIterator() {
            curr = head;
        }

        /**
         * Checks if there is a "next" element of the list
         * @return true, if there is "next" and false otherwise
         */
        public boolean hasNext() {
            return curr != null;
        }

        /**
         * Returns the "next" node and advances the iterator
         * @return next node
         */
        public RatingNode next() {
            if (!hasNext()) {
                System.out.println("No next node. ");
                return null;
            }
            RatingNode oldNode = curr;
            curr = curr.next();
            return oldNode;
        }

    }

    // You can use this method to check if your RatingsList works correctly, before you are ready to run the tests
    public static void main(String[] args) {
        RatingsList movieRatingsList  = new RatingsList();
        // Add movies and ratings manually
        movieRatingsList.insertByRating(1, 5.0);
        movieRatingsList.insertByRating(3, 2.0);
        movieRatingsList.insertByRating(2, 4.0);
        movieRatingsList.insertByRating(4, 3.0);
        movieRatingsList.print(); // check if insertions worked correctly; the list should be sorted in descreasing order of rating.
        /* Expected: sorted by ratings in descreasing order
        1, 5.0
        2, 4.0
        4, 3.0
        3, 2.0
        5, 1.0
         */
        // TODO: Call different methods on the movieRatings list
        // to see if they work correctly.

    }

}