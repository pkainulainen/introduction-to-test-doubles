package net.petrikainulainen.testdoubles;

/**
 * A constant class which contains our test data.
 */
public final class TodoItems {

    public static final String NEW_TITLE = "Write a new blog post";
    public static final Long UNKNOWN_ID = 999L;

    /**
     * Prevents instantiation.
     */
    private TodoItems() {}

    public static final class WriteBlogPost {

        public static final Long ID = 1L;
        public static final String TITLE = "Write a blog post";
    }
}
