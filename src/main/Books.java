package main;

/**
 * The Books class
 */
public class Books {
	/**
	 * The id
	 */
    public int id;
    /**
     * The availability
     */
    public boolean available;
    /**
     * The author
     */
    public String authorName;
    /** 
     * The title
     */
    public String title;
    /**
     * The date
     */
    public String creationDate;
    /**
     * The category
     */
    public String category;

    public Books() {
		super();
	}

    /**
     * Initiates a new Book
     * @param id
     * @param available
     * @param authorName
     * @param title
     * @param creationDate
     * @param category
     */
	public Books(int id,
                boolean available,
                String authorName,
                String title, String creationDate,
                String category) {
        this.id               = id;
        this.available        = available;
        this.authorName = authorName;
        this.title            = title;
        this.creationDate     = creationDate;
        this.category         = category;
    }

	/**
	 * Gets the id
	 * @return id
	 */
    public int getId() {
        return id;
    }
    
    /**
	 * Sets the id
	 * @param id
	 */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the availability
     * @return
     */
    public boolean isAvailable() {
        return available;
    }
    
    /**
     * Sets the availability
     * @param available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    /**
     * Gets the author
     * @return
     */
    public String getAuthorName() {
        return authorName;
    }
    
    /**
     * Sets the author
     * @param authorName
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
 
    /**
     * Gets the title
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the tile
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date
     * @return the date
     */
    public String getCreationDate() {
        return creationDate;
    }
    
    /**
     * Sets the date
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the category
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", available=" + available +
                ", authorName='" + authorName + '\'' +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", category='" + category + '\'' +
                '}';
    }
}