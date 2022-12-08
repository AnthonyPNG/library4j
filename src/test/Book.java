package test;

import java.util.Date;

public class Book {
    /**
     * The id.
     */
    public int id;
    /**
     * The Available.
     */
    public boolean available;
    /**
     * The Author first name.
     */
    public String authorFirstName;
    /**
     * The Author second name.
     */
    public String authorSecondName;
    /**
     * The Title.
     */
    public String title;
    /**
     * The Creation date.
     */
    public Date creationDate;
    /**
     * The Category.
     */
    public String category;

    /**
     * Instantiates a new Book.
     *
     * @param id               the id
     * @param available        the available
     * @param authorFirstName  the author first name
     * @param authorSecondName the author second name
     * @param title            the title
     * @param creationDate     the creation date
     * @param category         the category
     */
    public Book(int id, boolean available, String authorFirstName, String authorSecondName, String title, Date creationDate, String category) {
        this.id               = id;
        this.available        = available;
        this.authorFirstName  = authorFirstName;
        this.authorSecondName = authorSecondName;
        this.title            = title;
        this.creationDate     = creationDate;
        this.category         = category;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets available.
     *
     * @param available the available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets author first name.
     *
     * @return the author first name
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     * Sets author first name.
     *
     * @param authorFirstName the author first name
     */
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    /**
     * Gets author second name.
     *
     * @return the author second name
     */
    public String getAuthorSecondName() {
        return authorSecondName;
    }

    /**
     * Sets author second name.
     *
     * @param authorSecondName the author second name
     */
    public void setAuthorSecondName(String authorSecondName) {
        this.authorSecondName = authorSecondName;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets suitable String out of Book.
     *
     * @return String out of Book
     */
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", available=" + available + ", authorFirstName='" + authorFirstName +
        		'\'' + ", authorSecondName='" + authorSecondName + '\'' + ", title='" + title + '\'' + 
        		", creationDate=" + creationDate + ", category='" + category + '\'' + '}';
    }
}