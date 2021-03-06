package com.mehdisarf.mehdisarfbookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "Book")
@NamedQueries(
        {
                @NamedQuery(name = "Book.findAll", query = "select b from Book b order by b.title"),
                @NamedQuery(name = "Book.countAll", query = "select count(*) from Book b"),
                @NamedQuery(name = "Book.countByCategory", query = "select count(b) from Book b join Category  c on b.category.categoryId = c.categoryId and c.categoryId = :catId"),
                @NamedQuery(name = "Book.findByTitle", query = "select b from Book b where b.title = :title"),
                @NamedQuery(name = "Book.findByCategory", query = "select b from Book b join Category c on b.category.categoryId = c.categoryId and c.categoryId =: categoryId"),
                @NamedQuery(name = "Book.listNew", query = "select b from Book b order by b.publishDate desc "),

                @NamedQuery(name = "Book.search", query = "select b from Book b where b.title like '%' || :keyword|| '%'" +
                        " or b.author like '%' || :keyword || '%' or b.description like '%' || :keyword || '%'")
        }
)
public class Book {

    private Integer bookId;
    private Category category;
    private String title;
    private String author;
    private String description;
    private String isbn;
    private byte[] image;
    private String base64Image;
    private float price;
    private Date publishDate;
    private Date lastUpdateTime;
    private Set<Review> reviews = new HashSet<Review>(0);
    private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

    public Book() {
    }

    public Book(Integer bookId) {
        super();
        this.bookId = bookId;
    }

    public Book(Category category, String title, String author, String description, String isbn, byte[] image,
                float price, Date publishDate, Date lastUpdateTime) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Book(Category category, String title, String author, String description, String isbn, byte[] image,
                float price, Date publishDate, Date lastUpdateTime, Set<Review> reviews, Set<OrderDetail> orderDetails) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
        this.price = price;
        this.publishDate = publishDate;
        this.lastUpdateTime = lastUpdateTime;
        this.reviews = reviews;
        this.orderDetails = orderDetails;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookId == null) {
            if (other.bookId != null)
                return false;
        } else if (!bookId.equals(other.bookId))
            return false;
        return true;
    }

    @Column(name = "title", nullable = false, length = 128)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false, length = 64)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", nullable = false, length = 16777215)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "isbn", nullable = false, length = 15)
    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "image", nullable = false)
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "price", nullable = false, precision = 12, scale = 0)
    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "publish_date", nullable = false, length = 10)
    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false, length = 19)
    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    public Set<Review> getReviews() {
        TreeSet<Review> sortedReviews = new TreeSet<>(new Comparator<Review>() {

            @Override
            public int compare(Review review1, Review review2) {

                return review2.getReviewTime().compareTo(review1.getReviewTime());
            }

        });

        sortedReviews.addAll(reviews);
        return sortedReviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
    public Set<OrderDetail> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Transient
    public String getBase64Image() {
        // this converts bytes[] to Base64 String.
        this.base64Image = Base64.getEncoder().encodeToString(this.image);
        return base64Image;
    }

    @Transient
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Transient
    public float getAverageRating() {

        float sum = 0.0f;

        if (this.reviews.isEmpty())
            return 0.0f;

        for (Review review : this.reviews) {
            sum += review.getRating();
        }

        return sum / this.reviews.size();
    }

    @Transient
    public String convertAverageRatingToString(float averageRating) {

        String result = "";

        int averageRatingFloor = (int) averageRating;
        int startPointForOff = averageRatingFloor + 1;

        for (int i = 1; i <= averageRatingFloor; i++) {
            result = result + "on,";
        }

        if (averageRating > averageRatingFloor) {
            result = result + "half,";
            startPointForOff++;
        }

        for (int i = startPointForOff; i <= 5; i++) {
            result = result + "off,";
        }

        return result.substring(0, result.length() - 1);
    }

    @Transient
    public String getRatingString() {
        return convertAverageRatingToString(getAverageRating());
    }

}