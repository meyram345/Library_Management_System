package kz.aitu.oop.endterm.library.entities;

public class Book {
        private String book_uuid;
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

        public Book(String book_uuid, String title, String author, int lending_period, int totalAmount) {
                setBook_uuid(book_uuid);
                setLending_period(lending_period);
                setTitle(title);
                setAuthor(author);
                setTotalAmount(totalAmount);
        }

        public String getBook_uuid() {
                return book_uuid;
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

        public void setBook_uuid(String book_uuid) {
                this.book_uuid = String.valueOf(java.util.UUID.randomUUID());
        }

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
