package dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_UID")
    private Long bookUid;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    public BookDto() {
    }

    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getBookUid() {
        return bookUid;
    }

    public void setBookUid(Long bookUid) {
        this.bookUid = bookUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookUid=" + bookUid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }


}