package kz.aitu.oop.endterm.library.entities;

import java.util.UUID;

public class Book {
        private UUID book_uuid;
        private String title;
        private String author;
        private int totalAmount;
        private int lending_period;

        public Book() {}

        public Book(String title, String author, int lending_period, int totalAmount) {
                setAuthor(author);
                setLending_period(lending_period);
                setTitle(title);
                setTotalAmount(totalAmount);
        }

        public Book(String title, String author) {
                setAuthor(author);
                setTitle(title);
        }

        public Book(UUID book_uuid, String title, String author, int lending_period, int totalAmount) {
                setBook_uuid(book_uuid);
                setLending_period(lending_period);
                setTitle(title);
                setAuthor(author);
                setTotalAmount(totalAmount);
        }


    public UUID getBook_uuid() {
                return UUID.randomUUID();
        }

        public String getTitle() {
                return title;
        }

        public int getTotalAmount() {
                return totalAmount;
        }

        public String getAuthor() {
                return author;
        }

        public void setBook_uuid(UUID book_uuid) {
                this.book_uuid = book_uuid;
        }

        public String getBook_id() {return book_uuid.toString();}

        public void setAuthor(String author) {
                this.author = author;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
        }

        public int getLending_period() {
                return lending_period;
        }

        public void setLending_period(int lending_period) {
                this.lending_period = lending_period;
        }
}
