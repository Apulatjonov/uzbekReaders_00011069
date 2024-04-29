package com.example.bookstore.serivces;

import com.example.bookstore.entities.*;
import com.example.bookstore.exceptions.AlreadyRatedException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.models.*;
import com.example.bookstore.repos.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/24/2024 03:36
 */

@CrossOrigin
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final UserRepo userRepo;
    private final BookRatedUsersRepo rateRepo;
    private final PageRepository pageRepository;
    private final ReaderBookRepo readerBookRepo;
    private final AuthorRepo authorRepo;
    private final BookViewRepo bookViewRepo;
    private final CategoryRepo categoryRepo;
    private final TransactionRepo transactionRepo;
    private final BookStatsRepo bookStatsRepo;

    public BookDTO getBookSignedIn(Long id, Long userId) {
        Optional<User> userEn = userRepo.findById(userId);
        if (userEn.isEmpty()) {
            throw new UserNotFoundException(userId.toString(), 404);
        }
        User user = userEn.get();
        Optional<Book> entity = bookRepo.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = entity.get();
        BookView bookView = bookViewRepo.findByUserIdAndBookId(userId, id).orElse(new BookView());
        bookView.setBookId(book.getId());
        bookView.setUserId(userId);
        bookView.setAccessedAt(LocalDateTime.now());
        bookView = bookViewRepo.save(bookView);
        user.getRecentlyViewedBooks().add(bookView);
        userRepo.save(user);

        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(id);
        BookStats stats = new BookStats();
        if (statsEnt.isPresent()) {
            stats = statsEnt.get();
        }
        stats.setViews(stats.getViews() + 1);
        bookStatsRepo.save(stats);

        BookDTO dto = new BookDTO(book);
        return dto;
    }

    public BookDTO addBook(BookDTO dto) {
        Author author = null;
        if (dto.getAuthor().getId() != null) {
            Optional<Author> authorEnt = authorRepo.findById(dto.getAuthor().getId());
            if (authorEnt.isEmpty()) {
                throw new NotFoundException("Author not found!", 404);
            }
            author = authorEnt.get();
        } else {
            author = new Author();
            author.setFullName(dto.getAuthor().getFullName());
            author = authorRepo.save(author);
        }

        Category category = null;
        if (dto.getCategory().getId() != null) {
            Optional<Category> categoryEnt = categoryRepo.findById(dto.getCategory().getId());
            if (categoryEnt.isEmpty()) {
                throw new NotFoundException("Category not found!", 404);
            }
            category = categoryEnt.get();
        } else {
            category = new Category();
            category.setCategory(dto.getCategory().getName());
            category = categoryRepo.save(category);
        }
        Optional<User> userEnt = userRepo.findById(dto.getUserId());
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(dto.getUserId().toString(), 404);
        }
        Book book = dto.toEntity();
        book.setAuthor(author);
        book.setAuthorName(author.getFullName());
        book.setCategory(category);
        book.setCategoryName(category.getCategory());
        book.setUser(userEnt.get());
        book.setScore(0L);
        book.setTotalPages(0L);
        Book saved = bookRepo.save(book);

        BookStats stats = new BookStats();
        stats.setReadsNumber(0L);
        stats.setCurrentlyReading(0L);
        stats.setReadsNumber(0L);
        stats.setTotalRevenue(Double.valueOf(0));
        stats.setComments(0L);
        stats.setViews(0L);
        stats.setRatesNumber(0L);
        stats.setCurrentRevenue(Double.valueOf(0));
        stats.setLastTimeRead(LocalDateTime.now());
        stats.setBook(book);
        bookStatsRepo.save(stats);

        return new BookDTO(saved);
    }

    public AuthorDTO addAuthor(String name) {
        Author author = new Author(name);
        return new AuthorDTO(authorRepo.save(author));
    }

    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> authors = authorRepo.findAll().stream()
                .map(AuthorDTO::new)
                .collect(Collectors.toList());
        return authors;
    }

    public List<BookDTO> search(String search) {
        List<Book> list = bookRepo.searchBooksByAuthorNameContainingIgnoreCaseOrTitleContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(search, search, search);
        List<BookDTO> books = new ArrayList<>();
        for (Book book : list) {
            books.add(new BookDTO(book));
        }
        return books;
    }

    public List<BookDTO> getBooks() {
        Sort sort = Sort.by(Sort.Direction.ASC, "score");
        Page<Book> books = bookRepo.findAll(PageRequest.of(0, 10, sort));
        List<BookDTO> collect = books.stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return collect;
    }

    public RateBookDTO rate(BookRatingDTO dto) {
        Optional<User> userEnt = userRepo.findById(dto.getUserId());
        if (userEnt.isEmpty()) {
            throw new NotFoundException(dto.getUserId().toString(), 404);
        }
        User user = userEnt.get();
        Optional<Book> ent = bookRepo.findById(dto.getBookId());
        if (ent.isEmpty()) {
            throw new NotFoundException(dto.getBookId().toString(), 404);
        }
        Book book = ent.get();
        Optional<BookRatedUsers> rateEnt = rateRepo.findByUserIdAndBookId(dto.getUserId(), dto.getBookId());
        if (rateEnt.isPresent()) {
            throw new AlreadyRatedException("Already rated!", 402);
        }
        BookRatedUsers rating = new BookRatedUsers();
        rating.setBookId(book.getId());
        rating.setUserId(dto.getUserId());
        rating.setScore(dto.getRating());
        BookRatedUsers saved = rateRepo.save(rating);
        int size = rateRepo.findAllByBookId(dto.getBookId()).size();
        long newScore = dto.getRating();
        if (size > 0) {
            long allScore = book.getScore() * size + dto.getRating();
            newScore = allScore / (size + 1);
        }
        book.setScore(newScore);
        Book save = bookRepo.save(book);
        user.getRatedBooks().add(save);
        userRepo.save(user);

        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(dto.getBookId());
        BookStats stats = statsEnt.get();
        stats.setRatesNumber(stats.getRatesNumber() + 1);
        bookStatsRepo.save(stats);

        return saved.toDTO(newScore);
    }

    public List<BooksReadDTO> currentlyReading(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        List<BooksReadDTO> books = user.getBooks().stream()
                .map(BooksReadDTO::new)
                .collect(Collectors.toList());
        List<BooksReadDTO> collect = books.stream()
                .map(book -> book.setCurrentPage(pageRepository.findById(readerBookRepo.findByReaderIdAndBookId(id, book.getId()).get().getCurrentPage()).get().getPageNumber()))
                .collect(Collectors.toList());
        return collect;
    }

    public List<BookViewDTO> getRecentlyViewed(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        Set<BookView> views = user.getRecentlyViewedBooks();
        List<BookViewDTO> books = new ArrayList<>();
        for (BookView view : views) {
            Optional<Book> book = bookRepo.findById(view.getBookId());
            if (book.isPresent()) {
                books.add(new BookViewDTO(book.get(), view.getAccessedAt()));
            }
        }
        return books;
    }

    public List<BookDTO> getRatedBooks(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new UserNotFoundException(id.toString(), 404);
        }
        User user = userEnt.get();
        List<BookDTO> books = user.getRatedBooks().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDTO> getByCategory(Long id) {
        return bookRepo.findAllByCategoryId(id).stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    public BookDTO getBook(Long id) {
        Optional<Book> entity = bookRepo.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = entity.get();
        BookDTO dto = new BookDTO(book);
        return dto;
    }

    public PageDTO getPages(Long userId, Long id) {
        Optional<User> userEnt = userRepo.findById(userId);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        User user = userEnt.get();
        Optional<Book> bookEnt = bookRepo.findById(id);
        if (bookEnt.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = bookEnt.get();
        Optional<ReaderBook> readerBookEnt = readerBookRepo.findByReaderIdAndBookId(userId, book.getId());
        if (readerBookEnt.isPresent()) {
            ReaderBook readerBook = readerBookEnt.get();
            Optional<BookPage> pageEntity = pageRepository.findById(readerBook.getCurrentPage());
            if (pageEntity.isEmpty()) {
                throw new NotFoundException("Page not found!", 404);
            }
            transaction(user, book);
            BookPage page = pageEntity.get();
            PageDTO dto = new PageDTO(page);
            dto.setBookTitle(book.getTitle());
            dto.setAuthorName(book.getAuthorName());
            dto.setTotalPages(book.getTotalPages());
            if (book.getAuthor() != null) {
                dto.setAuthorId(book.getAuthor().getId());
            }
            user.getBooks().add(book);
            userRepo.save(user);

            Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(id);
            BookStats stats = statsEnt.get();
            stats.setCurrentlyReading(stats.getCurrentlyReading() + 1);
            stats.setReadsNumber(stats.getReadsNumber() + 1);
            stats.setLastTimeRead(LocalDateTime.now());
            bookStatsRepo.save(stats);

            return dto;
        } else {
            Optional<BookPage> pageEntity = pageRepository.findByBookIdAndPageNumber(book.getId(), 1L);
            if (pageEntity.isEmpty()) {
                throw new NotFoundException("Page not found!", 404);
            }
            transaction(user, book);
            BookPage page = pageEntity.get();
            ReaderBook readerBook = new ReaderBook();
            readerBook.setBook(book);
            readerBook.setBookId(book.getId());
            readerBook.setReader(user);
            readerBook.setReaderId(user.getId());
            readerBook.setCurrentPage(page.get_id());
            readerBookRepo.save(readerBook);
            PageDTO dto = new PageDTO(page);
            dto.setBookTitle(book.getTitle());
            dto.setAuthorName(book.getAuthorName());
            dto.setTotalPages(book.getTotalPages());
            if (book.getAuthor() != null) {
                dto.setAuthorId(book.getAuthor().getId());
            }
            user.getBooks().add(book);
            userRepo.save(user);

            Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(id);
            BookStats stats = statsEnt.get();
            stats.setCurrentlyReading(stats.getCurrentlyReading() + 1);
            stats.setReadsNumber(stats.getReadsNumber() + 1);
            stats.setLastTimeRead(LocalDateTime.now());
            bookStatsRepo.save(stats);

            return dto;
        }
    }

    public PageDTO next(Long userId, Long id) {
        Optional<Book> bookEnt = bookRepo.findById(id);
        if (bookEnt.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = bookEnt.get();
        Optional<User> userEnt = userRepo.findById(userId);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        User user = userEnt.get();
        Optional<ReaderBook> readerBookEnt = readerBookRepo.findByReaderIdAndBookId(userId, id);
        if (readerBookEnt.isEmpty()) {
            Optional<BookPage> pageEnt = pageRepository.findByBookIdAndPageNumber(id, 1L);
            if (pageEnt.isEmpty()) {
                throw new NotFoundException("Page not found!", 404);
            }
            transaction(user, book);
            BookPage page = pageEnt.get();
            ReaderBook readerBook = new ReaderBook();
            readerBook.setCurrentPage(page.get_id());
            readerBook.setBook(book);
            readerBook.setBookId(book.getId());
            readerBook.setReader(user);
            readerBook.setReaderId(userId);
            readerBookRepo.save(readerBook);
            PageDTO dto = new PageDTO(page);
            dto.setTotalPages(book.getTotalPages());
            dto.setAuthorName(book.getAuthorName());
            dto.setBookTitle(book.getTitle());
            if (book.getAuthor() != null) {
                dto.setAuthorId(book.getAuthor().getId());
            }
            return dto;
        }
        ReaderBook readerBook = readerBookEnt.get();
        BookPage prevPage = pageRepository.findById(readerBook.getCurrentPage()).get();
        if (prevPage.getPageNumber() > book.getTotalPages()) {
            throw new NotFoundException("Last page!", 404);
        }
        Optional<BookPage> pageEnt = pageRepository.findByBookIdAndPageNumber(id, prevPage.getPageNumber() + 1);
        if (pageEnt.isEmpty()) {
            throw new NotFoundException("Page not found!", 404);
        }
        transaction(user, book);
        readerBook.setCurrentPage(pageEnt.get().get_id());
        readerBookRepo.save(readerBook);
        PageDTO dto = new PageDTO(pageEnt.get());
        dto.setBookTitle(book.getTitle());
        dto.setTotalPages(book.getTotalPages());
        dto.setAuthorName(book.getAuthorName());
        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
        }
        return dto;
    }

    public PageDTO prev(Long userId, Long id) {
        Optional<Book> bookEnt = bookRepo.findById(id);
        if (bookEnt.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = bookEnt.get();
        Optional<User> userEnt = userRepo.findById(userId);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        User user = userEnt.get();
        Optional<ReaderBook> readerBookEnt = readerBookRepo.findByReaderIdAndBookId(userId, id);
        if (readerBookEnt.isEmpty()) {
            Optional<BookPage> pageEnt = pageRepository.findByBookIdAndPageNumber(id, 1L);
            if (pageEnt.isEmpty()) {
                throw new NotFoundException("Page not found!", 404);
            }
            transaction(user, book);
            BookPage page = pageEnt.get();
            ReaderBook readerBook = new ReaderBook();
            readerBook.setCurrentPage(page.get_id());
            readerBook.setBook(book);
            readerBook.setBookId(book.getId());
            readerBook.setReader(user);
            readerBook.setReaderId(userId);
            readerBookRepo.save(readerBook);
            PageDTO dto = new PageDTO(page);
            dto.setTotalPages(book.getTotalPages());
            dto.setAuthorName(book.getAuthorName());
            dto.setBookTitle(book.getTitle());
            if (book.getAuthor() != null) {
                dto.setAuthorId(book.getAuthor().getId());
            }
            return dto;
        }
        ReaderBook readerBook = readerBookEnt.get();
        BookPage nextPage = pageRepository.findById(readerBook.getCurrentPage()).get();
        if (nextPage.getPageNumber() <= 0L) {
            throw new NotFoundException("Last page!", 404);
        }
        Optional<BookPage> pageEnt = pageRepository.findByBookIdAndPageNumber(id, nextPage.getPageNumber() - 1);
        if (pageEnt.isEmpty()) {
            throw new NotFoundException("Page not found!", 404);
        }
        transaction(user, book);
        readerBook.setCurrentPage(pageEnt.get().get_id());
        readerBookRepo.save(readerBook);
        PageDTO dto = new PageDTO(pageEnt.get());
        dto.setBookTitle(book.getTitle());
        dto.setTotalPages(book.getTotalPages());
        dto.setAuthorName(book.getAuthorName());
        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
        }
        return dto;
    }

    public List<BookDTO> getBooksOfAuthor(Long authorId) {
        Optional<Author> author = authorRepo.findById(authorId);
        if (author.isEmpty()) {
            new NotFoundException("Author not found!", 404);
        }
        List<BookDTO> books = bookRepo.findAllByAuthorId(authorId).stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<BookDTO> getMyBooks(Long id) {
        Optional<User> userEnt = userRepo.findById(id);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        List<BookDTO> books = userEnt.get().getMyPublications().stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
        return books;
    }

    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> list = categoryRepo.findAll().stream()
                .map(category -> new CategoryDTO(category))
                .collect(Collectors.toList());
        return list;
    }

    public Boolean quitReading(Long id, Long userId) {
        Optional<Book> bookEnt = bookRepo.findById(id);
        if (bookEnt.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Optional<User> userEnt = userRepo.findById(userId);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        Book book = bookEnt.get();
        User user = userEnt.get();
        user.getBooks().remove(book);
        userRepo.save(user);
        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(id);
        BookStats stats = statsEnt.get();
        if (stats.getCurrentlyReading() > 0) {
            stats.setCurrentlyReading(stats.getCurrentlyReading() - 1);
            stats.setCurrentRevenue(stats.getCurrentRevenue() - transactionRepo.findByReaderIdAndBookId(userId, id).get().getAmount());
            bookStatsRepo.save(stats);
        }
        return Boolean.TRUE;
    }

    private void transaction(User user, Book book) {
        if (book.getUser().getId() != user.getId()) {
            Optional<Transaction> transactionEnt = transactionRepo.findByReaderIdAndBookId(user.getId(), book.getId());
            if (transactionEnt.isPresent()) {
                Transaction transaction = transactionEnt.get();
                if (ChronoUnit.HOURS.between(transaction.getPaidDate(), LocalDateTime.now()) > 24) {
                    transaction.setPaidDate(LocalDateTime.now());
                    transaction.setAmount(0.2);
                    transaction.setOverallPayment(transaction.getOverallPayment() + transaction.getAmount());
                    transactionRepo.save(transaction);

                    Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(book.getId());
                    BookStats stats = statsEnt.get();
                    stats.setTotalRevenue(transaction.getAmount());
                    bookStatsRepo.save(stats);
                }
            } else {
                Transaction transaction = new Transaction();
                transaction.setBook(book);
//            transaction.setBookId(book.getId());
                transaction.setReader(user);
//            transaction.setReaderId(user.getId());
                if (book.getUser() != null) {
                    transaction.setReceiver(book.getUser());
//                transaction.setReceiverId(book.getUser().getId());
                }
                transaction.setAmount(0.2);
                transaction.setOverallPayment(transaction.getAmount());
                transaction.setPaidDate(LocalDateTime.now());
                transactionRepo.save(transaction);

                Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(book.getId());
                BookStats stats = statsEnt.get();
                stats.setCurrentRevenue(stats.getCurrentRevenue() + transactionRepo.findByReaderIdAndBookId(user.getId(), book.getId()).get().getAmount());
                stats.setTotalRevenue(transaction.getAmount());
                bookStatsRepo.save(stats);
            }
        }
    }

    public Boolean finishReading(Long id, Long userId) {
        Optional<Book> bookEnt = bookRepo.findById(id);
        if (bookEnt.isEmpty()) {
            throw new NotFoundException("Book not found!", 404);
        }
        Optional<User> userEnt = userRepo.findById(userId);
        if (userEnt.isEmpty()) {
            throw new NotFoundException("User not found!", 404);
        }
        Book book = bookEnt.get();
        User user = userEnt.get();
        user.getBooks().remove(book);
        user.getFinished().add(book);
        userRepo.save(user);
        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(id);
        BookStats stats = statsEnt.get();
        if (stats.getCurrentlyReading() > 0) {
            stats.setCurrentlyReading(stats.getCurrentlyReading() - 1);
            stats.setCurrentRevenue(stats.getCurrentRevenue() - transactionRepo.findByReaderIdAndBookId(userId, id).get().getAmount());
            bookStatsRepo.save(stats);
        }
        return Boolean.TRUE;
    }

    public BookStatsDTO getStatsByBook(Long bookId) {
        Optional<BookStats> statsEnt = bookStatsRepo.findByBookId(bookId);
        if (statsEnt.isEmpty()) {
            throw new NotFoundException("System error!", 404);
        }
        BookStats stats = statsEnt.get();
        return new BookStatsDTO(stats);
    }

    public List<BookStatsDTO> getStatsOfUser(Long id) {

        List<Book> all = bookRepo.findAll();
        for (Book book : all) {
            BookStats stats = bookStatsRepo.findByBookId(book.getId()).get();
            stats.setComments(Long.valueOf(book.getComments().size()));
            bookStatsRepo.save(stats);
        }


        List<BookStatsDTO> list = bookRepo.findAllByUserId(id).stream()
                .map(book -> new BookStatsDTO(bookStatsRepo.findByBookId(book.getId()).get()))
                .collect(Collectors.toList());
        return list;
    }

    public Boolean addPage(PageDTO dto) {
        Optional<Book> bookEnt = bookRepo.findById(dto.getBookId());
        if (bookEnt.isEmpty()){
            throw new NotFoundException("Book not found!", 404);
        }
        Book book = bookEnt.get();
        BookPage page = new BookPage();
        page.setBookId(dto.getBookId());
        page.setPageNumber(dto.getPageNumber());
        page.setContent(dto.getContent());
        pageRepository.save(page);
        book.setTotalPages(Long.valueOf(book.getTotalPages() + 1));
        bookRepo.save(book);
        return Boolean.TRUE;
    }
}
