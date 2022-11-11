package net.petrikainulainen.testdoubles;

/**
 * Contains the information of one todo item.
 */
class TodoItem {

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
